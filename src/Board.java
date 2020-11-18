import java.util.Arrays;

public class Board {

    Piece[][] board;
    boolean gameOver = false;
    Color winner = Color.PURPLE;

    Board() {
        this.board = new Piece[][]{
                {Piece.BLUE, Piece.BLUE, Piece.BLUEFIRE, Piece.BLUE, Piece.BLUE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.RED, Piece.REDOX, Piece.RED, Piece.RED}};
    }

    private Board(Board board) {
        Piece[][] newBoard = new Piece[5][];
        for (int i = 0; i < board.board.length; i++) {
            newBoard[i] = board.board[i].clone();
        }
        this.board = newBoard;
        this.gameOver = board.gameOver;
        this.winner = board.winner;
    }

    Board(Piece[][] board) {
        this.board = board;
    }


    Boolean validMove(Move move) {
        return validMove(move.offset, move.x, move.y);
    }

    Boolean validMove(Offset offset, int x, int y) {
        boolean yOnBoard = (y + offset.y < 5) && (y + offset.y > -1);
        boolean xOnBoard = (x + offset.x < 5) && (x + offset.x > -1);
        return yOnBoard && xOnBoard && (this.board[y][x].color != this.board[y + offset.y][x + offset.x].color);
    }


    Board applyMove(Offset offset, int x, int y) {
        Board res = new Board(this);
        res.board[y + offset.y][x + offset.x] = res.board[y][x];
        res.board[y][x] = Piece.EMPTY;
        if (this.board[y + offset.y][x + offset.x] == Piece.REDOX) {
            res.gameOver = true;
            res.winner = Color.BLUE;
        }
        if (this.board[y + offset.y][x + offset.x] == Piece.BLUEFIRE) {
            res.gameOver = true;
            res.winner = Color.RED;
        }
        return res;
    }

    public int nPieces(Color color) {
        int res = 0;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if(board[y][x].color == color) {
                    res++;
                }
            }
        }
        return res;
    }

    public String toString() {
        String res = "";
        for (int i = 4; i > -1; i--) {
            res += Arrays.toString(this.board[i]);
            if (i != 0) {
                res += "\n";
            }
        }
        return res;
    }
}
