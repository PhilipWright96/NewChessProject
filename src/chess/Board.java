package chess;

public class Board {
    private int BOARD_WIDTH = 8;
    private Piece[][] board;

    public Board(){
        this.board = new Piece[BOARD_WIDTH][BOARD_WIDTH];
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (i == 1 || i == BOARD_WIDTH - 2){
                    board[i][j] = new Pawn();
                }
            }
        }
    }

    public void showBoard(){
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (board[i][j] != null){
                    System.out.println(board[i][j].getType());
                }
            }
            if (i != BOARD_WIDTH - 1){
                System.out.println();
            }
        }
    }
}