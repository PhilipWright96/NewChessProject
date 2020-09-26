package chess.game;

import java.util.Arrays;

public class ChessMove {
    private int moveFromColumn;
    private int moveFromRow;
    private int moveToColumn;
    private int moveToRow;

    public ChessMove(String input){
        char[] inputArray = input.toCharArray();

        char[] moveFrom = Arrays.copyOfRange(inputArray, 0, 2);
        this.moveFromColumn = moveFrom[0] - 97;
        this.moveFromRow = Character.getNumericValue(moveFrom[1] - 1);

        char[] moveTo = Arrays.copyOfRange(inputArray, 3, 5);
        this.moveToColumn = moveTo[0] - 97;
        this.moveToRow = Character.getNumericValue(moveTo[1] - 1);
    }

    public int getMoveFromColumn(){
        return this.moveFromColumn;
    }

    public int getMoveFromRow(){
        return this.moveFromRow;
    }

    public int getMoveToColumn(){
        return this.moveToColumn;
    }

    public int getMoveToRow(){
        return this.moveToRow;
    }

    public int getRowChangeNum(){
        return Math.abs(getMoveFromRow() - getMoveToRow());
    }

    public int getColumnChangeNum(){
        return Math.abs(getMoveFromColumn() - getMoveToColumn());
    }

    public boolean moveToNewSquare(){
        return getMoveFromRow() != getMoveToRow() || getMoveFromColumn() != getMoveToColumn();
    }
}