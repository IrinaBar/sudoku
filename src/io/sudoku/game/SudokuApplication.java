package io.sudoku.game;

public class SudokuApplication {

    public static void main(String[] args) {
        String startValues =
                "400 000 000" +
                        "000 009 000" +
                        "000 000 785" +
                        "007 048 050" +
                        "001 300 000" +
                        "006 070 000" +
                        "860 000 903" +
                        "700 005 062" +
                        "003 700 000";
        Sudoku game = new Sudoku(startValues);


        game.setNumberAt(8,0,8);

        System.out.println(game.toString());
       /* String my=
                        "452687319" +
                        "378159426" +
                        "619423705" +
                        "237948651" +
                        "541362897" +
                        "986571234" +
                        "865214973" +
                        "794835162" +
                        "123796548";
        Sudoku test = new Sudoku(my);
        System.out.println(test.isValid());*/

       /* String prof =
                "0 3 0 0 0 0 0 0 0" +
                        "0 0 0 1 9 5 0 0 0" +
                        "0 0 8 0 0 0 0 6 0" +
                        "8 0 0 0 6 0 0 0 0" +
                        "4 0 0 8 0 0 0 0 1" +
                        "0 0 0 0 2 0 0 0 0" +
                        "0 6 0 0 0 0 2 8 0" +
                        "0 0 0 4 1 9 0 0 5" +
                        "0 0 0 0 0 0 0 7 0";

                        Sudoku test1 = new Sudoku(prof);
        test1.solve();
        System.out.println(test1.toString());*/

        /*System.out.println(game.toString());
        game.solve();
        if (game.isValid()) {
            System.out.println("Sudoku is valid");
        } else {
            System.out.println("Sudoku is not valid");
        }
        System.out.println(game.toString());*/
    }
}

