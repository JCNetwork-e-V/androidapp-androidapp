package com.jcnetwork.android.app1.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.jcnetwork.android.app1.R;


public class RegistrationActivity extends AppCompatActivity {

    TextView link_normal, link_executive, link_development, link_workshop_intern, link_workshop_extern, link_ablauf, link_workshop_feedback, link_vereinswettbewerb, maps_city, start_cv, start_city, start_club;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registraion);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Find views
        link_normal = findViewById(R.id.normal_tv);
        link_executive = findViewById(R.id.executive_tv);
        link_development = findViewById(R.id.development_tv);
        link_workshop_intern = findViewById(R.id.intern_workshop_tv);
        link_workshop_extern = findViewById(R.id.extern_workshop_tv);
        link_ablauf = findViewById(R.id.ablauf_tv);
        link_workshop_feedback = findViewById(R.id.workshop_feedback_tv);
        link_vereinswettbewerb = findViewById(R.id.wettbewerbs_tv);
        maps_city = findViewById(R.id.reise_tv);
        start_cv = findViewById(R.id.start_cv_tv);
        start_city = findViewById(R.id.city_tv);
        start_club = findViewById(R.id.club_tv);

        // Set on click listener
        link_normal.setOnClickListener(myClickListener);
        link_executive.setOnClickListener(myClickListener);
        link_development.setOnClickListener(myClickListener);
        link_workshop_intern.setOnClickListener(myClickListener);
        link_workshop_extern.setOnClickListener(myClickListener);
        link_ablauf.setOnClickListener(myClickListener);
        link_workshop_feedback.setOnClickListener(myClickListener);
        link_vereinswettbewerb.setOnClickListener(myClickListener);
        maps_city.setOnClickListener(myClickListener);
        start_cv.setOnClickListener(myClickListener);
        start_city.setOnClickListener(myClickListener);
        start_club.setOnClickListener(myClickListener);
    }

    private final View.OnClickListener myClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.normal_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/days-application/");
                    break;
                case R.id.executive_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://executivedays.jcevents.jcnetwork.de/days-application/");
                    break;
                case R.id.development_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://developmentdays.jcevents.jcnetwork.de/days-application/");
                    break;
                case R.id.intern_workshop_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/workshops/intern/");
                    break;
                case R.id.extern_workshop_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/workshops/extern/");
                    break;
                case R.id.ablauf_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/jce_information/ablaufplan/");
                    break;
                case R.id.workshop_feedback_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://forms.office.com/Pages/ResponsePage.aspx?id=jEv0Heij8U2Bx4qPQXqlGfrQKq0_81dEmzX_r9g-R-dUOVpDTVIxV1pJU1E1SUpWV1cwMjI5OEFBQy4u");
                    break;
                case R.id.wettbewerbs_tv:
                    // Open link in browser to register
                    openLinkInBrower("https://days.jcnetwork.de/jce_information/vereinswettbewerb/");
                    break;
                case R.id.reise_tv:
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=39104 Magdeburg");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    break;
                case R.id.start_cv_tv:
                    // Create intent to open new activity
                    openActivityInApp(CVActivity.class);
                    break;
                case R.id.city_tv:
                    // Show dialog info
                    showDialog(R.string.city_name, R.string.city_description, "https://days.jcnetwork.de/wp-content/uploads/2022/01/Bild4.jpg");
                    break;
                case R.id.club_tv:
                    // Show dialog info
                    showDialog(R.string.club_name, R.string.club_description, "https://days.jcnetwork.de/wp-content/uploads/2016/05/Vereinsbild--768x432.jpg");
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * Method to show popup window with image, title and descriptive text to topic
     * @param title_id topic name e.g. club, city
     * @param description_resource_id string resource id where the description of the topic can be foudn
     * @param image_resource_string image resource id/website address of the image online
     */
    private void showDialog(int title_id, int description_resource_id, String image_resource_string) {
        final Dialog dialog = new Dialog(RegistrationActivity.this);
        // Title in layout; therefore remove default title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Let user close dialog by clicking outside the pop up layout
        dialog.setCancelable(true);
        // Set up the custom layout
        dialog.setContentView(R.layout.dialog_popup);
        // Find views within the custom layout
        TextView title_tv = dialog.findViewById(R.id.dialog_title);
        TextView description_tv = dialog.findViewById(R.id.dialog_description);
        ImageView img_v = dialog.findViewById(R.id.dialog_img);
        ImageButton img_btn = dialog.findViewById(R.id.dialog_close_btn);
        // Set content to views
        title_tv.setText(title_id);
        description_tv.setText(description_resource_id);
        // Load in image with glide
        Glide
                .with(this)
                .load(image_resource_string)
                .placeholder(R.drawable.ic_logo_jcnetwork_days_on_white)
                .into(img_v);
        // Close dialog when close image button is pressed
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // Show dialog
        dialog.show();
    }

    /**
     * Method to open given link in browser via intnet
     */
    private void openLinkInBrower(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    /**
     * Method to open given link in browser via intnet
     */
    private void openActivityInApp(Class c) {
        Intent startNewActivity = new Intent(RegistrationActivity.this, c);
        RegistrationActivity.this.startActivity(startNewActivity);
    }
}
