package chess.board;

import java.awt.image.*;
import javax.imageio.*;

import chess.pieces.Piece;
import chess.pieces.PieceFactory;
import chess.util.Teams;

import java.net.*;
import java.io.*;
import java.util.*;

public final class ChessSprites {
    private ChessSprites() {}
    public static final int SIZE = 64;
    public static final BufferedImage SHEET;
    static {
        try {
            // see https://stackoverflow.com/a/19209651/2891664
            SHEET = ImageIO.read(new URL("https://i.stack.imgur.com/memI0.png"));
        } catch (IOException x) {
            throw new UncheckedIOException(x);
        }
    }
    public static final BufferedImage GOLD_QUEEN    = SHEET.getSubimage(0 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_QUEEN  = SHEET.getSubimage(0 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_KING     = SHEET.getSubimage(1 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_KING   = SHEET.getSubimage(1 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_ROOK     = SHEET.getSubimage(2 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_ROOK   = SHEET.getSubimage(2 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_KNIGHT   = SHEET.getSubimage(3 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_KNIGHT = SHEET.getSubimage(3 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_BISHOP   = SHEET.getSubimage(4 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_BISHOP = SHEET.getSubimage(4 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_PAWN     = SHEET.getSubimage(5 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_PAWN   = SHEET.getSubimage(5 * SIZE, SIZE, SIZE, SIZE);
    public static final List<BufferedImage> SPRITES_IN_ORDER =
        Collections.unmodifiableList(Arrays.asList(GOLD_ROOK,  SILVER_ROOK,
                                                   GOLD_KNIGHT,   SILVER_KNIGHT,
                                                   GOLD_BISHOP,   SILVER_BISHOP,
                                                   GOLD_KING, SILVER_KING,
                                                   GOLD_QUEEN, SILVER_QUEEN,
                                                   GOLD_BISHOP,  SILVER_BISHOP,
                                                   GOLD_KNIGHT, SILVER_KNIGHT, 
                                                   GOLD_ROOK, SILVER_ROOK));

    private static final HashMap<Piece, BufferedImage> pieceToImage  = new HashMap<Piece, BufferedImage>() {{ 
        put(PieceFactory.constructPiece(Piece.Types.QUEEN, Teams.GOLD), GOLD_QUEEN);
        put(PieceFactory.constructPiece(Piece.Types.QUEEN, Teams.SILVER), SILVER_QUEEN);
        put(PieceFactory.constructPiece(Piece.Types.KING, Teams.GOLD), GOLD_KING);
        put(PieceFactory.constructPiece(Piece.Types.KING, Teams.SILVER), SILVER_KING);
        put(PieceFactory.constructPiece(Piece.Types.ROOK, Teams.GOLD), GOLD_ROOK);
        put(PieceFactory.constructPiece(Piece.Types.ROOK, Teams.SILVER), SILVER_ROOK);
        put(PieceFactory.constructPiece(Piece.Types.KNIGHT, Teams.GOLD), GOLD_KNIGHT);
        put(PieceFactory.constructPiece(Piece.Types.KNIGHT, Teams.SILVER), SILVER_KNIGHT);
        put(PieceFactory.constructPiece(Piece.Types.BISHOP, Teams.GOLD), GOLD_BISHOP);
        put(PieceFactory.constructPiece(Piece.Types.BISHOP, Teams.SILVER), SILVER_BISHOP);
        put(PieceFactory.constructPiece(Piece.Types.PAWN, Teams.GOLD), GOLD_PAWN);
        put(PieceFactory.constructPiece(Piece.Types.PAWN, Teams.SILVER), SILVER_PAWN);
    }};

    public static BufferedImage getCorrespondingImageFromPiece(Piece piece){
        return pieceToImage.get(piece);
    }
}