package chess.game;

import java.util.regex.*;

import chess.board.IChessBoard;
import chess.pieces.IPiece;
import chess.player.Player;

public class InputChecker {
    private static final String VALID_CHESS_MOVE = "[a-h][1-8]\\-[a-h][1-8]";

    public static boolean checkPlayerInput(String input, Player player, IChessBoard board){
        return correctInputSyntax(input) && correctInputLogic(input, player, board);
    }

    private static boolean correctInputSyntax(String input){
        boolean result = Pattern.matches(VALID_CHESS_MOVE, input);
        if (result == false){
            System.out.println("Input : " + input + " is a invalid move. Correct input format is (for example) a1-a3. Please try again");
        }
        return result;
    }

    private static boolean correctInputLogic(String input, Player player, IChessBoard board){
        ChessMove attemptedMove = new ChessMove(input);
        IPiece pieceBeingMoved = board.getPieceBeingMoved(attemptedMove);

        if (pieceBeingMoved == null){
            System.out.println("No piece found to move");
            return false;
        };

        if (!attemptedMove.moveToNewSquare()){
            System.out.println("You must move your piece to a new square");
            return false;
        }

        if (pieceBeingMoved.getTeam() != player.getTeam()){
            System.out.println("You may only move your own pieces");
            return false;
        }

        if (!pieceBeingMoved.moveValid(attemptedMove)){
            System.out.println("You cannot move this piece in that way");
            return false;
        }

        if (moveTakingPieceOfSameTeam(attemptedMove, player, board)){
            System.out.println("You cannot take a piece of the same team");
            return false;
        }

        return true;
    }

    private static boolean moveTakingPieceOfSameTeam(ChessMove move, Player player, IChessBoard board){
        IPiece pieceBeingTaken = board.getPieceBeingTaken(move);
        if (pieceBeingTaken == null || player.getTeam() != pieceBeingTaken.getTeam()){
            return false;
        }
        return true;
    }
}