package chess.game;

import java.util.Arrays;
import java.util.regex.*;

import chess.pieces.Piece;
import chess.player.Player;

public class InputChecker {
    private static final String VALID_CHESS_MOVE = "[a-h][1-8]\\-[a-h][1-8]";

    public static boolean checkPlayerInput(String input, Player player, Piece[][] board){
        return correctInputSyntax(input) && correctInputLogic(input, player, board);
    }

    private static boolean correctInputSyntax(String input){
        boolean result = Pattern.matches(VALID_CHESS_MOVE, input);
        if (result == false){
            System.out.println("Input : " + input + " is a invalid move. Correct input format is (for example) a1-a3. Please try again");
        }
        return result;
    }

    private static boolean correctInputLogic(String input, Player player, Piece[][] board){

        char[] inputArray = input.toCharArray();
        char[] moveFrom = Arrays.copyOfRange(inputArray, 0, 2);
        
        int moveFromColumn = moveFrom[0] - 97;
        int moveFromRow = Character.getNumericValue(moveFrom[1] - 1);

        Piece pieceBeingMoved = board[moveFromColumn][moveFromRow];

        if (pieceBeingMoved == null){
            System.out.println("No piece found to move");
            return false;
        };

        if (pieceBeingMoved.getTeam() != player.getTeam()){
            System.out.println("You may only move your own pieces");
            return false;
        }

        return true;
    }
}