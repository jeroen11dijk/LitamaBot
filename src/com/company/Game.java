package com.company;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class Game {

    Board board;
    private Color turn;
    private Hand red;
    private Hand blue;
    private Card middle;
    private Hand currentHand;

    Game(Board board, Color turn, Hand red, Hand blue, Card middle) {
        this.board = board;
        this.turn = turn;
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
                        if(board.validMove(move)) {
                            res.add(move);
                        }
                    }
                    for (Offset offset : this.currentHand.second.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        Move move = new Move(this.currentHand.second, offset, x, y);
                        if(board.validMove(move)) {
                            res.add(move);
                        }
                    }
                }
            }
        }
        return res;
    }

    Game applyMove(Move move) {
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
            return new Game(board, newTurn, newHand, new Hand(this.blue.first, this.blue.second, Color.BLUE), newMiddle);
        } else {
            return new Game(board, newTurn, new Hand(this.red.first, this.red.second, Color.RED), newHand, newMiddle);
        }
    }

    int evaluate() {
        if (this.board.gameOver) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        int[][] canReach = new int[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.board.board[y][x].color == this.turn) {
                    res += 1;
                    for (Offset offset : this.currentHand.first.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReach[y + offset.y][x + offset.x] = 1;
                        }
                    }
                    for (Offset offset : this.currentHand.second.offsets) {
                        if (this.board.validMove(offset, x, y)) {
                            canReach[y + offset.y][x + offset.x] = 1;
                        }
                    }
                }
            }
        }
        for (int [] column : canReach) {
            for (int i :column) {
                res += 2*i;
            }
        }
        return res;
    }

    private void addGame(ArrayList<Game> res, Board board, Color newTurn, Hand change, Card newMiddle) {
        if (this.turn == Color.RED) {
            res.add(new Game(board, newTurn, change, new Hand(this.blue.first, this.blue.second, Color.BLUE), newMiddle));
        } else {
            res.add(new Game(board, newTurn, new Hand(this.red.first, this.red.second, Color.RED), change, newMiddle));
        }
    }

    public int alphabeta(int depth, int alpha, int beta) {
        if (depth == 0 || this.board.gameOver) {
            return this.evaluate();
        }
        if (this.turn == Color.BLUE) {
            int value = Integer.MIN_VALUE;
            for (Move move : this.moveGen()) {
                Game newGame = this.applyMove(move);
                value = max(value, newGame.alphabeta(depth - 1, alpha, beta));
                alpha = max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Move move : this.moveGen()) {
                Game newGame = this.applyMove(move);
                value = min(value, newGame.alphabeta(depth - 1, alpha, beta));
                beta = min(beta, value);
                if (beta <= alpha) {
                    break;
                }
            }
            return value;
        }
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", turn=" + turn +
                ", red=" + red +
                ", blue=" + blue +
                ", middle=" + middle +
                ", currentHand=" + currentHand +
                '}';
    }
}

