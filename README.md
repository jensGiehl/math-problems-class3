# Math Worksheet Generator

This Java application generates random math worksheets in PDF format, designed for primary school students. It covers various topics including arithmetic with tens, weights and units, and word problems.

## Features

The generator creates a PDF file (`MathWorksheet.pdf`) containing the following sections:

### 1. Arithmetic (Calculations with Tens)
*   **Multiplication:** Basic multiplication tasks involving single digits and multiples of 10 and 100.
*   **Factor Finding:** Exercises to find three different multiplication pairs for a given target number (e.g., for 400: 2x200, 4x100, 8x50).
*   **Multiplication Tables:** A table to complete multiplication tasks with a base number and various factors.
*   **Commutative Property:** Tasks to write the commutative property (Tauschaufgabe) and solve.
*   **Division:** Division problems involving multiples of 10.

### 2. Weights and Units
*   **Matching:** Connect equal weights (e.g., 1000 g = 1 kg).
*   **Comparison:** Compare weights using <, >, or =.
*   **Total Weight Calculation:** Calculate the total weight of a shopping list.
*   **Fill in the Blanks:** Complete equations to reach a target weight (e.g., 1 kg).

### 3. Word Problems
*   **Randomized Scenarios:** Generates 10 word problems selected from a pool of 25 different templates.
*   **Topics:** Includes scenarios like school bags, shopping, packages, sharing sweets, weight differences, and more.
*   **Format:** Each problem includes space for the calculation and the answer.

## Usage

1.  Open the project in your IDE (e.g., IntelliJ IDEA, Android Studio).
2.  Ensure the dependencies (iText) are correctly configured in your `pom.xml` or build path.
3.  Run the `MathWorksheetGenerator` class.
4.  The `MathWorksheet.pdf` file will be generated in the project root directory.

## Customization

You can modify the `MathWorksheetGenerator.java` file to adjust:
*   The number of tasks per section.
*   The range of random numbers used.
*   The types of word problems.
*   The layout and formatting of the PDF.
