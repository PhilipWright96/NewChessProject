package chess.player;

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
    
}