package com.jcnetwork.android.app1.hiddenactivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.app1.adapters.FAQExpandableCardViewAdapter;
import com.jcnetwork.android.app1.models.QandA;
import com.jcnetwork.android.app1.R;

import java.util.ArrayList;
import java.util.List;

import static com.jcnetwork.android.app1.R.*;
import static com.jcnetwork.android.app1.R.menu.*;

public class FAQsActivity extends AppCompatActivity {

    private RecyclerView mList;
    private final List<QandA> qandAList = new ArrayList<>();
    private FAQExpandableCardViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.hidden_activity_faq);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, drawable.color_gradient));
        }

        // Find views
        // button to write email to jcnetwork support
        ImageButton mSupportBtn = (ImageButton) findViewById(id.support_button);
        mList = (RecyclerView) findViewById(id.list);
        //mList.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mList.setLayoutManager(manager);

        // General
        //        QandA qandA = new QandA("Question 1", "Answer 1"); TEMPLATE
        //        qandAList.add(qandA);
        QandA qand1 = new QandA("Was ist das JCNetwork Certified Junior Consultant Program?", "Das JCNetwork Certified Junior Consultant Program zeichnet herausragende Junior Consultants aus, die ein breites theoretisches Wissen sowie die praktische Anwendung theoretischer Kenntnisse vorweisen k??nnen. Die pers??nliche Exzellenz der Junior Consultants spiegelt direkt die Qualit??t der Beratungsleistungen wider, die durch die regionalen studentischen Unternehmensberatungen angeboten werden. Durch umfangreiche Ma??nahmen zur Qualit??tssicherung und der Unterst??tzung durch unseren Zertifizierungspartner UMS Consulting GmbH & Co. KG k??nnen wir garantieren, dass zertifizierte Junior Consultants ??ber vielf??ltige beratungsrelevante Kompetenzen verf??gen. Die Inhalte des Zertifikates resultieren aus den Anforderungen mit denen studentische Berater im Rahmen ihrer T??tigkeiten konfrontiert werden.");
        qandAList.add(qand1);
        QandA qand2 = new QandA("Wer ist unser Zertifizierungspartner?", "Die UMS Consulting GmbH & Co. KG ist die f??hrende Strategieumsetzungsberatung und unterst??tzt Unternehmen weltweit dabei, vorhandene Potenziale aufzuzeigen und zu realisieren.");
        qandAList.add(qand2);
        QandA qand3 = new QandA("Wie wird man JCNetwork Certified Junior Consultant?", "Alle Mitglieder einer dem JCNetwork angeschlossenen studentischen Unternehmensberatung k??nnen nach Erreichen vorgeschriebener Qualifikationen als Junior Consultant zertifiziert werden. Hierzu muss sowohl theoretisches Wissen erlangt werden, relevante Praxiserfahrung gesammelt werden und im Rahmen einer Abschlusspr??fung ein reales Fallbeispiel aus dem Beratungsalltag absolvieren.");
        qandAList.add(qand3);
        QandA qand4 = new QandA("Welche Programmpunkte sind verpflichtend f??r den Teilnehmer?", "Das ist von Veranstaltung zu Veranstaltung unterschiedlich. Generell jedoch alle Workshops, bei denen du dich angemeldet hast. Plenen, au??erhalb der Vorstandsrunde, sind Pflicht f??r mind. einen Vorstand aus jedem Verein. Au??erdem sind die Firmenkontaktmesse und die MV verpflichtend.");
        qandAList.add(qand4);
        qandAList.add(new QandA("Wo finde ich meine Teilnehmer-ID?", "Per E-Mail erh??lt jeder seine Teilnehmer-ID. Bitte ??berpr??fe zuerst dein Postfach bevor Du dich an Support wendest."));
        // Workshops
        QandA qand5 = new QandA("Wie bewerbe ich mich auf externe Workshops?", "Mit einem ausgef??llten Lebenslauf kannst du dich bei Anmeldestart auf externe Workshops bewerben. Pro Tipp: auf gleich mehrere bewerben, um gr????ere Chancen zu haben, einen der begehrten Pl??tze zu ergattern.");
        qandAList.add(qand5);
        QandA qand6 = new QandA("Ich habe mich f??r mehrere externe Workshops in einem Slot beworben. Falls ich von mehreren Unternehmen zugelassen werde, kann ich mir den Workshop dann aussuchen?", "Nein, der Workshop wird vom JCNetwork zugeteilt.");
        qandAList.add(qand6);
        QandA qand7 = new QandA("Ich kann mich nicht auf externe Workshops bewerben. Woran kann das liegen?", "Entweder ist der Lebenslauf noch nicht ausgef??llt oder die Bewerbungsphase ist bereits abgelaufen.");
        qandAList.add(qand7);
        qandAList.add(new QandA("Wo sehe ich, zu welchem Workshop ich zugelassen wurde?", "Auf der Event-Webseite unter deinem Profil."));
        QandA qand8 = new QandA("Wie erhalte ich Credit-Points f??r meine Teilnahme an Schulungen?", "Unbedingt die Umfrage ausf??llen am Ende der Schulung. Um den Rest k??mmert sich der JCNetwork Vorstand. Konkrete Fragen k??nnen direkt an den Vorstand f??r Weiterbildung gestellt werden.");
        qandAList.add(qand8);
        QandA qand9 = new QandA("Ich bin Referent und habe eine Frage bzgl. meines Workshops.", "Fragen direkt an Vorstand Weiterbildung.");
        qandAList.add(qand9);
        qandAList.add(new QandA("Wie verl??uft die Zuteilung von externen Workshops?",
                "Die Unternehmen bekommen Einblick in die eingereichten Lebensl??ufe der Bewerber und suchen sich Ihre Teilnehmer entsprechend aus. Achte daher darauf einen vollst??ndigen und ??berzeugenden Lebenslauf einzureichen."));
        qandAList.add(new QandA("Ich wurde zu einem externen Workshop zugelassen, kann jedoch nicht teilnehmen. Was nun?", "Unbedingt eine E-Mail mit entsprechender Begr??ndung an support@jcnetwork.de bis sp??testens 18 Uhr des Vortages schicken, sodass noch reagiert werden kann."));
        // Anmelden und Abmelden
        QandA qand19 = new QandA("Ich war noch nie bei einer Veranstaltung. Wie kann ich mich anmelden?", "Falls du noch nicht registriert bist, registriere dich bitte zuerst unter https://intern.jcnetwork.de/register. Danach kannst du dich auf der Eventseite anmelden mit dem erstellten Konto.");
        qandAList.add(qand19);
        QandA qand10 = new QandA("Welche Unterschiede gibt es in den Anmeldeoptionen?", "Das unterscheidet sich von Veranstaltung zu Veranstaltung. Generell ist es abh??ngig von deiner Rolle in der Veranstaltung, z.B. normaler Junior Consultant oder Mithelfer bei der Ausrichtung des Events.");
        qandAList.add(qand10);
        QandA qand11 = new QandA("Alle Anmeldeoptionen sind ausgebucht. Wird die Anzahl an Pl??tzen nochmal aufgestockt?", "Falls die Anmeldeoptionen ausgebucht sind, wird alles daf??r getan, weitere Kapazit??ten zu organisieren und f??r die Anmeldung freizuschalten. Falls es zu einer Aufstockung der Pl??tze kommt, werden die Vereine ??ber Social Media und E-Mail darauf hingewiesen. Die Vergabe der Pl??tze erfolgt nach dem First-Come-First-Serve Prinzip. Daher hopp-hopp in der Anmeldung!");
        qandAList.add(qand11);
        QandA qand12 = new QandA("Ich bin Referent f??r einen Workshop. Muss ich mich ??ber die Eventseite anmelden?", "Ja.");
        qandAList.add(qand12);
        QandA qand13 = new QandA("Ich muss mich von den JCNetwork Days abmelden. Bei wem muss ich mich melden?", "Beim Vorstand Event Management.");
        qandAList.add(qand13);
        // Unterkunft
        QandA qand14 = new QandA("Kann ich meine Unterkunft bzw. Zimmer wechseln?", "Nein. Nach Abschluss der Zuteilung werden keine ??nderungen seitens des JCNetwork vorgenommen.");
        qandAList.add(qand14);
        QandA qand15 = new QandA("Ich m??chte mit einer bestimmten Person auf ein Zimmer. An wen wende ich mich?", "Bitte schreibe deine Wunschperson in das Feld ???Sonstiges??? im Anmeldeformular. Wir versuchen deinen Wunsch zu ber??cksichtigen, k??nnen jedoch nichts versprechen.");
        qandAList.add(qand15);
        // Finanzen und Bezahlung
        QandA qand16 = new QandA("Ich ben??tige eine Rechnung bzw. Zahlungsbest??tigung seitens des JCNetwork. Wie bekomme ich diese?", "Mit einer kurzen Anfrage beim Vorstand Finanzen und Recht.");
        qandAList.add(qand16);
        QandA qand17 = new QandA("Ich bin Referent, muss ich zahlen?", "Bei physischen Veranstaltungen immer, bei digitalen nur, wenn auch an anderen Veranstaltungspunkten teilgenommen wird.");
        qandAList.add(qand17);
        QandA qand18 = new QandA("Bekomme ich meine Teilnahmegeb??hr erstattet, wenn ich mich aus privaten Gr??nden abmelden muss?", "Normalerweise nicht. Besondere Umst??nde k??nnen mit Finanzen und Recht besprochen werden.");
        qandAList.add(qand18);
        QandA fr_4 = new QandA("Ich habe die Mitteilung bekommen, dass ich eine Mahngeb??hr zahlen muss, da ich zu sp??t ??berwiesen habe. Ich habe jedoch meinen Beitrag innerhalb der Frist beglichen. Warum bzw. was mache ich jetzt?", "Relevant f??r die Feststellung des Zahlungseingangs ist das Valutadatum auf dem Konto des JCNetwork. Eine versp??tete Zahlung liegt demnach auch dann vor, wenn der Teilnahmebeitrag fristgerecht in Auftrag gegeben wurde, jedoch erst nach dem Stichtag auf dem Konto des JCNetwork verbucht wurde. In solchen F??llen wird gibt es jedoch eine Kulanzregelung. Weis mittels Screenshots oder Buchungsbest??tigung der Bank nach, dass der Betrag fristgem???? in Auftrag gegeben wurde. Andernfalls bist du verpflichtet, einen Versp??tungszuschlag zu entrichten. Der ??berweisungszweck (ID.Vorname.Nachname) ist dabei einzuhalten und um den Baustein \"Versp??tungszuschlag\" zu erg??nzen (ID.Vorname.Nachname.Verspaetungszuschlag). Die ID findest du in der Anmeldebest??tigung. Sofern keine Zahlung des Versp??tungszuschlages vor Beginn der Veranstaltung erfolgt, kann dir die Teilnahme verwehrt werden.");
        qandAList.add(fr_4);
        qandAList.add(new QandA("Welche Strafen gibt es f??r eine Nicht Teilnahme jeglicher Art?", "Die Strafen sind in dem Verhaltenskodex beschrieben, der Teil der AGB darstellt. Bitte lies diesen aufmerksam durch, um Strafen zu vermeiden und eine sch??ne Zeit auf unseren Veranstaltungen zu verbringen."));
        qandAList.add(new QandA("Ich reise erst nach der m??glichen Check-In Zeit an. Kann ein Mitglied meines Vereins mich einchecken?", "Ja, wenn der Name des Mitglieds rechtzeitig bei uns (support@jcnetwork.de) eingegangen ist und fall noch ausstehende Betr??ge vorhanden sind, diese von der Person bezahlt werden."));

        // Set up adapter
        adapter = new FAQExpandableCardViewAdapter(qandAList);

        // Set to list
        mList.setAdapter(adapter);

        // Set on click listener to image button to contact support
        String[] recipients = {"support@jcnetwork.de"};

        mSupportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Only email apps should handle this intent (otherwise SEND ok)
                    emailIntent.setData(Uri.parse("mailto:")); // Only email apps no social media stuff
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // string array
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Generelle Frage"); // subject -> easy search later
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Was ist deine Frage?"); // text
                    if (emailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(emailIntent);
                    } else {
                        Toast.makeText(FAQsActivity.this, "Keine E-Mail App gefunden. Falls du noch Fragen hast, melde dich gerne bei support@jcnetwork.de", Toast.LENGTH_SHORT).show();
                    }
                }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate with search menu
        getMenuInflater().inflate(R.menu.search_faq_menu, menu);

        // Set up search action
        MenuItem menuItem = menu.findItem(id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Called when user presses enter
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }
            // Called while user is typing
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                // Refresh with old data if empty text
                if (newText.isEmpty()) {
                    // Set up adapter
                    adapter = new FAQExpandableCardViewAdapter(qandAList);

                    // Set to list
                    mList.setAdapter(adapter);
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
