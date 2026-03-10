package de.agiehl.school.math;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
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

public class MathWorksheetGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) {
        var dest = "MathWorksheet.pdf";
        try {
            createPdf(dest);
            System.out.println("PDF created: " + new File(dest).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPdf(String dest) throws IOException {
        try (var writer = new PdfWriter(dest);
             var pdf = new PdfDocument(writer);
             var document = new Document(pdf)) {
            
            // Default is A4 Portrait, so no need to set PageSize.A4.rotate()

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
        // Optimized column widths: First column smaller, others wider
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

        for (int i = 0; i < 25; i++) {
            int type = i % 10;
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
                    int flascheLeer = (random.nextInt(10) + 10) * 10;
                    problems.add("Eine volle 1-Liter-Flasche mit Wasser wiegt insgesamt 1150 g. Die leere Flasche wiegt " + flascheLeer + " g.\nFrage: Wie schwer ist das Wasser alleine?");
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
