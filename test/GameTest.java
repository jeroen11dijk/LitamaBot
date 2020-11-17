import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

class GameTest {

    Game setUp() {
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        return new Game(board, turn, red, blue, middle);
    }

    @Test
    void TestPerft0() {
        Game game = setUp();
        assertEquals(1, game.perft(0));
    }

    @Test
    void TestPerft1() {
        Game game = setUp();
        assertEquals(10, game.perft(1));
    }

    @Test
    void TestPerft2() {
        Game game = setUp();
        assertEquals(130, game.perft(2));
    }

    @Test
    void TestPerft3() {
        Game game = setUp();
        assertEquals(1989, game.perft(3));
    }

    @Test
    void TestPerft4() {
        Game game = setUp();
        assertEquals(28509, game.perft(4));
    }

    @Test
    void TestPerft5() {
        Game game = setUp();
        assertEquals(487780, game.perft(5));
    }

    @Test
    void TestPerft6() {
        Game game = setUp();
        assertEquals(7748422, game.perft(6));
    }

    @Test
    void evaluateStart(){
        Game game = setUp();
        assertEquals(15, game.evaluate());
    }

    @Test
    void mateIn1() {
        Hand blue = new Hand(Card.TIGER, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUE, Piece.BLUE, Piece.BLUEFIRE, Piece.EMPTY, Piece.BLUE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUE, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.RED, Piece.REDOX, Piece.RED, Piece.RED}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, red, blue, middle);
        Evaluation opening = game.alphabeta(2, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(opening);
//        int i = 0;
//        while(!game.board.gameOver) {
//            System.out.println("Move: " + i);
//            Move move = game.alphabeta2(7, Integer.MIN_VALUE, Integer.MAX_VALUE).move;
//            System.out.println(move);
//            game = game.applyMove(move);
//            System.out.println(game);
//            i++;
//        }
    }
}