package com.jcnetwork.android.app1.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.jcnetwork.android.app1.BuildConfig;
import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.app1.hiddenactivities.FAQsActivity;
import com.jcnetwork.android.app1.hiddenactivities.ImpressumActivity;
import com.jcnetwork.android.app1.hiddenactivities.LoginActivity;
import com.jcnetwork.android.app1.hiddenactivities.SettingsActivity;
import com.jcnetwork.android.app1.models.ProgramPoint;
import com.jcnetwork.android.app1.network.CheckNetwork;
import com.jcnetwork.android.app1.network.RetrofitMethods;
import com.jcnetwork.android.app1.notifications.NotificationWorker;
import com.jcnetwork.android.app1.notifications.Notifications;
import com.jcnetwork.android.app1.utils.Constants;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Testing phase beginning!

    // Log in boolean check
    public Boolean isLoggedIn;

    // For Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Set up views
    CoordinatorLayout mCoordinator;
    ImageButton checkInButton, scheduleButton, pointButton, cityButton, profileButton, clubButton, brainButton, engageButton, firmButton;
    TextView nameTV;
    NavigationView navDrawer;
    DrawerLayout drawerLayout;
    TextView navNameTV, navEmailTV;
    androidx.appcompat.widget.Toolbar toolbar;

    // Data
    List<ProgramPoint> mProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Get Firebasetoken to enable instant messaging for testing purposes to this device
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(LOG_TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();

                    // Log and toast
                    String msg = "R.string.msg_token_fmt" + token;
                    Log.d(LOG_TAG, msg);
                });

        // Check shared preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                Constants.LOGGED_IN_KEY, Context.MODE_PRIVATE);
        // Get logged in boolean (default false)
        isLoggedIn = sharedPreferences.getBoolean(Constants.LOGGED_IN_KEY,
                false);

        // Check if user is logged in or not
        if (!isLoggedIn) {
            // If not, then launch Login activity for first time
            Intent login = new Intent(this, LoginActivity.class);
            // To do so, pass in data to let login activity know that this is a first timer :)
            login.putExtra(Constants.OPEN_LOGIN_INTENTION, Constants.LOGIN);
            // Start activity
            startActivity(login);
        }

        // Set up other shared preferences to write user data to
        SharedPreferences userSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);

        // Set up Notifications
        setUpNotifications();

        // Test internet connection
        if (CheckNetwork.checkConnection(this)) {
            Log.i(LOG_TAG, "internet available");
            // Initialize Retrofit
            RetrofitMethods retrofitMethods = new RetrofitMethods(this);

            // Make call to get ablauf plan data and user name (will be stored in shared preferences)
            mProgram =  retrofitMethods.getProgram();


        } else {
            Log.i(LOG_TAG, "no internet");
        }

        // Find views
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        checkInButton = (ImageButton) findViewById(R.id.check_in_button);
        scheduleButton = (ImageButton) findViewById(R.id.schedule_button);
        pointButton = (ImageButton) findViewById(R.id.certification_points_button);
        cityButton = (ImageButton) findViewById(R.id.register_button);
        profileButton = (ImageButton) findViewById(R.id.profile_button);
        clubButton = (ImageButton) findViewById(R.id.portale_button);
        brainButton = (ImageButton) findViewById(R.id.brain_button);
        engageButton = (ImageButton) findViewById(R.id.engage_button);
        firmButton = (ImageButton) findViewById(R.id.firm_button);
        navDrawer = (NavigationView) findViewById(R.id.navigation_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View headerView = navDrawer.getHeaderView(0);
        navNameTV = (TextView) headerView.findViewById(R.id.nav_name_tv);
        navEmailTV = (TextView) headerView.findViewById(R.id.nav_email_tv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Set text for
        navEmailTV.setText(userSharedPreferences.getString(Constants.USER_EMAIL, Constants.EMPTY_STRING_DEFAULT));
        navNameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));

        // Set up action bar toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.content_description_closed_drawer, R.string.content_description_opened_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // Change color (default makes it black even though icon is original white) to white
        toggle.getDrawerArrowDrawable().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);

        //Setup Navigation drawer
        setUpNavigationDrawer();

        // Show user name and update once shared preferences has been updated with name from retrofit method
        nameTV = (TextView) findViewById(R.id.name_tv);
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
        nameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
        nameTV.startAnimation(in);
        userSharedPreferences.registerOnSharedPreferenceChangeListener((sharedPreferences1, key) -> {
            // Set to data
            navNameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
            nameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
            nameTV.startAnimation(in);
            Log.i(LOG_TAG, "onSharedPreferenceChanged called");
        });

        // Set onClickListeners to views
        checkInButton.setOnClickListener(myCardClickListener);
        scheduleButton.setOnClickListener(myCardClickListener);
        pointButton.setOnClickListener(myCardClickListener);
        cityButton.setOnClickListener(myCardClickListener);
        profileButton.setOnClickListener(myCardClickListener);
        clubButton.setOnClickListener(myCardClickListener);
        brainButton.setOnClickListener(myCardClickListener);
        engageButton.setOnClickListener(myCardClickListener);
        firmButton.setOnClickListener(myCardClickListener);

    }

    /**
     * Method to set up navigation drawer
     */
    private void setUpNavigationDrawer() {

        // Set default checked item to home
        navDrawer.setCheckedItem(R.id.home);

        // Setup listener to item selection
        navDrawer.setNavigationItemSelectedListener(item -> {
            // Switch statements to navigate to appropriate destination based on id
            switch (item.getItemId()) {
                case R.id.settings:
                    // Open settings
                    Intent openSettings = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(openSettings);
                    break;
                case R.id.support:
                    // Open mail to send a help request
                    String[] recipients = {"support@jcnetwork.de"}; // Alternative: IM971@jcnetwork.de von teams
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Only email apps should handle this intent (otherwise SEND ok)
                    emailIntent.setData(Uri.parse("mailto:")); // Only email apps no social media stuff
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // string array
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Supportanfrage Android App"); // subject -> easy search later
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Was können wir für dich tun?\n Feedback? \n Fehler? \n Wünsche? \n Nur her damit!"); // text
                    if (emailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(emailIntent);
                    }
                    break;
                case R.id.share:
                    // TODO Add link after publishing app on Google Play Store
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,
                            "Hey, check out die neue JCNetwork App auf Google Play Store: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                    shareIntent.setType("text/plain");
                    startActivity(shareIntent);
                    break;
                case R.id.faq:
                    // Open faq
                    Intent openFAQ = new Intent(MainActivity.this, FAQsActivity.class);
                    startActivity(openFAQ);
                    break;
                case R.id.impressum:
                    // Open faq
                    Intent openImpressum = new Intent(MainActivity.this, ImpressumActivity.class);
                    startActivity(openImpressum);
                    break;
                case R.id.logout:
                    // Log user out
                    SharedPreferences userSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor userEditor = userSharedPreferences.edit();
                    userEditor.clear();
                    userEditor.apply();
                    // Update sharedpreferences to logged in false
                    SharedPreferences loggedInSharedPreferences = getSharedPreferences(Constants.LOGGED_IN_KEY,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loggedInSharedPreferences.edit();
                    editor.putBoolean(Constants.LOGGED_IN_KEY,
                            false);
                    editor.apply();
                    // Launch LogIn Activity to logout and login again
                    Intent openLogin = new Intent(MainActivity.this, LoginActivity.class);
                    // To do so, pass in data
                    openLogin.putExtra(Constants.OPEN_LOGIN_INTENTION, Constants.LOGOUT);
                    // Start login activity
                    startActivity(openLogin);
                    break;
                default:
                    break;
            }

            // On click close all open drawer views
            drawerLayout.closeDrawers();
            return true;
        });
    }

    /**
     * Override on back pressed in case navigation drawer is open
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close it
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // Follow standard behavior
            super.onBackPressed();
        }
    }

    /**
     * Method to set up notifications for 1. Workshop registration and 2. Event registration
     */
    private void setUpNotifications() {

        // Firebase Cloud Messaging Notification setup
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }


        Notifications mNotifications = new Notifications(this);
        mNotifications.createNotificationChannel();
        // Get current time as Date
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();

        // Convert date into Date
        Date workshopTime = null;
        Date eventTime = null;
        try {
            // TODO Set to default times for testing, but late get it from actual events & workshop registration times
            workshopTime = ProgramPointAnalysis.getDateFromString("2020-10-05 20:14:00");
            eventTime = ProgramPointAnalysis.getDateFromString("2020-10-05 20:14:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Get difference in milliseconds
        assert workshopTime != null;
        long differenceInMillisecondsForWorkshops = workshopTime.getTime() - currentTime.getTime();
        assert eventTime != null;
        long differenceInMillisecondsForEvents = eventTime.getTime() - currentTime.getTime();
        Log.i("Notifications", String.valueOf(differenceInMillisecondsForWorkshops));

        // Check if user wants the notifications before putting in work to send them
        if (differenceInMillisecondsForWorkshops < 0) {
            // If event happened in past, don't send a notification and cancel any existing work
            WorkManager workManager = WorkManager.getInstance(this);
            workManager.cancelAllWorkByTag("scheduledWorkshopNotificationTag");
        } else {
            // WORKSHOP REGISTRATION ONE TIME NOTIFICATIONS
            if (mNotifications.userWantsWorkshopNotifications()) {
                // Build notification work for workshop registration
                OneTimeWorkRequest notifyWorkshop = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInitialDelay(differenceInMillisecondsForWorkshops, TimeUnit.MILLISECONDS) // 1 min = 60.000 millisec
                        .setInputData(mNotifications.createInputForWorkRequest(Notifications.WORKSHOP_NOTIFICATION))
                        .addTag("scheduledWorkshopNotificationTag")
                        .build();
                // Submit it to the system
                WorkManager.getInstance(this).enqueue(notifyWorkshop);
            } else {
                // Cancel any workshop notification set up by work manager
                WorkManager workManager = WorkManager.getInstance(this);
                workManager.cancelAllWorkByTag("scheduledWorkshopNotificationTag");
            }
        }

        if (differenceInMillisecondsForEvents < 0) {
            // Event happened in past, so don't send a notification
            WorkManager workManager = WorkManager.getInstance(this);
            workManager.cancelAllWorkByTag("scheduleEventNotification");
        } else {
            // EVENT REGISTRATION ONE TIME NOTIFICATIONS
            if (mNotifications.userWantsEventNotifications()) {
                // Build notification work for events registration
                OneTimeWorkRequest notifyEvent = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                        .setInitialDelay(differenceInMillisecondsForEvents, TimeUnit.MILLISECONDS) // 1 min = 60.000 millisec
                        .setInputData(mNotifications.createInputForWorkRequest(Notifications.DAYS_NOTIFICATION))
                        .addTag("scheduleEventNotification")
                        .build();
                // Submit it to the system
                WorkManager.getInstance(this).enqueue(notifyEvent);
            } else {
                // Cancel any event notification set up by work manager
                WorkManager workManager = WorkManager.getInstance(this);
                workManager.cancelAllWorkByTag("scheduleEventNotification");
            }
        }
    }

    /**
     * Whole Segment below to animate textview for user name and let it appear one character at a time
     */
    private Handler mHandler = new Handler();
    private int mIndex;
    private CharSequence mText;
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            nameTV.setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                long mDelay = 150;
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    /**
     * Creates an onClickListener which checks the id of the view and reacts accorodingly
     */
    private View.OnClickListener myCardClickListener = view -> {
        // React differently according to id
        switch (view.getId()) {
            case R.id.firm_button:
                // Create intent to open new activity to go view partner firms
                Intent viewFirms = new Intent(MainActivity.this, FirmActivity.class);
                MainActivity.this.startActivity(viewFirms);
                break;
            case R.id.engage_button:
                // Create intent to open new activity to go view engagement possibilities as fellow
                Intent showFellowActivity = new Intent(MainActivity.this, EngageActivity.class);
                MainActivity.this.startActivity(showFellowActivity);
                break;
            case R.id.brain_button:
                // Create intent to open new activity to go through brain teasers
                Intent openBT = new Intent(MainActivity.this, BrainteaserActivity.class);
                MainActivity.this.startActivity(openBT);
                break;
            case R.id.portale_button:
                // Create intent to open new activity to introduce club
                Intent openClubIntro = new Intent(MainActivity.this, PortaleActivity.class);
                MainActivity.this.startActivity(openClubIntro);
                break;
            case R.id.register_button:
                // Create intent to open new activity to introduce city
                Intent openCityIntro = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(openCityIntro);
                break;
            case R.id.profile_button:
                // Create intent to open new activity to show lebenslauf/cv with webview
                Intent openCV = new Intent(MainActivity.this, CVActivity.class);
                MainActivity.this.startActivity(openCV);
                break;
            case R.id.check_in_button:
                // Create intent to open new activity to show qr code for check in
                Intent openQRActivity = new Intent(MainActivity.this, CheckInActivity.class);
                MainActivity.this.startActivity(openQRActivity);
                break;
            case R.id.certification_points_button:
                // Create intent to open new activity to show certification points
                Intent openPointActivity = new Intent(MainActivity.this, PointsActivity.class);
                MainActivity.this.startActivity(openPointActivity);
                break;
            case R.id.schedule_button:
                // Create intent to open new activity to show certification points
                Intent openScheduleActivity = new Intent(MainActivity.this, PlanActivity.class);
                // Open new activity
                MainActivity.this.startActivity(openScheduleActivity);
                break;
            default:
                break;
        }
    };

    /**
     * Menu Set up
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        // Open settings activity
        if (itemThatWasClickedId == R.id.settings) {
            Intent openSettings = new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
            return true;
        }
        // Open faq activity
        if (itemThatWasClickedId == R.id.faq) {
            Intent openFAQ = new Intent(this, FAQsActivity.class);
            startActivity(openFAQ);
            return true;
        }
        // Logout user and clear all data
        if (itemThatWasClickedId == R.id.logout) {

            // Clear preferences
            SharedPreferences userSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor userEditor = userSharedPreferences.edit();
            userEditor.clear();
            userEditor.apply();
            // Update sharedpreferences to logged in false
            SharedPreferences loggedInSharedPreferences = getSharedPreferences(Constants.LOGGED_IN_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = loggedInSharedPreferences.edit();
            editor.putBoolean(Constants.LOGGED_IN_KEY,
                    false);
            editor.apply();
            // Launch LogIn Activity to logout and login again
            Intent openLogin = new Intent(this, LoginActivity.class);
            // To do so, pass in data
            openLogin.putExtra(Constants.OPEN_LOGIN_INTENTION, Constants.LOGOUT);
            // Start login activity
            startActivity(openLogin);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
