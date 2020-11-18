public class Main {

    public static void main(String[] args) {
        // https://discordapp.com/channels/348658686962696195/424610429634084867/772901206414065715
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        Game game = new Game(board, turn, red, blue, middle);
        System.out.println(game.negamaxRoot(2, Integer.MIN_VALUE, Integer.MAX_VALUE));
//        game = game.applyMove(openingMoveBlue);
//        Move openingMoveRed = game.alphabeta(6, Integer.MIN_VALUE, Integer.MAX_VALUE).move;
//        System.out.println(openingMoveRed);
    }

    // blue = ox, boar, red = horse, elephant, side = crab
}
