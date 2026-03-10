package de.agiehl.school.math;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.util.Random;

public class WeightsSection implements WorksheetSection {

    private final Random random = new Random();

    @Override
    public void addTo(Document document) {
        document.add(new Paragraph("2. Größen und Einheiten (Gewichte)").setBold().setFontSize(14));

        addMatchingTasks(document);
        addComparisonTasks(document);
        addTotalWeightTasks(document);
        addFillInTheBlanksTasks(document);
    }

    private void addMatchingTasks(Document document) {
        document.add(new Paragraph("a) Verbinde gleiche Gewichte:"));
        var table = new Table(UnitValue.createPercentArray(new float[]{1, 2, 1})).useAllAvailableWidth();

        addMatchingRow(table, "1000 g", "1/2 kg");
        addMatchingRow(table, "500 g", "1 kg");
        addMatchingRow(table, "250 g", "1/4 kg");

        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private void addMatchingRow(Table table, String left, String right) {
        table.addCell(new Cell().add(new Paragraph(left)).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(" ")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph(right)).setBorder(Border.NO_BORDER));
    }

    private void addComparisonTasks(Document document) {
        document.add(new Paragraph("b) Setze <, > oder = ein:"));
        var table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        for (int i = 0; i < 9; i++) {
            String left = generateWeight();
            String right = generateWeight();
            table.addCell(new Cell().add(new Paragraph(left + " ___ " + right)));
        }
        document.add(table);
        document.add(new Paragraph("\n"));
    }

    private String generateWeight() {
        if (random.nextBoolean()) {
            return (random.nextInt(10) + 1) * 100 + " g";
        } else {
            return random.nextBoolean() ? "1 kg" : "1/2 kg";
        }
    }

    private void addTotalWeightTasks(Document document) {
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
        document.add(new Paragraph("\n"));
    }

    private void addFillInTheBlanksTasks(Document document) {
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
}
