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
import sudoku.puzzleRepo.Repo;
import sudoku.view.cell.Cell;

import java.util.*;

public class Puzzle {
    // All variables have package access
    // The numbers on the puzzle
    public int[][] numbers;
    // The clues - isGiven (no need to guess) or need to guess
    public boolean[][] isGiven;
    public SudokuDiff sudokuDiff;
    public int getRandom;

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
        // I hardcode a puzzle here for illustration and testing.
        numbers =   new int[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        isGiven  = new boolean[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
        Random randomizer = new Random();
        randomizer.setSeed(System.currentTimeMillis());
        this.getRandom = randomizer.nextInt(10000);
        String sudokuString = switch (sudokuDiff) {
            case INTERMEDIATE -> Repo.intermediate.get(getRandom);
            case CHALLENGING -> Repo.challenging.get(getRandom);
            case TOUGH -> Repo.tough.get(getRandom);
            case SUPER_TOUGH -> Repo.superTough.get(getRandom);
            case INSANE -> Repo.insane.get(getRandom);
            default -> Repo.easy.get(getRandom);
        };
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
    public boolean solveSudoku(){
        Stack<Cell> stack = new Stack<>();
        boolean[][] isLocked = setLocked(numbers);
        int curRow = 0;
        int curCol = 0;
        int curValue =1;
        int time = 0 ;
        System.out.println("==="+"UNSOLVED"+"===");
        for(int r = 0 ; r < SudokuConstants.GRID_SIZE ; r++){
            for (int c = 0 ; c < SudokuConstants.GRID_SIZE ; c++){
                System.out.print(numbers[r][c] + " ");
            }
            System.out.println();
        }
        while(stack.size() < SudokuConstants.GRID_SIZE*SudokuConstants.GRID_SIZE){

            time++;
            if(isLocked[curRow][curCol]){
                Cell lockedCell = new Cell(curRow, curCol);
                lockedCell.setNumber(numbers[curRow][curCol]);
                stack.push(lockedCell);
                curRow = curRow + (curCol+1)/9;
                curCol = (curCol+1)%9;
                continue;
            }
            for (;curValue <= 9 ; curValue++){
                if (isValid(numbers, curRow, curCol, curValue)){
                    break;
                }
            }
            if(curValue <= 9){
                Cell cell = new Cell(curRow, curCol);
                cell.setNumber(curValue);
                numbers[curRow][curCol] = curValue;
                isGiven[curRow][curCol] = true;
                stack.push(cell);
                curRow = curRow + (curCol+1)/9;
                curCol = (curCol+1)%9;
                curValue = 1;
            }else{
                if (stack.size() > 0) {
                    // Assign to a Cell variable the top of the stack (stack.pop())
                    Cell cell = stack.pop();
                    // while the Cell is locked
                    while (isLocked[cell.getRow()][cell.getCol()]) {
                        // if stack size is greater than 0
                        if (stack.size() > 0) {
                            // assign to the Cell variable the top of the stack (i.e. pop)
                            cell = stack.pop();
                        } else {
                            // print out the number of steps (time)
                            // return false (no solution found)
                            System.out.println("Number of steps: " + time);
                            return false;
                        }
                    }
                    // assign to curRow the row value of the Cell
                    curRow = cell.getRow();
                    // assign to curCol the col value of the Cell
                    curCol = cell.getCol();
                    // assign to curValue the value of the Cell + 1
                    curValue = cell.getNumber() + 1;
                    // set the value of the board Cell at curRow, curCol to 0
                    numbers[curRow][curCol] =  0;
                } else {
                    // print out the number of steps (time)
                    // return false (no solution found)
                    System.out.println("Number of steps: " + time);
                    return false;
                }
            }
        }
        System.out.println("==="+"SOLVED"+"===");
        for(int r = 0 ; r < SudokuConstants.GRID_SIZE ; r++){
            for (int c = 0 ; c < SudokuConstants.GRID_SIZE ; c++){
                System.out.print(numbers[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("Number of steps: " + time);

        return true;
    }
    public boolean[][] setLocked(int[][] board){
        boolean[][] isLocked = new boolean[9][9];
        for(int r = 0 ; r < 9 ; r++){
            for(int c = 0 ; c < 9 ; c++){
                if(board[r][c]!=0){
                    isLocked[r][c] = true;
                }
            }
        }

        return isLocked;
    }

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
            if (isValid(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1))
                    return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }
    public boolean isValid(int[][] board, int row, int col, int currValue){
        // check row
        for(int r = 0 ; r < 9 ; r++){
            if(r != row && board[r][col] == currValue){
                return false;
            }
        }
        //check column
        for(int c = 0 ; c < 9 ; c++){
            if(c != col && board[row][c] == currValue){
                return false;
            }
        }
        int rowStartSquare = row - (row%3);
        int colStartSquare = col - (col%3);
        for(int r = 0 ; r < 3 ; r++){
            for(int c = 0 ; c < 3 ; c++){
                if(r != row && c != col && board[rowStartSquare+r][colStartSquare+c] == currValue){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValid(int row, int col) {
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
                if (numbers[r][c] == 0 || isValid(r, c) == false) {
                    return false;
                }
            }
        }
        return true;
    }

}