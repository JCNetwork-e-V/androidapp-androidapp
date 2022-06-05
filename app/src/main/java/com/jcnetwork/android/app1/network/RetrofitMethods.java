package com.jcnetwork.android.app1.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jcnetwork.android.app1.models.JSONResult;
import com.jcnetwork.android.app1.models.ProgramPoint;
import com.jcnetwork.android.app1.utils.Constants;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Class to store network operations over retrofit
 */

public class RetrofitMethods {

    //private static NetworkAPI mNetworkAPI;
    // For logging
    private final String LOG_TAG = this.getClass().getSimpleName();
    private final Context mContext;

    // Internet
    Retrofit mRetrofit = APIClient.getmRetrofit(ABLAUF_URL);

    // Endpoints
    // URL for Ablaufplan
    //private static final String INTERN_URL = "https://intern.jcnetwork.de/app/json_ios.php?id=";
    private static final String ABLAUF_URL = "https://intern.jcnetwork.de/app/json_ios.php/";
    // URL for Login as webview
    private static final String LOGIN_URL = "https://intern.JCNetwork.de/oauth.php/";
    // URL for Points
    private static final String CERTIFICATION_URL = "https://certification.jcnetwork.de/member.php/";
    // URL for CV webview
    private static final String LEBENSLAUF_URL = "https://intern.jcnetwork.de/profile/page.php?redirect_to=https://days.jcnetwork.de/profile&user_id=";
    // URL for Certification points
    private static final String CERTIFICATION_POINTS_URL = "https://intern.jcnetwork.de/app/json_cert.php?hash=";


    // Constructor to pass in context (needed to access shared preferences)
    public RetrofitMethods(Context c) {
        mContext = c;
    }

    /**
     * Method to get events of personal program at days...
     * @return list of events within program
     */
    public List<ProgramPoint> getProgram() {
        // Initialize empty list
        final List<ProgramPoint> list = new ArrayList<>();

        // Retrofit
        Retrofit retrofit = APIClient.getmRetrofit(ABLAUF_URL);

        // API
        NetworkAPI mNetworkApi = retrofit.create(NetworkAPI.class);

        // Get Ablauf_ID from preferences
        SharedPreferences userSharedPreferences = this.mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        String ABLAUF_ID = userSharedPreferences.getString(Constants.ABLAUF_ID, ""); //TODO Add logic to alert user that no data as found because no ablaufid present

        Call<JSONResult> call = mNetworkApi.getJSONResults(ABLAUF_ID);
        call.enqueue(new Callback<JSONResult>() {
            @Override
            public void onResponse(@NotNull Call<JSONResult> call, @NotNull Response<JSONResult> response) {

                // Get data
                JSONResult jsonResult = response.body();
                if (jsonResult != null) {
                    // Get program
                    list.addAll(jsonResult.getmProgram());

                    // Use GSON to convert JSON POJO objects in program into JSON strings
                    Gson gson = new Gson();
                    String program_json = gson.toJson(list);

                    // Get user name
                    String name = jsonResult.getmUserName();

                    // Store to shared preferences
                    SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    // Add username and program
                    editor.putString(Constants.USER_NAME_KEY, name);
                    editor.putString(Constants.ABLAUFSPLAN_JSON_RESULT_KEY, program_json);
                    // Apply
                    editor.apply();
                }
            }

            @Override
            public void onFailure(@NotNull Call<JSONResult> call, @NotNull Throwable t) {
                call.cancel();
            }
        });

        // Return list of programs
        return list;
    }

    //TODO Delete later
    private static String getStringFromDate(Date date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string = myFormat.format(date);
        return string;
    }
}
