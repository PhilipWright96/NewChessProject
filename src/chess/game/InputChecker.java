package chess.game;

import java.util.regex.*;

public class InputChecker {
    private static final String VALID_CHESS_MOVE = "[a-e][1-8]\\-[a-e][1-8]";

    public static boolean checkPlayerInput(String input){
        return correctInputSyntax(input);
    }

    private static boolean correctInputSyntax(String input){
        boolean result = Pattern.matches(VALID_CHESS_MOVE, input);
        if (result == false){
            System.out.println("Input : " + input + " is a invalid move. Correct input format is a1-a3. Please try again");
        }
        return result;
    }
}