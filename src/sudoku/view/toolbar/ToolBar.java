/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
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
    public JButton btn_theme;
    public JButton btn_hint;
    public ImageIcon imageIcon;
    public   JComboBox jComboBox;
    public ToolBar(){
        btn_hint = new JButton("HINT");
        btn_hint.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_hint.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_hint.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_hint.addActionListener(e -> {
            SudokuMain.hint();
        });

        imageIcon = new ImageIcon(ToolBar.class.getResource("../../images/sun.png"));
        Image image = imageIcon.getImage();
        Image newImageSun = image.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        btn_theme = new JButton(new ImageIcon(newImageSun));
        btn_theme.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_theme.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_theme.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_theme.addActionListener(e -> {
            SudokuMain.switchTheme();
        });

        btn_solve = new JButton("SOLVE");
        btn_solve.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_solve.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_solve.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_solve.addActionListener(e -> {
                SudokuMain.backtrackSolve();

        });
        btn_newGame = new JButton("NEW GAME");
        btn_newGame.setBackground(ToolBarResources.BG_TOOLBAR);
        btn_newGame.setForeground(ToolBarResources.FG_TOOLBAR);
        btn_newGame.setFont(ToolBarResources.FONT_TOOLBAR);
        btn_newGame.addActionListener(e ->{
            SudokuMain.newGame();
        });


        String[] diff = {"EASY", "INTERMEDIATE", "CHALLENGING","TOUGH", "SUPERTOUGH","INSANE"};
        jComboBox = new JComboBox<>(diff);

        add(btn_theme);
        add(btn_hint);
        add(btn_solve);
        add(btn_newGame);
        add(jComboBox);
    }
}
