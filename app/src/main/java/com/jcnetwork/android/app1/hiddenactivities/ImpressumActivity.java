package com.jcnetwork.android.app1.hiddenactivities;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.app1.network.CheckNetwork;
import com.jcnetwork.android.app1.R;

public class ImpressumActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hidden_activity_impressum);
        setTitle("Impressum");

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Check internet connection
        if (CheckNetwork.checkConnection(this)) {
            // THERE IS A CONNECTION SO WE WILL DISPLAY THE ONLINE IMPRESSUM
                // Proceed to make and load the webview
                WebView myWebView = new WebView(this);
                // Show page
                setContentView(myWebView);
                // Load page
                myWebView.loadUrl("https://www.jcnetwork.de/impressum/");
        }
    }
}
