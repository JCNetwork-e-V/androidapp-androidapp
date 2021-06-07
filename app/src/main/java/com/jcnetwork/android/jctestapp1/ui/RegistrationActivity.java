package com.jcnetwork.android.jctestapp1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.jctestapp1.R;

public class RegistrationActivity extends AppCompatActivity {

    // For loggin
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Views
    private ImageButton daysBtn;
    private ImageButton executivedaysBtn;
    private ImageButton develoopmentdaysBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Find views
        daysBtn = (ImageButton) findViewById(R.id.normal_days_image);
        executivedaysBtn = (ImageButton) findViewById(R.id.executive_days_image);
        develoopmentdaysBtn = (ImageButton) findViewById(R.id.development_days_image);

        // Register on click listener
        daysBtn.setOnClickListener(myWebViewListener);
        executivedaysBtn.setOnClickListener(myWebViewListener);
        develoopmentdaysBtn.setOnClickListener(myWebViewListener);
    }

    /**
     * Creates an onClickListener which checks the id of the view and points user to correct webaddress in browser
     */
    private View.OnClickListener myWebViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // React differently according to id
            switch (view.getId()) {
                case R.id.normal_days_image:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/days-application/");
                    break;
                case R.id.executive_days_image:
                    // Open link in browser to register
                    openLinkInBrower("https://executivedays.jcevents.jcnetwork.de/days-application/");
                    break;
                case R.id.development_days_image:
                    // Open link in browser to register
                    openLinkInBrower("https://developmentdays.jcevents.jcnetwork.de/days-application/");
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Method to open given link in browser via intnet
     */
    private void openLinkInBrower(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
