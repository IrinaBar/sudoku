package io.sudoku.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testklasse.
 *
 * @author Barvenko Irina
 */
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

    String err = "5 3 0 6 7 8 9 1 4 \n" +
            "6 7 2 1 9 5 3 4 8 \n" +
            "1 9 8 3 4 2 5 6 7 \n" +
            "8 5 9 7 6 1 4 2 3 \n" +
            "4 2 6 8 5 3 7 9 1 \n" +
            "7 1 3 9 2 4 8 5 6 \n" +
            "9 6 1 5 3 7 2 8 2 \n" +
            "2 8 7 4 1 9 6 3 5 \n" +
            "3 4 5 2 8 6 1 7 9 ";

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

    String simpleInvalid = "000000100000000010000000001000000000000000000000000000000000000000000000000000000";

    @Test(timeout = 10000)
    public void testInvalid() {
        Sudoku test = new Sudoku(simpleInvalid);
        assertFalse("Can not be solved", test.solve());
    }

    @Test(timeout = 10000)
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

    @Test(timeout = 10000)
    public void canNotBeSolved() {
        try {
            Sudoku test = new Sudoku(err);
            test.solve();
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

    }

    @Test(timeout = 10000)
    public void canNotBeSolvedDoubbleNumbers() {
        Sudoku test = new Sudoku(notSolvedYetButValid);
        test.setNumberAt(1, 1, 5);
        assertFalse("This sudoku can not be solved because of the doubble numbers in the row ", test.solve());
        test.setNumberAt(7, 2, 6);
        assertFalse("This sudoku can not be solved because of the incorrect numbers in block 3x3", test.solve());
    }

    @Test(timeout = 10000)
    public void testIsValid() {
        Sudoku test1 = new Sudoku(notSolvedYetButValid);
        assertTrue("sudoku is valid", test1.isValid());
        Sudoku test1_1 = new Sudoku(notSolvedYetButValid);
        test1_1.setNumberAt(8, 0, 8);
        assertFalse("column 0 doubble 8 number", test1_1.isValid());

    }

    @Test(timeout = 10000)
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

    @Test(timeout = 10000)
    public void testSetAndGetNumbers() {
        Sudoku test = new Sudoku(bsp);
        test.setNumberAt(0, 8, 3);
        assertEquals("Set the number 3 in the row: 0, column: 8", 3, test.getNumberAt(0, 8));
    }

    @Test(timeout = 10000)
    public void testNoDoubbleNumbersInRows() {
        Sudoku test3 = new Sudoku(bsp);
        test3.setNumberAt(0, 8, 3);
        assertFalse("Contains doubble numbers in row 0", test3.isValid());
    }

    @Test(timeout = 10000)
    public void testNoDoubbleNumbersInColumns() {
        Sudoku test4 = new Sudoku(bsp);
        test4.setNumberAt(8, 0, 8);
        assertEquals("Set the number 8 in the row: 8, column: 0", 8, test4.getNumberAt(8, 0));
        assertFalse("Contains doubble numbers in colums 0", test4.isValid());
    }

    @Test(timeout = 10000)
    public void testIsDone() {
        Sudoku test5 = new Sudoku(done);
        Sudoku test6 = new Sudoku(bsp);
        assertTrue("Sudoku solved completely", test5.isDone());
        assertFalse("Sudoku is not solved yet", test6.isDone());
    }

}
