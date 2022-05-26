package com.jcnetwork.android.app1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.app1.R;

public class PortaleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portale);

        // Find views
        // Variables
        ImageButton jobwall = findViewById(R.id.jobwall);
        ImageButton trainer_academy = findViewById(R.id.trainer_academy);
        ImageButton alumni = findViewById(R.id.alumni);
        ImageButton certification = findViewById(R.id.certification);
        ImageButton insights = findViewById(R.id.insights);
        ImageButton projekte = findViewById(R.id.projekt_mgt);

        // Set on click listeners
        jobwall.setOnClickListener(myPortaleClickListener);
        trainer_academy.setOnClickListener(myPortaleClickListener);
        alumni.setOnClickListener(myPortaleClickListener);
        certification.setOnClickListener(myPortaleClickListener);
        insights.setOnClickListener(myPortaleClickListener);
        projekte.setOnClickListener(myPortaleClickListener);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }
    }

    /**
     * Helper method to open link in browser via intent
     */
    public void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    /**
     * Private Click Listener
     */
    private final View.OnClickListener myPortaleClickListener = view -> {
        // React differently according to id
        switch (view.getId()) {
            case R.id.jobwall:
                openUrl("https://days.jcnetwork.de/jobwall/");
                break;
            case R.id.projekt_mgt:
                openUrl("https://jcnetwork-projektmanagement.de/");
                break;
            case R.id.trainer_academy:
                openUrl("https://www.jcnetwork.de/jcnetwork-trainer-academy/");
                break;
            case R.id.alumni:
                openUrl("https://jcnetwork-alumni.de/");
                break;
            case R.id.certification:
                openUrl("https://certification.jcnetwork.de/");
                break;
            case R.id.insights:
                openUrl("https://blog.jcnetwork.de/");
                break;
            default:
                break;
        }
    };

}