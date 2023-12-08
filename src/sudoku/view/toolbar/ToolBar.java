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
    public ImageIcon imageIcon;
    public   JComboBox jComboBox;
    public ToolBar(){
        boolean isDark = false;
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
                SudokuMain.solve();

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
        add(btn_solve);
        add(btn_newGame);
        add(jComboBox);
    }
}
