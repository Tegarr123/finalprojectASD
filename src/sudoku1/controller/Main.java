package sudoku1.controller;

import sudoku1.model.Puzzle;
import sudoku1.view.SudokuWindow;

public class Main {
    private static SudokuWindow sudokuWindow;
    public static Puzzle puzzle;
    private static double diffCoef = 0.76;
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
