package chess.input;

import chess.board.IChessBoard;
import chess.game.ChessMove;
import chess.game.ICheckChecker;
import chess.pieces.IPiece;
import chess.pieces.Piece.Types;
import chess.player.Player;
import java.util.regex.Pattern;

public class InputChecker {

    private ClearPathChecker pathChecker;

    private static final String VALID_CHESS_MOVE = "[a-h][1-8]\\-[a-h][1-8]";

    public InputChecker(ClearPathChecker pathChecker) {
        this.pathChecker = pathChecker;
    }

    public boolean checkPlayerInput(
        String input,
        Player player,
        IChessBoard board,
        ICheckChecker checkChecker
    ) {
        return (
            correctInputSyntax(input) &&
            correctInputLogic(input, player, board, checkChecker)
        );
    }

    private boolean correctInputSyntax(String input) {
        boolean result = Pattern.matches(VALID_CHESS_MOVE, input);
        if (result == false) {
            System.out.println(
                "Input : " +
                input +
                " is a invalid move. Correct input format is (for example) a1-a3. Please try again"
            );
        }
        return result;
    }

    private boolean correctInputLogic(
        String input,
        Player player,
        IChessBoard board,
        ICheckChecker checkChecker
    ) {
        ChessMove attemptedMove = new ChessMove(input);
        IPiece pieceBeingMoved = board.getPieceBeingMoved(attemptedMove);

        if (pieceBeingMoved == null) {
            System.out.println("No piece found to move");
            return false;
        }
        if (!attemptedMove.isMovingToNewSquare()) {
            System.out.println("You must move your piece to a new square");
            return false;
        }

        if (pieceBeingMoved.getTeam() != player.getTeam()) {
            System.out.println("You may only move your own pieces");
            return false;
        }

        if (!pieceBeingMoved.moveValid(attemptedMove, board)) {
            System.out.println("You cannot move this piece in that way");
            return false;
        }

        if (moveTakingPieceOfSameTeam(attemptedMove, player, board)) {
            System.out.println("You cannot take a piece of the same team");
            return false;
        }

        if (!pathForAttemptedMoveClear(attemptedMove, board, pieceBeingMoved)) {
            System.out.println("You cannot move through a piece");
            return false;
        }

        if (
            movePuttingOwnKingInCheck(
                attemptedMove,
                player,
                board,
                checkChecker
            )
        ) {
            System.out.println(
                "You cannot make a move that puts your king in check"
            );
            return false;
        }

        return true;
    }

    private boolean moveTakingPieceOfSameTeam(
        ChessMove move,
        Player player,
        IChessBoard board
    ) {
        IPiece pieceBeingTaken = board.getPieceBeingTaken(move);
        if (
            pieceBeingTaken != null &&
            player.getTeam() == pieceBeingTaken.getTeam()
        ) {
            return true;
        }
        return false;
    }

    private boolean pathForAttemptedMoveClear(
        ChessMove move,
        IChessBoard board,
        IPiece piece
    ) {
        if (piece.getType() == Types.KNIGHT) {
            return true;
        }
        return pathChecker.pathForMoveClear(move, board.getPieceArray());
    }

    private boolean movePuttingOwnKingInCheck(
        ChessMove move,
        Player player,
        IChessBoard board,
        ICheckChecker checkChecker
    ) {
        return checkChecker.ownKingInCheck(move, player, board, pathChecker);
    }
}
