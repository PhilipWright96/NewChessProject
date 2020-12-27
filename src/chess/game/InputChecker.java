package chess.game;

import java.util.Scanner;
import java.util.regex.*;

import chess.board.IChessBoard;
import chess.pieces.IPiece;
import chess.pieces.Piece.Types;
import chess.player.Player;

public class InputChecker {
    private static final String VALID_CHESS_MOVE = "[a-h][1-8]\\-[a-h][1-8]";

    public static ChessMove getValidInputFromPlayer(Scanner userInputScanner, Player player, IChessBoard board){
        boolean inputValid = false;
        String input = null; 
        while (inputValid == false){
            input = player.getPlayerInput(userInputScanner);
            inputValid = InputChecker.checkPlayerInput(input, player, board);
        }
        return new ChessMove(input);
    }

    private static boolean checkPlayerInput(String input, Player player, IChessBoard board){
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

        if (!pieceBeingMoved.moveValid(attemptedMove, board)){
            System.out.println("You cannot move this piece in that way");
            return false;
        }

        if (moveTakingPieceOfSameTeam(attemptedMove, player, board)){
            System.out.println("You cannot take a piece of the same team");
            return false;
        }

        if (!pathForAttemptedMoveClear(attemptedMove, board, pieceBeingMoved)){
            System.out.println("You cannot move through a piece");
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

    private static boolean pathForAttemptedMoveClear(ChessMove move, IChessBoard board, IPiece piece){
        if (piece.getType() == Types.KNIGHT){
            return true;
        }
        return ClearPathChecker.pathForMoveClear(move, board.getChessBoard());
    }
}