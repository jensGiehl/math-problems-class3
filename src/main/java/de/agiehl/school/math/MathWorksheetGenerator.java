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
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;
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

            // Set a font that supports special characters
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

        // Kleine und große Aufgaben
        document.add(new Paragraph("a) Löse die Aufgaben:"));
        var table1 = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        for (int i = 0; i < 3; i++) {
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b))));
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 10))));
            table1.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 100))));
        }
        document.add(table1);
        document.add(new Paragraph("\n"));

        // Faktoren finden
        document.add(new Paragraph("b) Finde drei Malaufgaben für das Ergebnis:"));
        int target = (random.nextInt(8) + 2) * 100; // e.g., 200, 300...
        document.add(new Paragraph("Zielzahl: " + target + " -> ____ \u00B7 ____, ____ \u00B7 ____, ____ \u00B7 ____"));
        document.add(new Paragraph("\n"));

        // Rechentabellen
        document.add(new Paragraph("c) Vervollständige die Tabelle:"));
        float[] columnWidths = {1, 1, 1, 1};
        var table2 = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
        
        table2.addCell(new Cell().add(new Paragraph("\u00B7")));
        int f1 = random.nextInt(8) + 2;
        int f2 = random.nextInt(8) + 2;
        int f3 = random.nextInt(8) + 2;
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f1))));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f2))));
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(f3))));
        
        int base = random.nextInt(9) + 1;
        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base))));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));

        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base * 10))));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));

        table2.addCell(new Cell().add(new Paragraph(String.valueOf(base * 100))));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));
        table2.addCell(new Cell().add(new Paragraph("")));
        document.add(table2);
        document.add(new Paragraph("\n"));

        // Tauschaufgaben
        document.add(new Paragraph("d) Schreibe die Tauschaufgabe und löse:"));
        for (int i = 0; i < 2; i++) {
            int a = random.nextInt(9) + 1;
            int b = (random.nextInt(9) + 1) * 10;
            document.add(new Paragraph(String.format("%d \u00B7 %d = ____  Tauschaufgabe: ____ \u00B7 ____ = ____", a, b)));
        }
        document.add(new Paragraph("\n"));

        // Gemischte Division
        document.add(new Paragraph("e) Dividiere:"));
        var table3 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        for (int i = 0; i < 2; i++) {
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

        // Gewichte verbinden
        document.add(new Paragraph("a) Verbinde gleiche Gewichte:"));
        // Left column, Space for drawing lines, Right column
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

        // Vergleichen
        document.add(new Paragraph("b) Setze <, > oder = ein:"));
        var table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        table.addCell(new Cell().add(new Paragraph("500 g ___ 1/2 kg")));
        table.addCell(new Cell().add(new Paragraph("1000 g ___ 1 kg")));
        table.addCell(new Cell().add(new Paragraph("250 g ___ 1/4 kg")));
        table.addCell(new Cell().add(new Paragraph("800 g ___ 1 kg")));
        table.addCell(new Cell().add(new Paragraph("1200 g ___ 1 kg")));
        table.addCell(new Cell().add(new Paragraph("50 g ___ 500 g")));
        document.add(table);
        document.add(new Paragraph("\n"));

        // Gewichtsbestimmung
        document.add(new Paragraph("c) Berechne das Gesamtgewicht:"));
        document.add(new Paragraph("Einkauf: Mehl (1 kg), Zucker (500 g), Butter (250 g)"));
        document.add(new Paragraph("Gesamtgewicht: ________________"));
        document.add(new Paragraph("\n"));

        // Ergänzen
        document.add(new Paragraph("d) Ergänze:"));
        document.add(new Paragraph("___ g + 250 g = 1 kg"));
        document.add(new Paragraph("700 g + ___ g = 1 kg"));
        document.add(new Paragraph("___ g + 100 g = 500 g"));
        document.add(new Paragraph("350 g + ___ g = 500 g"));
        document.add(new Paragraph("\n"));
    }

    private static void addWordProblemSection(Document document) {
        document.add(new Paragraph("3. Sachrechnen").setBold().setFontSize(14));
        document.add(new Paragraph("Ein leerer Schulranzen wiegt 800 g. Laura packt 3 Bücher ein. Jedes Buch wiegt 300 g."));
        document.add(new Paragraph("Frage: Wie schwer ist der Ranzen jetzt insgesamt?"));
        document.add(new Paragraph("\nRechnung: __________________________________________________"));
        document.add(new Paragraph("\nAntwort: ___________________________________________________"));
    }
}
