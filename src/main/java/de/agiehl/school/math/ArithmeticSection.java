package de.agiehl.school.math;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.Random;

public class ArithmeticSection implements WorksheetSection {

    private final Random random = new Random();

    @Override
    public void addTo(Document document) {
        document.add(new Paragraph("1. Arithmetik (Rechnen mit Zehnerzahlen)").setBold().setFontSize(14));

        addMultiplicationTasks(document);
        addFactorFindingTasks(document);
        addMultiplicationTable(document);
        addCommutativeTasks(document);
        addDivisionTasks(document);
    }

    private void addMultiplicationTasks(Document document) {
        document.add(new Paragraph("a) Löse die Aufgaben:"));
        var table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        for (int i = 0; i < 6; i++) {
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b))));
            table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 10))));
            table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 100))));
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private void addFactorFindingTasks(Document document) {
        document.add(new Paragraph("b) Finde drei Malaufgaben für das Ergebnis:"));
        document.add(new Paragraph("Beispiel: Zielzahl 400").setFontSize(10).setItalic());
        document.add(new Paragraph("2 \u00B7 200 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("4 \u00B7 100 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("8 \u00B7 50 = 400").setFontSize(10).setItalic().setMarginLeft(20));
        document.add(new Paragraph("\n"));

        var table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
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
            table.addCell(cell);
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private void addMultiplicationTable(Document document) {
        document.add(new Paragraph("c) Vervollständige die Tabelle:"));
        var table = new Table(UnitValue.createPercentArray(new float[]{0.5f, 2f, 2f, 2f, 2f}));
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setTextAlignment(TextAlignment.CENTER);
        table.useAllAvailableWidth();

        table.addCell(new Cell().add(new Paragraph("\u00B7").setBold()).setHeight(30));
        int f1 = random.nextInt(8) + 2;
        int f2 = random.nextInt(8) + 2;
        int f3 = random.nextInt(8) + 2;
        int f4 = random.nextInt(8) + 2;
        table.addCell(new Cell().add(new Paragraph(String.valueOf(f1)).setBold()).setHeight(30));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(f2)).setBold()).setHeight(30));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(f3)).setBold()).setHeight(30));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(f4)).setBold()).setHeight(30));

        int base = random.nextInt(9) + 1;
        addRow(table, base, f1);
        addRow(table, base * 10, -1); // -1 indicates no example calculation
        addRow(table, base * 100, -1);

        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private void addRow(Table table, int base, int exampleFactor) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(base)).setBold()).setHeight(30));
        if (exampleFactor != -1) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(base * exampleFactor)).setItalic()).setHeight(30));
        } else {
            table.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        }
        table.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table.addCell(new Cell().add(new Paragraph("")).setHeight(30));
        table.addCell(new Cell().add(new Paragraph("")).setHeight(30));
    }

    private void addCommutativeTasks(Document document) {
        document.add(new Paragraph("d) Schreibe die Tauschaufgabe und löse:"));
        document.add(new Paragraph("Beispiel: 5 \u00B7 30 = 150  Tauschaufgabe: 30 \u00B7 5 = 150").setFontSize(10).setItalic());
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(9) + 1;
            int b = (random.nextInt(9) + 1) * 10;
            document.add(new Paragraph(String.format("%d \u00B7 %d = ____  Tauschaufgabe: ____ \u00B7 ____ = ____", a, b)));
        }
        document.add(new Paragraph("\n"));
    }

    private void addDivisionTasks(Document document) {
        document.add(new Paragraph("e) Dividiere:"));
        var table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        for (int i = 0; i < 8; i++) {
            int divisor = random.nextInt(8) + 2;
            int result = (random.nextInt(9) + 1) * 10;
            int dividend = result * divisor;
            table.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend, divisor))));

            int divisor10 = divisor * 10;
            int resultSmall = random.nextInt(9) + 1;
            int dividend10 = resultSmall * divisor10;
            table.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend10, divisor10))));
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }
}
