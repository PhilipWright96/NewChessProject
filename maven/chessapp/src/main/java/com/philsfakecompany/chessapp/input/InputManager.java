package input;

public class InputManager {

    public InputRetriever buildRetriever() {
        ClearPathChecker pathChecker = new ClearPathChecker();
        InputChecker inputChecker = new InputChecker(pathChecker);
        return new InputRetriever(inputChecker, new ScannerWrapper());
    }
}
