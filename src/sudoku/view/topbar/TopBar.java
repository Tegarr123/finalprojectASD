package sudoku.view.topbar;

import sudoku.controller.SudokuMain;
import sudoku.view.toolbar.ToolBarResources;

import javax.swing.*;

public class TopBar extends JPanel {
    public JLabel WMG7;
    public JLabel difficulty;
    public TopBar(){
        WMG7 = new JLabel("@made by group 7");
        WMG7.setFont(TopBarResources.FONT_TOPBAR);
        WMG7.setBackground(TopBarResources.BG_TOPBAR);
        WMG7.setForeground(TopBarResources.FG_TOPBAR);

        difficulty = new JLabel("Difficulty : EASY"+" - "+ SudokuMain.puzzle.getRandom+"     ");
        difficulty.setFont(TopBarResources.FONT_TOPBAR_DIFF);
        difficulty.setBackground(TopBarResources.BG_TOPBAR);
        difficulty.setForeground(TopBarResources.FG_TOPBAR);

    }
}
