package chess;

public class Board {
    private int BOARD_WIDTH = 8;
    private char[][] board;

    public Board(){
        this.board = new char[BOARD_WIDTH][BOARD_WIDTH];
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (i == 0 || i == BOARD_WIDTH - 1){
                    board[i][j] = 'x';
                }
            }
        }
    }

    public void showBoard(){
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                System.out.print(this.board[i][j]);
            }
            if (i != BOARD_WIDTH - 1){
                System.out.println();
            }
        }
    }
}