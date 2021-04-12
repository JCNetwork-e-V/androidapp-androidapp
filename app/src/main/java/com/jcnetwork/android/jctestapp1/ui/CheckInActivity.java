package com.jcnetwork.android.jctestapp1.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.utils.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Help from: https://www.android-examples.com/generate-qr-code-in-android-using-zxing-library-in-android-studio/
 */

public class CheckInActivity extends AppCompatActivity {


    // Set up variables
    ImageView qrCodeImg;
    Bitmap qrCodeBitMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        setTitle("Check-In");

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Find view
        qrCodeImg = (ImageView)findViewById(R.id.qr_code_img);

        // Initialize Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

        // Retrieve userId from shared preferences
        String ablaufID = sharedPreferences.getString(Constants.ABLAUF_ID, Constants.EMPTY_STRING_DEFAULT);

        // Url to encode in QR code
        String url = "https://intern.jcnetwork.de/days/check.php?id=" + ablaufID; //TODO Check which one you want, as ablauf_id gives you green screen with paid and no hotel info

        // Create QR code from url and set to image
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 512, 512);
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
    }



    // Color
    //         case "jcblau":
    //            return Color(red: 51/255, green: 102/255, blue: 153/255)
    //        case "partygrey":
    //            return Color(red: 93/255, green: 93/255, blue: 93/255)
    //        case "grey":
    //            return Color(red: 191/255, green: 191/255, blue: 191/255)
    //        case "blau":
    //            return Color(red: 31/255, green: 82/255, blue: 133/255)

}
