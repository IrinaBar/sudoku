package io.sudoku.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasse for the Sudoku-exercise.
 *
 * @author Barvenko Irina
 * @version 2020-06-14
 */
public class Sudoku implements ISudoku {

    /**
     * @param SIZE is the same size for the number of rows and columns,
     * describes a square matrix consisting of 9 x 9 Fields
     */
    public static final int SIZE = 9;

    /**
     * @param sudoku is a lits in another list to initialized a grid.
     */
    private final List<ArrayList<Integer>> board = new ArrayList<>();

    /**
     * Handle invalid length and numbers
     *
     * @param input wait for the new String to input
     */
    public Sudoku(String input) {

        String[] strValues = input.replaceAll(" ", "").split("");
        int gridSize = SIZE * SIZE;

        if (strValues.length != gridSize) {
            throw new IllegalArgumentException();
        }

        List<Integer> intValues = new ArrayList<>();
        for (String val : strValues) {
            try {
                intValues.add(Integer.parseInt(val));
            } catch (NumberFormatException e) {
                System.err.println("Value [" + val + "] is not an \n" + "initialized Value");
            }
        }

        int indexFromInitValues = 0;
        for (int i = 0; i < 9; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            int colCount = 0;
            while (colCount < 9) {
                row.add(intValues.get(indexFromInitValues));
                colCount++;
                indexFromInitValues++;
            }
            board.add(row);
        }
    }

    @Override
    public int getNumberAt(int row, int col) {
        return board.get(row).get(col);
    }

    @Override
    public void setNumberAt(int row, int col, int number) {
        board.get(row).set(col, number);
    }

    @Override
    public boolean isValid() {
        if (!isValidRows()) {
            System.out.println("\n" + "The rows contain the same numbers");
            return false;
        }

        if (!isValidCols()) {
            System.out.println("The columns contain the same numbers");
            return false;
        }

        return true;
    }


    private boolean isValidRows() {
        for (int row = 0; row < SIZE; row++) {
            for (int n = 1; n <= SIZE; n++) {
                int countEntries = Collections.frequency(board.get(row), n);
                //System.out.println("In row [" + row + "] has found entries for the number [" + n + "] = " + countEntries);
                if (countEntries > 1) return false;
            }
        }

        return true;
    }

    private boolean isValidCols() {
        for (int col = 0; col < SIZE; col++) {
            List<Integer> temp = new ArrayList<>();
            for (int row = 0; row < SIZE; row++) {
                temp.add(getNumberAt(col, row));
            }
            for (int n = 1; n <= SIZE; n++) {
                int countEntries = Collections.frequency(temp, n);
                if (countEntries > 1) return false;
            }
        }
        return true;
    }

    @Override
    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (getNumberAt(row, col) == 0) {

                    List<Integer> possibleValues = getPossibilities(row, col);

                    for (Integer possibleValue : possibleValues) {
                        setNumberAt(row, col, possibleValue);

                        if (solve()) {
                            return true;
                        } else {
                            setNumberAt(row, col, 0);
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Gets a grid position (0<=row, col<=8) as a parameter and determines
     * all possible digits that can still be entered in this grid position,
     * so that the Sudoku is valid after the number has been entered.
     * @param row are row Values
     * @param col are column Values
     * @return possible Values for this position in row and column
     */
    private List<Integer> getPossibilities(int row, int col) {
        System.out.println(row + " " + col);
        List<Integer> possibleValues = new ArrayList<>();
        for (int n = 1; n <= SIZE; n++) {
            if (!isInRow(row, n) && !isInCol(col, n) && !isInBox3x3(row, col, n)) {
                possibleValues.add(n);
            }
        }

        return possibleValues;
    }

    /**
     * @param row    are the row Values
     * @param number the number to check
     * @return true, if the number found in this row
     */
    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board.get(row).get(i) == number)
                return true;
        }
        return false;
    }

    /**
     * @param col    are the column Values
     * @param number the number to check
     * @return true, if the number found in this column
     */
    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++) {
            if (board.get(i).get(col) == number) return true;
        }

        return false;
    }

    /**
     * @param row    are the row Values
     * @param col    are the column Values
     * @param number the number to check
     * @return true, if the number found in this 3x3 block
     */
    private boolean isInBox3x3(int row, int col, int number) {
        int rowrow = row - row % 3;
        int colcol = col - col % 3;
        for (int i = rowrow; i < rowrow + 3; i++) {
            for (int j = colcol; j < colcol + 3; j++) {
                if (getNumberAt(i,j) == number) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        board.forEach(row -> {
            row.forEach(col -> {
                output.append(col).append(" ");
            });
            output.append("\n");
        });

        return output.toString();
    }
}
