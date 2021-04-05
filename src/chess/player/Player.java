package chess.player;

import chess.input.ScannerWrapper;
import chess.util.Teams;
import java.util.NoSuchElementException;

public class Player {

    private String username;
    private Teams team;

    public Player(String username, Teams team) {
        this.username = username;
        this.team = team;
    }

    public Teams getTeam() {
        return this.team;
    }

    public String getPlayerInput(ScannerWrapper userInputScanner) {
        String response = null;
        try {
            while (response == null) {
                System.out.println("Player " + team + ": Play");
                response = userInputScanner.nextLine();
                return response;
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Exception found.");
            e.printStackTrace();
        }
        return null;
    }
}
