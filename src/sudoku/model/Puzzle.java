package sudoku.model;

import sudoku.controller.SudokuMain;
import sudoku.puzzleRepo.Repo;

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

    //(For advanced students) use singleton design pattern for this class
}

