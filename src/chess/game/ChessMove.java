package chess.game;

import java.util.Arrays;

public class ChessMove {

    private int moveFromColumn;
    private int moveFromRow;
    private int moveToColumn;
    private int moveToRow;

    public ChessMove(String input) {
        char[] inputArray = input.toCharArray();

        char[] moveFrom = Arrays.copyOfRange(inputArray, 0, 2);
        moveFromColumn = moveFrom[0] - 97;
        moveFromRow = Character.getNumericValue(moveFrom[1] - 1);

        char[] moveTo = Arrays.copyOfRange(inputArray, 3, 5);
        moveToColumn = moveTo[0] - 97;
        moveToRow = Character.getNumericValue(moveTo[1] - 1);
    }

    public ChessMove(
        int moveFromColumn,
        int moveFromRow,
        int moveToColumn,
        int moveToRow
    ) {
        this.moveFromColumn = moveFromColumn;
        this.moveFromRow = moveFromRow;
        this.moveToColumn = moveToColumn;
        this.moveToRow = moveToRow;
    }

    public int getMoveFromColumn() {
        return moveFromColumn;
    }

    public int getMoveFromRow() {
        return moveFromRow;
    }

    public int getMoveToColumn() {
        return moveToColumn;
    }

    public int getMoveToRow() {
        return moveToRow;
    }

    public int getRowChangeNum() {
        return Math.abs(moveFromRow - moveToRow);
    }

    public int getColumnChangeNum() {
        return Math.abs(moveFromColumn - moveToColumn);
    }

    public boolean isMovingToNewSquare() {
        return (moveFromRow != moveToRow || moveFromColumn != moveToColumn);
    }

    public boolean isStraight() {
        if (moveFromColumn == moveToColumn || moveFromRow == moveToRow) {
            return true;
        }
        return false;
    }

    public boolean isHorizontal() {
        if (moveFromColumn != moveToColumn) {
            return true;
        }
        return false;
    }
}
