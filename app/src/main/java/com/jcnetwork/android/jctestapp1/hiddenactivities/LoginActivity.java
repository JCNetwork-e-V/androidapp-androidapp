package com.jcnetwork.android.jctestapp1.hiddenactivities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.ui.MainActivity;
import com.jcnetwork.android.jctestapp1.utils.Constants;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    // Views
    private ImageView mLogoImg;
    private Button loginBtn;
    private Button registerBtn;
    private WebView mWebView;

    // For Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        // Find view
        mLogoImg = (ImageView) findViewById(R.id.logo);
        loginBtn = (Button) findViewById(R.id.login_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        mWebView = (WebView) findViewById(R.id.webview);

        // Get data
        Intent openedIntent = getIntent();
        String intention = Objects.requireNonNull(openedIntent.getExtras()).getString(Constants.OPEN_LOGIN_INTENTION, Constants.LOGIN);
        Log.i(LOG_TAG, "intention is to " + intention);

        // If intention is to login, set up accordingly
        if (intention.contains(Constants.LOGIN)) {
            showHomeScreen();
        }
        // Else, log user out and then return to login
        if (intention.contains(Constants.LOGOUT)) {
            // Log user out
            signUserOut();
        }
    }



    /**
     * Method to set up landing page for first time users (LOGO and Login button only)
     */
    private void showHomeScreen() {
        // Show and hide views
        mLogoImg.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.VISIBLE);
        registerBtn.setVisibility(View.VISIBLE);
        mWebView.setVisibility(View.GONE);
        // Set on click listener for login
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change text color to white of button
                loginBtn.setTextColor(getColor(R.color.white));
                // Show webview and hide others
                mLogoImg.setVisibility(View.GONE);
                loginBtn.setVisibility(View.GONE);
                registerBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
                // Set up login
                setUpLogin();
            }
        });

        // Set on click listener for register
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change text color to white of button
                registerBtn.setTextColor(getColor(R.color.white));
                // Show webview and hide others
                mLogoImg.setVisibility(View.GONE);
                loginBtn.setVisibility(View.GONE);
                registerBtn.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
                // Set up register
                setUpRegister();
            }
        });
    }

    /**
     * Method to set up Register for first time users of the JCNetwork
     */
    private void setUpRegister() {
        // Adjust settings
//        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        // Create client
//        MyWebViewClient webViewClient = new MyWebViewClient(LoginActivity.this, Constants.LOGIN);
//        mWebView.setWebViewClient(webViewClient);

        // Set content view to webview
        //setContentView(mWebView);

        // Load page to login
        mWebView.loadUrl("https://intern.jcnetwork.de/register/");
    }

    /**
     * Method to set up login for first time users of the app (direct webview to microsoft login page)
      */
    private void setUpLogin() {
        // Adjust settings
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Create client
        MyWebViewClient webViewClient = new MyWebViewClient(LoginActivity.this, Constants.LOGIN);
        mWebView.setWebViewClient(webViewClient);
        mWebView.setWebChromeClient(new MyWebCromeClient());

        // Set content view to webview
        //setContentView(mWebView);

        // Load page to login
        mWebView.loadUrl("https://intern.jcnetwork.de/oauth.php");
    }

    /**
     * Method to configure signing out
     */
    private void signUserOut() {
        //mWebView.removeAllViews();
        // Show webview and hide others
        mLogoImg.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);
        mWebView.setVisibility(View.VISIBLE);

        // Adjust settings
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Create client // TODO Decide on one
        MyWebViewClient webViewClient = new MyWebViewClient(LoginActivity.this, Constants.LOGOUT);
        mWebView.setWebViewClient(webViewClient);

        // Set content view to webview
        //setContentView(mWebView);

        // Load page to login
        mWebView.loadUrl("https://intern.jcnetwork.de/logout.php");
    }


    /**
     * To make the webview show the view instead of the browser being opened (by default)
     */
    private class MyWebViewClient extends WebViewClient {

        private Activity activity = null;
        private String intention;

        public MyWebViewClient(Activity activity, String intention) {
            this.activity = activity;
            this.intention = intention;
            Log.i(LOG_TAG, "MyWebViewClient initialised");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if(url.contains("jcnetwork")) return false; // to make it show in app (not browswer)
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.i(LOG_TAG, "onPageFinished called");
            if (intention.contains(Constants.LOGOUT)) {
                // Check if redirected to intern
                if (url.contains("https://intern.jcnetwork.de/")) {
                    // Then set up Login
                    setUpLogin();
                }
            }

            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.i(LOG_TAG, "onLoadResource called");
            super.onLoadResource(view, url);
        }
    }

    /** Try 2 **/
    final class MyWebCromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.i(LOG_TAG, "MyWebCromeClient with onJsAlert called");
            result.confirm();
            return true;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.i(LOG_TAG, consoleMessage.message());
            // Get ablaufID
            if (consoleMessage.message().contains("ablaufID")) {
                // Add the message to the email
                String ablaufID = consoleMessage.message();
                // Split string by ce
                String[] splits = ablaufID.split(" ");
                // Take the second element i.e. index 1
                String id = splits[1];
                // Check
                Log.i(LOG_TAG, "ablaufID is " + id); 
                // Save user data
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.ABLAUF_ID,
                        id);
                editor.apply();
            }
            // Get LebenslaufID
            if (consoleMessage.message().contains("LebenslaufID")) {
                // Add the message to the email
                String LebenslaufID = consoleMessage.message();
                // Split string by ce
                String[] splits = LebenslaufID.split(" ");
                // Take the second element i.e. index 1
                String id = splits[1];
                Log.i(LOG_TAG, "LebenslaufID is " + id);
                // Save user data
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.LEBENSLAUF_ID_KEY,
                        id);
                editor.apply();
            }
            // Get cert_id
            if (consoleMessage.message().contains("cert_id")) {
                // Add the message to the email
                String cert_id = consoleMessage.message();
                // Split string by ce
                String[] splits = cert_id.split(" ");
                // Take the second element i.e. index 1
                String id = splits[1];
                Log.i(LOG_TAG, "cert_id is " + id);
                // Save user data
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.USER_CERTIFICATION_ID,
                        id);
                editor.apply();
            }
            // Get email
            if (consoleMessage.message().contains("email")) {
                // Add the message to the email
                String email = consoleMessage.message();
                // Split string by ce
                String[] splits = email.split(" ");
                // Take the second element i.e. index 1
                String mail = splits[1];
                Log.i(LOG_TAG, "email is " + mail);
                // Save user data
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.USER_EMAIL,
                        mail);
                editor.apply();
            }
            // Log user in upon init done
            if (consoleMessage.message().contains("inti done")) {
                // Return to main activity
                // Set isLogged in to true after login (*successful)

                // Save user data
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGGED_IN_KEY,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.LOGGED_IN_KEY,
                        true);
                editor.apply();


                // Start Main Activity
                Intent returnToMain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(returnToMain);

                // Better to give user control i.e. via dialog: OK -> go to main; STAY -> able to log out
                // Otherwise we'd have the issue that user is always logged in...
                // Show Dialog
//                LoginSuccessFragment dialog = new LoginSuccessFragment();
//                dialog.show(getSupportFragmentManager(), "success");
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }


}
