package com.jcnetwork.android.jctestapp1.hiddenactivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.FAQExpandableCardViewAdapter;
import com.jcnetwork.android.jctestapp1.models.QandA;

import java.util.ArrayList;
import java.util.List;

public class FAQsActivity extends AppCompatActivity {

    /** Variables **/
    private ImageButton mSupportBtn; // button to write email to jcnetwork support
    private RecyclerView mList;
    private List<QandA> qandAList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hidden_activity_faq);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Find views
        mSupportBtn = (ImageButton) findViewById(R.id.support_button);
        mList = (RecyclerView) findViewById(R.id.list);
        //mList.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mList.setLayoutManager(manager);


        // Create data
        QandA qandA = new QandA("Question 1", "Answer 1");
        qandAList.add(qandA);
        QandA qand2 = new QandA("Was ist das JCNetwork Certified Junior Consultant Program?", "Das JCNetwork Certified Junior Consultant Program zeichnet herausragende Junior Consultants aus, die ein breites theoretisches Wissen sowie die praktische Anwendung theoretischer Kenntnisse vorweisen können. Die persönliche Exzellenz der Junior Consultants spiegelt direkt die Qualität der Beratungsleistungen wider, die durch die regionalen studentischen Unternehmensberatungen angeboten werden. Durch umfangreiche Maßnahmen zur Qualitätssicherung und der Unterstützung durch unseren Zertifizierungspartner UMS Consulting GmbH & Co. KG können wir garantieren, dass zertifizierte Junior Consultants über vielfältige beratungsrelevante Kompetenzen verfügen. Die Inhalte des Zertifikates resultieren aus den Anforderungen mit denen studentische Berater im Rahmen ihrer Tätigkeiten konfrontiert werden.");
        qandAList.add(qand2);
        QandA qand3 = new QandA("Wer ist unser Zertifizierungspartner?", "Die UMS Consulting GmbH & Co. KG ist die führende Strategieumsetzungsberatung und unterstützt Unternehmen weltweit dabei, vorhandene Potenziale aufzuzeigen und zu realisieren.");
        qandAList.add(qand3);
        QandA qand4 = new QandA("Wie wird man JCNetwork Certified Junior Consultant?", "Alle Mitglieder einer dem JCNetwork angeschlossenen studentischen Unternehmensberatung können nach Erreichen vorgeschriebener Qualifikationen als Junior Consultant zertifiziert werden. Hierzu muss sowohl theoretisches Wissen erlangt werden, relevante Praxiserfahrung gesammelt werden und im Rahmen einer Abschlussprüfung ein reales Fallbeispiel aus dem Beratungsalltag absolvieren.");
        qandAList.add(qand4);


        // Set up adapter
        FAQExpandableCardViewAdapter adapter = new FAQExpandableCardViewAdapter(qandAList);

        // Set to list
        mList.setAdapter(adapter);

        // Set on click listener to image button to contact support
        String[] recipients = {"support@jcnetwork.de"}; // Alternative: IM971@jcnetwork.de von teams
        //TODO Replace with general mail to ask questions

        mSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Only email apps should handle this intent (otherwise SEND ok)
                emailIntent.setData(Uri.parse("mailto:")); // Only email apps no social media stuff
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // string array
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Generelle Frage"); // subject -> easy search later
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Was ist deine Frage?"); // text
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }

}
