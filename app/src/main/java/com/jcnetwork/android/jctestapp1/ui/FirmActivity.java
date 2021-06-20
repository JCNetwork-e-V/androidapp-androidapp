package com.jcnetwork.android.jctestapp1.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.FirmAdapter;
import com.jcnetwork.android.jctestapp1.models.Firm;
import com.jcnetwork.android.jctestapp1.transformations.CubeOutTransformer;

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
        firmAdapter.addFirm(new Firm("UMS",
                "Energizing people and organizations. Seit 1984. Weltweit.\n" +
                        "\n" +
                        "Die Strategieumsetzungsberatung - Partnerschaftlich, authentisch und begeisternd und immer mit klarem Blick auf die Erfolgsfaktoren einer Veränderung. Denn am Ende werden Sie uns daran messen, ob Sie Ihre individuellen Ziele nachhaltig erreicht haben. Nutzen Sie unsere Kompetenz und über 35-jährige Erfahrung rund um Leadership, Innovation, Digitalisierung oder Prozessoptimierung. Von der Strategieentwicklung bis zur konkreten Umsetzung von Maßnahmen begleiten wir Sie branchenübergreifend und weltweit.\n" +
                        "\n" +
                        "Der Beratungsansatz - im Fokus steht die Entwicklung organisationaler Fähigkeiten. Basis dafür sind, neben einer überzeugenden Strategie, individuelle Fähigkeiten, optimierte Prozesse und Strukturen sowie aktives Kulturmanagement.\n" +
                        "\n" +
                        "Durch die Zusammenarbeit mit UMS lernen Ihre Mitarbeiterinnen und Mitarbeiter - ganz im Sinne unseres Claims \"Enabling Success\" -, Veränderungsprozesse selbstständig zu gestalten und zu managen.",
                "https://ums-gmbh.com/en/",
                "https://cdn.jcnetwork.de/images/external_company_logo/ums_logo_535x200px_zentriert.png",
                "",
                "https://www.linkedin.com/company/ums-consulting-training-gmbh/"));
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
        firmAdapter.addFirm(new Firm("MHP",
                "MHP – Excellence in Management- and IT-Consulting\u200B\n" +
                        "\n" +
                        "MHP gehört als Porsche-Tochterunternehmen und Prozesslieferant mit dem Beratungsansatz der Symbiose aus Management- und IT-Beratung sowie als Automotive-Experte zu den führenden Beratungen weltweit.\n" +
                        "\n" +
                        "Seit der Gründung im Mai 1996 verzeichnet unser Unternehmen ein kontinuierliches Wachstum. Unsere Kunden beraten und betreuen wir in den Leistungsbereichen Management Consulting, System Integration, Managed Services und Business Solutions von weltweit 13 Standorten aus.\n" +
                        "\n" +
                        "Mit unserem Beratungsansatz – Symbiose aus Management- und IT-Beratung – und als Branchenexperte bieten wir über 300 namhaften Kunden, neben umfassender IT-Kompetenz, auch tiefgehendes Prozess-Know-How und sind somit DER Prozesslieferant. Dabei fokussieren wir uns auf die Automobilbranche und Fertigungswirtschaft und übertragen strategische Innovationen auch auf andere Branchen.",
                "https://www.mhp.com/de/home",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4346fdf6_mhp-links-535x200.png",
                "https://www.xing.com/pages/mieschkehofmannundpartner",
                "https://www.linkedin.com/company/mhp-a-porsche-company/"));
        firmAdapter.addFirm(new Firm("MLP",
                "MLP ist dein Gesprächspartner in allen Finanzfragen - von Geldanlage über Altersvorsorge bis zu Versicherungen. Gemeinsam besprechen wir deine Vorstellungen und zeigen dir, welche Möglichkeiten du hast. Danach entscheidest du, was für dich passt und was nicht. Statt auf eigene Produkte greifen wir zur Umsetzung auf die Angebote aller relevanten Anbieter zurück. Zudem bieten wir dir eigene Bankprodukte wie Konten und Kreditkarten an.\u200B\n" +
                        "\n" +
                        "MLP wurde 1971 in einer Studenten-Kneipe in Heidelberg gegründet. Für MLP arbeiten rund 1.900 Berater und gut 1.700 Mitarbeiter.\n" +
                        "\n" +
                        "\n" +
                        "\u200BMLP in Zahlen\n" +
                        "\n" +
                        "\u200Büber 535.000 Privat- und ca. 20.000 Firmenkunden\n" +
                        "\u200BBetreuung von 36,1 MRD. Euro Kundenvermögen\n" +
                        "\u200BRund 1.900 Berater und 1.700 Mitarbeiter\n" +
                        "\u200BBundesweit ca. 65 Hochschulteams\n" +
                        "\u200Bca. 130 Geschäftsstellen\u200B\u200B\n" +
                        "\n" +
                        "Bereits seit 2010 unterstützen wir das studentisches Engagement bei JCNetwork in besonderer Weise auf lokaler und bundesweiter Ebene. Als Goldpartner möchten wir im Rahmen unserer Partnerschaft einen spürbaren Mehrwert bieten, die einzelnen Mitglieder in ihrem Engagement und ihrer persönlichen Entwicklung fördern und uns selbst als attraktives Unternehmen für eine berufliche Karriere ins Spiel bringen. Unsere Kooperation bietet zudem eine gute Möglichkeit, sich gegenseitig kennenzulernen und begeistern zu lassen.\u200B",
                "https://mlp-financify.de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4466fdd3_mlp-links-535x200.png",
                "https://www.xing.com/pages/mlpfinanzberatungse",
                "https://www.linkedin.com/company/mlp/?originalSubdomain=de"));
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
        // Silver partners from here
        firmAdapter.addFirm(new Firm("BwConsulting",
                "Die BwConsulting ist ein Beratungsunternehmen mit einem sehr speziellen Fokus. Als Inhouse-Gesellschaft beraten wir die strategischen Projekte des Bundesministeriums der Verteidigung (BMVg) und der Bundeswehr. Im Auftrag der ministeriellen Leitung sind wir damit treibende Kraft für die kontinuierliche Weiterentwicklung des gesamten Ressorts. Unsere Beraterinnen und Berater bringen ihre methodische und fachliche Expertise dabei unmittelbar in Projekte mit besonderer gesellschafts- und sicherheitspolitischer Relevanz ein. Unser Fokus liegt hierbei auf den drei Beratungsfeldern Strategie & Steuerung, Prozesse & Organisation und Projektmanagementberatung.\u200B\u200B",
                "https://www.bwconsulting.de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc3f6cd21c_bwconsulting-links-535x200.png",
                "https://www.xing.com/pages/bwconsulting-dieinhouse-beratungderbundeswehr",
                "https://www.linkedin.com/company/bwconsulting-the-inhouse-consultancy-of-bundeswehr/"));
        firmAdapter.addFirm(new Firm("mm1",
                "mm1 ist die Unternehmensberatung für Connected Business. Mit rund 100 Beraterinnen und Beratern unterstützen wir große Unternehmen bei der digitalen Transformation und gestalten Produkte und Prozesse für eine zunehmend vernetzte Welt. Das Beratungsangebot reicht von der Entwickl\u00ADung von Connected Business Strategien und Geschäftsmodellen bis zur Sicherstellung der effizienten operativen Umsetzung. \n" +
                        "\n" +
                        "Wir bieten aufstrebenden Beraterinnen und Beratern die ideale Umgebung für fachliches und persönliches Wachstum mit steiler Lernkurve. Großartige Projekte, eine offene und unkomplizierte Unternehmenskultur, ein umfangreiches Weiterbildungsprogramm, unternehmerischer Startup-Spirit und eine moderne Arbeitswelt mit freier Wohnortwahl sprechen für deinen Karrierestart bei mm1.",
                "https://mm1.com/en/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4418c591_mm1-links-535x200.png",
                "https://www.xing.com/pages/mm1consulting-managementpartg",
                "https://www.linkedin.com/company/mm1/"));
        firmAdapter.addFirm(new Firm("MGRP",
                "Bei MGRP (Management Group Dr. Röser & Partner) zu arbeiten heißt, sich am Puls der Zeit zu verwirklichen. Unsere Kunden kommen aus den unterschiedlichsten Branchen: von der Automobil- und Zulieferindustrie über soziale Organisationen und Technologieschmieden bis hin zu Versicherungsfirmen. Für jedes dieser Unternehmen analysieren und erschließen wir die Potenziale digitaler Technologien – und sichern so ihre Zukunftsfähigkeit.  \n" +
                        "\n" +
                        "Als Topmanagementberatung mit Start-up-Charakter sind wir anpassungsfähig, setzen Qualität vor Quantität und machen weiter, wo andere aufgegeben haben. Warum? Weil bei uns aufgeschlossene Menschen arbeiten, die wissen, wie man Veränderung erfolgreich und nachhaltig gestaltet. Dabei legen wir Wert auf flache Hierarchien, individuelle Freiräume und ein wertschätzendes Miteinander und gemeinsames Wachsen. Jeder ist bei MGRP willkommen, der wie wir auf Offenheit und Initiative setzt. Vom Werkstudenten bis zum Senior Professional – ob Qualitätsfanatiker, Charakterkopf, Zahlenmensch oder Überzeugungstäter.",
                "https://mg-rp.com/de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_60561590326405_mgrp-535x200.png",
                "https://www.xing.com/pages/mgrp",
                "https://www.linkedin.com/company/mg-rp/"));
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
        // Bronze from here
        firmAdapter.addFirm(new Firm("DB Management Consulting",
                "DB Management Consulting ist die interne Topmanagement-Beratung der Deutschen Bahn. Zusammen mit unseren Kunden entwickeln wir konzernweit wegweisende Konzepte für den DB-Erfolg und gestalten so die Zukunft der DB wesentlich mit.\n" +
                        "\n" +
                        "So vielfältig die Märkte, Leistungen, Technologien und Aufgaben der Deutschen Bahn sind, so bunt sind auch unsere Projektthemen. Von der Digitalisierung der Kundenschnittstelle über die Entwicklung von Strategien zur nachhaltigen Steigerung der Produkt- und Servicequalität bis hin zur Einführung eines einheitlichen Produktionssystems in den Werken – die Bandbreite unserer Problemstellungen ist breit.\n" +
                        "\n" +
                        "Ein Einstieg bei DB MC bietet die Möglichkeit, in nächster Nähe zum Konzernvorstand zu arbeiten, den DB-Konzern in seiner Vielfalt, Komplexität und Dynamik aus der Innenperspektive kennenzulernen und die Zukunft der Mobilität und Logistik mitzugestalten. Regelmäßiges Feedback und ein hochwertiges Trainings- und Weiterbildungsangebot legen bei uns den Grundstein für die optimale fachliche und persönliche Entwicklung jedes Einzelnen.",
                "https://management-consulting.deutschebahn.com/dbmc",
                "https://cdn.jcnetwork.de/images/external_company_logo/db_logo_535x200px_zentriert.png",
                "https://www.xing.com/pages/deutschebahn",
                "https://www.linkedin.com/company/deutsche-bahn-management-consulting/"));
        firmAdapter.addFirm(new Firm("Campus Consult",
                "Wir, die Campus Consult Projektmanagement GmbH, haben es uns seit unserer Gründung 1997 zur Aufgabe gemacht, junge Talente und renommierte Unternehmen zusammenzubringen. Unser Ziel ist es, Studenten, aber auch Absolventen, die Möglichkeiten zu geben schon während bzw. nach Abschluss ihres Studiums praktische Berufserfahrung zu sammeln und hierbei vielleicht schon ihren zukünftigen Arbeitgeber kennenzulernen. Deshalb bieten wir ihnen spannende Projekte aus den Bereichen Projekt- und Prozessmanagement sowie aus dem IT-Bereich an.\n" +
                        "\n" +
                        "Die Benefits, welche die Studenten durch die Zusammenarbeit mit uns haben sind bspw. eine praktische sowie persönliche Aus- und Weiterbildung, der Aufbau eines persönlichen Karrierenetzwerkes und ein Erfahrungsaustausch mit den jeweiligen Unternehmen und deren Mitarbeitern. Zudem können sie sich selbst ausprobieren und in verschiedene Branchen, Abteilungen und Bereiche schnuppern.\n" +
                        "\n" +
                        "Seit mehr als 20 Jahren betreuen wir nun erfolgreich Studenten und Absolventen auf ihrem Karriereweg. Diese Erfahrung nutzen wir, um auch in Zukunft das bestmögliche Angebot zur Förderung eurer Karriere anbieten zu können.",
                "https://campus-consult.de/",
                "https://www.jcnetwork.de/media/jcnetwork/m_5d42bc4018c583_campusconsult-links-535x200.png",
                "https://www.xing.com/pages/campusconsultprojektmanagementgmbh",
                "https://www.linkedin.com/company/campus-consult-projektmanagement-gmbh/"));
        firmAdapter.addFirm(new Firm("Moysies & Partner",
                "Moysies & Partner ist eine junge, dynamische IT- und Managementberatung für Behörden, Gesundheitswirtschaft und Verbände im deutschsprachigen Raum mit klarem Branchenfokus. In unseren drei Geschäftsbereichen – Gesundheitswesen, Arbeit & Soziales sowie Digitale Verwaltung – erarbeiten wir gemeinsam mit unseren Auftraggebern neue Strategien, Konzepte und Lösungen für Digitalisierungsvorhaben, begleiten die Umsetzung und übernehmen vielfältige Aufgaben im Projektmanagement. Unsere Firmenwerte Verantwortung, Verbindlichkeit, Teamgeist und Kreativität prägen dabei unser Miteinander, sowohl bei der Arbeit mit unseren Kunden als auch im eigenen Team.\n" +
                        "\n" +
                        "Uns reizen die enormen Ambivalenzen im Public Sector. Und zwar jeden Tag aufs Neue! Die Public Sector-Beratung stellt besondere Herausforderung an Beratungsunternehmen: Die öffentliche Verwaltung ist nicht singulär auf den Kunden ausgerichtet, sondern obliegt einer Vielzahl von Zielsystemen; verlässliche Wettbewerbspreise gibt es in der Regel nicht, dafür umso mehr Regularien. Wir unterstützen unsere Auftraggeber dabei trotz alledem gute und demokratisch legitime Entscheidungen treffen und umsetzen zu können. Und handlungsfähig zu bleiben.\n" +
                        "\n" +
                        "Dazu braucht es mutige und lösungsorientierte Unternehmer/innen. Wirtschaft kann doch jeder! Wir suchen die besten Unternehmer/innen, um Digitalisierungs- und Transformationsprozesse im Public Sector voranzubringen.",
                "https://moysies.de/",
                "https://cdn.jcnetwork.de/images/external_company_logo/Moysies&Partner_logo_535x200px_zentriert.png",
                "https://www.xing.com/pages/moysies-partnerit-undmanagementberatung",
                "https://www.linkedin.com/company/moysies-partner-it-und-managementberatung/"));
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

        // Add cube out page transformer to view pager
        firmViewPager.setPageTransformer(new CubeOutTransformer());

        // Add adapter to viewpager
        firmViewPager.setAdapter(firmAdapter);
    }

}
