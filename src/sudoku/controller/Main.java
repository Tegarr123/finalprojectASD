package sudoku.controller;

import sudoku.model.Puzzle;
import sudoku.view.SudokuBody;
import sudoku.view.SudokuWindow;

public class Main {
    private static SudokuWindow sudokuWindow;
    public static Puzzle puzzle;
    private static double diffCoef = 0.36;
    Main(){
        GameTimer.init();
        puzzle = new Puzzle(diffCoef);
        sudokuWindow = new SudokuWindow();

    }


    public static void next(){
        GameTimer.init();
        puzzle.newGame();
        sudokuWindow.newGame();
    }
    public static void solve(){
        puzzle.solve(0);
        sudokuWindow.stop();
    }
    public static String getTime(){
        String gameTime = GameTimer.getTime();
        GameTimer.updateTime();
        return gameTime;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
