package input;

import input.ClearPathChecker.*;
import input.InputChecker.*;
import input.InputRetriever.*;
import input.ScannerWrapper.*;

public class InputManager {

    public InputRetriever buildRetriever() {
        ClearPathChecker pathChecker = new ClearPathChecker();
        InputChecker inputChecker = new InputChecker(pathChecker);
        return new InputRetriever(inputChecker, new ScannerWrapper());
    }
}
