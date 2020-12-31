package chess.input;

import chess.game.ClearPathChecker;
import chess.game.InputChecker;
import chess.game.InputRetriever;

public class InputManager {
    public InputRetriever buildRetriever(){
        ClearPathChecker pathChecker = new ClearPathChecker();
        InputChecker inputChecker = new InputChecker(pathChecker);
        return new InputRetriever(inputChecker);
    }
}
