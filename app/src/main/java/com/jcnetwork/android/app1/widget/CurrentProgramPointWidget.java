package com.jcnetwork.android.app1.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.jcnetwork.android.app1.models.ProgramPoint;
import com.jcnetwork.android.app1.utils.Constants;
import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.conversion.ProgramPointAnalysis;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import static com.jcnetwork.android.app1.R.layout.current_program_point_widget;

/**
 * Implementation of App Widget functionality to display the current program point to user
 */
public class CurrentProgramPointWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) throws ParseException {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), current_program_point_widget);

        // Empty default strings
        String title = "";
        String place = "";
        String time = "";
        String address = "";
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        String programString = preferences.getString(Constants.ABLAUFSPLAN_JSON_RESULT_KEY, Constants.EMPTY_STRING_DEFAULT);
        Gson gson = new Gson();
        if (!Objects.requireNonNull(programString).isEmpty()) {
            // Convert to program and calculate current/next point
            List<ProgramPoint> program = gson.fromJson(programString, new TypeToken<List<ProgramPoint>>(){}.getType());
            Log.i("CurrentProgramPointWidget", "program point first time begin is" + program.get(0).getBegin());
            Log.i("CurrentProgramPointWidget", "program point last time begin is" + program.get(program.size() -1).getBegin());
            // Get position of current program
            int position = ProgramPointAnalysis.getCurrentProgramPosition(program);
            // Get data
            title = program.get(position).getTitle();
            place = program.get(position).getPlace();
            time = ProgramPointAnalysis.getTimeStringFromBeginAndEnd(program.get(position).getBegin(), program.get(position).getEnd());
            address = program.get(position).getAddress();
        }

        // Set current program data to views
        views.setTextViewText(R.id.title, title);
        views.setTextViewText(R.id.place, place);
        views.setTextViewText(R.id.time, time);

        // Create intent to launch Google maps with address data
        if (!address.isEmpty()) {
            Uri addressUri = Uri.parse("geo:0,0?q=" + address);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mapIntent, 0);
            views.setOnClickPendingIntent(R.id.go_arrow, pendingIntent);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i("WIDGET", "onUpdate called");
        for (int appWidgetId : appWidgetIds) {
            try {
                updateAppWidget(context, appWidgetManager, appWidgetId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}

