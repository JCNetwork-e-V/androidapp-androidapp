package com.jcnetwork.android.app1.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.utils.MECARDContactEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.jcnetwork.android.app1.utils.Constants.EMPTY_STRING_DEFAULT;
import static com.jcnetwork.android.app1.utils.Constants.SHARED_PREFERENCE_FILE_NAME;
import static com.jcnetwork.android.app1.utils.Constants.USER_EMAIL;
import static com.jcnetwork.android.app1.utils.Constants.USER_NAME_KEY;
import static com.jcnetwork.android.app1.utils.Constants.USER_NOTE;
import static com.jcnetwork.android.app1.utils.Constants.USER_PHONE;

public class MyQRCode extends AppCompatActivity {

    ImageView qrCodeImg;
    EditText name_tv, phone_tv, email_tv, note_tv;
    Button generateQR_btn;
    String name, phone, email, note;
    String[] finalcontact;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_qr);
        super.onCreate(savedInstanceState);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Find view
        qrCodeImg = (ImageView)findViewById(R.id.qr_code_img);
        name_tv = findViewById(R.id.name_tv);
        phone_tv = findViewById(R.id.phone_tv);
        email_tv = findViewById(R.id.email_tv);
        note_tv = findViewById(R.id.note_tv);
        generateQR_btn = findViewById(R.id.generate);

        // Initialize Shared Preferences
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

        // Retrieve contact info from shared preferences
        getStringsFromSharedPrefs();

        // Check if note is empty, then put JCNetwork there
        if (note.isEmpty()) {
            note = "Auf den JCNetwork Days in Magdeburg 2022 kennengerlernt";
        }

        // Fill edit texts with current default strings in shared preferences
        name_tv.setText(name);
        phone_tv.setText(phone);
        email_tv.setText(email);
        note_tv.setText(note);

        // Generate QR image
        generateQRImage();

        // Set up on click for button
        generateQR_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get strings from edittexts
                name = name_tv.getText().toString();
                phone = phone_tv.getText().toString();
                email = email_tv.getText().toString();
                note = note_tv.getText().toString();

                // Update QR code image
                generateQRImage();

                // Inform user of update
                Toast.makeText(MyQRCode.this, "Der QR Code ist aktualisiert", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Encode contact information
     * @return String list/array in form of a MECARD Encoded Contact
     */
    private String[] encodeContactInfo() {
        MECARDContactEncoder mycontact = new MECARDContactEncoder();
        // Put strings into arrays
        List<String> name_array =   new ArrayList<>();
        name_array.add(name);
        List<String> address_array =   new ArrayList<>();
        address_array.add("");
        List<String> phone_array =   new ArrayList<>();
        phone_array.add(phone);
        List<String> phonetype_array =   new ArrayList<>();
        phonetype_array.add("");
        List<String> email_array =   new ArrayList<>();
        email_array.add(email);
        List<String> urls_array =   new ArrayList<>();
        urls_array.add("");

        // Encode information
        String[] finalcontact = mycontact.encode(name_array,
                "",
                address_array,
                phone_array,
                phonetype_array,
                email_array,
                urls_array,
                note);

        // Return encoded contact information
        return finalcontact;
    }

    /**
     * Get simple string from string list
     * @param str_array String list/array
     * @return extracted string
     */
    private String getStringFromArray(String[] str_array) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str_array.length; i++) {
            sb.append(str_array[i]);
        }
        String str = sb.toString();
        return str;
    };

    /**
     * Get current contact infos stores in preferences
     */
    private void getStringsFromSharedPrefs(){
        // Retrieve contact info from shared preferences
        name = sharedPreferences.getString(USER_NAME_KEY, EMPTY_STRING_DEFAULT);
        phone = sharedPreferences.getString(USER_PHONE, EMPTY_STRING_DEFAULT);
        email = sharedPreferences.getString(USER_EMAIL, EMPTY_STRING_DEFAULT);
        note = sharedPreferences.getString(USER_NOTE, EMPTY_STRING_DEFAULT);
    }

    /**
     * Save current contact infos in preferences
     */
    private void storeStringsToSharedPrefs(){
        // Store contact info tp shared preferences
        sharedPreferences.edit().putString(USER_NAME_KEY,name).apply();
        sharedPreferences.edit().putString(USER_PHONE,phone).apply();
        sharedPreferences.edit().putString(USER_EMAIL,email).apply();
        sharedPreferences.edit().putString(USER_NOTE,note).apply();
    }

    private void generateQRImage(){
        // Encode contact information
        String[] finalcontact = encodeContactInfo();

        // Convert to String
        String str = getStringFromArray(finalcontact);

        // Create QR code from url and set to image
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.qr_code_img)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        // Store updated contact into to preferences
        storeStringsToSharedPrefs();
    }
}
