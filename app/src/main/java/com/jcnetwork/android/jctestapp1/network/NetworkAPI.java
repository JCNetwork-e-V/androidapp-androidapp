package com.jcnetwork.android.jctestapp1.network;

import com.jcnetwork.android.jctestapp1.models.JSONResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkAPI {

    // Return program points from with relative URL in get (base will be passed in at other point)
    @GET("results")
    Call<JSONResult> getJSONResults(@Query("id") String Id);
}
