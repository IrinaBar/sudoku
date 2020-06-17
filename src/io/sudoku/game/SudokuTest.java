package io.sudoku.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuTest {
    String bsp = "030000000000195000008000060" +
            "800060000400800001000020000060000280000419005000000070";
    String done =
            "5 3 4 6 7 8 9 1 2" +
                    "6 7 2 1 9 5 3 4 8" +
                    "1 9 8 3 4 2 5 6 7" +
                    "8 5 9 7 6 1 4 2 3" +
                    "4 2 6 8 5 3 7 9 1" +
                    "7 1 3 9 2 4 8 5 6" +
                    "9 6 1 5 3 7 2 8 4" +
                    "2 8 7 4 1 9 6 3 5" +
                    "3 4 5 2 8 6 1 7 9";

    @Test
    public void testIsSolvedCorrect() {
        Sudoku test = new Sudoku(bsp);
        test.solve();
        assertEquals("Right solution", "5 3 4 6 7 8 9 1 2 \n" +
                "6 7 2 1 9 5 3 4 8 \n" +
                "1 9 8 3 4 2 5 6 7 \n" +
                "8 5 9 7 6 1 4 2 3 \n" +
                "4 2 6 8 5 3 7 9 1 \n" +
                "7 1 3 9 2 4 8 5 6 \n" +
                "9 6 1 5 3 7 2 8 4 \n" +
                "2 8 7 4 1 9 6 3 5 \n" +
                "3 4 5 2 8 6 1 7 9 \n", test.toString());
    }

    @Test
    public void testIsValid() {
        String notSolvedYetButValid =
                "0 3 0 0 0 0 0 0 8" +
                        "0 0 0 1 9 5 0 0 0" +
                        "0 0 8 0 0 0 0 6 0" +
                        "8 0 0 0 6 0 0 0 0" +
                        "4 0 0 8 0 0 0 0 1" +
                        "0 0 0 0 2 0 0 0 0" +
                        "0 6 0 0 0 0 2 8 0" +
                        "0 0 0 4 1 9 0 0 5" +
                        "0 0 0 0 0 0 0 7 0";
        Sudoku test1 = new Sudoku(notSolvedYetButValid);
        assertTrue("sudoku is valid", test1.isValid());
        Sudoku test1_1 = new Sudoku(notSolvedYetButValid);
        test1_1.setNumberAt(8, 0, 8);
        assertFalse("column 0 doubble 8 number", test1_1.isValid());

    }

    @Test
    public void testEnoughNumbers() {
        String notEnoughNumbers =
                "0 3 0 0 0 0" +
                        "0 0 0 1 9 5 0 0 0" +
                        "0 0 8 0 0 0 0 6 0" +
                        "8 0 0 0 6 0 0 0 0" +
                        "4 0 0 8 0 0 0 0 1" +
                        "0 0 0 0 2 0 0 0 0" +
                        "0 6 0 0 0 0 2 8 0" +
                        "0 0 0 4 1 9 0 0 5" +
                        "0 0 0 0 0 0 0 7 0";
        try {
            Sudoku test2 = new Sudoku(notEnoughNumbers);
            test2.isValid();
            fail("This sudoku has not enough numbers");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testNoDoubbleNumbersInRows() {

        Sudoku test3 = new Sudoku(bsp);
        test3.setNumberAt(0, 8, 3);
        assertEquals("Set the number 3 in the row: 0, column: 8", 3, test3.getNumberAt(0, 8));
        assertEquals("Contains doubble numbers in row 0", false, test3.isValid());
    }

    @Test
    public void testNoDoubbleNumbersInColumns() {

        Sudoku test4 = new Sudoku(bsp);
        test4.setNumberAt(8, 0, 8);
        assertEquals("Set the number 8 in the row: 8, column: 0", 8, test4.getNumberAt(8, 0));
        assertEquals("Contains doubble numbers in colums 0", false, test4.isValid());
    }

    @Test
    public void testIsDone() {
        Sudoku test5 = new Sudoku(done);
        assertEquals("Sudoku has no doubble numbers and solved", true, test5.isDone());
    }

}
