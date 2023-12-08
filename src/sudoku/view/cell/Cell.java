package sudoku.view.cell;

import sudoku.enums.CellStatus;

import javax.swing.*;

public class Cell extends JTextField {
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    // Define named constants for JTextField's colors and fonts
    //  to be chosen based on sudoku.enums.CellStatus


    // Define properties (package-visible)
    /** The row and column number [0-8] of this cell */
    int row, col;
    /** The puzzle number [1-9] for this cell */
    int number;
    /** The status of this cell defined in enum sudoku.enums.CellStatus */
    public CellStatus status;

    /** Constructor */
    public Cell(int row, int col) {
        super();   // JTextField
        this.row = row;
        this.col = col;
        // Inherited from JTextField: Beautify all the cells once for all
        super.setHorizontalAlignment(JTextField.CENTER);
        super.setFont(CellResources.FONT_NUMBERS);
    }
    public void setNumber(int number){
        this.number = number;
        paint();
    }

    /** Reset this cell for a new game, given the puzzle number and isGiven */
    public void newGame(int number, boolean isGiven) {
        this.number = number;
        status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
        paint();    // paint itself
    }

    /** This sudoku2.view.cell.Cell (JTextField) paints itself based on its status */
    public void paint() {
        if (status == CellStatus.GIVEN) {
            // Inherited from JTextField: Set display properties
            super.setText(number + "");
            super.setEditable(false);
            super.setBackground(CellResources.BG_GIVEN);
            super.setForeground(CellResources.FG_GIVEN);
        } else if (status == CellStatus.TO_GUESS) {
            // Inherited from JTextField: Set display properties
            super.setText("");
            super.setEditable(true);
            super.setBackground(CellResources.BG_TO_GUESS);
            super.setForeground(CellResources.FG_NOT_GIVEN);
        } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
            super.setBackground(CellResources.BG_CORRECT_GUESS);
        } else if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
            super.setBackground(CellResources.BG_WRONG_GUESS);
        }
    }
    public int getNumber(){
        return number;
    }
    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
