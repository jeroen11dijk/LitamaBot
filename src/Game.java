import java.util.ArrayList;

import static java.lang.Math.max;

public class Game {

    public int HIGH = 100000;

    Board board;
    private Color turn;
    private Color me;
    private Hand red;
    private Hand blue;
    private Card middle;
    private Hand currentHand;

    Game(Board board, Color turn, Color me, Hand red, Hand blue, Card middle) {
        this.board = board;
        this.turn = turn;
        this.me = me;
        this.red = red;
        this.blue = blue;
        this.middle = middle;
        this.currentHand = this.turn == Color.RED ? this.red : this.blue;
    }

    ArrayList<Move> moveGen() {
        ArrayList<Move> res = new ArrayList<Move>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.board.board[y][x].color == this.turn) {
                    for (Offset offset : this.currentHand.first.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        Move move = new Move(this.currentHand.first, offset, x, y);
                        if (board.validMove(move)) {
                            res.add(move);
                        }
                    }
                    for (Offset offset : this.currentHand.second.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        Move move = new Move(this.currentHand.second, offset, x, y);
                        if (board.validMove(move)) {
                            res.add(move);
                        }
                    }
                }
            }
        }
        return res;
    }

    Game applyMove(Move move) {
        if(board.gameOver) {
            return this;
        }
        Board board = this.board.applyMove(move.offset, move.x, move.y);
        Color newTurn = this.turn == Color.RED ? Color.BLUE : Color.RED;
        Hand newHand;
        if (this.currentHand.first == move.card) {
            newHand = new Hand(this.middle, this.currentHand.second, this.turn);
        } else {
            newHand = new Hand(this.middle, this.currentHand.first, this.turn);
        }
        Card newMiddle = move.card;
        if (this.turn == Color.RED) {
            return new Game(board, newTurn, this.me, newHand, new Hand(this.blue.first, this.blue.second, Color.BLUE), newMiddle);
        } else {
            return new Game(board, newTurn, this.me, new Hand(this.red.first, this.red.second, Color.RED), newHand, newMiddle);
        }
    }

    int evaluate(int depth) {
        if (this.board.gameOver && this.board.winner == Color.BLUE) {
            return HIGH + depth;
        }
        if (this.board.gameOver && this.board.winner == Color.RED) {
            return -HIGH - depth;
        }

        int res = 0;
        res += this.board.nPieces(Color.BLUE);
        res -= this.board.nPieces(Color.RED);
        return res;
    }

    Move negamaxRoot(int depth) {
        if(this.board.gameOver) {
            return null;
        }
        Move res = null;
        int alpha = -Integer.MAX_VALUE;
        int beta = Integer.MAX_VALUE;
        int value = Integer.MIN_VALUE;
        int color = this.turn == Color.RED ? -1 : 1;
        for (Move move : this.moveGen()) {
            Game newGame = this.applyMove(move);
            int newValue = -newGame.negamaxRec(depth - 1, -beta, -alpha, -color);
            if (newValue > value) {
                res = move;
                value = newValue;
            }
            alpha = max(value, alpha);
            if (alpha >= beta) {
                break;
            }
        }
        return res;
    }

    Integer negamaxRec(int depth, int alpha, int beta, int color) {
        if (depth == 0 || this.board.gameOver) {
            return color * this.evaluate(depth);
        }
        int res = Integer.MIN_VALUE;
        for (Move move : this.moveGen()) {
            Game newGame = this.applyMove(move);
            res = max(res, -newGame.negamaxRec(depth - 1, -beta, -alpha, -color));
            alpha = max(res, alpha);
            if (alpha >= beta) {
                break;
            }
        }
        return res;
    }

    long perft(int depth) {
        if (depth == 0 || this.board.gameOver) {
            return 1;
        }
        long res = 0;
        ArrayList<Move> moves = this.moveGen();
        for (Move newMove : moves) {
            Game newGame = this.applyMove(newMove);
            res += newGame.perft(depth - 1);
        }
        return res;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + "\n" + board +
                ", turn=" + turn +
                ", red=" + red +
                ", blue=" + blue +
                ", middle=" + middle +
                ", currentHand=" + currentHand +
                '}';
    }
}

