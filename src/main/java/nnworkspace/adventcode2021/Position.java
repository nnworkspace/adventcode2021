package nnworkspace.adventcode2021;

public class Position {
    private int rowIdx, colIdx;

    public Position(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }
}
