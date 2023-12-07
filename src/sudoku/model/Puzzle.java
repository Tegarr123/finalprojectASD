package sudoku.model;

import java.util.Random;

public class Puzzle {
    private CellComponent sudokuCells[][];
    private CellComponent toGuessCell[];
    private int numOfToGuessCell;
    private boolean hasAnswer;
    private double diffCoef;

    public Puzzle(double diffCoef){
        this.diffCoef = diffCoef;
        newGame();
    }
    public Puzzle(CellComponent[][] readSudokuData){
        update(readSudokuData);
    }

    public void init(){
        sudokuCells = new CellComponent[11][11];
        toGuessCell = new CellComponent[81];
        numOfToGuessCell = 0;
        hasAnswer = false;
    }
    public void newGame(){
        Random randomizer = new Random();
        randomizer.setSeed(System.currentTimeMillis());
        if(diffCoef > 1 || diffCoef < 0){
            diffCoef = 0.36;
        }
        int counter = (int) Math.ceil(diffCoef * 81);
        do {
            init();
            for (int now = 0 ; now < counter ; now++){
                int row = randomizer.nextInt(9)+1;
                int col = randomizer.nextInt(9)+1;
                int times = 0;
                if(sudokuCells[row][col] == null ||sudokuCells[row][col].dataNum == 0){
                    do {
                        sudokuCells[row][col] = new CellComponent(row, col, randomizer.nextInt(9)+1);
                        update(sudokuCells);
                        times++;
                        if(times == 9){
                            sudokuCells[row][col] = null;
                            update(sudokuCells);
                            now--;
                            break;
                        }
                    } while (check(row, col)==false);
                }else {
                    now--;
                }
            }
            setHasAns();
        }while (hasAnswer == false);
    }
    private void update(CellComponent[][] readSudokuData){
        init();
        for (int row = 1 ; row <= 9 ; row++){
            for (int col = 1 ; col <= 9 ; col++){
                sudokuCells[row][col] = readSudokuData[row][col];
                if(sudokuCells[row][col] == null || sudokuCells[row][col].dataNum == 0){
                    sudokuCells[row][col] = new CellComponent(row, col);
                    toGuessCell[numOfToGuessCell] = sudokuCells[row][col];
                    numOfToGuessCell++;
                }
            }
        }
    }
    public boolean check(int row, int col){
        for (int i = 1 ; i<=9;i++){
            if (i != col && sudokuCells[row][col].dataNum == sudokuCells[row][i].dataNum){
                return false;
            }
            if (i != row && sudokuCells[row][col].dataNum == sudokuCells[i][col].dataNum){
                return false;
            }
        }
        for (int r  = (row - 1) / 3 * 3 + 1 ; r <= (row + 2) / 3 * 3 ; r++){
            for (int c = (col - 1) / 3 * 3 + 1 ; c <= (col + 2) / 3 * 3 ; c++){
                if (r != row && c != col && sudokuCells[row][col].dataNum == sudokuCells[r][c].dataNum ){
                    return false;
                }
            }
        }
        return true;
    }
    public int getNum(int row, int col){
        return sudokuCells[row][col].dataNum;
    }
    public boolean canEdit(int row, int col){
        return sudokuCells[row][col].canEdit;
    }
    public void setNum(int row, int col, int num){
        sudokuCells[row][col].dataNum = num;
    }
    public boolean solve(int countCellSolved){
        if (countCellSolved == numOfToGuessCell){
            return true;
        }
        for (int i = 1 ; i <= 9 ; i++){
            setNum(toGuessCell[countCellSolved].row, toGuessCell[countCellSolved].col, i);
            if (check(toGuessCell[countCellSolved].row, toGuessCell[countCellSolved].col) && solve(countCellSolved + 1)){
                return true;
            }
        }
        setNum(toGuessCell[countCellSolved].row, toGuessCell[countCellSolved].col, 0);
        return false;
    }
    private void setHasAns(){
        Puzzle backup = new Puzzle(sudokuCells);
        backup.solve(0);
        for (int row = 1 ; row <= 9 ; row ++){
            for (int col = 1 ; col <= 9 ; col++){
                if (backup.sudokuCells[row][col].dataNum != this.sudokuCells[row][col].dataNum){
                    hasAnswer = true;
                    return;
                }
            }
        }
        hasAnswer = false;
    }
    public boolean isWin(){
        for (int r = 1 ; r <= 9 ; r++){
            for (int c = 1 ; c <= 9 ; c++){
                if (sudokuCells[r][c].dataNum == 0 || check(r, c) == false){
                    return false;
                }
            }
        }
        return true;
    }
}
