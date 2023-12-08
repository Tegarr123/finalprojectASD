package sudoku1.model;

public class CellComponent {
    int row;
    int col;
    int dataNum;
    boolean canEdit;

    public CellComponent(int row, int col, int dataNum) {
        this.row = row;
        this.col = col;
        this.dataNum = dataNum;
        this.canEdit = false;
    }

    public CellComponent(int row, int col) {
        this.row = row;
        this.col = col;
        this.dataNum = 0;
        this.canEdit = true;
    }

}
