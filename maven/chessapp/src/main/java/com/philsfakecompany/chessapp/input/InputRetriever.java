package input;

import board.*;
import game.*;
import player.*;

public class InputRetriever {

    private ScannerWrapper userInputScanner;
    private InputChecker inputChecker;

    public InputRetriever(
        InputChecker inputChecker,
        ScannerWrapper scannerWrapper
    ) {
        this.userInputScanner = scannerWrapper;
        this.inputChecker = inputChecker;
    }

    public ChessMove getValidInputFromPlayer(
        Player player,
        IChessBoard board,
        ICheckChecker checkChecker
    ) {
        boolean inputValid = false;
        String input = null;
        while (inputValid == false) {
            input = player.getPlayerInput(userInputScanner);
            inputValid =
                inputChecker.checkPlayerInput(
                    input,
                    player,
                    board,
                    checkChecker
                );
        }
        return new ChessMove(input);
    }

    public void closeScanner() {
        userInputScanner.close();
    }
}
