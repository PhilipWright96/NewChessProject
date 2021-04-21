package board.GUI;

import game.*;
import pieces.*;

public interface IChessBoardGUI {
    public void initializeBoardGUI();

    public void updateBoardWithPawns();

    public void updateBoardWithSpecialPieces();

    public void updateBoardWithNewMove(
        ChessMove newMove,
        IPiece pieceBeingMoved
    );
}
