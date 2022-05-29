package com.jcnetwork.android.app1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.hiddenactivities.FAQsActivity;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EngageActivity extends AppCompatActivity {

    // Variables
    MaterialCardView vVCard, crCard, fuRCard, iMCard, mCard, eMCard, hrCard, wBCard;
    TextView headingTV, subheadingTV, descriptionTV;
    ImageButton emailBtn, close_btn;
    ImageView iconImg, v_img, cr_img, fur_img, im_img, m_img, em_img, hr_img, wb_img;
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

        // Find images
        v_img = findViewById(R.id.vorstandsvorsitz_img);
        cr_img = findViewById(R.id.customer_relations_img);
        fur_img = findViewById(R.id.finanzen_und_recht_img);
        im_img = findViewById(R.id.informationsmanagement_img);
        m_img = findViewById(R.id.marketing_img);
        em_img = findViewById(R.id.eventmanagement_img);
        hr_img = findViewById(R.id.human_resources_img);
        wb_img = findViewById(R.id.weiterbildung_img);

        // Fill images with online images and keep if unavailable the default icons
        loadBlueImageIn(v_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f7695453_icon-network.png", R.drawable.vorstandsvorsitz);
        loadBlueImageIn(cr_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f769544d_icon-handshake.png", R.drawable.customer_relations);
        loadBlueImageIn(fur_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f4695441_icon-banknotes.png", R.drawable.finanzen_recht);
        loadBlueImageIn(im_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b393b695447_icon-workstation.png", R.drawable.informationsmanagement);
        loadBlueImageIn(m_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f8695459_icon-like.png", R.drawable.marketing);
        loadBlueImageIn(em_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f5695447_icon-calendar.png", R.drawable.event_management);
        loadBlueImageIn(hr_img, "https://www.jcnetwork.de/media/jcnetwork/s_573b34f5695447_icon-conference.png", R.drawable.human_resources);
        loadBlueImageIn(wb_img, "https://www.jcnetwork.de/media/jcnetwork/s_5d0973a91a45b2_jcnetwork-thought.png", R.drawable.weiterbildung);

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
        close_btn.setOnClickListener(view -> {
            // Return to full screen
            setupGridScreen();
        });

        // Set up on click listener to participate or ask questions to HR ​.
        emailBtn.setOnClickListener(view -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Only email apps should handle this intent (otherwise SEND ok)
            emailIntent.setData(Uri.parse("mailto:")); // Only email apps no social media stuff
            String[] recipients = {"mitmachen@JCNetwork.de"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // string array
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mitmachen beim JCNetwork"); // subject -> easy search later
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(EngageActivity.this, "Keine E-Mail App gefunden. Wenn du als Fellow oder Vorstand aktiv werden möchtest, melde dich gerne jederzeit bei mitmachen@JCNetwork.de", Toast.LENGTH_LONG).show();
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
     * Method to load in logo image from website into the respective imageview
     */
    private void loadImage(String imageLink, int defaultImg) {
        // Find image view
        iconImg = findViewById(R.id.image_view);

        // Load in image with glide
        Glide
                .with(this)
                .load(imageLink)
                .placeholder(defaultImg)
                .into(iconImg);
    }

    /**
    * Method to load online image into view directly
     */
    private void loadBlueImageIn(ImageView img, String imageLink, int defaultImg) {
        Glide
                .with(this)
                .load(imageLink)
                .placeholder(defaultImg)
                .into(img);
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
    private final View.OnClickListener myCardClickListener = view -> {
        // Set boolean to true
        isLargeView = TRUE;

        // React differently according to id
        switch (view.getId()) {
            case R.id.vorstandsvorsitz:
                fillLargeCard("Vorstandsvorsitz",
                        "Für die Geselligen:",
                        "Werde zur direkten Verbindung zu unseren Vereinen. Oder unterstütze bei dem Controlling der Verbandsstrategie. Oder akquiriere Pro Bono Beratungsprojekte.",
                        R.drawable.vorstandsvorsitz);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f7695453_icon-network.png",
                        R.drawable.vorstandsvorsitz);
                break;
            case R.id.customer_relations:
                fillLargeCard("Customer Relations",
                        "Für Verkaufsprofis:",
                        "Akquiriere und betreue die Partner und Sponsoren des JCNetwork für die Veranstaltungsformate, wie die JCNetwork Days. Und entwickle neue Produktfelder.",
                        R.drawable.customer_relations);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f769544d_icon-handshake.png",
                        R.drawable.customer_relations);
                break;
            case R.id.finanzen_und_recht:
                fillLargeCard("Finanzen & Recht",
                        "Für die Sorgfältigen:",
                        "Unterstütze den Vorstand bei Finanz- und Jahresabschlüssen, achte auf die Einhaltung des Datenschutzes oder hilf bei rechtlichen Fragen.",
                        R.drawable.finanzen_recht);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f4695441_icon-banknotes.png",
                        R.drawable.finanzen_recht);
                break;
            case R.id.informationsmanagement:
                fillLargeCard("Informationsmanagement",
                        "Für IT-Fans:",
                        "Verwalte die Office 365 Infrastruktur, Insights oder das Certification Portal. Oder definiere neue Prozesse und Dokumentationsrichtlinien.",
                        R.drawable.informationsmanagement);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b393b695447_icon-workstation.png",
                        R.drawable.informationsmanagement);
                break;
            case R.id.marketing:
                fillLargeCard("Marketing",
                        "Für Kreative:",
                        "Entwickle interne und externe Marketing-Maßnahmen für unsere Produkte, stärke das Branding des JCNetwork und gestalte unseren Auftritt im Social Media.",
                        R.drawable.marketing);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f8695459_icon-like.png",
                        R.drawable.marketing);
                break;
            case R.id.eventmanagement:
                fillLargeCard("Eventmanagement",
                        "Für Organisationstalente:",
                        "Unterstütze bei der Organisation der JCNetwork Days, JCNetwork Executive Days und den JCNetwork Development Days. Digitalisiere Prozesse und controlle die Finanzen.",
                        R.drawable.event_management);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f5695447_icon-calendar.png",
                        R.drawable.event_management);
                break;
            case R.id.human_resources:
                fillLargeCard("Human Resources",
                        "Für Menschenkenner:",
                        "Betreue und entwickle das JCNetwork Fellowship Progam. Gestalte Recruitingkampagnen für neuer Fellows und Vorstände. Und lass die Alumniarbeit aufleben.",
                        R.drawable.human_resources);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_573b34f5695447_icon-conference.png",
                        R.drawable.human_resources);
                break;
            case R.id.weiterbildung:
                fillLargeCard("Weiterbildung",
                        "Für Wissensdurstige:",
                        "Entwickle und betreue exzellente Weiterbildungsmöglichkeiten wie die JCNetwork Trainer Academy für jeden einzelnen Junior Consultant.",
                        R.drawable.weiterbildung);
                loadImage("https://www.jcnetwork.de/media/jcnetwork/s_5d0973a91a45b2_jcnetwork-thought.png",
                        R.drawable.weiterbildung);
                break;
            default:
                break;
        }
    };

}
