package de.agiehl.school.math;

import com.itextpdf.layout.Document;

/**
 * Represents a section of the math worksheet.
 */
public interface WorksheetSection {

    /**
     * Adds the content of this section to the PDF document.
     *
     * @param document The PDF document to add content to.
     */
    void addTo(Document document);
}
