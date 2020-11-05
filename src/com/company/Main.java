package com.company;


public class Main {

    public static void main(String[] args) {
        // https://discordapp.com/channels/348658686962696195/424610429634084867/772901206414065715
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        Game game = new Game(board, turn, red, blue, middle);
        long time = System.nanoTime();
        long res = board.perft(7, game);
        float time2 = (System.nanoTime() - time) / 1000000000.f;
        System.out.println(time2);
        System.out.println(res);
        System.out.println(res / time2);
//        for (Game newGame : game.notAMoveGen()) {
//            System.out.println(newGame);
//        }
    }

    // blue = ox, boar, red = horse, elephant, side = crab
}
