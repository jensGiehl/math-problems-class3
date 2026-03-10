package de.agiehl.school.math;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Generates a math worksheet in PDF format.
 */
public class MathWorksheetGenerator {

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

            List<WorksheetSection> sections = List.of(
                    new ArithmeticSection(),
                    new WeightsSection(),
                    new WordProblemSection()
            );

            for (WorksheetSection section : sections) {
                section.addTo(document);
            }
        }
    }
}
