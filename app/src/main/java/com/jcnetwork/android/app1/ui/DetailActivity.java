package com.jcnetwork.android.app1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.app1.models.ProgramPoint;

import java.text.ParseException;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    // BG Image
    private ImageView imgV;

    // Data
    private ProgramPoint currentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Get data from the intent that was used to open this activity
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle != null) {
            currentEvent = dataBundle.getParcelable("EVENT_KEY");
        }

        // Set up views
        TextView titleTV = (TextView) findViewById(R.id.title);
        TextView placeTV = (TextView) findViewById(R.id.place);
        TextView timeTV = (TextView) findViewById(R.id.time);
        TextView descriptionTV = (TextView) findViewById(R.id.description);
        imgV = (ImageView) findViewById(R.id.color_bar); // same id as color bar from schedule to enable transition to this
        // Feedback functionality
        ImageButton giveWorkShopFeedBackBtn = (ImageButton) findViewById(R.id.workshop_feedback_button); // only visible if event is a workshop and links to the feedback form
        // Add to calendar
        ImageButton addToCalendarBtn = (ImageButton) findViewById(R.id.add_to_calendar_button);
        // Map intent
        ImageView goArrowImg = (ImageView) findViewById(R.id.go_arrow);

        // Set data to views
        titleTV.setText(currentEvent.getTitle());
        placeTV.setText(currentEvent.getPlace());
        try {
            timeTV.setText(ProgramPointAnalysis.getTimeStringFromBeginAndEnd(currentEvent.getBegin(), currentEvent.getEnd()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        descriptionTV.setText(currentEvent.getDescription());
        // Check if this currentProgram is a Workshop by checking if "undertitle" is "Workshopslot"
        if (currentEvent.getPlace().contains("Workshopslot")){
            giveWorkShopFeedBackBtn.setVisibility(View.VISIBLE);
        }

        // Set image based on event
        setImage(currentEvent.getImage());

        // Add on click listener to give workshop feedback button
        // TODO Always update to current workshop feedback link! (Changes)
        giveWorkShopFeedBackBtn.setOnClickListener(v -> {
            // Open workshop feedback form in browser (?)
            Intent openFeedbackForm = new Intent(Intent.ACTION_VIEW);
            openFeedbackForm.setData(Uri.parse("https://forms.office.com/Pages/ResponsePage.aspx?id=jEv0Heij8U2Bx4qPQXqlGauGSdS-orlEjHgp-bphYONUNkpCQlo5T0JOV0VVVDVXTDNNRFFUWjZDWS4u"));
            startActivity(openFeedbackForm);
        });

        // Add on click listener to addToCalendarBtn
        addToCalendarBtn.setOnClickListener(view -> {
            // Create new calendar intent
            Intent calendarIntent = null;
            try {
                calendarIntent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, currentEvent.getTitle())
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, currentEvent.getAddress())
                        .putExtra(CalendarContract.Events.DESCRIPTION, currentEvent.getDescription())
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, ProgramPointAnalysis.getDateFromString(currentEvent.getBegin()).getTime()  )
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, ProgramPointAnalysis.getDateFromString(currentEvent.getEnd()).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Check if the intent can be resolved before acting on it
            if (Objects.requireNonNull(calendarIntent).resolveActivity(getPackageManager()) != null) {
                startActivity(calendarIntent);
            }
        });

        // Add on click listener to go arrow image view
        goArrowImg.setOnClickListener(view -> {
            // Get location from current program
            String address = currentEvent.getAddress();
            // Check if information was provided
            if (!address.isEmpty()) {
                String searchUrl = "https://www.google.com/maps/search/?api=1&query=";
                Uri addressUri = Uri.parse(searchUrl + Uri.encode(address));
                // Make new intent to open Google Maps and pass in the address as destination
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
                // Set to google maps to ensure this app (if available) is used for the intent
                mapIntent.setPackage("com.google.android.apps.maps");
                // Start intent if possible
                if (mapIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
                    view.getContext().startActivity(mapIntent);
                }
            }
        });

    }

    /**
     * Method to set the right image based on...
     * @param imageString the string provided in the JSON e.g. "Workshop3_W" for the "img" key
     */
    private void setImage(String imageString) {
        if (imageString.contains("Check-In")) {
            // return check-in img
            imgV.setImageResource(R.mipmap.check_in_w);
        }
        if (imageString.contains("MV")) {
            // return mv img
            imgV.setImageResource(R.mipmap.mv_donnerstag_w);
        }
        if (imageString.contains("Plenen")) {
            // return plenum img
            imgV.setImageResource(R.mipmap.plenum_w);
        }
        if (imageString.contains("FKM")) {
            // return firmenkontaktmesse img
            imgV.setImageResource(R.mipmap.fkm_w);
        }
        if (imageString.contains("Party")) {
            // return party img
            imgV.setImageResource(R.mipmap.party_w);
        }
        if (imageString.contains("Fr\\u00fchst\\u00fcck")) {
            // return breakfast img
            imgV.setImageResource(R.mipmap.fruehstueck_w);
        }
        if (imageString.contains("Mittagessen")) {
            // return lunch img
            imgV.setImageResource(R.mipmap.mittagessen_w);
        }
        if (imageString.contains("Abendessen")) {
            // return dinner img
            imgV.setImageResource(R.mipmap.abendessen_w);
        }
        if (imageString.contains("Networking")) {
            // return networking img
            imgV.setImageResource(R.mipmap.networking_w);
        }
        if (imageString.contains("Workshop1")) {
            // return workshop img
            imgV.setImageResource(R.mipmap.workshop1_w);
        }
        if (imageString.contains("Workshop2")) {
            // return workshop img
            imgV.setImageResource(R.mipmap.workshop2_w);
        }
        if (imageString.contains("Workshop3")) {
            // return workshop img
            imgV.setImageResource(R.mipmap.workshop3_w);
        }
        if (imageString.contains("Workshop4")) {
            // return workshop img
            imgV.setImageResource(R.mipmap.workshop4_w);
        }
    }

}
