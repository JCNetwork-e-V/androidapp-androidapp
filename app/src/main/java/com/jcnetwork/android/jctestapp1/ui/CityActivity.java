package com.jcnetwork.android.jctestapp1.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.jctestapp1.R;

public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_intro);
        setTitle("Die Stadt");

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }
    }

}
