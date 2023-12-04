package sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;

    GameBoardPanel boardPanel = new GameBoardPanel();
    JButton jButton = new JButton("new game");

    public SudokuMain(){
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(boardPanel, BorderLayout.CENTER);

        boardPanel.newGame();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);
    }

    public static void main(String[] args) {
        SudokuMain sudokuMain = new SudokuMain();
    }
}
