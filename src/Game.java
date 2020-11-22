import java.util.ArrayList;

import static java.lang.Math.max;

public class Game {

    public int HIGH = 100000;

    Board board;
    private final Color turn;
    private final Color me;
    private final Hand red;
    private final Hand blue;
    private final Card middle;
    private final Hand currentHand;

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
        ArrayList<Move> moves = new ArrayList<Move>();
        ArrayList<Move> priorityMoves = new ArrayList<Move>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.board.board[y][x].color == this.turn) {
                    for (Offset offset : this.currentHand.first.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        Move move = new Move(this.currentHand.first, offset, x, y);
                        if (board.validMove(move)) {
                            if (this.board.board[y + offset.y][x + offset.x].color != Color.PURPLE) {
                                priorityMoves.add(move);
                            } else {
                                moves.add(move);
                            }
                        }
                    }
                    for (Offset offset : this.currentHand.second.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        Move move = new Move(this.currentHand.second, offset, x, y);
                        if (board.validMove(move)) {
                            if (this.board.board[y + offset.y][x + offset.x].color != Color.PURPLE) {
                                priorityMoves.add(move);
                            } else {
                                moves.add(move);
                            }
                        }
                    }
                }
            }
        }
        priorityMoves.addAll(moves);
        return priorityMoves;
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
        int[][] canReachBlue = new int[5][5];
        int[][] canReachRed = new int[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.board.board[y][x] == Piece.BLUE) {
                    res += 3;
                    for (Offset offset : this.blue.first.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReachBlue[y + offset.y][x + offset.x] = 1;
                        }
                    }
                    for (Offset offset : this.blue.second.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReachBlue[y + offset.y][x + offset.x] = 1;
                        }
                    }
                }
                if (this.board.board[y][x] == Piece.RED) {
                    res -= 3;
                    for (Offset offset : this.blue.first.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReachRed[y + offset.y][x + offset.x] = 1;
                        }
                    }
                    for (Offset offset : this.blue.second.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReachRed[y + offset.y][x + offset.x] = 1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                res += 2 * canReachBlue[i][j];
                res -= 2 * canReachRed[i][j];
            }
        }
        return res;
    }

    Move negamaxRoot(int depth) {
        if(this.board.gameOver) {
            return null;
        }
        Move res = null;
        int alpha = -(HIGH + depth - 1);
        int beta = HIGH + depth - 1;
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

