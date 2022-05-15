package com.jcnetwork.android.app1.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.adapters.FirmAdapter;
import com.jcnetwork.android.app1.models.Firm;
import com.jcnetwork.android.app1.transformations.CubeOutTransformer;

public class FirmActivity extends AppCompatActivity {

    // Set up variables
    FirmAdapter firmAdapter;
    ViewPager2 firmViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm);

        // Find views
        firmViewPager = findViewById(R.id.view_pager);

        // Set up support bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.color_gradient));
        }

        // Set up adapter and fill with data
        firmAdapter = new FirmAdapter(this, this);
        // Platinum
        firmAdapter.addFirm(new Firm("P3",
                "Die P3 wurde 1996 in Aachen als Spin-Off des Fraunhofer Instituts (für Produktionstechnologie) gegründet. Sie ist heute an 17 Standorten auf 3 Kontinenten vertreten und arbeitet erfolgreich mit 1.200 Ingenieuren und Beratern in den Branchen Automotive, Digital Industry, Energy, Rail & Transportation, Banking & Insurance.\n" +
                        "Unsere Kunden profitieren von unserer Erfahrung in der Managementberatung, Projektorganisation, Ingenieurdienstleistung und Technologieentwicklung. Dabei ist unser großer Vorteil, dass wir Projekte ganzheitlich betreuen können – von Strategie und Konzeption, Implementierung und Prozessoptimierung bis hin zu Engineering und Testing. Und wir kombinieren unsere Expertise der verschiedenen Branchen, wie zum Beispiel das Security Knowhow der Telekommunikation für Connected Car oder das Wissen über Energienetze zum Laden von E-Fahrzeugen.",
                "https://www.p3-group.com",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/P3-2.png",
                "https://www.xing.com/pages/p3-group",
                "https://de.linkedin.com/company/p3"));

        // Gold
        firmAdapter.addFirm(new Firm("BwConsulting",
                "Die BwConsulting ist ein Beratungsunternehmen mit einem sehr speziellen Fokus. Als Inhouse-Gesellschaft beraten wir die strategischen Projekte des Bundesministeriums der Verteidigung (BMVg) und der Bundeswehr. Im Auftrag der ministeriellen Leitung sind wir damit treibende Kraft für die kontinuierliche Weiterentwicklung des gesamten Ressorts. Unsere Beraterinnen und Berater bringen ihre methodische und fachliche Expertise dabei unmittelbar in Projekte mit besonderer gesellschafts- und sicherheitspolitischer Relevanz ein. Unser Fokus liegt hierbei auf den drei Beratungsfeldern Strategie & Steuerung, Prozesse & Organisation und Projektmanagementberatung.\u200B\u200B",
                "https://www.bwconsulting.de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc3f6cd21c_bwconsulting-links-535x200.png",
                "https://www.xing.com/pages/bwconsulting-dieinhouse-beratungderbundeswehr",
                "https://www.linkedin.com/company/bwconsulting-the-inhouse-consultancy-of-bundeswehr/"));
        firmAdapter.addFirm(new Firm("CORE",
                "CORE SE spezialisiert sich als eine der führenden IT-Managementberatungen auf IT-Transformationen.\n" +
                        "\n" +
                        "In erster Linie bietet CORE keine klassische Beratung, sondern vielfältige Entwicklungsperspektiven mit schneller Verantwortungsübernahme in unterschiedlichen Karrierepfaden innerhalb eines ThinkTanks. Damit haben wir klare Vorteile gegenüber klassischen Strukturen: Unser Beraten entspringt einem tiefen Verständnis für verantwortungsvolles, unternehmerisches Handeln – und in diesem Geist forschen wir, konzipieren und entwickeln IT-Lösungen, betreiben sensible IT-Komponenten und investieren in neue Chancen.\n" +
                        "\n" +
                        "Innerhalb der CORE SE haben wir über unsere Entitäten COREinstitute, COREtransform, COREengineering, COREoperating und COREventures eine hohe Vielfalt spannender Themen und Aufgaben, in der sich jeder nach seinen Stärken entwickeln kann.\n" +
                        "\n" +
                        "Wichtig sind uns Menschen hinter dem Studium. Bei CORE haben wir die unterschiedlichen Hintergünde und Bandbreiten: Von Psychologie über Mathematik, Informatik, Physik, BWL/VWL, Recht oder Philosophie. Ein gutes Verständnis für anspruchsvolle Problemstellungen und eine strukturierte Herangehensweise, logisches Denkvermögen gepaart mit Neugier und einer ausgeprägten Auffassungsgabe sind uns wichtig.",
                "https://core.se/",
                      "https://cdn.jcnetwork.de/images/external_company_logo/core_logo_535x200px_zentriert.png",
                      "https://www.xing.com/pages/core-se",
                "https://www.linkedin.com/company/core.se/"));
        firmAdapter.addFirm(new Firm("Woodmark Consulting",
                "Woodmark Consulting ist der Partner für AI, Data & Analytics Beratung.\n" +
                        "\n" +
                        "Von Strategy, Governance, Planning, Architecture, Engineering, Cloud, Artificial Intelligence & Reporting bis IoT.\n" +
                        "\n" +
                        "Woodmark in Zahlen: 23 Jahre erfolgreich\n" +
                        "\n" +
                        "100+Mitarbeiter:innen\n" +
                        "200+ Zertifizierungen\n" +
                        "100% Klimaneutral\n" +
                        "4 Standorte\n" +
                        "Was uns ausmacht:\n" +
                        "\n" +
                        "Wir arbeiten auf Augenhöhe und halten zusammen\n" +
                        "Wir leben Diversity – nur du selbst machst den Unterschied\n" +
                        "Wir fördern deine Gesundheit & zeigen soziales Engagement\n" +
                        "Wir arbeiten nachhaltig und sind seit 2022 klimaneutral\n" +
                        "Uns verbindet die Passion for Data & Passion for People\n" +
                        "Wohlfühl-Fakten:\n" +
                        "\n" +
                        "2x Beste Berater\n" +
                        "5x Top Arbeitgeber Mittelstand\n" +
                        "4,6 kununu Score\n" +
                        "100% Weiterempfehlung bei Glassdoor",
                "https://www.woodmark.de",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/Woodmark-1-300x150.png",
                "https://www.xing.com/pages/woodmarkconsultingag\n",
                "https://bf.linkedin.com/company/woodmark-consulting-ag"));

        // Silber Partner
        firmAdapter.addFirm(new Firm("Accenture",
                "Accenture ist ein weltweit tätiges Beratungsunternehmen, führend in Digitalisierung, Cloud und Security. Wir bringen unsere umfassende Erfahrung und spezialisierten Fähigkeiten in mehr als 40 Branchen ein und bieten Dienstleistungen aus den Bereichen Strategy & Consulting, Interactive, Technology und Operations – gestützt auf das weltweit größte Netzwerk aus Centern für Advanced Technology und Intelligent Operations. Unsere 674.000 Mitarbeitenden arbeiten jeden Tag für Kunden in über 120 Ländern daran, Technologie und menschliche Kreativität zu vereinen. Wir setzen auf Veränderung, um Mehrwert und gemeinsamen Erfolg zu schaffen – für Kunden, Mitarbeitende, Aktionäre, Partner und für die Gemeinschaft. Besuchen Sie uns unter accenture.de",
                "https://www.accenture.com/de-de",
                "https://www.jcnetwork.de/media/jcnetwork/m_61e16f80119635_accenture-535x200.png",
                "https://www.xing.com/pages/accenture\n",
                "https://www.linkedin.com/company/accenture-dach/"));
        firmAdapter.addFirm(new Firm("Assure Consulting",
                "Assure Consulting ist eine der führenden Unternehmensberatungen für Projektmanagement-Dienstleistungen in Deutschland. Wir unterstützen internationale Konzerne dabei, komplexe Großprojekte erfolgreich zu realisieren. Zu den Dienstleistungen zählen der Setup und Betrieb von PMOs und Project Offices (PO), die Leitung von Projekten und Teilprojekten sowie die Op-timierung von Projektmanagement-Methodik.\n" +
                        "\n" +
                        "Assure Consulting wurde 2003 in Wehrheim (bei Frankfurt) gegründet und beschäftigt aktuell ca. 100 Mitarbeiter. Zu den Kunden zählen internationale Konzerne aus den Branchen: Anlagenbau, Automotive, Banken, Energie, Pharma, Telekommunikation, Tourismus und Versicherungen. Wir verstehen uns als eine mitarbeiterorientierte Unternehmensberatung: Im Fokus des Unter-nehmens stehen unsere Kolleginnen und Kollegen. Besonders wichtig sind uns in diesem Zu-sammenhang individuelle Förderung, Forderung und Wertschätzung des Einzelnen sowie eine offene und vertrauensvolle Unternehmenskultur.\n" +
                        "\n" +
                        "Unsere Firmenphilosophie: Stay and Grow statt Up or Out!",
                "https://www.assure.de/de",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc3e6cd215_assureconsulting-links-535x200.png",
                "https://www.xing.com/pages/assureconsultinggmbh",
                "https://www.linkedin.com/company/assure-consulting-gmbh/"));
        firmAdapter.addFirm(new Firm("Bearing Point",
                "BearingPoint ist eine unabhängige Management- und Technologieberatung mit europäischen Wurzeln und globaler Reichweite. Das Unternehmen agiert in drei Bereichen: Consulting, Solutions, Ventures. Consulting umfasst das klassische Beratungsgeschäft. Solutions fokussiert auf eigene technische Lösungen in den Bereichen Digital Transformation, Regulatory Technology sowie Advanced Analytics, und Ventures treibt die Finanzierung und Entwicklung von Start-ups voran. Zu BearingPoints Kunden gehören viele der weltweit führenden Unternehmen und Organisationen. Das globale Netzwerk von BearingPoint mit mehr als 10.000 Mitarbeitern unterstützt Kunden in über 75 Ländern und engagiert sich gemeinsam mit ihnen für einen messbaren und langfristigen Geschäftserfolg.\n" +
                        "\n" +
                        "Wir bieten Beratung, die Strategie und Technologie vereint. Unsere Kunden schätzen uns als Geschäftsmodell-Transformatoren und Digitalisierungsexperten. Wir verbinden Unternehmertum und Innovationsgeist, unsere Berater denken strategisch, analytisch und kreativ.\n" +
                        "\n" +
                        "Bei uns erwarten Dich außergewöhnliche Chancen, vielfältige Gestaltungsräume, ein Klima der Wertschätzung und Kollegen, die oft zu Freunden werden.",
                "https://www.bearingpoint.com/de-de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc3f18c57c_bearingpoint-links-535x200.png",
                "https://www.xing.com/pages/bearingpoint",
                "https://www.linkedin.com/company/bearingpoint/"));
        firmAdapter.addFirm(new Firm("Capgemini Invent",
                "Capgemini Invent ist die weltweite Beratungseinheit der Capgemini-Gruppe für digitale Innovation, Design und Transformation. Sie ermöglicht CxOs, die Zukunft ihrer Unternehmen zu gestalten. Dafür arbeiten über 10.000 Mitarbeiterinnen und Mitarbeiter an mehr als 36 Standorten sowie in 37 Kreativstudios weltweit. Sie vereinen Strategieberatung, Data Science, Produkt- und Experience Design, Markenmanagement sowie Technologie-Know-how, um neue Digitallösungen, Produkte, Umgebungen als auch Geschäftsmodelle für eine nachhaltige Zukunft zu entwickeln.",
                "https://www.capgemini.com/de-de/service/invent/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/Capgemini-Invent-1-300x150.png",
                "https://www.xing.com/pages/capgemini-invent",
                "https://www.linkedin.com/company/capgemini-invent"));
        firmAdapter.addFirm(new Firm("CPC",
                "WER WIR SIND\n" +
                        "\n" +
                        "Wir sind eine führende Change Management Beratung mit ca. 100 Beratern, die rund 1.500 Projekte unterschiedlichster Größenordnung für unsere Kunden weltweit bereits erfolgreich durchgeführt hat.\n" +
                        "Wir haben mehr als 25 Jahre Erfahrung am Markt und in unzähligen Branchen.\n" +
                        "Wir sind die einzige Beratung in Deutschland, die von Changefirst Ltd. lizenziert ist die PCI® – People Centred Implementation – in unserem Beratungsumfeld einzusetzen und zu schulen.\n" +
                        "WOFÜR WIR STEHEN\n" +
                        "\n" +
                        "Wir agieren mit unseren Kunden partnerschaftlich und auf Augenhöhe.\n" +
                        "Wir machen Veränderungen durch Erleben greifbar für das Management, die Mitarbeiter und die gesamte Organisation – innovativ, digital und agil.\n" +
                        "Wir übernehmen Verantwortung und partizipieren. Mitunternehmerschaft ist ein wichtiger Aspekt unserer Firmenkultur, darum sind 45% unserer Mitarbeiter am Unternehmen beteiligt.\n" +
                        "Wir wurden für unsere Arbeitsergebnisse und Ansätze sowie für unsere interne Zusammenarbeit regelmäßig von renommierten Organisationen ausgezeichnet – unter anderem Great Place To Work 2011-2022 und Hidden Champion 2021/22.",
                "https://cpc-ag.de/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/CPC-2-300x149.png",
                "https://www.xing.com/pages/cpcunternehmensmanagementag",
                "https://www.linkedin.com/company/cpc-unternehmensmanagement-ag/?trk=ppro_cprof"));
        firmAdapter.addFirm(new Firm("Deloitte",
                "Making an impact that matters. Deloitte konzentriert sich als eine der führenden Professional Services Firms auf mehr als nur die klassischen Aufgaben einer Prüfungs- und Beratungsgesellschaft. Wir erbringen Dienstleistungen in den Bereichen Audit & Assurance, Risk Advisory, Tax & Legal, Financial Advisory und Consulting für Unternehmen und Institutionen aus allen Branchen. Als Vorreiter entwickeln wir innovative Lösungen für unsere Kunden und eröffnen Chancen für unsere Talente. Ganz gleich, ob BWL oder MINT – Diversity fördert Innovation durch unterschiedliche Sichtweisen und Charaktere.",
                "https://www2.deloitte.com/de",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/Deloitte-2-300x150.png",
                "https://www.xing.com/pages/deloitte",
                "https://www.linkedin.com/company/deloitte"));
        firmAdapter.addFirm(new Firm("Detecon Consulting",
                "Detecon Consulting\n" +
                        "\n" +
                        "Wir sind eine führende, weltweit agierende Management- und Technologieberatung mit Hauptsitz im Köln, vertreten in 12 Ländern. Von der Erfindung der SMS bis zum Treiber von 5G, wir sind seit über 40 Jahren True Digital Transformer: AI, Connected Car, Cloud, Cyber Security, EAM, IoT, Robotics und Virtual Reality sind unsere „Digital Fields of Play! Wir gestalten die Welt von morgen und zeigen unseren Kunden, wie sie ihr Geschäft mit Kreativität und Mut zur Innovation auf die nächste Stufe heben.\n" +
                        "\n" +
                        "Wir stehen für Vielfalt: in den Entwicklungsmöglichkeiten, in den Projekten, in unseren Nationalitäten, in den Karrierechancen, und vor Allem in unsere Kultur. Wir legen Wert auf das Miteinander und den Wissenstransfer innerhalb der Detecon. Anstelle eines Up-Or-Out Systems statten wir unsere Young Consultants durch individuelle Trainings- und Weiterbildungsmöglichkeiten sowie unsere Knowledge Communities mit den passgenauen Skill-Set und Fachwissen aus, die sie für ihren weiteren Karriereweg benötigen.\n" +
                        "\n" +
                        "Freizeit und Ausgleich neben dem Job ist uns wichtig und wird hier aktiv vorgelebt. Unser Body & Soul-Programm bietet eine Vielzahl an sportlichen Aktivitäten und gesundheitlichen Fördermaßnahmen: Massagetermine, Mobiler Fitness Coach, Beach Volleyball- und Klettergruppe, Fussball- und Basketball-Team, Gipfelstürmer und Segeltörner.\n" +
                        "\n" +
                        "Ob Detecon-Band oder Detecon-Baskets: After-Works, Studenten-Lunch oder unsere Summer Night Party mit über 300 Alumni: unsere Interkationenen geht weit über die fachliche Zusammenarbeit hinaus.\u200B",
                "https://www.detecon.com/de",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4146fdef_detecon-links-535x200.png",
                "https://www.xing.com/pages/detecon",
                "https://www.linkedin.com/company/detecon/?originalSubdomain=de"));
        firmAdapter.addFirm(new Firm("KPMG",
                "Es gibt gute Gründe für eine Karriere bei KPMG: In unseren Teams arbeiten rund 12.500 Mitarbeiterinnen und Mitarbeiter an 26 Standorten in Deutschland. Dabei stehen wir für Vielfalt und eine Kultur der gegenseitigen Wertschätzung. Aufgrund dieser diversen und vielschichtigen Kompetenzen in unseren Teams können wir interdisziplinär an innovativen Themen arbeiten und gemeinsam auch große Projekte stemmen. Du begleitest mit uns unsere Mandanten in den Bereichen Wirtschaftsprüfung, Steuerberatung und Consulting, oder agierst hinter den Kulissen in Central Services. Wir suchen Talente, die gemeinsam mit uns die Herausforderungen von morgen angehen wollen. Schon gewusst? Wir stellen nicht nur Absolvent:innen aus dem Bereich Wirtschaftswissenschaften ein. Auch motivierte Absolvent:innen und (Young) Professionals mit naturwissenschaftlichem Hintergrund (Mathematik, Physik oder Informatik) erwarten bei uns spannende Projekte.",
                "https://home.kpmg/de",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/KPMG-1-300x150.png",
                "https://www.xing.com/pages/kpmg",
                "https://de.linkedin.com/company/kpmg"));
        firmAdapter.addFirm(new Firm("MGRP",
                "Bei MGRP (Management Group Dr. Röser & Partner) zu arbeiten heißt, sich am Puls der Zeit zu verwirklichen. Unsere Kunden kommen aus den unterschiedlichsten Branchen: von der Automobil- und Zulieferindustrie über soziale Organisationen und Technologieschmieden bis hin zu Versicherungsfirmen. Für jedes dieser Unternehmen analysieren und erschließen wir die Potenziale digitaler Technologien – und sichern so ihre Zukunftsfähigkeit.  \n" +
                        "\n" +
                        "Als Topmanagementberatung mit Start-up-Charakter sind wir anpassungsfähig, setzen Qualität vor Quantität und machen weiter, wo andere aufgegeben haben. Warum? Weil bei uns aufgeschlossene Menschen arbeiten, die wissen, wie man Veränderung erfolgreich und nachhaltig gestaltet. Dabei legen wir Wert auf flache Hierarchien, individuelle Freiräume und ein wertschätzendes Miteinander und gemeinsames Wachsen. Jeder ist bei MGRP willkommen, der wie wir auf Offenheit und Initiative setzt. Vom Werkstudenten bis zum Senior Professional – ob Qualitätsfanatiker, Charakterkopf, Zahlenmensch oder Überzeugungstäter.",
                "https://mg-rp.com/de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_60561590326405_mgrp-535x200.png",
                "https://www.xing.com/pages/mgrp",
                "https://www.linkedin.com/company/mg-rp/"));
        firmAdapter.addFirm(new Firm("MHP",
                "Die MHP Management- und IT-Beratung GmbH ist eines der führenden Beratungsunternehmen und ein Tochterunternehmen der Porsche AG (81,8%). Der besondere Beratungsansatz von MHP ist die Symbiose aus Management- und IT-Beratung. Dabei optimieren und digitalisieren wir als Digitalisierungsexperte mit den Leistungsbereichen Management Consulting, System Integration, Managed Services und Digital Services and Solutions die Prozesse unserer Kunden über die komplette Wertschöpfungskette. Als Branchenexperte – vor allem für Mobility und Manufacturing – bieten wir unseren Kunden somit, neben umfassender IT-Kompetenz, auch tiefgehendes Management- und Prozess-Know-How. Strategische Innovationen übertragen wir auch auf andere Branchen. Aktuell beraten und betreuen über 2.800 Mitarbeiter von 19 Standorten in Deutschland – Ludwigsburg (3x), Berlin, Essen, Frankfurt a. M., Ingolstadt, München, Nürnberg, Wolfsburg (2x), England (Reading), den USA (Atlanta), China (Shanghai), Rumänien (Cluj-Napoca, Timișoara), Tschechien (Prag), Österreich (St. Pölten) und Israel (Tel Aviv) – über 300 Kunden weltweit.",
                "https://www.mhp.com/de/home",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4346fdf6_mhp-links-535x200.png",
                "https://www.xing.com/pages/mieschkehofmannundpartner",
                "https://www.linkedin.com/company/mhp-a-porsche-company/"));
        firmAdapter.addFirm(new Firm("MLP",
                "Die MLP Gruppe ist der Partner in allen Finanzfragen – für Privatkunden ebenso wie für Firmen und institutionelle Investoren. Ausgangspunkt in allen Bereichen sind die Vorstellungen und Bedürfnisse unserer Kunden. Darauf aufbauend stellen wir ihnen ihre Optionen nachvollziehbar dar, so dass sie selbst die passenden Finanzentscheidungen treffen können. Bei der Umsetzung greifen wir auf die Angebote aller relevanten Anbieter auf dem Markt zurück.\n" +
                        "\n" +
                        "MLP wurde 1971 in einer Studenten-Kneipe in Heidelberg gegründet. Für die MLP Finanzberatung SE sind knapp 2.050 selbstständige Kundenberaterinnen und -berater und gut 2.100 Mitarbeiterinnen und Mitarbeiter in der MLP Gruppe tätig. Zu den Kunden gehören vor allem Mediziner, Wirtschaftswissenschaftler, Ingenieure und Juristen.\n" +
                        "\n" +
                        "Bereits seit 2010 unterstützt MLP das studentische Engagement bei JCNetwork in besonderer Weise auf lokaler und bundesweiter Ebene. Als Goldpartner möchten wir im Rahmen unserer Partnerschaft einen spürbaren Mehrwert bieten, die einzelnen Mitglieder in ihrem Engagement und ihrer persönlichen Entwicklung fördern und uns selbst als attraktives Unternehmen für eine berufliche Karriere ins Spiel bringen. Unsere Kooperation bietet zudem eine gute Möglichkeit, sich gegenseitig kennenzulernen und begeistern zu lassen.\u200B",
                "https://mlp-financify.de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4466fdd3_mlp-links-535x200.png",
                "https://www.xing.com/pages/mlpfinanzberatungse",
                "https://www.linkedin.com/company/mlp/?originalSubdomain=de"));
        firmAdapter.addFirm(new Firm("mm1",
                "mm1 ist die Unternehmensberatung für Connected Business. Mit rund 100 Beraterinnen und Beratern unterstützen wir große Unternehmen bei der digitalen Transformation und gestalten Produkte und Prozesse für eine zunehmend vernetzte Welt. Das Beratungsangebot reicht von der Entwickl\u00ADung von Connected Business Strategien und Geschäftsmodellen bis zur Sicherstellung der effizienten operativen Umsetzung. \n" +
                        "\n" +
                        "Wir bieten aufstrebenden Beraterinnen und Beratern die ideale Umgebung für fachliches und persönliches Wachstum mit steiler Lernkurve. Großartige Projekte, eine offene und unkomplizierte Unternehmenskultur, ein umfangreiches Weiterbildungsprogramm, unternehmerischer Startup-Spirit und eine moderne Arbeitswelt mit freier Wohnortwahl sprechen für deinen Karrierestart bei mm1.",
                "https://mm1.com/en/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4418c591_mm1-links-535x200.png",
                "https://www.xing.com/pages/mm1consulting-managementpartg",
                "https://www.linkedin.com/company/mm1/"));
        firmAdapter.addFirm(new Firm("Mücke Roth & Company",
                "Mücke Roth & Company ist eine inhabergeführte Unternehmensberatung in München. Seit 2003 unterstützen wir Unternehmen, Wachstumspotenziale durch die Digitalisierung zu erschließen. Wir schaffen die Basis für strategische Entscheidungen in Zeiten steigender Komplexität. In über 500 Projekten konnte wir das Kerngeschäft unserer Kunden stärken und neue digitale Geschäftsfelder erschließen. Darüber hinaus betätigen wir uns gemeinsam mit unseren ca. 50 Mitarbeitern als aktive Start-up Investoren.",
                "https://www.muecke-roth.de",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/M%C3%BCckeRoth-2-300x150.png",
                "https://www.xing.com/pages/mueckerothcompany",
                "https://de.linkedin.com/company/muecke-roth-company"));
        firmAdapter.addFirm(new Firm("Nexgen",
                "Als NEXGEN Business Consultants beraten wir Banken, Asset Manager, Börsen und Wertpapierdienstleister mit fachlicher Kompetenz und technischem Know-how.\n" +
                        "\n" +
                        "Wir begleiten unsere Kunden bei allen Herausforderungen rund um die Themen Kapitalmarktregulierung und Wertpapierprozesse. Das Leistungsportfolio reicht dabei von der gemeinsamen Entwicklung von innovativen Geschäfts- und Prozesslösungen bis hin zur Umsetzung.",
                "https://www.nexgenbc.com/",
                "https://www.jcnetwork.de/media/jcnetwork/m_6166a7231b19b5_nexgen-535x200.png",
                "https://www.xing.com/pages/nexgenbusinessconsultantsgmbh",
                "https://www.linkedin.com/company/nexgen-business-consultants/"));
        firmAdapter.addFirm(new Firm("NTT DATA",
                "Wir sind besonders – durch unsere Mitarbeiter: Bei NTT DATA suchen wir Kolleginnen und Kollegen, die neben fachlichen Spitzenleistungen viel Engagement und Teamgeist sowie den Wunsch mitbringen, die Zukunft unserer Kunden und das digitale Zeitalter erfolgreich mitzugestalten.\n" +
                        "\n" +
                        "NTT DATA – ein Teil der NTT Group – ist Trusted Global Innovator von Business- und IT-Lösungen mit Hauptsitz in Tokio. Wir unterstützen unsere Kunden bei ihrer Transformation durch Consulting, Branchenlösungen, Business Process Services, IT-Modernisierung und Managed Services. Mit NTT DATA können Kunden und die Gesellschaft im Allgemeinen selbstbewusst in die digitale Zukunft gehen. Wir setzen uns für den langfristigen Erfolg unserer Kunden ein und kombinieren globale Präsenz mit lokaler Kundenbetreuung in über 50 Ländern.\u200B\u200B\n" +
                        "\n" +
                        "NTT DATA ist ein führender Anbieter von Business- und IT-Lösungen mit über 123.000 Mitarbeitern in über 50 Ländern und einem Umsatz von über 19,5 Milliarden US Dollar.\n" +
                        "\n" +
                        "\u200BNTT DATA ist Teil der NTT-Unternehmensgruppe mit über 300.000 Mitarbeitern in über 88 Ländern und einem Umsatz von über 107 Milliarden US Dollar. Zur NTT Group in Deutschland, Österreich und Schweiz gehören neben NTT DATA die Unternehmen itelligence, NTT DATA Services, NTT Ltd. (hello.global.ntt). Die NTT Ltd. führt die Marken Dimension Data, NTT Communications und NTT Security zusammen.\u200B",
                "https://de.nttdata.com/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d4d6f6d5750ca_nttdata-links-535x200.png",
                "https://www.xing.com/pages/nttdatadeutschland",
                "https://www.linkedin.com/company/ntt-data-deutschland-gmbh/"));
        firmAdapter.addFirm(new Firm("PwC",
                "PricewaterhouseCoopers (PwC)\n" +
                        "\n" +
                        "Unsere Mandanten stehen tagtäglich vor neuen Aufgaben, möchten neue Ideen umsetzen und suchen Rat. Sie erwarten, dass wir sie ganzheitlich betreuen und praxisorientierte Lösungen mit größtmöglichem Nutzen entwickeln. Deshalb setzen wir für jeden Mandanten, ob Global Player, Familienunternehmen oder kommunaler Träger, unser gesamtes Potenzial ein: Erfahrung, Branchenkenntnis, Fachwissen, Qualitätsanspruch, Innovationskraft und die Ressourcen unseres Expertennetzwerks in 157 Ländern. Besonders wichtig ist uns die vertrauensvolle Zusammenarbeit mit unseren Mandanten, denn je besser wir sie kennen und verstehen, umso gezielter können wir sie unterstützen.\n" +
                        "\n" +
                        "PwC. 10.400 engagierte Menschen an 21 Standorten. 1,9 Mrd. Euro Gesamtleistung. Führende Wirtschaftsprüfungs- und Beratungsgesellschaft in Deutschland.",
                "https://www.pwc.de/",
                "https://cdn.jcnetwork.de/images/external_company_logo/pwc_logo_535x200px_zentriert.png",
                "https://www.xing.com/pages/pwcdeutschland",
                "https://www.linkedin.com/company/pwc/"));
        firmAdapter.addFirm(new Firm("zeb",
                "zeb ist die führende Strategie- und Managementberatung für Finanzdienstleister in Europa. Mit europaweit über 1000 Mitarbeitern entwickeln wir nachhaltige Strategien und setzen diese zusammen mit unseren Kunden - Banken, Versicherungen und andere Finanzinstitute – entlang der gesamten Wertschöpfungskette erfolgreich um. Vor allem die Entwicklung, Umsetzung und Implementierung von IT-Strategien und Lösungen im Zeitalter der Digitalisierung haben wir uns auf die Fahne geschrieben. \n" +
                        "\n" +
                        "Egal ob in Münster, Mailand oder Moskau – wir von zeb sprechen überall auf der Welt die gleiche Sprache: Klartext. Ein ehrliches Arbeitsumfeld, verbindliche Aussagen und offene Kommunikation sind dabei Teil unserer Unternehmenskultur und Grundlage für den langfristigen Erfolg von uns und unseren Kunden.",
                "https://zeb-consulting.com/de-DE",
                "https://www.jcnetwork.de/media/jcnetwork/m_5e08ee0d6c5d55_zeb-neu-links-535x200.png",
                "https://www.xing.com/pages/zeb",
                "https://www.linkedin.com/company/zebconsulting/"));

        // Bronze
        firmAdapter.addFirm(new Firm("Amplifon",
                "Amplifon ist der Weltmarktführer für Hörlösungen. Als internationales Unternehmen sind wir auf allen fünf Kontinenten in 28 Ländern vertreten und leben täglich das Ziel, Menschen die Emotionen der Klänge neu entdecken zu lassen - denn unsere Kunden stehen bei uns im Mittelpunkt. Seit 2005 ist die Amplifon Deutschland GmbH mit Sitz in Hamburg auf dem deutschen Markt tätig und betreibt mit mehr als 1.900 Mitarbeitern bundesweit über 550 eigene Fachgeschäfte. In unserer deutschen Zentrale in Hamburg leben wir eine leistungsfördernde und dynamische Unternehmenskultur, in der der persönliche Beitrag im Vordergrund steht. Flache Hierarchien und motivierte Teams bestimmen den Arbeitsalltag und bieten Raum für eine individuelle Karriere.",
                "https://www.amplifon.com/de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_61487cc836ffee_logo-amplifon-535x200.png",
                "https://www.xing.com/pages/amplifondeutschlandgmbh",
                "https://www.linkedin.com/company/amplifon/posts/?feedView=all"));
        firmAdapter.addFirm(new Firm("BDU",
                "Wir vertreten die deutsche Consultingwirtschaft mit ihren bundesweit mehr als 38,1 Mrd. Euro Umsatz und über 219.000 Mitarbeitenden. Unsere Mission: Die ständige Verbesserung dieser für die deutsche Wirtschaft bedeutenden Dienstleistung und Sicherstellung des hohen Qualitätsniveaus unserer Mitgliedsunternehmen: Von der Unternehmensberatung über die Strategieberatung und Sanierungsberatung bis hin zur Personalberatung. Durch Studien, Zertifizierungen, Leitfäden, Networking und Weiterbildung tragen wir dazu bei, dass Unternehmen und Verwaltung in Deutschland immer bestmöglich beraten werden. Darüber hinaus fördern wir  den kollegialen Austausch in verschiedenen Arbeitskreisen, z.B. zu Personal- und Recruitingthemen, aber auch zu Diversity und Nachhaltigkeit.\n" +
                        "\n" +
                        "ANGEBOTENE WORKSHOPS",
                "https://www.bdu.de",
                "https://days.jcnetwork.de/wp-content/uploads/2022/05/22_RGB_mit-Wortmarke-1-1024x512.png",
                "https://www.xing.com/pages/bundesverbanddeutscherunternehmensberaterbdue-v",
                "https://www.linkedin.com/company/bundesverband-deutscher-unternehmensberater-bdu-e-v-/?trk=organization-update_share-update_update-text&originalSubdomain=ms"));
        firmAdapter.addFirm(new Firm("JCNetwork Alumni",
                "Netzwerken auch nach der aktiven Zeit als studentische Beratende.\n" +
                        "\n" +
                        "JCNetwork Alumni ist das Ehemaligen-Netzwerk für Student Consultants. Wir bieten die Gelegenheit, das persönliche und berufliche Netzwerk über das Studium hinaus aufrechtzuerhalten und auszubauen. Zum Start oder während der beruflichen Laufbahn – egal ob im Consulting oder außerhalb – wir helfen bei der Vernetzung mit den richtigen Personen und bieten Impulse zur persönlichen Weiterentwicklung.\n" +
                        "\n" +
                        "Auf den JCNetwork Days könnt ihr uns auf der Firmenkontaktmesse, im Rahmen des Alumni-Programmes am Samstag oder ganz informell auf den Abendveranstaltungen antreffen, um euch mit uns darüber auszutauschen, wie ihr nach eurem Studium weiterhin Teil dieses Netzwerkes bleiben und davon profitieren könnt.\n" +
                        "\n" +
                        "Mehr Informationen zu JCNetwork Alumni auf:\n" +
                        "https://jcnetwork-alumni.de/\n" +
                        "\n" +
                        "Gerne könnt ihr mich auch jederzeit auf den JCNetwork Days ansprechen!",
                "https://jcnetwork-alumni.de/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/JCNetwork-Alumni-300x150.png",
                "",
                "https://www.linkedin.com/company/jcnetwork-alumni"));
        firmAdapter.addFirm(new Firm("Junior Consultant Network Projektmanagement",
                "Junior Consultant Network Projektmanagement ist die gemeinsame Projektgesellschaft der Mitgliedsinitiativen des JCNetwork.\n" +
                        "\n" +
                        "Mit Junior Consultant Network Projektmanagement bieten wir Junior Consultants und Vereinen die Gelegenheit, studentische Beratungsprojekte rechtlich sicher und mit einem deutlich verminderten Verwaltungsaufwand durchzuführen.\n" +
                        "\n" +
                        "Gegenüber potentiellen Klienten agieren wir als Marktplatz für Studentische Unternehmensberatung und können hierdurch attraktive Beratungsprojekte an Vereine und Studierende vermitteln.",
                "https://jcnetwork-projektmanagement.de/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/JCNetwork-Projektmanagement-300x150.png",
                "",
                "https://de.linkedin.com/company/jcnetwork-projektmanagement"));
        firmAdapter.addFirm(new Firm("Karer Consulting",
                "Die Karer Consulting ist eine international tätige Unternehmensberatung mit Standorten in Deutschland und der Schweiz.\n" +
                        "\n" +
                        "Mit über 25 Jahren Erfahrung sind wir ein zuverlässiger Partner, wenn es darum geht, IT-Organisationen zukunftsfähig zu gestalten und bei der Realisierung strategisch wichtiger IT-Projekte zu unterstützen.\n" +
                        "\n" +
                        "Damit tragen unsere Berater*innen täglich dazu bei, den Wertbeitrag der IT bei unseren Kunden zu steigern.",
                "https://www.karer-consulting.com/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/Karer-Consulting-2-300x150.png",
                "https://www.xing.com/pages/karerconsulting\n",
                "https://de.linkedin.com/company/karer-consulting-ag"));
        firmAdapter.addFirm(new Firm("Staufen",
                "In jedem Unternehmen steckt ein noch besseres. Mit dieser Überzeugung berät und qualifiziert die Staufen AG seit 1994 Unternehmen und Mitarbeitende weltweit. Ihren Kunden bietet die Staufen AG eine einzigartige Kombination aus Fachberatung und Organisationsentwicklung. Nur diese Verzahnung von Prozess-Exzellenz und Führungs-Exzellenz sichert im Unternehmen dauerhaft eine nachhaltige Veränderungskultur. Mit passenden Strategien und Methoden setzt die Staufen AG die richtigen Veränderungen schnell in Gang und erzielt messbare Erfolge. Das Beratungsunternehmen unterstützt in bedarfsorientierten Rollen – beratend, coachend und qualifizierend – Führungskräfte und Mitarbeitende dabei, in ihrem Unternehmen eine leistungsfördernde und wertschätzende Unternehmenskultur zu etablieren und auf diese Weise die Wertschöpfung zu steigern. So erhöht die Staufen AG den Wert des Unternehmens. Die internationale Lean Management-Beratung betreut mit 300 Mitarbeitenden weltweit Kunden in den Kompetenzfeldern Operational Excellence, Supply Chain Network Management, Organisationsentwicklung, Leadership Excellence, Digitalisierung und Industrie 4.0. Die Staufen Akademie bietet dazu auch zertifizierte, praxisorientierte und passgenaue Qualifizierungen an. 2022 wurde die Staufen AG zum neunten Mal in Folge als „Beste Unternehmensberater“ ausgezeichnet und im Rahmen der Branchenstudie „Hidden Champions 2022/23“ der Wissenschaftlichen Gesellschaft für Management und Beratung (WGMB) zu Deutschlands bester Lean Management-Beratung gekürt.",
                "https://www.staufen.ag/",
                "https://days.jcnetwork.de/wp-content/uploads/2022/04/STAUFEN-2-300x150.png",
                "https://www.xing.com/pages/staufenag",
                "https://www.linkedin.com/company/staufen-ag"));




//        firmAdapter.addFirm(new Firm("Roland Berger",
//                "Roland Berger ist die einzige Strategieberatung europäischer Herkunft mit einer starken internationalen Präsenz. Als unabhängige Firma, im alleinigen Besitz unserer Partnerinnen und Partner, sind wir mit 50 Büros in allen wichtigen Märkten präsent. Unsere 2400 Mitarbeiterinnen und Mitarbeiter zeichnet eine einzigartige Kombination aus analytischem Denken und empathischer Einstellung aus. Angetrieben von unseren Werten Unternehmergeist, Exzellenz und Empathie sind wir überzeugt davon, dass Wirtschaft und Gesellschaft ein neues, nachhaltiges Paradigma benötigen, das den gesamten Wertschöpfungskreislauf im Blick hat. Durch die Arbeit in kompetenzübergreifenden Teams über alle relevanten Branchen und Geschäftsfunktionen hinweg bietet Roland Berger weltweit die beste Expertise, um die tiefgreifenden Herausforderungen unserer Zeit heute und morgen erfolgreich zu meistern.",
//                "https://www.rolandberger.com/de/",
//                "https://www.jcnetwork.de/media/jcnetwork/m_61041be526a295_roland-berger-535x200.png",
//                "https://www.xing.com/pages/rolandberger",
//                "https://www.linkedin.com/company/rolandberger/"));
//        firmAdapter.addFirm(new Firm("DB Management Consulting",
//                "DB Management Consulting ist die interne Topmanagement-Beratung der Deutschen Bahn. Zusammen mit unseren Kunden entwickeln wir konzernweit wegweisende Konzepte für den DB-Erfolg und gestalten so die Zukunft der DB wesentlich mit.\n" +
//                        "\n" +
//                        "So vielfältig die Märkte, Leistungen, Technologien und Aufgaben der Deutschen Bahn sind, so bunt sind auch unsere Projektthemen. Von der Digitalisierung der Kundenschnittstelle über die Entwicklung von Strategien zur nachhaltigen Steigerung der Produkt- und Servicequalität bis hin zur Einführung eines einheitlichen Produktionssystems in den Werken – die Bandbreite unserer Problemstellungen ist breit.\n" +
//                        "\n" +
//                        "Ein Einstieg bei DB MC bietet die Möglichkeit, in nächster Nähe zum Konzernvorstand zu arbeiten, den DB-Konzern in seiner Vielfalt, Komplexität und Dynamik aus der Innenperspektive kennenzulernen und die Zukunft der Mobilität und Logistik mitzugestalten. Regelmäßiges Feedback und ein hochwertiges Trainings- und Weiterbildungsangebot legen bei uns den Grundstein für die optimale fachliche und persönliche Entwicklung jedes Einzelnen.",
//                "https://management-consulting.deutschebahn.com/dbmc",
//                "https://cdn.jcnetwork.de/images/external_company_logo/db_logo_535x200px_zentriert.png",
//                "https://www.xing.com/pages/deutschebahn",
//                "https://www.linkedin.com/company/deutsche-bahn-management-consulting/"));
//        firmAdapter.addFirm(new Firm("Campus Consult",
//                "Wir, die Campus Consult Projektmanagement GmbH, haben es uns seit unserer Gründung 1997 zur Aufgabe gemacht, junge Talente und renommierte Unternehmen zusammenzubringen. Unser Ziel ist es, Studenten, aber auch Absolventen, die Möglichkeiten zu geben schon während bzw. nach Abschluss ihres Studiums praktische Berufserfahrung zu sammeln und hierbei vielleicht schon ihren zukünftigen Arbeitgeber kennenzulernen. Deshalb bieten wir ihnen spannende Projekte aus den Bereichen Projekt- und Prozessmanagement sowie aus dem IT-Bereich an.\n" +
//                        "\n" +
//                        "Die Benefits, welche die Studenten durch die Zusammenarbeit mit uns haben sind bspw. eine praktische sowie persönliche Aus- und Weiterbildung, der Aufbau eines persönlichen Karrierenetzwerkes und ein Erfahrungsaustausch mit den jeweiligen Unternehmen und deren Mitarbeitern. Zudem können sie sich selbst ausprobieren und in verschiedene Branchen, Abteilungen und Bereiche schnuppern.\n" +
//                        "\n" +
//                        "Seit mehr als 20 Jahren betreuen wir nun erfolgreich Studenten und Absolventen auf ihrem Karriereweg. Diese Erfahrung nutzen wir, um auch in Zukunft das bestmögliche Angebot zur Förderung eurer Karriere anbieten zu können.",
//                "https://campus-consult.de/",
//                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4018c583_campusconsult-links-535x200.png",
//                "https://www.xing.com/pages/campusconsultprojektmanagementgmbh",
//                "https://www.linkedin.com/company/campus-consult-projektmanagement-gmbh/"));
//        firmAdapter.addFirm(new Firm("Moysies & Partner",
//                "Moysies & Partner ist eine junge, dynamische IT- und Managementberatung für Behörden, Gesundheitswirtschaft und Verbände im deutschsprachigen Raum mit klarem Branchenfokus. In unseren drei Geschäftsbereichen – Gesundheitswesen, Arbeit & Soziales sowie Digitale Verwaltung – erarbeiten wir gemeinsam mit unseren Auftraggebern neue Strategien, Konzepte und Lösungen für Digitalisierungsvorhaben, begleiten die Umsetzung und übernehmen vielfältige Aufgaben im Projektmanagement. Unsere Firmenwerte Verantwortung, Verbindlichkeit, Teamgeist und Kreativität prägen dabei unser Miteinander, sowohl bei der Arbeit mit unseren Kunden als auch im eigenen Team.\n" +
//                        "\n" +
//                        "Uns reizen die enormen Ambivalenzen im Public Sector. Und zwar jeden Tag aufs Neue! Die Public Sector-Beratung stellt besondere Herausforderung an Beratungsunternehmen: Die öffentliche Verwaltung ist nicht singulär auf den Kunden ausgerichtet, sondern obliegt einer Vielzahl von Zielsystemen; verlässliche Wettbewerbspreise gibt es in der Regel nicht, dafür umso mehr Regularien. Wir unterstützen unsere Auftraggeber dabei trotz alledem gute und demokratisch legitime Entscheidungen treffen und umsetzen zu können. Und handlungsfähig zu bleiben.\n" +
//                        "\n" +
//                        "Dazu braucht es mutige und lösungsorientierte Unternehmer/innen. Wirtschaft kann doch jeder! Wir suchen die besten Unternehmer/innen, um Digitalisierungs- und Transformationsprozesse im Public Sector voranzubringen.",
//                "https://moysies.de/",
//                "https://cdn.jcnetwork.de/images/external_company_logo/Moysies&Partner_logo_535x200px_zentriert.png",
//                "https://www.xing.com/pages/moysies-partnerit-undmanagementberatung",
//                "https://www.linkedin.com/company/moysies-partner-it-und-managementberatung/"));



        // Add cube out page transformer to view pager
        firmViewPager.setPageTransformer(new CubeOutTransformer());

        // Add adapter to viewpager
        firmViewPager.setAdapter(firmAdapter);
    }

}
