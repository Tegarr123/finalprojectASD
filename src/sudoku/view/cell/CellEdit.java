package sudoku.view.cell;

import sudoku.controller.Main;
import sudoku.view.SudokuWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CellEdit extends JTextField {
    CellEdit(int row, int col){
        if(Main.puzzle.getNum(row, col) != 0){
            setText(String.valueOf(Main.puzzle.getNum(row, col)));
        }
        setFont(SudokuWindow.boardFont);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setRightColor();
        setHorizontalAlignment(JTextField.CENTER);
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(getDocument().getLength() > 1){
                    Main.puzzle.setNum(row, col, 0);
                    setWrongColor();
                }else{
                    changedUpdate(e);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getDocument().getLength() == 0){
                    Main.puzzle.setNum(row, col, 0);
                    setRightColor();
                }else {
                    changedUpdate(e);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try{
                    int value = Integer.valueOf(getDocument().getText(0, 1));
                    Main.puzzle.setNum(row, col, value);
                    if (Main.puzzle.check(row, col)){
                        setRightColor();
                    } else {
                        throw new Exception();
                    }
                }catch (Exception err){
                    Main.puzzle.setNum(row, col, 0);
                    setWrongColor();
                    return;
                }
                if (Main.puzzle.isWin()){
                    Main.solve();
                    int choice = JOptionPane.showConfirmDialog(null, "your time" + Main.getTime()
                                        + ", new game?","Sudoku | Completed", JOptionPane.DEFAULT_OPTION);
                    if (choice == 0){
                        Main.next();
                    }
                }
            }
        });

    }
    private void setRightColor(){
        setBackground(Color.white);
        setForeground(Color.BLACK);
    }
    private void setWrongColor(){
        setForeground(Color.white);
        setBackground(Color.RED);
    }
}
