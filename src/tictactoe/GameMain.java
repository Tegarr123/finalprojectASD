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

import HomePage.PhotoFrame;
import sudoku.controller.SudokuMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;

public class GameMain extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.white;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_GROSS = new Color(239, 105, 80);
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225);
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;
    private BottomBar bottomBar;
    private int xWins=0;
    private int oWins=0;
    private String player1name;
    private String player2name;
    private JFrame frame;

    public String getPlayer1name(){
        return player1name;
    }

    public String getPlayer2name(){
        return player2name;
    }
    public GameMain(){
        JOptionPane.showMessageDialog(this,"GET 3 POINTS TO WIN THIS GAME, LET'S GO!");
        player1name = JOptionPane.showInputDialog(this, "Enter your name as Crab:", "Player 1", JOptionPane.PLAIN_MESSAGE);
        player2name = JOptionPane.showInputDialog(this, "Enter your name as Octopus:", "Player 2", JOptionPane.PLAIN_MESSAGE);
        if (player1name == null || player1name.trim().isEmpty()) {
            player1name = "Crab";
        }

        if (player2name == null || player2name.trim().isEmpty()) {
            player2name = "Octopus";
        }
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
        //super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 70));
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 74));
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, true));


        board.path[0] = "src/tictactoe/pantai.jpg";
        board.path[1] = "src/tictactoe/bg-2.jpeg";
        board.path[2] = "src/tictactoe/bg-3.jpeg";
        initGame();
        bottomBar.buttonSwitchBackground.addActionListener(e -> {
            board.removeAll();
            board.loadImage();
            board.initGame();
            board.newGame();
            updateUI();

        });
        bottomBar.add(bottomBar.buttonSwitchBackground);
        add(board, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.NORTH);
        newGame();

        bottomBar.add(bottomBar.toHomePage);
        add(board, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.NORTH);
        bottomBar.toHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                currentFrame.dispose();
                new PhotoFrame();
            }
        });
        bottomBar.add(bottomBar.toSudoku);
        add(board, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.NORTH);

        bottomBar.add(bottomBar.toSudoku);
        add(board, BorderLayout.CENTER);
        add(bottomBar, BorderLayout.NORTH);
        bottomBar.toSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
                currentFrame.dispose();
                new SudokuMain();
            }
        });
    }
    public void initGame(){
        board = new Board();
        bottomBar = new BottomBar();

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
            statusBar.setText((currentPlayer == Seed.CROSS) ? player1name +"'s Turn" : player2name+"'s Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again");
        } else if (currentState == State.CROSS_WON) {
            xWins++;
            statusBar.setForeground(Color.RED);
            statusBar.setText(player1name+" Win! Poin : "+ xWins+" | Click to play again");
            updateScoreboard();
        }else if (currentState == State.NOUGHT_WON) {
            oWins++;
            statusBar.setForeground(Color.RED);
            statusBar.setText(player2name+" Win! Poin : "+ oWins+" | Click to play again");
            updateScoreboard();
        }
    }


    private void updateScoreboard() {

        statusBar.setForeground(Color.BLACK);

        if (xWins == 3) {
            JOptionPane.showMessageDialog(this, player1name+"'s Points : "+xWins+ " | "+player2name+"'s Points : "+oWins+"\n"+player1name+" WIN!!");
            xWins=0;
            oWins=0;
            currentPlayer = Seed.CROSS;
            statusBar.setText((currentPlayer == Seed.CROSS) ? player1name +"'s Turn" : player2name+"'s Turn");
            newGame();
        }
        else if (oWins == 3) {
            JOptionPane.showMessageDialog(this, player1name+"'s Points : "+xWins+ " | "+player2name+"'s Points : "+oWins+"\n"+player2name+" WIN!!");
            xWins=0;
            oWins=0;
            currentPlayer = Seed.CROSS;
            statusBar.setText((currentPlayer == Seed.CROSS) ? player1name +"'s Turn" : player2name+"'s Turn");
            newGame();
        } else {
            statusBar.setForeground(Color.BLACK);
        }


    }



}


