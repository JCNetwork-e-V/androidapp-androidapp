package com.jcnetwork.android.app1.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jcnetwork.android.app1.R;

public class RegistrationFragment extends Fragment {

    // Variables
    private static final String POSITION_KEY = "position";
    private int position;

    // Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    /** Constructor **/
    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance(Integer pos) {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        Bundle data = new Bundle();
        data.putInt(POSITION_KEY, pos);
        registrationFragment.setArguments(data);
        return registrationFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION_KEY);
            Log.i(LOG_TAG, "Position" + String.valueOf(position));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_webview, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up webview
        WebView myWebView = new WebView(getContext());
        requireActivity().setContentView(myWebView);

        // Adjust settings
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Create client
        MyWebViewClient webViewClient = new MyWebViewClient(getActivity());
        myWebView.setWebViewClient(webViewClient);

        // Check position and point to different website depending on position
        switch (position) {
            case 0: // Normal or digital
                myWebView.loadUrl("https://days.jcnetwork.de/days-application/");
                break;
            case 1: // Executive
                myWebView.loadUrl("https://executivedays.jcevents.jcnetwork.de/days-application/");
                break;
            case 2: // Development
                myWebView.loadUrl("https://developmentdays.jcevents.jcnetwork.de/days-application/");
                break;
            default:
                myWebView.loadUrl("https://www.jcnetwork.de/home/");
        }

    }

    /**
     * To make the webview show the view instead of the browser being opnened (by default)
     */
    private class MyWebViewClient extends WebViewClient {

        private Activity activity = null;

        public MyWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.contains("jcnetwork")) return false;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }

}
