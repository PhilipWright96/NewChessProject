package chess.pieces;

import chess.game.ChessMove;
import chess.util.Teams;

public class Pawn extends Piece{

    private boolean hasMoved = false;

    public boolean moveValid(ChessMove move){
        if (checkCorrectColumn(move) && checkCorrectRow(move)){
            hasMoved = true;
            return true;
        }
        return false;
    }

    private boolean checkCorrectColumn(ChessMove move){
        if (move.getMoveFromColumn() == move.getMoveToColumn()){
            return true;
        }
        return false;
    }

    private boolean checkCorrectRow(ChessMove move){
        if (getTeam() == Teams.SILVER){
            if (hasMoved == false && move.getMoveFromRow() - 2 == move.getMoveToRow()){
                return true;
            }
            else if (move.getMoveFromRow() - 1 == move.getMoveToRow()){
                return true;
            }
        }
        else {
            if (hasMoved == false && move.getMoveFromRow() + 2 == move.getMoveToRow()){
                return true;
            }
            else if (move.getMoveFromRow() + 1 == move.getMoveToRow()){
                return true;
            }
        }
        return false;
    }
}