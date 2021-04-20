package com.philsfakecompany.chessapp.input.InputManager;

import com.philsfakecompany.chessapp.input.ClearPathChecker.*;
import com.philsfakecompany.chessapp.input.InputChecker.*;
import com.philsfakecompany.chessapp.input.InputRetriever.*;
import com.philsfakecompany.chessapp.input.ScannerWrapper.*;

public class InputManager {

    public InputRetriever buildRetriever() {
        ClearPathChecker pathChecker = new ClearPathChecker();
        InputChecker inputChecker = new InputChecker(pathChecker);
        return new InputRetriever(inputChecker, new ScannerWrapper());
    }
}
