/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package sudoku.view.cell;

import sudoku.controller.SudokuMain;
import sudoku.model.Puzzle;
import sudoku.model.SudokuConstants;
import sudoku.view.GameBoardPanel;
import sudoku.view.listener.CellInputListener;

import javax.swing.*;
import java.awt.*;

public class CellSquareArea extends JPanel {
    GridLayout gridLayout = new GridLayout(SudokuConstants.SUBGRID_SIZE, SudokuConstants.SUBGRID_SIZE);
    private int row;
    private int col;
    CellInputListener cellInputListener = new CellInputListener();

    /** It also contains a sudoku.model.Puzzle with array numbers and isGiven */

    public CellSquareArea(int row, int col){

        this.row = row;
        this.col = col;
        initialize();
    }
    public void initialize(){
        super.setLayout(gridLayout);
        for (int r = 0 ; r < SudokuConstants.SUBGRID_SIZE ; r++){
            for (int c = 0 ; c < SudokuConstants.SUBGRID_SIZE ; c++){
                int cellRow = 3 * this.row + r;
                int cellCol = 3 * this.col + c;
                Cell newCell = new Cell(cellRow, cellCol);
                newCell.newGame(SudokuMain.puzzle.numbers[cellRow][cellCol],
                        SudokuMain.puzzle.isGiven[cellRow][cellCol]);
                if (SudokuMain.puzzle.isGiven[cellRow][cellCol]){
                    newCell.addActionListener(cellInputListener);
                }
                if (!SudokuMain.puzzle.isGiven[cellRow][cellCol]) SudokuMain.toGuessCell.add(newCell);
                add(newCell);
            }
        }
    }

}
