package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardPanel extends JPanel {
    private class CellInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get a reference of the JTextField that triggers this action event
            Cell sourceCell = (Cell)e.getSource();

            // Retrieve the int entered
            int numberIn = Integer.parseInt(sourceCell.getText());
            // For debugging
            System.out.println("You entered " + numberIn);

            /*
             * [TODO 5] (later - after TODO 3 and 4)
             * Check the numberIn against sourceCell.number.
             * Update the cell status sourceCell.status,
             * and re-paint the cell via sourceCell.paint().
             */
            //if (numberIn == sourceCell.number) {
            //   sourceCell.status = CellStatus.CORRECT_GUESS;
            //} else {
            //   ......
            //}
            //sourceCell.paint();   // re-paint this cell based on its status
            if(numberIn == sourceCell.number){
                sourceCell.status = CellStatus.CORRECT_GUESS;
            }else{
                sourceCell.status = CellStatus.WRONG_GUESS;
            }
            sourceCell.paint();

            /*
             * [TODO 6] (later)
             * Check if the player has solved the puzzle after this move,
             *   by calling isSolved(). Put up a congratulation JOptionPane, if so.
             */
            if(isSolved()){
                JOptionPane.showMessageDialog(null, "Congratulation!");
            }
        }
    }
    private static final long serialVersionUID = 1L;

    public static final int CELL_SIZE = 60;
    public static final int BOARD_WIDTH = CELL_SIZE * SudokuConstants.GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * SudokuConstants.GRID_SIZE;

    private Cell[][] cells = new Cell[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];
    private Puzzle puzzle = new Puzzle();

    public GameBoardPanel(){
        CellInputListener listener = new CellInputListener();
        super.setLayout(new GridLayout(SudokuConstants.GRID_SIZE, SudokuConstants.GRID_SIZE));
        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE;col++){
                cells[row][col] = new Cell(row,col);
                super.add(cells[row][col]);
                if (cells[row][col].isEditable()) {
                    cells[row][col].addActionListener(listener);   // For all editable rows and cols
                }
            }
        }


        super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }
    public void newGame(){
        puzzle.newPuzzle(2);

        for (int row = 0 ;row < SudokuConstants.GRID_SIZE ; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE ; col++){
                cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
            }
        }
    }
    public boolean isSolved(){
        for (int row = 0 ; row < SudokuConstants.GRID_SIZE ; row++){
            for (int col = 0 ; col < SudokuConstants.GRID_SIZE ; col++){
                if(cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS){
                    return false;
                }
            }
        }
        return true;
    }
}
