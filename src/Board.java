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

    Board(String board) {
        Piece[][] res = new Piece[5][5];
        String[] boardArray =  board.split("(?<=\\G.{5})");
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                int piece = Character.getNumericValue(boardArray[y].charAt(x));
                if (piece == 0) {
                    res[y][x] = Piece.EMPTY;
                }
                if (piece == 1) {
                    res[y][x] = Piece.BLUE;
                }
                if (piece == 2) {
                    res[y][x] = Piece.BLUEFIRE;
                }
                if (piece == 3) {
                    res[y][x] = Piece.RED;
                }
                if (piece == 4) {
                    res[y][x] = Piece.REDOX;
                }
            }
        }
        this.board = res;
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
        if (this.board[y][x] == Piece.REDOX && y + offset.y == 0 && x + offset.x == 2) {
            res.gameOver = true;
            res.winner = Color.RED;
        }
        if (this.board[y][x] == Piece.BLUEFIRE && y + offset.y == 4 && x + offset.x == 2) {
            res.gameOver = true;
            res.winner = Color.BLUE;
        }
        return res;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 4; i > -1; i--) {
            res.append(Arrays.toString(this.board[i]));
            if (i != 0) {
                res.append("\n");
            }
        }
        return res.toString();
    }
}
