package com.jcnetwork.android.jctestapp1.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.ScheduleAdapter;
import com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.jctestapp1.models.ProgramPoint;
import com.jcnetwork.android.jctestapp1.utils.Constants;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.util.List;

import static com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis.getNumberOfDays;

public class PlanActivity extends AppCompatActivity {

    // For loggin
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Views
    ViewPager2 mViewPager2;
    TabLayout mTabLayout;
    ScheduleAdapter mViewAdapter;

    // Data
    List<ProgramPoint> mProgram;
    private int DurationInDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Get data from shared preferences
        SharedPreferences userPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, MODE_PRIVATE);
        String programString = userPreferences.getString(Constants.ABLAUFSPLAN_JSON_RESULT_KEY, Constants.EMPTY_STRING_DEFAULT);
        // Convert to what we need
        Gson gson = new Gson();
        mProgram = gson.fromJson(programString, new TypeToken<List<ProgramPoint>>(){}.getType());

        // Get how many days we need
        assert mProgram != null;
        if (mProgram.size()>0) {
            try {
                DurationInDays = (int) getNumberOfDays(mProgram);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i(LOG_TAG, "Number of days" + DurationInDays);
        }

            // Find views
            mViewPager2 = (ViewPager2) findViewById(R.id.view_pager);
            mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

            // Set up viewpager
            mViewAdapter = new ScheduleAdapter(this, mProgram, DurationInDays);
            mViewPager2.setAdapter(mViewAdapter);

            // Set up tablayout
            new TabLayoutMediator(mTabLayout, mViewPager2,
                    (tab, position) -> {
                        // Set default day
                        String dayOfWeek = "Some day";
                        try {
                            // Get day of the week based on position e.g. 0 -> Day 1 -> Thursday
                            dayOfWeek = ProgramPointAnalysis.getDayOfWeek(mProgram, position);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        tab.setText(dayOfWeek);
                    }).attach();



    }
}