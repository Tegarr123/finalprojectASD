package sudoku.view.cell;

import sudoku.controller.Main;
import sudoku.view.SudokuWindow;

import javax.swing.*;
import java.awt.*;

public class CellView extends JLabel {
    CellView(int row, int col){
        super(String.valueOf(Main.puzzle.getNum(row, col)), JLabel.CENTER);
        setFont(SudokuWindow.boardFont);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setForeground(Color.BLACK);
    }
}
