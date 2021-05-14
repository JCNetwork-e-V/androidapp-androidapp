package com.jcnetwork.android.jctestapp1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.jcnetwork.android.jctestapp1.R;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EngageActivity extends AppCompatActivity {

    // Variables
    MaterialCardView vVCard, crCard, fuRCard, iMCard, mCard, eMCard, hrCard, wBCard;
    TextView headingTV, subheadingTV, descriptionTV;
    ImageButton emailBtn, close_btn;
    ImageView iconImg;
    Boolean isLargeView = FALSE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupGridScreen();
    }

    /**
     * Method to set up the normal screen with grid view
     */
    private void setupGridScreen() {
        // Set boolean
        isLargeView = FALSE;

        // Set up main content view
        setContentView(R.layout.activity_engage);

        // Find views
        vVCard = findViewById(R.id.vorstandsvorsitz);
        crCard = findViewById(R.id.customer_relations);
        fuRCard = findViewById(R.id.finanzen_und_recht);
        iMCard = findViewById(R.id.informationsmanagement);
        mCard = findViewById(R.id.marketing);
        eMCard = findViewById(R.id.eventmanagement);
        hrCard = findViewById(R.id.human_resources);
        wBCard = findViewById(R.id.weiterbildung);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Set up on custom on click listeners for the cards
        vVCard.setOnClickListener(myCardClickListener);
        crCard.setOnClickListener(myCardClickListener);
        fuRCard.setOnClickListener(myCardClickListener);
        iMCard.setOnClickListener(myCardClickListener);
        mCard.setOnClickListener(myCardClickListener);
        eMCard.setOnClickListener(myCardClickListener);
        hrCard.setOnClickListener(myCardClickListener);
        wBCard.setOnClickListener(myCardClickListener);
    }

    /**
     * Method to fill out large card
     */
    private void fillLargeCard(String title, String subheading, String description, int icon) {
        // Set content view for all
        setContentView(R.layout.fragment_engage_card_large);

        // Find views for all
        headingTV = findViewById(R.id.heading);
        subheadingTV = findViewById(R.id.subheading);
        descriptionTV = findViewById(R.id.description);
        emailBtn = findViewById(R.id.email_action_button);
        close_btn = findViewById(R.id.close_btn);
        iconImg = findViewById(R.id.image_view);

        // Set up on click listener to close full card view
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Return to full screen
                setupGridScreen();
            }
        });

        // Set up on click listener to participate or ask questions to HR ​.
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Only email apps should handle this intent (otherwise SEND ok)
                emailIntent.setData(Uri.parse("mailto:")); // Only email apps no social media stuff
                String[] recipients = {"mitmachen@JCNetwork.de"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // string array
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mitmachen beim JCNetwork"); // subject -> easy search later
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });

        // Set text
        headingTV.setText(title);
        subheadingTV.setText(subheading);
        descriptionTV.setText(description);

        // Set image
        iconImg.setImageResource(icon);
    }

    /**
     * Override on back/up pressed when large card is viewed
     */
    @Override
    public void onBackPressed()
    {
        if (isLargeView) {
            // Return to full screen
            setupGridScreen();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Private Click Listener
     */
    private View.OnClickListener myCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Set boolean to true
            isLargeView = TRUE;

            // React differently according to id
            switch (view.getId()) {
                case R.id.vorstandsvorsitz:
                    fillLargeCard("Vorstandsvorsitz",
                            "Für die Geselligen:",
                            "Werde zur direkten Verbindung zu unseren Vereinen. Oder unterstütze bei dem Controlling der Verbandsstrategie. Oder akquiriere Pro Bono Beratungsprojekte.",
                            R.drawable.vorstandsvorsitz);
                    break;
                case R.id.customer_relations:
                    fillLargeCard("Customer Relations",
                            "Für Verkaufsprofis:",
                            "Akquiriere und betreue die Partner und Sponsoren des JCNetwork für die Veranstaltungsformate, wie die JCNetwork Days. Und entwickle neue Produktfelder.",
                            R.drawable.customer_relations);
                    break;
                case R.id.finanzen_und_recht:
                    fillLargeCard("Finanzen & Recht",
                            "Für die Sorgfältigen:",
                            "Unterstütze den Vorstand bei Finanz- und Jahresabschlüssen, achte auf die Einhaltung des Datenschutzes oder hilf bei rechtlichen Fragen.",
                            R.drawable.finanzen_recht);
                    break;
                case R.id.informationsmanagement:
                    fillLargeCard("Informationsmanagement",
                            "Für IT-Fans:",
                            "Verwalte die Office 365 Infrastruktur, Insights oder das Certification Portal. Oder definiere neue Prozesse und Dokumentationsrichtlinien.",
                            R.drawable.informationsmanagement);
                    break;
                case R.id.marketing:
                    fillLargeCard("Marketing",
                            "Für Kreative:",
                            "Entwickle interne und externe Marketing-Maßnahmen für unsere Produkte, stärke das Branding des JCNetwork und gestalte unseren Auftritt im Social Media.",
                            R.drawable.marketing);
                    break;
                case R.id.eventmanagement:
                    fillLargeCard("Eventmanagement",
                            "Für Organisationstalente:",
                            "Unterstütze bei der Organisation der JCNetwork Days, JCNetwork Executive Days und den JCNetwork Development Days. Digitalisiere Prozesse und controlle die Finanzen.",
                            R.drawable.event_management);
                    break;
                case R.id.human_resources:
                    fillLargeCard("Human Resources",
                            "Für Menschenkenner:",
                            "Betreue und entwickle das JCNetwork Fellowship Progam. Gestalte Recruitingkampagnen für neuer Fellows und Vorstände. Und lass die Alumniarbeit aufleben.",
                            R.drawable.human_resources);
                    break;
                case R.id.weiterbildung:
                    fillLargeCard("Weiterbildung",
                            "Für Wissensdurstige:",
                            "Entwickle und betreue exzellente Weiterbildungsmöglichkeiten wie die JCNetwork Trainer Academy für jeden einzelnen Junior Consultant.",
                            R.drawable.weiterbildung);
                    break;
                default:
                    break;
            }
        }
    };

}
