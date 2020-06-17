package io.sudoku.game;

import java.util.stream.Stream;

public interface ISudoku {
    /**
     * @param row Values to get.
     * @param col are column Values to get.
     * @return in place: row,column.
     */
    int getNumberAt(int row, int col);

    /**
     * are used to access the digits in the grid (the count starts each time)at 0).
     *
     * @param row    Values to set.
     * @param col    are column Values to set.
     * @param number Value to set.
     */
    void setNumberAt(int row, int col, int number);

    /**
     * checks whether the current state of the Sudoku is valid,
     * i.e. whether the rules have not been violated.
     * The puzzle does not have to be completely solved yet -
     * there may be zeros.
     *
     * @return true if the rules are not violated.
     */
    boolean isValid();

    /**
     *
     * @return whether sudoku is Done or not
     */
    default boolean isDone() {
        for (int row = 0; row < 9; row++) { // run along the rows
            for (int col = 0; col < 9; col++) { // run along the columns
                if (getNumberAt(row, col) == 0)return false;
            }
        }
        return isValid();
    }

    /**
     * Called to solve an incomplete sudoku puzzle.
     *
     * @return returns false if this is not possible - otherwise it returns true
     */
    boolean solve();

    /**
     * provides a string representation of the current puzzle state
     * with 9 lines and one space between the digits
     *
     * @return sudoku as a String
     */
    String toString();

}

