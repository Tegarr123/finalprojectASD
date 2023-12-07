package sudoku.view;

import sudoku.controller.Main;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JPanel {
    private ToolTimer toolTimer;

    ToolBar(){
        init();
    }
    private void init(){
        JLabel kel7 = new JLabel("Made by Group 7");
        kel7.setFont(SudokuWindow.toolFont);
        add(kel7);

        JButton btn_newGame = new JButton("New Game");
        btn_newGame.setFont(SudokuWindow.toolFont);
        btn_newGame.addActionListener(e -> {
            Main.next();
        });
        add(btn_newGame);

        JButton btn_solve = new JButton("Answer");
        btn_solve.setFont(SudokuWindow.toolFont);
        btn_solve.addActionListener(e -> {
            Main.solve();
                }
        );
        add(btn_solve);
        toolTimer = new ToolTimer();
        toolTimer.init();
        add(toolTimer);
    }
    void newGame(){
        stop();
        toolTimer.init();
    }
    void stop(){
        toolTimer.stop();
    }
    class ToolTimer extends JLabel{
        private Timer timer;
        ToolTimer(){
            timer = new Timer(1000, e -> {
                setText(Main.getTime());
            });
            setFont(SudokuWindow.toolFont);
        }
        void init(){
            timer.start();
            setText(Main.getTime());
        }
        void stop(){
            timer.stop();
        }
    }
}
