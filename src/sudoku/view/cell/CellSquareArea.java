package sudoku.view.cell;

import sudoku.controller.Main;

import javax.swing.*;
import java.awt.*;

public class CellSquareArea extends JPanel {
    private GridLayout gridLayout = new GridLayout(3, 3);
    private int row;
    private int col;

    public CellSquareArea(int row, int col){
        this.row = row;
        this.col = col;
        init();
    }
    private void init(){
        super.setLayout(gridLayout);
        for (int r = 0 ; r < 3 ; r++){
            for (int c = 0 ; c < 3 ; c++){
                int rowTemp = 3 * this.row + r + 1;
                int colTemp = 3 * this.col + c + 1;
                if(Main.puzzle.canEdit(rowTemp, colTemp)){
                    add(new CellEdit(rowTemp, colTemp));
                } else {
                    add(new CellView(rowTemp, colTemp));
                }
            }
        }
    }




}
