public class Main {

    public static void main(String[] args) {
        // https://discordapp.com/channels/348658686962696195/424610429634084867/772901206414065715
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        System.out.println(game.negamaxRoot(10));
//        game = game.applyMove(openingMoveBlue);
//        Move openingMoveRed = game.alphabeta(6, Integer.MIN_VALUE, Integer.MAX_VALUE).move;
//        System.out.println(openingMoveRed);
    }

    public static Piece[][] parseString(String board) {
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
        return res;
    }

    // blue = ox, boar, red = horse, elephant, side = crab
}
