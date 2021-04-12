package com.jcnetwork.android.jctestapp1.hiddenactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.network.CheckNetwork;

public class JobwallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv);
        setTitle(getString(R.string.jobwall));

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Check internet connection
        if (CheckNetwork.checkConnection(this)) {
            // THERE IS A CONNECTION SO WE WILL LOAD THE WEBVIEW
            WebView myWebView = new WebView(this);
            // Manipulate webview to only show table //TODO
            myWebView.getSettings().setJavaScriptEnabled(true);
            // Allow pdfs to be viewed by embedding url to: http://docs.google.com/gview?embedded=true&url=<url of a supported doc>
//            myWebView.getSettings().setDisplayZoomControls(true);
//            myWebView.getSettings().setAllowContentAccess(true);
//            myWebView.getSettings().setBlockNetworkLoads(false);
//            myWebView.getSettings().setAllowFileAccess(true);

            myWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    //TODO Check
                    // Inject a javascript function to hide some elements of the webpage
                    myWebView.loadUrl("javascript:(function() { " +
                            "document.getElementById('footer_widget_wrapper').style.display='none'; " +
                            "document.getElementById('footer_wrapper').style.display='none'; " +
                            "document.getElementById('header_wrapper').style.display='none'; " +
                            "document.getElementByClassName('main_header').style.display='none'; " +
                            "document.getElementByClassName('main_image').style.display='none'; " +
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
            setContentView(myWebView);
            // Load page
            myWebView.loadUrl("https://days.jcnetwork.de/jobwall/");
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
                    Intent refresh = new Intent(JobwallActivity.this, JobwallActivity.class);
                    startActivity(refresh);
                }
            });
        }
    }
}