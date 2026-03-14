package de.agiehl.school.math;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class WordProblemSection implements WorksheetSection {

    private final Random random = new Random();
    private final List<Supplier<String>> problemGenerators = new ArrayList<>();

    public WordProblemSection() {
        initializeGenerators();
    }

    @Override
    public void addTo(Document document) {
        document.add(new Paragraph("3. Sachrechnen").setBold().setFontSize(14));

        List<String> problems = new ArrayList<>();
        // Since there are 35 distinct generators and we only need 10,
        // we can shuffle the generators and pick the first 10.
        // This guarantees no duplicate problem types.
        List<Supplier<String>> selectedGenerators = new ArrayList<>(problemGenerators);
        Collections.shuffle(selectedGenerators);

        for (int i = 0; i < 10; i++) {
            problems.add(selectedGenerators.get(i).get());
        }

        for (int i = 0; i < 10; i++) {
            document.add(new Paragraph((i + 1) + ". " + problems.get(i)));
            document.add(new Paragraph("Rechnung: __________________________________________________"));
            document.add(new Paragraph("Antwort: ___________________________________________________"));
            document.add(new Paragraph("\n"));
        }
    }

    private void initializeGenerators() {
        problemGenerators.add(this::schoolBagProblem);
        problemGenerators.add(this::shoppingProblem);
        problemGenerators.add(this::packageProblem);
        problemGenerators.add(this::sweetsProblem);
        problemGenerators.add(this::boxWeightProblem);
        problemGenerators.add(this::bakerProblem);
        problemGenerators.add(this::fruitWeightProblem);
        problemGenerators.add(this::truckLoadProblem);
        problemGenerators.add(this::animalWeightProblem);
        problemGenerators.add(this::cookieTinProblem);
        problemGenerators.add(this::readingProblem);
        problemGenerators.add(this::penCostProblem);
        problemGenerators.add(this::movieTimeProblem);
        problemGenerators.add(this::travelDistanceProblem);
        problemGenerators.add(this::milkDistributionProblem);
        problemGenerators.add(this::rectanglePerimeterProblem);
        problemGenerators.add(this::classAttendanceProblem);
        problemGenerators.add(this::savingsProblem);
        problemGenerators.add(this::eggCartonProblem);
        problemGenerators.add(this::ageProblem);
        problemGenerators.add(this::busPassengerProblem);
        problemGenerators.add(this::libraryBooksProblem);
        problemGenerators.add(this::juiceGlassesProblem);
        problemGenerators.add(this::walkingStepsProblem);
        problemGenerators.add(this::lotteryWinProblem);
        problemGenerators.add(this::schoolDurationProblem);
        problemGenerators.add(this::stickerProblem);
        problemGenerators.add(this::chairRowsProblem);
        problemGenerators.add(this::juiceLeftoverProblem);
        problemGenerators.add(this::toySavingsProblem);
        problemGenerators.add(this::bookPagesProblem);
        problemGenerators.add(this::balloonDistributionProblem);
        problemGenerators.add(this::runningDistanceProblem);
        problemGenerators.add(this::appleBagProblem);
        problemGenerators.add(this::cinemaCostProblem);
    }

    private String schoolBagProblem() {
        int ranzen = (random.nextInt(5) + 5) * 100;
        int buch = (random.nextInt(4) + 2) * 100;
        int anzahl = random.nextInt(3) + 2;
        return "Ein leerer Schulranzen wiegt " + ranzen + " g. Ein Kind packt " + anzahl + " Bücher ein. Jedes Buch wiegt " + buch + " g.\nFrage: Wie schwer ist der Ranzen jetzt insgesamt?";
    }

    private String shoppingProblem() {
        int mehl = (random.nextInt(5) + 1) * 100;
        int zucker = (random.nextInt(5) + 1) * 100;
        return "Mama kauft " + mehl + " g Mehl und " + zucker + " g Zucker. Die leere Tasche wiegt 200 g.\nFrage: Wie schwer ist die volle Tasche?";
    }

    private String packageProblem() {
        int inhalt = (random.nextInt(8) + 2) * 100;
        int verpackung = (random.nextInt(3) + 1) * 100;
        return "Ein Paket wiegt insgesamt " + (inhalt + verpackung) + " g. Die Verpackung wiegt " + verpackung + " g.\nFrage: Wie schwer ist der Inhalt?";
    }

    private String sweetsProblem() {
        int gesamt = (random.nextInt(8) + 2) * 100;
        int personen = random.nextInt(3) + 2;
        while (gesamt % personen != 0) {
            gesamt = (random.nextInt(8) + 2) * 100;
        }
        return "Eine Tüte Bonbons wiegt " + gesamt + " g. Sie wird gerecht auf " + personen + " Kinder aufgeteilt.\nFrage: Wie viel Gramm bekommt jedes Kind?";
    }

    private String boxWeightProblem() {
        int a = (random.nextInt(8) + 2) * 100;
        int b = (random.nextInt(8) + 2) * 100;
        return "Kiste A wiegt " + a + " g. Kiste B wiegt " + b + " g.\nFrage: Wie viel Gramm ist der Unterschied?";
    }

    private String bakerProblem() {
        int teig = (random.nextInt(8) + 2) * 100;
        int broetchenAnzahl = random.nextInt(8) + 2;
        while (teig % broetchenAnzahl != 0) {
            teig = (random.nextInt(8) + 2) * 100;
        }
        return "Ein Bäcker hat " + teig + " g Teig. Er macht daraus " + broetchenAnzahl + " gleiche Brötchen.\nFrage: Wie viel wiegt ein Brötchen?";
    }

    private String fruitWeightProblem() {
        int apfel = (random.nextInt(10) + 10) * 10;
        int banane = (random.nextInt(10) + 10) * 10;
        return "Ein Apfel wiegt " + apfel + " g. Eine Banane wiegt " + banane + " g.\nFrage: Wie viel wiegen sie zusammen?";
    }

    private String truckLoadProblem() {
        int ladung = (random.nextInt(10) + 5) * 100;
        int abladung = (random.nextInt(ladung / 100 - 1) + 1) * 100;
        return "Ein LKW ist mit " + ladung + " kg Kartoffeln beladen. An der ersten Haltestelle lädt er " + abladung + " kg ab.\nFrage: Wie viel kg Ladung hat der LKW noch?";
    }

    private String animalWeightProblem() {
        int hund = (random.nextInt(20) + 5);
        int katze = (random.nextInt(5) + 2) * 1000;
        return "Ein Hund wiegt " + hund + " kg. Eine Katze wiegt " + katze + " g.\nFrage: Wie groß ist der Gewichtsunterschied in Gramm?";
    }

    private String cookieTinProblem() {
        int kekse = (random.nextInt(5) + 2) * 100;
        int dose = (random.nextInt(2) + 1) * 100;
        return "Oma backt " + kekse + " g Kekse. Sie füllt sie in eine Dose, die " + dose + " g wiegt.\nFrage: Wie schwer ist die volle Dose?";
    }

    private String readingProblem() {
        int seiten = (random.nextInt(5) + 3) * 10;
        int tage = random.nextInt(4) + 3;
        return "Ein Buch hat " + (seiten * tage) + " Seiten. Tim liest jeden Tag " + seiten + " Seiten.\nFrage: Wie viele Tage braucht er für das ganze Buch?";
    }

    private String penCostProblem() {
        int preis = (random.nextInt(8) + 2) * 10;
        int anzahlStifte = random.nextInt(5) + 2;
        return "Ein Stift kostet " + preis + " Cent. Lisa kauft " + anzahlStifte + " Stifte.\nFrage: Wie viel muss sie bezahlen?";
    }

    private String movieTimeProblem() {
        int start = (random.nextInt(5) + 8);
        int dauer = random.nextInt(3) + 1;
        return "Der Film beginnt um " + start + ":00 Uhr und dauert " + dauer + " Stunden.\nFrage: Wann ist der Film zu Ende?";
    }

    private String travelDistanceProblem() {
        int strecke = (random.nextInt(8) + 2) * 100;
        int gefahren = (random.nextInt(strecke / 100 - 1) + 1) * 100;
        return "Die Strecke nach Berlin ist " + strecke + " km lang. Papa ist schon " + gefahren + " km gefahren.\nFrage: Wie weit müssen sie noch fahren?";
    }

    private String milkDistributionProblem() {
        int milch = (random.nextInt(5) + 1) * 100;
        int becher = random.nextInt(4) + 2;
        return "In einer Kanne sind " + (milch * becher) + " ml Milch. Sie wird auf " + becher + " Becher verteilt.\nFrage: Wie viel Milch ist in jedem Becher?";
    }

    private String rectanglePerimeterProblem() {
        int breite = (random.nextInt(5) + 2) * 10;
        int laenge = breite * 2;
        return "Ein Rechteck ist " + laenge + " cm lang und " + breite + " cm breit.\nFrage: Wie groß ist der Umfang?";
    }

    private String classAttendanceProblem() {
        int klasse = random.nextInt(10) + 20;
        int krank = random.nextInt(5) + 1;
        return "In der Klasse 3a sind " + klasse + " Kinder. Heute sind " + krank + " Kinder krank.\nFrage: Wie viele Kinder sind in der Schule?";
    }

    private String savingsProblem() {
        int taschengeld = (random.nextInt(5) + 2) * 5;
        int sparen = random.nextInt(3) + 2;
        return "Tom bekommt " + taschengeld + " Euro Taschengeld im Monat. Er spart " + sparen + " Monate lang.\nFrage: Wie viel Geld hat er gespart?";
    }

    private String eggCartonProblem() {
        int eier = (random.nextInt(5) + 2) * 6;
        return "Oma kauft " + eier + " Eier. In eine Schachtel passen 6 Eier.\nFrage: Wie viele Schachteln braucht sie?";
    }

    private String ageProblem() {
        int alter = random.nextInt(5) + 8;
        int opa = alter * 7;
        return "Max ist " + alter + " Jahre alt. Sein Opa ist 7 mal so alt.\nFrage: Wie alt ist der Opa?";
    }

    private String busPassengerProblem() {
        int bus = random.nextInt(20) + 30;
        int aussteigen = random.nextInt(10) + 5;
        int einsteigen = random.nextInt(10) + 5;
        return "Im Bus sitzen " + bus + " Leute. An der Haltestelle steigen " + aussteigen + " aus und " + einsteigen + " ein.\nFrage: Wie viele Leute sind jetzt im Bus?";
    }

    private String libraryBooksProblem() {
        int regal = random.nextInt(4) + 3;
        int buecherProRegal = (random.nextInt(5) + 5) * 10;
        return "In der Bücherei gibt es " + regal + " Regale. In jedem Regal stehen " + buecherProRegal + " Bücher.\nFrage: Wie viele Bücher sind es insgesamt?";
    }

    private String juiceGlassesProblem() {
        int saft = (random.nextInt(5) + 5) * 100;
        int glas = 200;
        return "In einer Flasche sind " + saft + " ml Saft. Ein Glas fasst " + glas + " ml.\nFrage: Wie viele Gläser kann man füllen?";
    }

    private String walkingStepsProblem() {
        int schritte = (random.nextInt(5) + 5) * 100;
        int tageLaufen = random.nextInt(5) + 3;
        return "Lisa läuft jeden Tag " + schritte + " Schritte. Sie macht das " + tageLaufen + " Tage lang.\nFrage: Wie viele Schritte ist sie gelaufen?";
    }

    private String lotteryWinProblem() {
        int gewinn = (random.nextInt(8) + 2) * 100;
        int gewinner = random.nextInt(3) + 2;
        while (gewinn % gewinner != 0) {
            gewinn = (random.nextInt(8) + 2) * 100;
        }
        return "Im Lotto gewinnen 3 Freunde zusammen " + gewinn + " Euro. Sie teilen gerecht.\nFrage: Wie viel bekommt jeder?";
    }

    private String schoolDurationProblem() {
        int startH = random.nextInt(12) + 1;
        int endH = startH + random.nextInt(3) + 1;
        return "Der Unterricht beginnt um " + startH + ":00 Uhr und endet um " + endH + ":00 Uhr.\nFrage: Wie viele Stunden dauert der Unterricht?";
    }

    private String stickerProblem() {
        int totalStickers = (random.nextInt(5) + 2) * 10;
        int lostStickers = random.nextInt(5) + 1;
        return "Tim hat " + totalStickers + " Sticker. Er verliert " + lostStickers + " Sticker auf dem Schulweg.\nFrage: Wie viele Sticker hat er noch?";
    }

    private String chairRowsProblem() {
        int rows = random.nextInt(4) + 3;
        int chairsPerRow = random.nextInt(5) + 4;
        return "In der Aula stehen " + rows + " Reihen Stühle. In jeder Reihe stehen " + chairsPerRow + " Stühle.\nFrage: Wie viele Stühle gibt es insgesamt?";
    }

    private String juiceLeftoverProblem() {
        int totalJuice = (random.nextInt(5) + 2) * 1000; // ml
        int drunk = (random.nextInt(5) + 1) * 100;
        return "In einem Krug sind " + totalJuice + " ml Saft. Anna trinkt " + drunk + " ml.\nFrage: Wie viel Saft ist noch übrig?";
    }

    private String toySavingsProblem() {
        int toyPrice = (random.nextInt(5) + 2) * 5;
        int savedMoney = random.nextInt(5) + 5;
        return "Ein Spielzeug kostet " + toyPrice + " Euro. Ben hat schon " + savedMoney + " Euro gespart.\nFrage: Wie viel Geld fehlt ihm noch?";
    }

    private String bookPagesProblem() {
        int totalPages = (random.nextInt(5) + 5) * 10;
        int readPages = (random.nextInt(3) + 1) * 10;
        return "Das Buch hat " + totalPages + " Seiten. Marie hat schon " + readPages + " Seiten gelesen.\nFrage: Wie viele Seiten muss sie noch lesen?";
    }

    private String balloonDistributionProblem() {
        int balloons = (random.nextInt(5) + 2) * 3;
        return "Auf dem Fest werden " + balloons + " Luftballons verteilt. Jedes Kind bekommt 3 Ballons.\nFrage: Wie viele Kinder bekommen Ballons?";
    }

    private String runningDistanceProblem() {
        int lengthField = (random.nextInt(5) + 2) * 10; // m
        int rounds = random.nextInt(3) + 2;
        return "Der Sportplatz ist " + lengthField + " m lang. Paul rennt " + rounds + " mal die Länge entlang.\nFrage: Wie viele Meter ist er gelaufen?";
    }

    private String appleBagProblem() {
        int totalApples = (random.nextInt(5) + 2) * 4;
        return "Opa erntet " + totalApples + " Äpfel. Er packt immer 4 Äpfel in eine Tüte.\nFrage: Wie viele Tüten füllt er?";
    }

    private String cinemaCostProblem() {
        int cinemaTicket = random.nextInt(5) + 5;
        int popcorn = random.nextInt(3) + 3;
        return "Eine Kinokarte kostet " + cinemaTicket + " Euro. Das Popcorn kostet " + popcorn + " Euro.\nFrage: Wie viel kostet der Kinobesuch insgesamt?";
    }
}
