package de.agiehl.school.math;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
        
        Set<String> usedTasks = new HashSet<>();
        int count = 0;
        while (count < 6) {
            int a = random.nextInt(9) + 1;
            int b = random.nextInt(9) + 1;
            String key = Math.min(a, b) + "_" + Math.max(a, b);
            
            if (usedTasks.add(key)) {
                table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b))));
                table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 10))));
                table.addCell(new Cell().add(new Paragraph(String.format("%d \u00B7 %d =", a, b * 100))));
                count++;
            }
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
        Set<Integer> usedTargets = new HashSet<>();
        usedTargets.add(400); // Exclude example
        
        int count = 0;
        while (count < 3) {
            int target = (random.nextInt(8) + 2) * 100;
            if (usedTargets.add(target)) {
                Cell cell = new Cell().setBorder(Border.NO_BORDER);
                cell.add(new Paragraph("Zielzahl: " + target).setBold());
                cell.add(new Paragraph("____ \u00B7 ____ = " + target));
                cell.add(new Paragraph("____ \u00B7 ____ = " + target));
                cell.add(new Paragraph("____ \u00B7 ____ = " + target));
                cell.add(new Paragraph("\n"));
                table.addCell(cell);
                count++;
            }
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
        
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= 9; i++) {
            factors.add(i);
        }
        Collections.shuffle(factors);
        int f1 = factors.get(0);
        int f2 = factors.get(1);
        int f3 = factors.get(2);
        int f4 = factors.get(3);
        
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
        
        Set<String> usedTasks = new HashSet<>();
        usedTasks.add("5_30"); // Exclude example equivalents
        usedTasks.add("30_5");
        
        int count = 0;
        while (count < 4) {
            int a = random.nextInt(9) + 1;
            int b = (random.nextInt(9) + 1) * 10;
            String key = Math.min(a, b/10) + "_" + Math.max(a, b/10); // Check base numbers to avoid duplicates like 3*40 and 4*30
            
            if (usedTasks.add(key)) {
                document.add(new Paragraph(String.format("%d \u00B7 %d = ____  Tauschaufgabe: ____ \u00B7 ____ = ____", a, b)));
                count++;
            }
        }
        document.add(new Paragraph("\n"));
    }

    private void addDivisionTasks(Document document) {
        document.add(new Paragraph("e) Dividiere:"));
        var table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        
        Set<String> usedTasks = new HashSet<>();
        int count = 0;
        while (count < 8) {
            int divisor = random.nextInt(8) + 2;
            int result = (random.nextInt(9) + 1) * 10;
            int dividend = result * divisor;
            String key = dividend + "_" + divisor;
            
            if (usedTasks.add(key)) {
                table.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend, divisor))));

                int divisor10 = divisor * 10;
                int resultSmall = random.nextInt(9) + 1;
                int dividend10 = resultSmall * divisor10;
                table.addCell(new Cell().add(new Paragraph(String.format("%d : %d =", dividend10, divisor10))));
                count++;
            }
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }
}
