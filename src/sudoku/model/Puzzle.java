/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package sudoku.model;

import sudoku.controller.SudokuMain;
import sudoku.puzzleRepo.Repo;
import sudoku.view.cell.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    public int[][] numbers;
    // The clues - isGiven (no need to guess) or need to guess
    public boolean[][] isGiven;
    public SudokuDiff sudokuDiff;

    // Constructor
    public Puzzle(SudokuDiff sudokuDiff) {
        super();
        this.sudokuDiff = sudokuDiff;
        init();
    }

    // Generate a new puzzle given the number of cells to be guessed, which can be used
    //  to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven
    public void init() {
        System.out.println(sudokuDiff);
        // I hardcode a puzzle here for illustration and testing.
        numbers =   new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        isGiven  = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        Random randomizer = new Random();
        randomizer.setSeed(System.currentTimeMillis());
        int getRandom = randomizer.nextInt(10000);
        String sudokuString;
        switch (sudokuDiff){
            case INTERMEDIATE:
                sudokuString = Repo.intermediate.get(getRandom);
                break;
            case CHALLENGING:
                sudokuString = Repo.challenging.get(getRandom);
                break;
            case TOUGH:
                sudokuString = Repo.tough.get(getRandom);
                break;
            case SUPER_TOUGH:
                sudokuString = Repo.superTough.get(getRandom);
                break;
            case INSANE:
                sudokuString = Repo.insane.get(getRandom);
                break;
            default:
                sudokuString = Repo.easy.get(getRandom);
                break;
        }
        int i = 0;
        for(int r = 0 ; r < SudokuConstants.GRID_SIZE ; r++){
            for (int c = 0 ; c < SudokuConstants.GRID_SIZE; c++){
                int numericValue = Character.getNumericValue(sudokuString.charAt(i));
                this.numbers[r][c] = numericValue;
                i++;
            }
        }

        // Copy from hardcodedNumbers into the array "numbers"


        // Need to use input parameter cellsToGuess!
        // Hardcoded for testing, only 2 cells of "8" is NOT GIVEN


        // Copy from hardcodedIsGiven into array "isGiven"
        for (int row = 0; row < SudokuConstants.GRID_SIZE; ++row) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; ++col) {
                if (numbers[row][col] != 0){
                    isGiven[row][col] = true;
                }
            }
        }
    }
    public Puzzle getSolvedBackUp(){
        Puzzle backup = new Puzzle(sudokuDiff);
        for(int r = 0 ; r < SudokuConstants.GRID_SIZE ; r++){
            for (int c = 0 ; c < SudokuConstants.GRID_SIZE ; c++){
                backup.numbers[r][c] = this.numbers[r][c];
                backup.isGiven[r][c] = this.isGiven[r][c];
            }
        }
        backup.solveSudoku(backup.numbers, 0 , 0);
        return backup;
    }

    //(For advanced students) use singleton design pattern for this class

    public boolean solveSudoku(int grid[][], int row,
                               int col)  {


        if (row == SudokuConstants.GRID_SIZE - 1 && col == SudokuConstants.GRID_SIZE)
            return true;

        if (col == SudokuConstants.GRID_SIZE) {
            row++;
            col = 0;
        }


        if (grid[row][col] != 0)
            return solveSudoku(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {


            if (isSafe(grid, row, col, num)) {


                grid[row][col] = num;

                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }
    boolean isSafe(int[][] grid, int row, int col,
                          int num)
    {


        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;

        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;


        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }
    public boolean check(int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (i != col && numbers[row][col] == numbers[row][i]) {
                return false;
            }
            if (i != row && numbers[row][col] == numbers[i][col]) {
                return false;
            }
        }
        for (int r = (row) / 3 * 3; r < (row + 3) / 3 * 3 ; r++) {
            for (int c = (col ) / 3 * 3 ; c < (col + 3) / 3 * 3 ; c++) {
                if (r != row && c != col && numbers[row][col] == numbers[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isWin() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (numbers[r][c] == 0 || check(r, c) == false) {
                    return false;
                }
            }
        }
        return true;
    }
}

