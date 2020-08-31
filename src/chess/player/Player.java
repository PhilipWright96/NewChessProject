package chess.player;

import java.util.NoSuchElementException;
import java.util.Scanner;

import chess.util.Teams;

public class Player {
    private String username;
    private Teams team;

    public Player (String username, Teams team){
        this.username = username;
        this.team = team;
    }

    public Teams getTeam(){
        return this.team;
    }

    public String getPlayerInput(Scanner userInputScanner) {
        String response = null;
        try {
            while (response == null) {
                System.out.println("Player " + team + ": Play");
                response = userInputScanner.nextLine();
                return response;
            }
        }
        catch (IllegalStateException | NoSuchElementException e) {
            System.out.println("Exception found.");
            e.printStackTrace();
        }
        return null;
    }
    
}