package chess.game;

import java.util.Scanner;

import chess.board.IChessBoard;
import chess.player.Player;

public class InputRetriever {
    private Scanner userInputScanner;
    private InputChecker inputChecker;

    public InputRetriever(InputChecker inputChecker){
        userInputScanner = new Scanner(System.in);
        this.inputChecker = inputChecker;
    }

    public ChessMove getValidInputFromPlayer(Player player, IChessBoard board){
        boolean inputValid = false;
        String input = null; 
        while (inputValid == false){
            input = player.getPlayerInput(userInputScanner);
            inputValid = inputChecker.checkPlayerInput(input, player, board);
        }
        return new ChessMove(input);
    }

    public void closeScanner(){
        userInputScanner.close();
    }
}