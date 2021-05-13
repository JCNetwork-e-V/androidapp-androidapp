package com.jcnetwork.android.jctestapp1.ui;

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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.jcnetwork.android.jctestapp1.BuildConfig;
import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.jctestapp1.hiddenactivities.FAQsActivity;
import com.jcnetwork.android.jctestapp1.hiddenactivities.ImpressumActivity;
import com.jcnetwork.android.jctestapp1.hiddenactivities.JobwallActivity;
import com.jcnetwork.android.jctestapp1.hiddenactivities.LoginActivity;
import com.jcnetwork.android.jctestapp1.hiddenactivities.SettingsActivity;
import com.jcnetwork.android.jctestapp1.models.ProgramPoint;
import com.jcnetwork.android.jctestapp1.network.CheckNetwork;
import com.jcnetwork.android.jctestapp1.network.RetrofitMethods;
import com.jcnetwork.android.jctestapp1.notifications.NotificationWorker;
import com.jcnetwork.android.jctestapp1.notifications.Notifications;
import com.jcnetwork.android.jctestapp1.roomdb.ProgramViewModel;
import com.jcnetwork.android.jctestapp1.utils.Constants;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import com.jcnetwork.android.jctestapp1.hiddenactivities.ImpressumActivity;


public class MainActivity extends AppCompatActivity {

    // Log in boolean check
    public Boolean isLoggedIn;

    // Set up user variables
    private String userName;
    private String hotelName;
    private String hotelAddress;

    // For Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Set up views
    ScrollView mScrollV;
    CoordinatorLayout mCoordinator;
    ImageButton checkInButton, scheduleButton, pointButton, cityButton, profileButton, clubButton, brainButton, engageButton, firmButton;
    TextView nameTV;
    NavigationView navDrawer;
    DrawerLayout drawerLayout;
    TextView navNameTV, navEmailTV;
    androidx.appcompat.widget.Toolbar toolbar;

    // Data
    List<ProgramPoint> mProgram;

    // TODO Check // ViewModel
    private ProgramViewModel mProgramViewModel;

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
        // TODO Delete later or rather: to implement cloud messaging properly e.g. subscription to topics, more code is needed
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(LOG_TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "R.string.msg_token_fmt" + token;
                        Log.d(LOG_TAG, msg);
                    }
                });

        //getSupportActionBar().hide();
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setElevation(0);
//        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#374049")));
//        getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));

        // Set up view model // TODO
        //mProgramViewModel = new ViewModelProvider(this).get(ProgramViewModel.class);





        // Check shared preferences
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                Constants.LOGGED_IN_KEY, Context.MODE_PRIVATE);
        Log.i(LOG_TAG, "Logged in: " + String.valueOf(
                sharedPreferences.getBoolean(
                        Constants.LOGGED_IN_KEY,
                false)));
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


        // Set up Notifications //TODO Check: notifications need only be set up when
        //TODO a) the user is logged in (otherwise we can't fetch data)
        //TODO b) we got the data i.e. this should also check if data exists
        setUpNotifications();

        // TODO Test internet connection
        if (CheckNetwork.checkConnection(this)) {
            Log.i(LOG_TAG, "internet available");
            // Initialize Retrofit
            RetrofitMethods retrofitMethods = new RetrofitMethods(this);

            // Make call to get ablauf plan data and user name (will be stored in shared preferences)
            mProgram =  retrofitMethods.getProgram();

            // Add each to viewmodel
//            for (int i = 0; i < mProgram.size(); i++ ) {
//                mProgramViewModel.insert(mProgram.get(i));
//                // Log
//                Log.i(LOG_TAG, "adding Programpoint" + String.valueOf(i));
//            }


        } else {
            Log.i(LOG_TAG, "no internet");
        }



        // Find views
        //mScrollV = (ScrollView)findViewById(R.id.scrollView);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        checkInButton = (ImageButton) findViewById(R.id.check_in_button);
        scheduleButton = (ImageButton) findViewById(R.id.schedule_button);
        pointButton = (ImageButton) findViewById(R.id.certification_points_button);
        cityButton = (ImageButton) findViewById(R.id.city_button);
        profileButton = (ImageButton) findViewById(R.id.profile_button);
        clubButton = (ImageButton) findViewById(R.id.club_button);
        brainButton = (ImageButton) findViewById(R.id.brain_button);
        engageButton = (ImageButton) findViewById(R.id.engage_button);
        firmButton = (ImageButton) findViewById(R.id.firm_button);

        navDrawer = (NavigationView) findViewById(R.id.navigation_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View headerView = navDrawer.getHeaderView(0);
        navNameTV = (TextView) headerView.findViewById(R.id.nav_name_tv);
        navEmailTV = (TextView) headerView.findViewById(R.id.nav_email_tv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Set toolbar as actionbar
        //setSupportActionBar(toolbar); // if we activate this line; the menu on the right as well as the title could be shown


        // Set text for
        navEmailTV.setText(userSharedPreferences.getString(Constants.USER_EMAIL, Constants.EMPTY_STRING_DEFAULT));
        navNameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));


        //TODO Make toolbar and find it in view and set it as the support actionbar
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

        // TODO Make fade instead of spell animation
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(3000);
        nameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
        nameTV.startAnimation(in);
        //setCharacterDelay(150);
        //animateText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
        userSharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                // Set to data
                navNameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));

                //
                nameTV.setText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
                nameTV.startAnimation(in);
                //animateText(userSharedPreferences.getString(Constants.USER_NAME_KEY, Constants.EMPTY_STRING_DEFAULT));
                Log.i(LOG_TAG, "onSharedPreferenceChanged called");
            }
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
        // Find views
        //navNameTV = (TextView) findViewById(R.id.nav_name_tv);
        //navEmailTV = (TextView) findViewById(R.id.nav_email_tv);

        // Set default checked item to home
        navDrawer.setCheckedItem(R.id.home);



        // Get menu
        //navNameTV.setText("A"); //TODO Fix null object reference

        // Setup listener to item selection
        navDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                        // TODO Check
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
            }
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
     * TODO Potentially divide into internal and external workshop registration
     * TODO Potentially add notification for next event (Programpoint)...might be a pain in the...u know...
     * TODO Put in notifications class to clean up main activity
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

        /** WORKSHOP REGISTRATION **/
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

        /** EVENT REGISTRATION **/
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
    private long mDelay = 150;
    private CharSequence mText;
    private int mIndex;
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            nameTV.setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;
        nameTV.setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);

    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }

    /**
     * Creates an onClickListener which checks the id of the view and reacts accorodingly
     */ //TODO Set up engage + firm activity
    private View.OnClickListener myCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // React differently according to id
            switch (view.getId()) {
                case R.id.brain_button:
                    // Create intent to open new activity to go through brain teasers
                    Intent openBT = new Intent(MainActivity.this, BrainteaserActivity.class);
                    MainActivity.this.startActivity(openBT);
                    break;
                case R.id.club_button:
                    // Create intent to open new activity to introduce club //TODO Temporary redirect to Jobwall
                    Intent openClubIntro = new Intent(MainActivity.this, JobwallActivity.class);
                    MainActivity.this.startActivity(openClubIntro);
                    // This adds a transition //TODO
//                    overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
                    break;
                case R.id.city_button:
                    // Create intent to open new activity to introduce city //TODO Temporary redirect to Registraion
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
                    // Add data i.e. plan to this intent to pass along to the plan activity
//                    Bundle dataBundle = new Bundle();
//                    dataBundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) mProgram);
//                    openScheduleActivity.putExtras(dataBundle);


                    // Open new activity
                    MainActivity.this.startActivity(openScheduleActivity);
                    break;
                default:
                    break;
            }
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
//            // Make Snackbar to let the user know the process is going on
//            Snackbar.make(mCoordinator, // View you attach the snackbar to; usually coordinator layout
//                    "Logging off...", // text to display
//                    BaseTransientBottomBar.LENGTH_LONG).show(); // length to display it for
//            // Set Logged in boolean to false and save this to Shared Preferences
//            isLoggedIn = false;
            // Clear preferences
            SharedPreferences userSharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_FILE_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor userEditor = userSharedPreferences.edit();
            userEditor.clear();
            userEditor.apply();
            // TODO Clear Room (when/if) implemented or firebase database
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
