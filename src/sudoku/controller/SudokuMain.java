/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package sudoku.controller;


import sudoku.model.SudokuDiff;
import sudoku.puzzleRepo.Repo;
import sudoku.view.GameBoardPanel;
import sudoku.model.Puzzle;
import sudoku.view.cell.Cell;
import sudoku.view.toolbar.ToolBar;
import sudoku.view.topbar.TopBar;
import sudoku.view.topbar.TopBarResources;

import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.*;
/**
 * The main Sudoku program
 */
    public class SudokuMain extends JFrame{
        @Serial
        private static final long serialVersionUID = 1L;  // to prevent serial warning
        // private variables
        public static GameBoardPanel board;
        public static Puzzle puzzle;
        public static ToolBar toolBar;
        public static ArrayList<Cell> toGuessCell = new ArrayList<>();
        public static Puzzle backup;

        public static TopBar topbar;
        public static boolean isDark = false;

        // Constructor
        public SudokuMain() {
            Repo.repoInit();
            toolBar = new ToolBar();
            puzzle = new Puzzle(SudokuDiff.EASY);
            board = new GameBoardPanel();
            backup  = puzzle.getSolvedBackUp();
            topbar = new TopBar();
            topbar.add(topbar.difficulty);
            topbar.add(topbar.WMG7);


            add(topbar, BorderLayout.NORTH);
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
            Cell.getOptionPane = true;


            String diff = (String) SudokuMain.toolBar.jComboBox.getSelectedItem();
            topbar.difficulty.setText("Difficulty : " + diff+" - "+(puzzle.getRandom+1)+"     ");
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
                case null:
                    break;
                default:
                    puzzle.sudokuDiff = SudokuDiff.EASY;
                    break;
            }
            puzzle.init();
            backup = puzzle.getSolvedBackUp();
            board.newGame();

        }

        public static void backtrackSolve(){
            Cell.getOptionPane = false;
            boolean solve = puzzle.solveSudoku();
            System.out.println("Sudoku is " + ((solve) ? "solved" : "unsolved"));
            board.newGame();

        }
        public static void switchTheme(){
            ImageIcon imageIcon;
            if (isDark){
                imageIcon = new ImageIcon(Objects.requireNonNull(SudokuMain.class.getResource("../images/sun.png")));
                toolBar.setBackground(Color.WHITE);
                topbar.setBackground(Color.white);
                topbar.WMG7.setForeground(TopBarResources.FG_TOPBAR);
                topbar.difficulty.setForeground(TopBarResources.FG_TOPBAR);
            }else{
                toolBar.setBackground(new Color(8, 25, 65));
                topbar.setBackground(new Color(8, 25, 65));
                topbar.WMG7.setForeground(TopBarResources.FG_TOPBAR_DARK);
                topbar.difficulty.setForeground(TopBarResources.FG_TOPBAR_DARK);
                imageIcon = new ImageIcon(Objects.requireNonNull(SudokuMain.class.getResource("../images/moon.png")));
            }
            isDark = !isDark;
            board.newGame();
            Image image = imageIcon.getImage();
            Image newImage = image.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
            toolBar.btn_theme.setIcon(new ImageIcon(newImage));
        }
        public static void hint(){

            Random randomize = new Random();
            int getRandom = randomize.nextInt(0,toGuessCell.size());
            Cell getCellToSolve = toGuessCell.remove(getRandom);
            int row = getCellToSolve.getRow();
            int col = getCellToSolve.getCol();
            puzzle.numbers[row][col] = backup.numbers[row][col];
            puzzle.isGiven[row][col] = true;
            board.newGame();

        }




    }

