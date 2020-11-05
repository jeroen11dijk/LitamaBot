package com.company;

import java.util.ArrayList;

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

    ArrayList<Game> notAMoveGen() {
        ArrayList<Game> res = new ArrayList<Game>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (this.board.board[y][x].color == this.turn) {
                    for (Offset offset : this.currentHand.first.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        if (this.board.validMove(offset, x, y)) {
                            Board board = this.board.applyMove(offset, x, y);
                            Color newTurn = this.turn == Color.RED ? Color.BLUE : Color.RED;
                            Hand change = new Hand(this.middle, this.currentHand.second, this.turn);
                            Card newMiddle = this.currentHand.first;
                            addGame(res, board, newTurn, change, newMiddle);
                        }
                    }
                    for (Offset offset : this.currentHand.second.offsets) {
                        if (this.turn == Color.RED) {
                            offset = offset.invert();
                        }
                        if (this.board.validMove(offset, x, y)) {
                            Board board = this.board.applyMove(offset, x, y);
                            Color newTurn = this.turn == Color.RED ? Color.BLUE : Color.RED;
                            Hand change = new Hand(this.middle, this.currentHand.first, this.turn);
                            Card newMiddle = this.currentHand.second;
                            addGame(res, board, newTurn, change, newMiddle);
                        }
                    }
                }
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

