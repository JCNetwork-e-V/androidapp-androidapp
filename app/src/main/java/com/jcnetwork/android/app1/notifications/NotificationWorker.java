package com.jcnetwork.android.app1.notifications;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.ParseException;

/**
 * This worker (part of work manager) is used to schedule notifications
 */

public class NotificationWorker extends Worker {

    /** Constructor **/
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        /** Here we can define the work to do **/
        // Get context
        Context context = getApplicationContext();
        // Retrieve data
        int kind = getInputData().getInt("kind", 0);
        // Schedule a notification through the Notifications class by passing in the kind
        Notifications notifications = new Notifications(context);
        try {
            notifications.setNotificationAtDate(kind);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Return the success and be done with it (use Retry to do again; failure not to)
        return Result.success();
    }
}
