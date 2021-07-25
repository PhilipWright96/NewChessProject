package game;

import board.*;
import input.*;
import player.*;

public interface ICheckChecker {
    public boolean ownKingInCheck(
        ChessMove move,
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    );

    public boolean opposingKingInCheck(
        Player playerMoving,
        IChessBoard board,
        ClearPathChecker pathChecker
    );

    public boolean opposingKingInCheckmate(
        Player playerMoving,
        IChessBoard board,
        ChessMove checkMove,
        ClearPathChecker pathChecker
    );
}
