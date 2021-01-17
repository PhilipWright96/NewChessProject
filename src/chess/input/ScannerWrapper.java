package chess.input;

import java.util.Scanner;

public class ScannerWrapper {
    private Scanner userInputScanner;

    public ScannerWrapper(){
        userInputScanner = new Scanner(System.in);
    }

    public String nextLine(){
        return userInputScanner.nextLine();
    }

    public void close(){
        userInputScanner.close();
    }
    
}
