package com.jcnetwork.android.jctestapp1.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jcnetwork.android.jctestapp1.models.ProgramPoint;
import com.jcnetwork.android.jctestapp1.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * How widget is getting data
 * This is acting like the adapter for widget
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    // Variables
    List<ProgramPoint> mProgram = new ArrayList<>();
    Context mContext;
    Intent mIntent;

    /** Constructor **/
    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
        mIntent = intent;
    }

    /** Method to populate data **/
    private void initData() {
        // clear old data
        mProgram.clear();
        // get data
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        String programString = preferences.getString(Constants.ABLAUFSPLAN_JSON_RESULT_KEY, Constants.EMPTY_STRING_DEFAULT);
        Gson gson = new Gson();
        if (!programString.isEmpty()) {
            // Convert to program and calculate current/next point
            mProgram = gson.fromJson(programString, new TypeToken<List<ProgramPoint>>(){}.getType());
        }
    }


    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mProgram.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // Potentially you could here fill a list with data from mProgram by referencing position
        // e.g. RemoteViews remoteView = new RemoteViews(context.getPackageName(), android.R.layout_list_item_1_;
        // e.g. remoteView.setTextViewText(android.R.id.text1, mProgram.get(position));
        // e.g. return remoteView;
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
