package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Cell {
    public static final int SIZE = 120;
    public static final int PADDING = SIZE / 5;
    public static final int SEED_SIZE = SIZE - PADDING*2;
    public static final int SEED_STROKE_WIDTH = 8;

    public static final Image CROSS_ICON =new ImageIcon("src/tictactoe/crab-3.png").getImage();
    public static final Image NOUGHT_ICON= new ImageIcon("src/tictactoe/octopus-3.png").getImage();

    Seed content;
    int row, col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.content = Seed.NO_SEED;
    }
    public void newGame(){
        content = Seed.NO_SEED;
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(SEED_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int x1 = col * SIZE + PADDING;
        int y1 = row * SIZE + PADDING;
        if(this.content==Seed.CROSS){
            g.drawImage(CROSS_ICON, x1,y1, SIZE-2*PADDING, SIZE-2*PADDING, null);
        }else if(this.content==Seed.NOUGHT){
            g.drawImage(NOUGHT_ICON, x1,y1, SIZE-2*PADDING, SIZE-2*PADDING, null);
        }
    }

}
