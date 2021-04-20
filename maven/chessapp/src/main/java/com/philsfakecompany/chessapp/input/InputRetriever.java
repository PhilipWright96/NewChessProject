package com.philsfakecompany.chessapp.input.InputRetriever;

import com.philsfakecompany.chessapp.board.IChessBoard.*;
import com.philsfakecompany.chessapp.game.ChessMove.*;
import com.philsfakecompany.chessapp.game.ICheckChecker.*;
import com.philsfakecompany.chessapp.input.InputChecker.*;
import com.philsfakecompany.chessapp.input.ScannerWrapper.*;
import com.philsfakecompany.chessapp.player.Player.*;

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
