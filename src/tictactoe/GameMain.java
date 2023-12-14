/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #7
 * 1 - 5026221169 - R. Farel Danendra Hendrawan Putra
 * 2 - 5026221095 - Nida Aulia Amartika
 * 3 - 5026221109 - Ahmad Fadhino Tegar Permana
 */
package tictactoe;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;

public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L;

    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.white;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;
    private int xWins=0;
    private int oWins=0;


    public GameMain(){
        super.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if(currentState == State.PLAYING){
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED){
                        currentState = board.stepGame(currentPlayer, row, col);
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                    }
                }else{
                    newGame();
                }
                repaint();
            }
        });
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));
        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END);
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, true));

        initGame();
        newGame();
    }
    public void initGame(){
        board = new Board();
    }
    public void newGame(){
        for (int row = 0 ; row < Board.ROWS ; row++){
            for (int col = 0 ; col < Board.COLS ; col++){
                board.cells[row][col].content = Seed.NO_SEED;
            }
        }
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(COLOR_BG);

        board.paint(g);

        if (currentState == State.PLAYING){
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "Crab's Turn" : "Octopus's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again");
        }
        if (currentState == State.CROSS_WON) {
            xWins++;
            statusBar.setForeground(Color.RED);
            statusBar.setText("Crab Wins! Poin : "+ xWins+" | Click To Continue");
            updateScoreboard();
        }else if (currentState == State.NOUGHT_WON) {
            oWins++;
            statusBar.setForeground(Color.RED);
            statusBar.setText("Octopus Wins! Poin : "+ oWins+" | Click To Continue");
            updateScoreboard();

        }
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(TITLE);
                frame.setContentPane(new GameMain());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }


    private void updateScoreboard() {

        statusBar.setForeground(Color.BLACK);

        if (xWins == 3) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("CRAB WON THE GAME!! Click to play again");
            //currentState = State.GAME_OVER;
            xWins=0;
            oWins=0;
        }
        else if (oWins == 3) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("OCTOPUS WON THE GAME!! Click to play again");
            //currentState = State.GAME_OVER;
            xWins=0;
            oWins=0;
        } else {
            statusBar.setForeground(Color.BLACK);
        }
    }



}
