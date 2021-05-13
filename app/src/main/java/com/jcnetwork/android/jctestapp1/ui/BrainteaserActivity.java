package com.jcnetwork.android.jctestapp1.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.BrainteaserAdapter;
import com.jcnetwork.android.jctestapp1.models.Brainteaser;
import com.jcnetwork.android.jctestapp1.transformations.CubeOutTransformer;

public class BrainteaserActivity extends AppCompatActivity {

    // Set up variables
    BrainteaserAdapter btAdapter;
    ViewPager2 btViewPager;
    ImageButton addBTBtn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainteaser);

        // Find views
        btViewPager = findViewById(R.id.view_pager);
        addBTBtn = findViewById(R.id.add_brainteaser_btn);
        webView = findViewById(R.id.webview);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Set up adapter
        btAdapter = new BrainteaserAdapter(this);
        btAdapter.addBT(new Brainteaser("Kaffee vs. Tee", "Laut einer Umfrage trinken 80% der Junior Consultants Kaffee und 70% Tee. Was ist die obere und undere Grenze derer, die beides trinken?", "Bei Unabhängigkeit liegt die untere Grenze bei 50%. Bei maximaler Abhängigkeit liegt die obere Grenze bei 70%."));
        btAdapter.addBT(new Brainteaser("Skiurlaub", "Skiurlaub nach Corona: wenn vier Junior Consultants die Gondel eines Skilifts unten am Berg besteigen und den ganzen Weg nach oben fahren, an wie vielen Gondeln kommen sie vorbei?", "An allen."));
        btAdapter.addBT(new Brainteaser("Zertifikate pro Jahr", "1,5 Junior Consultants bekommen 1,5 Zertifikate in 1,5 Jahren. Wie viele Zertifikate bekommt ein Junior Consultant in einem Jahr?", "2/3."));

        // Add cube out page transformer to view pager
        btViewPager.setPageTransformer(new CubeOutTransformer());

        // Add adapter to viewpager
        btViewPager.setAdapter(btAdapter);

        // Set on click listener
        addBTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open form to submit new brainteaser
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                MyWebViewClient webViewClient = new MyWebViewClient(BrainteaserActivity.this);
                webView.setWebViewClient(webViewClient);
                webView.loadUrl("https://forms.office.com/r/TXzNhFfY24");
            }
        });

    }

    /**
     * My Webviewclint to override the default behavior that opens a link in browser
     */
    public class MyWebViewClient extends WebViewClient {

        private Activity activity = null;

        public MyWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            // Don't load in browser for the form
            if(url.contains("forms.office")) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
