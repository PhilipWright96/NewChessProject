package game;

public class Coordinates {

    private int rowCoordinate;
    private int columnCoordinate;

    public Coordinates(int rowCoordinate, int columnCoordinate) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }

    public int getRowCoordinate() {
        return this.rowCoordinate;
    }

    public int getColumnCoordinate() {
        return this.columnCoordinate;
    }

    private void setRowAndColumnCoordinates(
        int rowCoordinate,
        int columnCoordinate
    ) {
        this.rowCoordinate = rowCoordinate;
        this.columnCoordinate = columnCoordinate;
    }
}
