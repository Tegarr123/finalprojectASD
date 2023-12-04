package sudoku;

import javax.swing.*;
import java.awt.*;

public class GameBoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();

    public GameBoardPanel(){
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));
        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE;col++){
                cells[row][col] = new Cell(row,col);
                super.add(cells[row][col]);
            }
        }


        super.setPreferredSize(new Dimension(BOARD_HEIGHT, BOARD_WIDTH));
    }
    public void newGame(){
        puzzle.newPuzzle(2);

        for (int row = 0 ;row < SudokuConstants.GRID_SIZE ; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE ; col++){
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }
    public boolean isSolved(){
        for (int row = 0 ; row < SudokuConstants.GRID_SIZE ; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE ; col++){
                if(cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS){
                    return false;
                }
            }
        }
        return true;
    }
}
