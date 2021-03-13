package chess.input;

import chess.board.IChessBoard;
import chess.player.Player;
import chess.game.ChessMove;
import chess.game.ICheckChecker;

public class InputRetriever {
    private ScannerWrapper userInputScanner;
    private InputChecker inputChecker;

    public InputRetriever(InputChecker inputChecker){
        userInputScanner = new ScannerWrapper();
        this.inputChecker = inputChecker;
    }

    public ChessMove getValidInputFromPlayer(Player player, IChessBoard board, ICheckChecker checkChecker){
        boolean inputValid = false;
        String input = null; 
        while (inputValid == false){
            input = player.getPlayerInput(userInputScanner);
            inputValid = inputChecker.checkPlayerInput(input, player, board, checkChecker);
        }
        return new ChessMove(input);
    }

    public void closeScanner(){
        userInputScanner.close();
    }
}