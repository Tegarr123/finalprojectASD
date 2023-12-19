/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
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

        difficulty = new JLabel("Difficulty : EASY"+" - "+ (SudokuMain.puzzle.getRandom+1)+"     ");
        difficulty.setFont(TopBarResources.FONT_TOPBAR_DIFF);
        difficulty.setBackground(TopBarResources.BG_TOPBAR);
        difficulty.setForeground(TopBarResources.FG_TOPBAR);

    }
}
