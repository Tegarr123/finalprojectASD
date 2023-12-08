package sudoku1.view.cell;

import sudoku1.controller.Main;
import sudoku1.view.SudokuWindow;

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
