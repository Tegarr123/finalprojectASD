package sudoku.controller;

import sudoku.model.SudokuConstants;
import sudoku.model.SudokuDiff;
import sudoku.puzzleRepo.Repo;
import sudoku.view.GameBoardPanel;
import sudoku.model.Puzzle;
import sudoku.view.toolbar.ToolBar;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
/**
 * The main Sudoku program
 */
    public class SudokuMain extends JFrame{
        private static final long serialVersionUID = 1L;  // to prevent serial warning
        // private variables
        public static GameBoardPanel board;
        public static Puzzle puzzle;
        public static ToolBar toolBar;

        public static boolean isDark = false;

        // Constructor
        public SudokuMain() {
            Repo.repoInit();
            toolBar = new ToolBar();
            puzzle = new Puzzle(SudokuDiff.EASY);
            board = new GameBoardPanel();


            add(board, BorderLayout.CENTER);
            add(toolBar, BorderLayout.PAGE_END);

            // Initialize the game board to start the game


            pack();     // Pack the UI components, instead of using setSize()
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
            setResizable(false);
            setTitle("Sudoku");
            setVisible(true);
        }
        public static void newGame(){

            String diff = (String) SudokuMain.toolBar.jComboBox.getSelectedItem();
            switch (diff){
                case "INTERMEDIATE":
                    puzzle.sudokuDiff = SudokuDiff.INTERMEDIATE;
                    break;
                case "CHALLENGING":
                    puzzle.sudokuDiff = SudokuDiff.CHALLENGING;
                    break;
                case "TOUGH":
                    puzzle.sudokuDiff = SudokuDiff.TOUGH;
                    break;
                case "SUPERTOUGH":
                    puzzle.sudokuDiff = SudokuDiff.SUPER_TOUGH;
                    break;
                case "INSANE":
                    puzzle.sudokuDiff = SudokuDiff.INSANE;
                    break;
                default:
                    puzzle.sudokuDiff = SudokuDiff.EASY;
                    break;
            }
            puzzle.init();
            board.newGame();
        }
        public static void solve() {
            boolean[] given = new boolean[SudokuConstants.GRID_SIZE];
            Arrays.fill(given, true);
            for (int i = 0 ; i < SudokuConstants.GRID_SIZE ; i++) puzzle.isGiven[i] = given;
            puzzle.solveSudoku(puzzle.numbers, 0, 0);
            board.newGame();
        }
        public static void switchTheme(){
            ImageIcon imageIcon;
            if (isDark){
                imageIcon = new ImageIcon(SudokuMain.class.getResource("../images/sun.png"));
                toolBar.setBackground(Color.WHITE);
            }else{
                toolBar.setBackground(new Color(8, 25, 65));
                imageIcon = new ImageIcon(SudokuMain.class.getResource("../images/moon.png"));
            }
            isDark = !isDark;
            board.newGame();
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
            toolBar.btn_theme.setIcon(new ImageIcon(newImage));
        }


    }

