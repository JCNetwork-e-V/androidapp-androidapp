package com.jcnetwork.android.jctestapp1.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.utils.Constants;
import com.jcnetwork.android.jctestapp1.utils.ProgressBarAnimation;

import org.json.JSONObject;

public class PointsActivity extends AppCompatActivity{

    // For logging purposes
    private String LOG_TAG = PointsActivity.class.getSimpleName();

    // Set up variables
    private String getPointsStringURL = "https://intern.jcnetwork.de/app/json_cert.php?hash=";
    public int gesamtInt;
    public int caseInt;
    public int experienceInt;
    private int gesamtMax = 60;
    private int caseMax = 12;
    private int experienceMax = 60;
    private ProgressBar gesamtProgressBar;
    private ProgressBar caseProgressBar;
    private ProgressBar experienceProgressBar;
    private TextView gesamtTV;
    private TextView caseTV;
    private TextView experienceTV;

    // Shared preferences
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        setTitle("Deine Punkte");

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }
        // Initialize Shared Preferences
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

        // Find Progress Bars
        gesamtProgressBar = (ProgressBar)findViewById(R.id.gesamt_points_progress_bar);
        caseProgressBar = (ProgressBar)findViewById(R.id.case_points_progress_bar);
        experienceProgressBar = (ProgressBar)findViewById(R.id.experience_points_progress_bar);

        // Set maximum for progress bars
        gesamtProgressBar.setMax(gesamtMax);
        caseProgressBar.setMax(caseMax);
        experienceProgressBar.setMax(experienceMax);

        // Find text views
        gesamtTV = (TextView)findViewById(R.id.gesamt_points_tv);
        caseTV = (TextView)findViewById(R.id.case_points_tv);
        experienceTV = (TextView)findViewById(R.id.experience_points_tv);

        // Check if there is a saved Bundle i.e. the device was rotated
        if (savedInstanceState != null) {
            // If it was don't send a network request or animate the views, just set the views
            setProgressBars();
            Log.i(LOG_TAG, "savedInstanceState not null");
        } else {
            // Check if we have internet connection or not
            if (isOnline()) {
                // Send request and get data
                sendRequestResponse();
                Log.i(LOG_TAG, "sendRequestResponse called");
                // Call method to animate progress bars and put text
                //animateProgressBars();
            } else {
                // Call method to animate progress bars and put text which will display data if stored in SharedPreferences otherwise zero
                animateProgressBars();
                Log.i(LOG_TAG, "animateProgressBars in OnCreate called i.e. no internet");
            }
        }
    }

    /**
     * Method to check if we have internet connection
     */
    protected boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            // There is no active network
            return false;
        } else
            // There is an active network
            return true;
    }


    /**
     * Method to set progress bars without animation
     */
    private void setProgressBars() {
        // Read from shared preferences to get stored data and set 0 as default
        gesamtInt = sharedPreferences.getInt(Constants.GESAMT_POINTS_KEY, 0);
        caseInt = sharedPreferences.getInt(Constants.CASE_POINTS_KEY, 0);
        experienceInt = sharedPreferences.getInt(Constants.EXPERIENCE_POINTS_KEY, 0);
        Log.i(LOG_TAG, "gesamt after stored to shared preferences in animateProgressBars: "+ String.valueOf(gesamtInt));
        Log.i(LOG_TAG, "case after stored to shared preferences in animateProgressBars: "+ String.valueOf(caseInt));

        // Reset to original max
        caseProgressBar.setProgress(caseMax);

        // Set progress to the values obtained
        gesamtProgressBar.setProgress(gesamtInt);
        caseProgressBar.setProgress(caseInt);
        experienceProgressBar.setProgress(experienceInt);

        // Set text in textviews
        gesamtTV.setText(String.valueOf(gesamtInt) + " von 60 Punkten");
        caseTV.setText(String.valueOf(caseInt) + " von 12 Punkten");
        experienceTV.setText(String.valueOf(experienceInt) + " von 60 Punkten");
    }


    /**
     * Method to animate progress bars
     */
    private void animateProgressBars() {
        // Read from shared preferences to get stored data and set 0 as default
        gesamtInt = sharedPreferences.getInt(Constants.GESAMT_POINTS_KEY, 0);
        caseInt = sharedPreferences.getInt(Constants.CASE_POINTS_KEY, 0);
        experienceInt = sharedPreferences.getInt(Constants.EXPERIENCE_POINTS_KEY, 0);
        Log.i(LOG_TAG, "gesamt after stored to shared preferences in animateProgressBars: "+ String.valueOf(gesamtInt));
        Log.i(LOG_TAG, "case after stored to shared preferences in animateProgressBars: "+ String.valueOf(caseInt));

        // Set progress to the values obtained
        gesamtProgressBar.setProgress(gesamtInt);
        caseProgressBar.setProgress(caseInt);
        experienceProgressBar.setProgress(experienceInt);

        // Animate progress
        ProgressBarAnimation gesamtAnim = new ProgressBarAnimation(gesamtProgressBar, 0, gesamtInt);
        ProgressBarAnimation experienceAnim = new ProgressBarAnimation(experienceProgressBar, 0, experienceInt);
        // For smooth animation, the numbers in case are multiplied by 100
        int toCasePointsScaled = caseInt * 100;
        int maxCasePointsScaled = caseMax * 100;
        caseProgressBar.setMax(maxCasePointsScaled);
        ProgressBarAnimation caseAnim = new ProgressBarAnimation(caseProgressBar, 0, toCasePointsScaled);
        gesamtAnim.setDuration(1000);
        caseAnim.setDuration(1000);
        experienceAnim.setDuration(1000);
        gesamtProgressBar.startAnimation(gesamtAnim);
        caseProgressBar.startAnimation(caseAnim);
        experienceProgressBar.startAnimation(experienceAnim);

        // Set text in textviews
        gesamtTV.setText(String.valueOf(gesamtInt) + " von 60 Punkten");
        caseTV.setText(String.valueOf(caseInt) + " von 12 Punkten");
        experienceTV.setText(String.valueOf(experienceInt) + " von 60 Punkten");
    }

    /**
     * Method to perform network request to get JSON
     */
    private void sendRequestResponse() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.i(LOG_TAG, "Send request Response Called");

        // Make url
        String certId = sharedPreferences.getString(Constants.USER_CERTIFICATION_ID, Constants.EMPTY_STRING_DEFAULT);
        String constructedUrl = getPointsStringURL + certId;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, constructedUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i(LOG_TAG, response);
                        // Try to convert string to JSON Object and get info
                        try {
                            // Get shared preference editor to write to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            // Get Info and store to sharedPreferences
                            JSONObject object = new JSONObject(response);
                            gesamtInt = object.getInt("gesamt");
                            editor.putInt(Constants.GESAMT_POINTS_KEY, gesamtInt);
                            Log.i(LOG_TAG, "gesamt after stored to shared preferences in sendRequest: "+ String.valueOf(sharedPreferences.getInt(Constants.GESAMT_POINTS_KEY, 0)));
                            Log.i(LOG_TAG, String.valueOf(gesamtInt));
                            caseInt = object.getInt("case");
                            editor.putInt(Constants.CASE_POINTS_KEY, caseInt);
                            Log.i(LOG_TAG, String.valueOf(caseInt));
                            experienceInt = object.getInt("experience");
                            editor.putInt(Constants.EXPERIENCE_POINTS_KEY, experienceInt);
                            editor.apply();
                            Log.i(LOG_TAG, "gesamt after stored to shared preferences in sendRequest and commit called: "+ String.valueOf(sharedPreferences.getInt(Constants.GESAMT_POINTS_KEY, 0)));
                            Log.i(LOG_TAG, String.valueOf(experienceInt));
                        } catch (Throwable throwable) {
                            Log.e(LOG_TAG, "Failed to parse string to JSON Object and get info");
                        }
                        // Method to animate progress bars
                        animateProgressBars();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "VolleyError occurred");
            }
        });


        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    /**
     * Methods to save and restore the state/position within the scroll view when rotating the screen
     */
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}
