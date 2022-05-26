package com.jcnetwork.android.app1.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.preference.PreferenceManager;
import androidx.work.Data;

import com.jcnetwork.android.app1.ui.MainActivity;
import com.jcnetwork.android.app1.R;

import java.text.ParseException;

/**
 * Class to collect methods related to creating notifications to the user
 * // based on shared preferences data
 */
public class Notifications {

    public static final int WORKSHOP_NOTIFICATION = 1;
    public static final int DAYS_NOTIFICATION = 2;
    private static final String CHANNEL_ID = "70373083";
    private final Context mContext;

    // Work Manager tags in order to cancel scheduled ones if needed
    public static final String scheduledOneTimeTag = "oneTimeTag";
    // In case of cancalation call: WorkManager.getInstance().cancelAllWorkByTag(workTag);

    /**
     * Empty constructor
     */
    public Notifications(Context context) {
        mContext = context;
    }


    /**
     * To display notifications (on Android 8.0+), app's notification channels needs to be registered with system
     * by passing an instance of NotificationChannel to createNotificationChannel
     *
     * This needs to be called before posting any notifications e.g. execute ASAP your app starts
     * Calling it repeatedly is ok!
     */

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.channel_name);
            String description = mContext.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Method to check if the user wants workshop notifications
     * Background knowledge:
     * From SettingsActivity, user preferences are stored in shared preferences as key-value paris
     * Therefore we can retrieve them; here as booleans because these are on-off preferences
     */
    public Boolean userWantsWorkshopNotifications() {
        // Open preferences
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext);
        // Return preference for workshop notification
        return sharedPreferences.getBoolean("workshop_notification", true);
    }

    /**
     * Method to check if the user wants workshop notifications
     */
    public Boolean userWantsEventNotifications() {
        // Open preferences
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(mContext);
        // Return preference for day notification
        return sharedPreferences.getBoolean("day_register_notification", true);

    }


    /**
     * Method to build a notification
     */
    public void buildNotification(int kindOfNotification) {
        // Setup pendingIntent and Notification builder
        PendingIntent pendingIntent;
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(null);

        // Get what notification it is and fill up the two above based on it
        switch (kindOfNotification) {
            case 1:
                // create an intent to this workshop register class
                Intent workshopIntent = new Intent(mContext, MainActivity.class);
                // Create a stackbuilder that makes up navigation possible (otherwise app crashes)
                TaskStackBuilder workshopStackBuilder = TaskStackBuilder.create(mContext);
                workshopStackBuilder.addNextIntentWithParentStack(workshopIntent);
                // Continue
                pendingIntent = workshopStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//                workshopIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                pendingIntent = PendingIntent.getActivity(mContext, 0, workshopIntent, 0);
                sendInstantNotification("Workshop", "Workshopanmeldung offen", pendingIntent, 1);
                break;
            case 2:
                // Create an explicit intent to event register class
                Intent eventIntent = new Intent(mContext, MainActivity.class);
                // Create a stackbuilder that makes up navigation possible (otherwise app crashes)
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
                stackBuilder.addNextIntentWithParentStack(eventIntent);
                // Continue
                pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//                eventIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                pendingIntent = PendingIntent.getActivity(mContext, 0, eventIntent, 0);
                sendInstantNotification("Event", "Anmeldung offen", pendingIntent, 2);
                break;

            case 3:
                //TODO Create a one time scheduled event for testing
                // Task 1: Get time of event

                // Task 2: Calculate delay from NOW to then

                // Task 3: Convert to date
                // TODO Delete later
//                Intent someIntent = new Intent(mContext, MainActivity.class);
//                TaskStackBuilder expStackBuilder = TaskStackBuilder.create(mContext);
//                expStackBuilder.addNextIntentWithParentStack(someIntent);
//                pendingIntent = expStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//                sendInstantNotification("Title", "text", pendingIntent, kindOfNotification);

                break;
            default:
                // Set no intent
                break;
        }
    }

    /**
     * Method to send instant notification
     */
    public void sendInstantNotification(String title, String text, PendingIntent pendingIntent, int kindOfNotification) {
        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.mipmap.arrow_jcnetwork_blau)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Call the Notification Manager to show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        // NotificationId is a unique int for each notification that you must define
        notificationManager.notify(kindOfNotification, builder.build());
    }

    /**
     * More flexible method to provide a notification based on the kind of notification and the deadline provided
     */
    public void setNotificationAtDate(int kind) {
        // Initialize title, text and pending intent
        String title = "";
        String text = "";
        PendingIntent pendingIntent = null;

        // Fill those based on kind
        switch (kind) {
            case 1:
                // Set up title, text and pending intent
                title = "Workshop";
                text = "Anmeldungen fuer Workshops sind offen";
                Intent workshopIntent = new Intent(mContext, MainActivity.class);
                // Create a stackbuilder that makes up navigation possible (otherwise app crashes)
                TaskStackBuilder workshopStackBuilder = TaskStackBuilder.create(mContext);
                workshopStackBuilder.addNextIntentWithParentStack(workshopIntent);
                // Continue
                pendingIntent = workshopStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case 2:
                // Set up title, text and pending intent
                title = "Event";
                text = "Anmeldungen fuer Events sind offen";
                Intent eventIntent = new Intent(mContext, MainActivity.class);
                // Create a stackbuilder that makes up navigation possible (otherwise app crashes)
                TaskStackBuilder eventStackBuilder = TaskStackBuilder.create(mContext);
                eventStackBuilder.addNextIntentWithParentStack(eventIntent);
                // Continue
                pendingIntent = eventStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            default:
                break;
        }

        // Based on kind of notification, give back a different notification
        sendInstantNotification(title, text, pendingIntent, kind);
    }

    /**
     * Method to create data to be passed as input to the WorkRequest
     * Here: we need to pass the date e.g. "2020-10-05 19:30:00" (or ProgramPoint)
     * & int kindofNotificaiton
     */
    public Data createInputForWorkRequest(int kind) {
        // If data not null // TODO Check logic that no nonsense is passed in
        Data.Builder builder = new Data.Builder();
        // Add data to this
        builder.putInt("kind", kind);
        // Return as Data
        return builder.build();
    }

}
