package sudoku.view.toolbar;

import sudoku.controller.SudokuMain;
import sudoku.model.SudokuConstants;
import sudoku.model.SudokuDiff;
import sudoku.view.cell.CellResources;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    public JButton btn_newGame;
    public JButton btn_solve;
    public   JComboBox jComboBox;
    public ToolBar(){
        btn_solve = new JButton("SOLVE");
        btn_solve.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_solve.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_solve.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_solve.addActionListener(e -> {
                SudokuMain.solve();

        });
        add(btn_solve);

        btn_newGame = new JButton("NEW GAME");
        btn_newGame.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_newGame.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_newGame.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_newGame.addActionListener(e ->{
            SudokuMain.newGame();
        });
        add(btn_newGame);

        String[] diff = {"EASY", "INTERMEDIATE", "CHALLENGING","TOUGH", "SUPERTOUGH","INSANE"};
        jComboBox = new JComboBox<>(diff);
        add(jComboBox);
    }
}
