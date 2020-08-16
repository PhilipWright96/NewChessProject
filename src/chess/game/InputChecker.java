package chess.game;

import java.util.Arrays;
import java.util.regex.*;

import chess.pieces.Piece;

public class InputChecker {
    private static final String VALID_CHESS_MOVE = "[a-e][1-8]\\-[a-e][1-8]";

    public static boolean checkPlayerInput(String input, Piece[][] board){
        return correctInputSyntax(input) && correctInputLogic(input, board);
    }

    private static boolean correctInputSyntax(String input){
        boolean result = Pattern.matches(VALID_CHESS_MOVE, input);
        if (result == false){
            System.out.println("Input : " + input + " is a invalid move. Correct input format is a1-a3. Please try again");
        }
        return result;
    }

    private static boolean correctInputLogic(String input, Piece[][] board){

        char[] inputArray = input.toCharArray();
        char[] moveFrom = Arrays.copyOfRange(inputArray, 0, 2);
        
        int moveFromColumn = moveFrom[0] - 97;
        int moveFromRow = Character.getNumericValue(moveFrom[1] - 1);

        if (board[moveFromColumn][moveFromRow] == null){
            System.out.println("No piece found to move");
            return false;
        };
        return true;
    }
}