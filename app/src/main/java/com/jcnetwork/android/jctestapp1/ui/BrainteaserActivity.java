package com.jcnetwork.android.jctestapp1.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.BrainteaserAdapter;
import com.jcnetwork.android.jctestapp1.models.Brainteaser;
import com.jcnetwork.android.jctestapp1.transformations.CubeOutTransformer;

import java.util.ArrayList;

public class BrainteaserActivity extends AppCompatActivity {

    // Set up variables
    ArrayList<Brainteaser> brainteasers = new ArrayList<>();
    BrainteaserAdapter btAdapter;
    ViewPager2 btViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainteaser);

        // Find viewpager
        btViewPager = (ViewPager2) findViewById(R.id.view_pager);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Set up adapter
        btAdapter = new BrainteaserAdapter(this);
        btAdapter.addBT(new Brainteaser("Frage", "Antwort"));
        btAdapter.addBT(new Brainteaser("Frage 2", "Antwort2"));
        btAdapter.addBT(new Brainteaser("Frage3", "Antwort3"));

        // Add cube out page transformer to view pager
        btViewPager.setPageTransformer(new CubeOutTransformer());

        // Add adapter to viewpager
        btViewPager.setAdapter(btAdapter);


        // Make brainteasers
//        Brainteaser bt_1 = new Brainteaser("Frage", "Antwort");
//        Brainteaser bt_2 = new Brainteaser("Frage 2", "Antwort 2");
//        Brainteaser bt_3 = new Brainteaser("Frage 3", "Antwort 3");
//
//        // Add these to list
//        brainteasers.add(bt_1);
//        brainteasers.add(bt_2);
//        brainteasers.add(bt_3);

    }
}
