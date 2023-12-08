package sudoku1.view;

import javax.swing.*;
import java.awt.*;

public class SudokuWindow extends JFrame {

    private SudokuBody sudokuBody;
    private ToolBar toolBar;
    public static Font boardFont = new Font("Comic Sans MS", Font.BOLD, 32);
    public static Font toolFont = new Font("Microsoft YaHei", Font.PLAIN, 15);


    public SudokuWindow(){
        sudokuBody = new SudokuBody();
        toolBar = new ToolBar();
        init();
    }
    private void init(){
        add(sudokuBody,BorderLayout.CENTER);
        add(toolBar,BorderLayout.PAGE_END);
        setTitle("Sudoku");
        setSize(450, 550);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void newGame(){
        sudokuBody.newGame();
        toolBar.newGame();
    }
    public void stop(){
        sudokuBody.newGame();
        toolBar.stop();
    }
}
