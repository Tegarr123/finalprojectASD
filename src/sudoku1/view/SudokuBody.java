package sudoku1.view;

import sudoku1.view.cell.CellSquareArea;

import javax.swing.*;
import java.awt.*;

public class SudokuBody extends JPanel {
    private GridLayout gridLayout = new GridLayout(3,3);

    SudokuBody(){
        init();
    }
    private void init(){
        super.setLayout(gridLayout);
        for (int row = 0; row < 3 ; row++){
            for (int col = 0 ; col < 3 ; col++){
                CellSquareArea cellSquareArea = new CellSquareArea(row, col);
                cellSquareArea.setBackground(Color.white);
                cellSquareArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                add(cellSquareArea);
            }
        }
    }
    void newGame(){
        removeAll();
        init();
        updateUI();
    }
}
