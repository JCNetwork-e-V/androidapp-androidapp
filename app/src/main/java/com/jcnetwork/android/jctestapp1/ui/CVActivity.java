package com.jcnetwork.android.jctestapp1.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.network.CheckNetwork;
import com.jcnetwork.android.jctestapp1.utils.Constants;

public class CVActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_intro);
        setTitle(getString(R.string.dein_lebenslauf));

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Check internet connection
        if (CheckNetwork.checkConnection(this)) {
            // THERE IS A CONNECTION SO WE WILL LOAD THE CV OF THE USER
            // Initialize Shared Preferences
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

            // Retrieve lebenslaufId and userId from shared preferences
            String lebenslaufId = sharedPreferences.getString(Constants.LEBENSLAUF_ID_KEY, Constants.EMPTY_STRING_DEFAULT);
//            String userId = sharedPreferences.getString(Constants.USER_ID_KEY, Constants.EMPTY_STRING_DEFAULT);
            Log.i("CVActivity", "LebenslaufId is " + lebenslaufId);

            // Check that both are not empty before proceeding
            if (!lebenslaufId.isEmpty()) {
                // Proceed to make and load the webview
                WebView myWebView = new WebView(this);
                // Hide parts of the page
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        //TODO Check why this doesn't work
                        // Inject a javascript function to hide some elements of the webpage
                        myWebView.loadUrl("javascript:(function() { " +
                                "document.getElementById('StayFocusd-infobar').style.display='none'; " +
                                "document.getElementById('hululu').style.display='none'; " +
                                "document.getElementByClassName('placeholder')[0].style.display='none'; " +
                                "document.getElementByClassName('container')[0].style.display='none'; " +
                                "document.getElementByClassName('placeholder_foot')[0].style.display='none'; " +
                                "document.getElementById('footer_wrapper').style.display='none'; " +
                                "})()");

                        // IF YOU HAVE IDS USE THIS APPROACH
                        // Use this for not showing elements
                        // document.getElementById(id).style.display = 'none';
                        // Use this for showing elements
                        // document.getElementById(id).style.display = 'block';

                        // IF YOU DON'T REFER TO THE CLASSES
                        // "document.getElementsByClassName('header_wrapper')[0].style.display='none'; "
                        //            "document.getElementsByClassName('navbar-header')[0].style.display='none'; "+
                        //           "document.getElementsByClassName('footer-social')[0].style.display='none'; "+
                        //           "document.getElementById('footer_bottom').style.display='none'; "+
                        //           "document.getElementById('footer_content').style.display='none';
                    }
                });
                // Show page
                setContentView(myWebView);
                // Load page
                myWebView.loadUrl("https://intern.jcnetwork.de/profile/page.php?redirect_to=https%3A%2F%2Fintern.jcnetwork.de%2Fapp%2Fredir.php%3Fid%3D" + lebenslaufId + "&user_id=" + lebenslaufId);

            } else {
                // Make toast that there was an error in fetching the data
                Toast.makeText(this, "Fehler im Abrufen der Daten", Toast.LENGTH_SHORT).show();
            }
        } else {
            // THERE IS NO CONNECTION SO WE WILL PROMPT THE USER TO CHECK THE CONNECTION AND TRY AGAIN
            // Display no internet fragment
            setContentView(R.layout.fragment_no_internet);

            // Find refresh button
            Button retryBtn = (Button) findViewById(R.id.retry_button);

            // Set on click listener on that button
            retryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // On click, relaunch this activity
                    Intent refresh = new Intent(CVActivity.this, CVActivity.class);
                    startActivity(refresh);
                }
            });
        }
    }
}
