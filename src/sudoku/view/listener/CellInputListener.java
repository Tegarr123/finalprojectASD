package sudoku.view.listener;

import sudoku.enums.CellStatus;
import sudoku.view.cell.Cell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellInputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get a reference of the JTextField that triggers this action event
        Cell sourceCell = (Cell) e.getSource();
        // Retrieve the int entered
        int numberIn = Integer.parseInt(sourceCell.getText());
        // For debugging
        System.out.println("You entered " + numberIn);
        if (numberIn == sourceCell.getNumber()) {
            sourceCell.setStatus(CellStatus.CORRECT_GUESS);
        } else {
            sourceCell.setStatus(CellStatus.WRONG_GUESS);
        }
        sourceCell.paint(); // re-paint this cell based on its status
    }
}
