/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package HomePage;

import sudoku.controller.SudokuMain;
import tictactoe.GameMain;

import javax.swing.*;
import java.awt.*;

public class PhotoFrame extends JFrame {
    public ButtonPanel buttonPanel;
    PhotoPanel photoPanel;
    public PhotoFrame(){
        buttonPanel = new ButtonPanel();
        photoPanel = new PhotoPanel();
        setTitle("Group 7 Homepage");
        buttonPanel.sudokuButton.addActionListener(e -> {
            this.dispose();
            new SudokuMain();
        });
        buttonPanel.tictactoe.addActionListener(e -> {
            this.dispose();
            JFrame frame = new JFrame("TIC TAC TOE");
            frame.setContentPane(new GameMain());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        add(photoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
class PhotoPanel extends JPanel{
    Image image;
    PhotoPanel(){
        getImage();
        setPreferredSize(new Dimension(832,394));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0,832,394, null);
    }
    public void getImage(){
        ImageIcon imageIcon = new ImageIcon(PhotoFrame.class.getResource("fotogroup.jpg"));
        image = imageIcon.getImage();
    }
}
class ButtonPanel extends JPanel{
    JButton sudokuButton;
    JButton tictactoe;
    ButtonPanel(){
        sudokuButton = new JButton("PLAY SUDOKU");
        add(sudokuButton);
        sudokuButton.setFont(new Font("Comic Sans MS",Font.BOLD,18));

        tictactoe = new JButton("PLAY TICTACTOE");
        add(tictactoe);
        tictactoe.setFont(new Font("Comic Sans MS",Font.BOLD,18));
    }
}
