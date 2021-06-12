package com.jcnetwork.android.jctestapp1.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.BrainteaserAdapter;
import com.jcnetwork.android.jctestapp1.models.Brainteaser;
import com.jcnetwork.android.jctestapp1.transformations.CubeOutTransformer;

public class BrainteaserActivity extends AppCompatActivity {

    // Set up variables
    BrainteaserAdapter btAdapter;
    ViewPager2 btViewPager;
    ImageButton addBTBtn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainteaser);

        // Find views
        btViewPager = findViewById(R.id.view_pager);
        addBTBtn = findViewById(R.id.add_brainteaser_btn);
        webView = findViewById(R.id.webview);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.black_to_dark_gradient));
        }

        // Set up adapter
        // Fill with data (here for example)
//        btAdapter.addBT(new Brainteaser("Titel",
//                "Aufgabe?",
//                "Antwort."));
        btAdapter = new BrainteaserAdapter(this);
        btAdapter.addBT(new Brainteaser("Kaffee vs. Tee", "Laut einer Umfrage trinken 80% der Junior Consultants Kaffee und 70% Tee. Was ist die obere und undere Grenze derer, die beides trinken?", "Bei Unabhängigkeit liegt die untere Grenze bei 50%. Bei maximaler Abhängigkeit liegt die obere Grenze bei 70%."));
        btAdapter.addBT(new Brainteaser("Skiurlaub", "Skiurlaub nach Corona: wenn vier Junior Consultants die Gondel eines Skilifts unten am Berg besteigen und den ganzen Weg nach oben fahren, an wie vielen Gondeln kommen sie vorbei?", "An allen."));
        btAdapter.addBT(new Brainteaser("Zertifikate pro Jahr", "1,5 Junior Consultants bekommen 1,5 Zertifikate in 1,5 Jahren. Wie viele Zertifikate bekommt ein Junior Consultant in einem Jahr?", "2/3."));
        btAdapter.addBT(new Brainteaser("So Alt!", "Junior steht am Neujahrsmorgen auf und grummelt: 'Am ersten Advent war ich 91 und es ist schon drei Wochen her seit meinem Geburtstag...wie alt werde ich im nächsten Jahr'?", "94."));
        btAdapter.addBT(new Brainteaser("Namensgebung",
        "Laras Vater hat fünf Töchter: 1. Nana, 2. Nene, 3. Nini, 4. Nono. Wie heißt die fünfte Tochter?",
        "Lara."));
        btAdapter.addBT(new Brainteaser("Wochentag",
        "Wenn vorgestern ein Tag nach Montag war, welcher Tag ist morgen?",
        "Freitag."));
        btAdapter.addBT(new Brainteaser("Würfelzahl",
                "Vor dir liegt ein Würfel, der die Zahl 5 anzeigt. Wenn du diesen Würfel zweimal nach vorne kippst, welche Zahl wird dann angezeigt?",
                "Zwei."));
        btAdapter.addBT(new Brainteaser("Segelspaß",
                "Junior will einen 420 breiten See überqueren mit einem Segelboot. Jeden Tag versucht er es erneut und legt die doppelte Strecke vom Vortag zurück. Nach 32 Tagen hat er endlich den See überquert. An welchem Tag hat Junior die Hälfte des Sees überquert?",
                "Am 31. Tag (Vortag)."));
        btAdapter.addBT(new Brainteaser("Mysteriöses Mascottchen",
                "Ein mysteriöses Mascottchen wurden von drei Junior Consultants gesichtet. Nico meint, dass es nicht schwarz wäre. Eric sagt, dass es entweder braun oder blau war. Anthony dagegen behauptet, dass es braun war. Du weißt, dass mindestens einer die Wahrheit sagt und mindestens einer lügt. Welche Farbe hat das Mascottchen?",
                "Blau."));
        btAdapter.addBT(new Brainteaser("Fünf",
                "Wie lässt sich die Zahl 56 nur mir Fünfen und üblichen Rechenoperationen ausdrücken?",
                "55 + 5/5."));
        btAdapter.addBT(new Brainteaser("Hundeliebhaber",
                "Du kommst an Dog Ville vorbei und siehst ein paar Menschen und überraschend viele Hunde. Bei der Durchfahrt zählst du insgesamt 40 Augen und 64 Beine. Wie viele Hunde hast du bei der Durchfahrt gesehen?",
                "12."));
        btAdapter.addBT(new Brainteaser("Tödliches Manöver",
                "Du bist in ein Spiel verwickelt in dem du zwei Mal den Revolver betätigen musst, in dem sich nur eine Patrone befindet. Du drückst einmal ab und überlebst. Jetzt hast du die Wahl: willst du direkt erneut abdrücken oder die Kammer ein Mal drehen bevor du am Auslöser ziehst?",
                "Drehen um die Wahrscheinlichkeit, dass du die Kammer mit der Patrone erwischst von 1/5 auf 1/6 zu senken."));
        btAdapter.addBT(new Brainteaser("Socken Mix",
                "Stromausfall um 5 Uhr morgens. Es ist stockdunkel und du versuchst, ein gleichfarbiges Paar Socken zu finden. Leider liegen alle lose herum. Insgesamt hast du 62 schwarze und 43 weiße Socken. Wie viele Socken nimmst du mit, wenn du sicher sein willst, dass du ein Paar gleichfarbige Socken dabeihast?",
                "Drei da es nur zwei Farbausprägungen gibt."));
        btAdapter.addBT(new Brainteaser("Diät",
                "Junior muss abnehmen und darf pro Tag nicht mehr als 1.500 Kilokalorien zu sich nehmen. Frühstück und Mittagessen waren zusammen 700, Mittag und Abendessen zusammen 900 und Frühstück und Abendessen 1.000 Kilokalorien. Darf Junior heute noch etwas essen oder ist er bereits am bzw. über dem Limit?",
                "Insgesamt hat er 1.300 Kilokalorien zu sich genommen und darf daher noch einen leichten Snack zu sich nehmen. Hinweis: hierbei kann man die Werte zusammenzählen und durch zwei teilen, um auf das Ergebnis zu kommen."));
        btAdapter.addBT(new Brainteaser("Abstand",
                "A und B laufen jeweils 8 m in entgegengesetzte Richtung und dann jeweils 6 m nach rechts. Wie weit sind sie voneinander entfernt?",
                "20 m. Hinweis: Pythagoras reicht um das Rätsel zu lösen."));
        btAdapter.addBT(new Brainteaser("Zahlenfolge",
                "Z-A-Y-B-X-C- was kommt dann?",
                "W."));
        btAdapter.addBT(new Brainteaser("Riches to Rags",
                "Junior erreicht ein Hotel und verliert sein gesamtes Vermögen. Warum?",
                "Mögliche Antwort: er hat Monopoly gespielt."));
        btAdapter.addBT(new Brainteaser("IT Katastrophe",
                "Einer der IM Fellows hat eine IT Katastrophe ausgelöst und der IM Vorstand will den Schuldigen nun finden. Drei Kandidaten stehen zur Auswahl. Eric meint, dass es Leander war. Leander meint, dass es Luca war. Luca meint, dass Leander lügt. Der IM Vorstand weiß, dass nur der Schuldige lügen würde und die anderen Aussagen stimmen. Wer ist Schuld an der IT Katastrophe?",
                "Leander. (Hinweis: das war nur ein fiktives Beispiel, also nicht sauer sein, lieber Leander :))"));
        btAdapter.addBT(new Brainteaser("Tunnelfahrt",
                "Junior fährt mit einem 1 km langen Zug mit 60 km/h durch einen 1 km langen Tunnel. Wie lange ist der Zug im Tunnel?",
                "2 min."));
        btAdapter.addBT(new Brainteaser("Analoge Zeit",
                "Wie groß ist der Winkel zwischen Stunden- und Minutenzeiger auf einer analogen Uhr um 15:15 Uhr?",
                "7,5 Grad."));

        // Add cube out page transformer to view pager
        btViewPager.setPageTransformer(new CubeOutTransformer());

        // Add adapter to viewpager
        btViewPager.setAdapter(btAdapter);

        // Set on click listener
        addBTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open form to submit new brainteaser
                webView.setVisibility(View.VISIBLE);
                webView.getSettings().setJavaScriptEnabled(true);
                MyWebViewClient webViewClient = new MyWebViewClient(BrainteaserActivity.this);
                webView.setWebViewClient(webViewClient);
                webView.loadUrl("https://forms.office.com/r/TXzNhFfY24");
            }
        });

    }

    /**
     * My Webviewclint to override the default behavior that opens a link in browser
     */
    public class MyWebViewClient extends WebViewClient {

        private Activity activity = null;

        public MyWebViewClient(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            // Don't load in browser for the form
            if(url.contains("forms.office")) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
