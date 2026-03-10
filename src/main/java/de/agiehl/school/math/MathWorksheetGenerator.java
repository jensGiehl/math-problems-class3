package de.agiehl.school.math;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates a math worksheet in PDF format.
 */
public class MathWorksheetGenerator {

    private static final Random random = new Random();

    /**
     * Main method to execute the generator.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        var dest = "MathWorksheet.pdf";
        try {
            createPdf(dest);
            System.out.println("PDF created: " + new File(dest).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the PDF document.
     *
     * @param dest The destination path for the PDF file.
     * @throws IOException If an I/O error occurs.
     */
    public static void createPdf(String dest) throws IOException {
        try (var writer = new PdfWriter(dest);
             var pdf = new PdfDocument(writer);
             var document = new Document(pdf)) {

            var font = PdfFontFactory.createFont("src/main/resources/DejaVuSans.ttf", PdfEncodings.IDENTITY_H);
            document.setFont(font);

            document.add(new Paragraph("Mathearbeit - Übungsblatt").setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            addArithmeticSection(document);
            addWeightsSection(document);
            addWordProblemSection(document);
        }
    }

    private static void addArithmeticSection(Document document) {
        document.add(new Paragraph("1. Arithmetik (Rechnen mit Zehnerzahlen)").setBold().setFontSize(14));

        document.add(new Paragraph("a) Löse die Aufgaben:"));
        var table1 = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        for (int i = 0; i < 6; i++) {
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b))));
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 10))));
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 100))));
        }
        document.add(table1);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("b) Finde drei Malaufgaben für das Ergebnis:"));
        document.add(new Paragraph("Beispiel: Zielzahl 400").setFontSize(10).setItalic());
        document.add(new Paragraph("2 \u00B7 200 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("4 \u00B7 100 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("8 \u00B7 50 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("\n"));

        var table1b = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        for (int i = 0; i < 3; i++) {
            int target;
            do {
                target = (random.nextInt(8) + 2) * 100;
            } while (target == 400);

            Cell cell = new Cell().setBorder(Border.NO_BORDER);
            cell.add(new Paragraph("Zielzahl: " + target).setBold());
            cell.add(new Paragraph("____ \u00B7 ____ = " + target));
            cell.add(new Paragraph("____ \u00B7 ____ = " + target));
            cell.add(new Paragraph("____ \u00B7 ____ = " + target));
            cell.add(new Paragraph("\n"));
            table1b.addCell(cell);
        }
        document.add(table1b);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("c) Vervollständige die Tabelle:"));
        var table2 = new Table(UnitValue.createPercentArray(new float[]{0.5f, 2f, 2f, 2f, 2f}));
        table2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table2.setTextAlignment(TextAlignment.CENTER);
        table2.useAllAvailableWidth();

        table2.addCell(new Cell().add(new Paragraph("\u00B7").setBold()).setHeight(30));
        int f1 = random.nextInt(8) + 2;
        int f2 = random.nextInt(8) + 2;
        int f3 = random.nextInt(8) + 2;
        int f4 = random.nextInt(8) + 2;
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f1)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f2)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f3)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f4)).setBold()).setHeight(30));

        int base = random.nextInt(9) + 1;
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base * f1)).setItalic()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));

        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base * 10)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));

        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base * 100)).setBold()).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table2.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        document.add(table2);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("d) Schreibe die Tauschaufgabe und löse:"));
        document.add(new Paragraph("Beispiel: 5 \u00B7 30 = 150  Tauschaufgabe: 30 \u00B7 5 = 150").setFontSize(10).setItalic());
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(9) + 1;
            int b = (random.nextInt(9) + 1) * 10;
            document.add(new Paragraph(String.format("%d \u00B7 %d = ____  Tauschaufgabe: ____ \u00B7 ____ = ____", a, b)));
        }
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("e) Dividiere:"));
        var table3 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        for (int i = 0; i < 8; i++) {
            int divisor = random.nextInt(8) + 2;
            int result = (random.nextInt(9) + 1) * 10;
            int dividend = result * divisor;
            table3.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend, divisor))));

            int divisor10 = divisor * 10;
            int resultSmall = random.nextInt(9) + 1;
            int dividend10 = resultSmall * divisor10;
            table3.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend10, divisor10))));
        }
        document.add(table3);
        document.add(new Paragraph("\n"));
    }

    private static void addWeightsSection(Document document) {
        document.add(new Paragraph("2. Größen und Einheiten (Gewichte)").setBold().setFontSize(14));

        document.add(new Paragraph("a) Verbinde gleiche Gewichte:"));
        var tableConnect = new Table(UnitValue.createPercentArray(new float[]{1, 2, 1})).useAllAvailableWidth();

        tableConnect.addCell(new Cell().add(new Paragraph("1000 g")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph("1/2 kg")).setBorder(Border.NO_BORDER));

        tableConnect.addCell(new Cell().add(new Paragraph("500 g")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph("1 kg")).setBorder(Border.NO_BORDER));

        tableConnect.addCell(new Cell().add(new Paragraph("250 g")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
        tableConnect.addCell(new Cell().add(new Paragraph("1/4 kg")).setBorder(Border.NO_BORDER));

        document.add(tableConnect);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("b) Setze <, > oder = ein:"));
        var table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        for (int i = 0; i < 9; i++) {
            int val1 = (random.nextInt(10) + 1) * 100;
            String u1 = "g";
            int val2 = (random.nextInt(10) + 1) * 100;
            String u2 = "g";

            if (random.nextBoolean()) {
                val1 = random.nextBoolean() ? 1 : 2;
                u1 = val1 == 1 ? "kg" : "1/2 kg";
            }
            if (random.nextBoolean()) {
                val2 = random.nextBoolean() ? 1 : 2;
                u2 = val2 == 1 ? "kg" : "1/2 kg";
            }
            table.addCell(new Cell().add(new Paragraph(val1 + " " + u1 + " ___ " + val2 + " " + u2)));
        }
        document.add(table);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("c) Berechne das Gesamtgewicht:"));
        for (int i = 0; i < 4; i++) {
            int m = (random.nextInt(5) + 1) * 100;
            int z = (random.nextInt(5) + 1) * 100;
            int b = (random.nextInt(5) + 1) * 100;
            document.add(new Paragraph("Einkauf: Mehl (" + m + " g), Zucker (" + z + " g), Butter (" + b + " g)"));
            Paragraph p = new Paragraph("Gesamtgewicht: ________________");
            p.setMarginBottom(10f);
            document.add(p);
        }

        document.add(new Paragraph("d) Ergänze:"));
        for (int i = 0; i < 6; i++) {
            int target = random.nextBoolean() ? 1000 : 500;
            String targetStr = target == 1000 ? "1 kg" : "500 g";
            int part = (random.nextInt(target / 100 - 1) + 1) * 100;

            Paragraph p;
            if (random.nextBoolean()) {
                p = new Paragraph("_______ g + " + part + " g = " + targetStr);
            } else {
                p = new Paragraph(part + " g + _______ g = " + targetStr);
            }
            p.setMarginBottom(5f);
            document.add(p);
        }
        document.add(new Paragraph("\n"));
    }

    private static void addWordProblemSection(Document document) {
        document.add(new Paragraph("3. Sachrechnen").setBold().setFontSize(14));

        List<String> problems = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            int type = i % 35;
            switch (type) {
                case 0:
                    int ranzen = (random.nextInt(5) + 5) * 100;
                    int buch = (random.nextInt(4) + 2) * 100;
                    int anzahl = random.nextInt(3) + 2;
                    problems.add("Ein leerer Schulranzen wiegt " + ranzen + " g. Ein Kind packt " + anzahl + " Bücher ein. Jedes Buch wiegt " + buch + " g.\nFrage: Wie schwer ist der Ranzen jetzt insgesamt?");
                    break;
                case 1:
                    int mehl = (random.nextInt(5) + 1) * 100;
                    int zucker = (random.nextInt(5) + 1) * 100;
                    problems.add("Mama kauft " + mehl + " g Mehl und " + zucker + " g Zucker. Die leere Tasche wiegt 200 g.\nFrage: Wie schwer ist die volle Tasche?");
                    break;
                case 2:
                    int inhalt = (random.nextInt(8) + 2) * 100;
                    int verpackung = (random.nextInt(3) + 1) * 100;
                    problems.add("Ein Paket wiegt insgesamt " + (inhalt + verpackung) + " g. Die Verpackung wiegt " + verpackung + " g.\nFrage: Wie schwer ist der Inhalt?");
                    break;
                case 3:
                    int gesamt = (random.nextInt(8) + 2) * 100;
                    int personen = random.nextInt(3) + 2;
                    while (gesamt % personen != 0) {
                        gesamt = (random.nextInt(8) + 2) * 100;
                    }
                    problems.add("Eine Tüte Bonbons wiegt " + gesamt + " g. Sie wird gerecht auf " + personen + " Kinder aufgeteilt.\nFrage: Wie viel Gramm bekommt jedes Kind?");
                    break;
                case 4:
                    int a = (random.nextInt(8) + 2) * 100;
                    int b = (random.nextInt(8) + 2) * 100;
                    problems.add("Kiste A wiegt " + a + " g. Kiste B wiegt " + b + " g.\nFrage: Wie viel Gramm ist der Unterschied?");
                    break;
                case 5:
                    int teig = (random.nextInt(8) + 2) * 100;
                    int broetchenAnzahl = random.nextInt(8) + 2;
                    while (teig % broetchenAnzahl != 0) {
                        teig = (random.nextInt(8) + 2) * 100;
                    }
                    problems.add("Ein Bäcker hat " + teig + " g Teig. Er macht daraus " + broetchenAnzahl + " gleiche Brötchen.\nFrage: Wie viel wiegt ein Brötchen?");
                    break;
                case 6:
                    int apfel = (random.nextInt(10) + 10) * 10;
                    int banane = (random.nextInt(10) + 10) * 10;
                    problems.add("Ein Apfel wiegt " + apfel + " g. Eine Banane wiegt " + banane + " g.\nFrage: Wie viel wiegen sie zusammen?");
                    break;
                case 7:
                    int ladung = (random.nextInt(10) + 5) * 100;
                    int abladung = (random.nextInt(ladung / 100 - 1) + 1) * 100;
                    problems.add("Ein LKW ist mit " + ladung + " kg Kartoffeln beladen. An der ersten Haltestelle lädt er " + abladung + " kg ab.\nFrage: Wie viel kg Ladung hat der LKW noch?");
                    break;
                case 8:
                    int hund = (random.nextInt(20) + 5);
                    int katze = (random.nextInt(5) + 2) * 1000;
                    problems.add("Ein Hund wiegt " + hund + " kg. Eine Katze wiegt " + katze + " g.\nFrage: Wie groß ist der Gewichtsunterschied in Gramm?");
                    break;
                case 9:
                    int kekse = (random.nextInt(5) + 2) * 100;
                    int dose = (random.nextInt(2) + 1) * 100;
                    problems.add("Oma backt " + kekse + " g Kekse. Sie füllt sie in eine Dose, die " + dose + " g wiegt.\nFrage: Wie schwer ist die volle Dose?");
                    break;
                case 10:
                    int seiten = (random.nextInt(5) + 3) * 10;
                    int tage = random.nextInt(4) + 3;
                    problems.add("Ein Buch hat " + (seiten * tage) + " Seiten. Tim liest jeden Tag " + seiten + " Seiten.\nFrage: Wie viele Tage braucht er für das ganze Buch?");
                    break;
                case 11:
                    int preis = (random.nextInt(8) + 2) * 10;
                    int anzahlStifte = random.nextInt(5) + 2;
                    problems.add("Ein Stift kostet " + preis + " Cent. Lisa kauft " + anzahlStifte + " Stifte.\nFrage: Wie viel muss sie bezahlen?");
                    break;
                case 12:
                    int start = (random.nextInt(5) + 8);
                    int dauer = random.nextInt(3) + 1;
                    problems.add("Der Film beginnt um " + start + ":00 Uhr und dauert " + dauer + " Stunden.\nFrage: Wann ist der Film zu Ende?");
                    break;
                case 13:
                    int strecke = (random.nextInt(8) + 2) * 100;
                    int gefahren = (random.nextInt(strecke / 100 - 1) + 1) * 100;
                    problems.add("Die Strecke nach Berlin ist " + strecke + " km lang. Papa ist schon " + gefahren + " km gefahren.\nFrage: Wie weit müssen sie noch fahren?");
                    break;
                case 14:
                    int milch = (random.nextInt(5) + 1) * 100;
                    int becher = random.nextInt(4) + 2;
                    problems.add("In einer Kanne sind " + (milch * becher) + " ml Milch. Sie wird auf " + becher + " Becher verteilt.\nFrage: Wie viel Milch ist in jedem Becher?");
                    break;
                case 15:
                    int breite = (random.nextInt(5) + 2) * 10;
                    int laenge = breite * 2;
                    problems.add("Ein Rechteck ist " + laenge + " cm lang und " + breite + " cm breit.\nFrage: Wie groß ist der Umfang?");
                    break;
                case 16:
                    int klasse = random.nextInt(10) + 20;
                    int krank = random.nextInt(5) + 1;
                    problems.add("In der Klasse 3a sind " + klasse + " Kinder. Heute sind " + krank + " Kinder krank.\nFrage: Wie viele Kinder sind in der Schule?");
                    break;
                case 17:
                    int taschengeld = (random.nextInt(5) + 2) * 5;
                    int sparen = random.nextInt(3) + 2;
                    problems.add("Tom bekommt " + taschengeld + " Euro Taschengeld im Monat. Er spart " + sparen + " Monate lang.\nFrage: Wie viel Geld hat er gespart?");
                    break;
                case 18:
                    int eier = (random.nextInt(5) + 2) * 6;
                    problems.add("Oma kauft " + eier + " Eier. In eine Schachtel passen 6 Eier.\nFrage: Wie viele Schachteln braucht sie?");
                    break;
                case 19:
                    int alter = random.nextInt(5) + 8;
                    int opa = alter * 7;
                    problems.add("Max ist " + alter + " Jahre alt. Sein Opa ist 7 mal so alt.\nFrage: Wie alt ist der Opa?");
                    break;
                case 20:
                    int bus = random.nextInt(20) + 30;
                    int aussteigen = random.nextInt(10) + 5;
                    int einsteigen = random.nextInt(10) + 5;
                    problems.add("Im Bus sitzen " + bus + " Leute. An der Haltestelle steigen " + aussteigen + " aus und " + einsteigen + " ein.\nFrage: Wie viele Leute sind jetzt im Bus?");
                    break;
                case 21:
                    int regal = random.nextInt(4) + 3;
                    int buecherProRegal = (random.nextInt(5) + 5) * 10;
                    problems.add("In der Bücherei gibt es " + regal + " Regale. In jedem Regal stehen " + buecherProRegal + " Bücher.\nFrage: Wie viele Bücher sind es insgesamt?");
                    break;
                case 22:
                    int saft = (random.nextInt(5) + 5) * 100;
                    int glas = 200;
                    problems.add("In einer Flasche sind " + saft + " ml Saft. Ein Glas fasst " + glas + " ml.\nFrage: Wie viele Gläser kann man füllen?");
                    break;
                case 23:
                    int schritte = (random.nextInt(5) + 5) * 100;
                    int tageLaufen = random.nextInt(5) + 3;
                    problems.add("Lisa läuft jeden Tag " + schritte + " Schritte. Sie macht das " + tageLaufen + " Tage lang.\nFrage: Wie viele Schritte ist sie gelaufen?");
                    break;
                case 24:
                    int gewinn = (random.nextInt(8) + 2) * 100;
                    int gewinner = random.nextInt(3) + 2;
                    while (gewinn % gewinner != 0) {
                        gewinn = (random.nextInt(8) + 2) * 100;
                    }
                    problems.add("Im Lotto gewinnen 3 Freunde zusammen " + gewinn + " Euro. Sie teilen gerecht.\nFrage: Wie viel bekommt jeder?");
                    break;
                case 25:
                    int startH = random.nextInt(12) + 1;
                    int endH = startH + random.nextInt(3) + 1;
                    problems.add("Der Unterricht beginnt um " + startH + ":00 Uhr und endet um " + endH + ":00 Uhr.\nFrage: Wie viele Stunden dauert der Unterricht?");
                    break;
                case 26:
                    int totalStickers = (random.nextInt(5) + 2) * 10;
                    int lostStickers = random.nextInt(5) + 1;
                    problems.add("Tim hat " + totalStickers + " Sticker. Er verliert " + lostStickers + " Sticker auf dem Schulweg.\nFrage: Wie viele Sticker hat er noch?");
                    break;
                case 27:
                    int rows = random.nextInt(4) + 3;
                    int chairsPerRow = random.nextInt(5) + 4;
                    problems.add("In der Aula stehen " + rows + " Reihen Stühle. In jeder Reihe stehen " + chairsPerRow + " Stühle.\nFrage: Wie viele Stühle gibt es insgesamt?");
                    break;
                case 28:
                    int totalJuice = (random.nextInt(5) + 2) * 1000; // ml
                    int drunk = (random.nextInt(5) + 1) * 100;
                    problems.add("In einem Krug sind " + totalJuice + " ml Saft. Anna trinkt " + drunk + " ml.\nFrage: Wie viel Saft ist noch übrig?");
                    break;
                case 29:
                    int toyPrice = (random.nextInt(5) + 2) * 5;
                    int savedMoney = random.nextInt(5) + 5;
                    problems.add("Ein Spielzeug kostet " + toyPrice + " Euro. Ben hat schon " + savedMoney + " Euro gespart.\nFrage: Wie viel Geld fehlt ihm noch?");
                    break;
                case 30:
                    int totalPages = (random.nextInt(5) + 5) * 10;
                    int readPages = (random.nextInt(3) + 1) * 10;
                    problems.add("Das Buch hat " + totalPages + " Seiten. Marie hat schon " + readPages + " Seiten gelesen.\nFrage: Wie viele Seiten muss sie noch lesen?");
                    break;
                case 31:
                    int balloons = (random.nextInt(5) + 2) * 3;
                    problems.add("Auf dem Fest werden " + balloons + " Luftballons verteilt. Jedes Kind bekommt 3 Ballons.\nFrage: Wie viele Kinder bekommen Ballons?");
                    break;
                case 32:
                    int lengthField = (random.nextInt(5) + 2) * 10; // m
                    int rounds = random.nextInt(3) + 2;
                    problems.add("Der Sportplatz ist " + lengthField + " m lang. Paul rennt " + rounds + " mal die Länge entlang.\nFrage: Wie viele Meter ist er gelaufen?");
                    break;
                case 33:
                    int totalApples = (random.nextInt(5) + 2) * 4;
                    problems.add("Opa erntet " + totalApples + " Äpfel. Er packt immer 4 Äpfel in eine Tüte.\nFrage: Wie viele Tüten füllt er?");
                    break;
                case 34:
                    int cinemaTicket = random.nextInt(5) + 5;
                    int popcorn = random.nextInt(3) + 3;
                    problems.add("Eine Kinokarte kostet " + cinemaTicket + " Euro. Das Popcorn kostet " + popcorn + " Euro.\nFrage: Wie viel kostet der Kinobesuch insgesamt?");
                    break;
            }
        }

        Collections.shuffle(problems);

        for (int i = 0; i < 10; i++) {
            document.add(new Paragraph((i + 1) + ". " + problems.get(i)));
            document.add(new Paragraph("Rechnung: __________________________________________________"));
            document.add(new Paragraph("Antwort: ___________________________________________________"));
            document.add(new Paragraph("\n"));
        }
    }
}
