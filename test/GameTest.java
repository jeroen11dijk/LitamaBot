import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game setUp() {
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        return new Game(board, turn, Color.BLUE, red, blue, middle);
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
        assertEquals(0, game.evaluate(0));
    }

    @Test
    void mateIn1Blue() {
        Hand blue = new Hand(Card.ROOSTER, Card.CRANE, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.FROG, Color.RED);
        Card middle = Card.TIGER;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUE, Piece.BLUE, Piece.BLUEFIRE, Piece.EMPTY, Piece.BLUE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUE, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.RED, Piece.REDOX, Piece.RED, Piece.RED}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(5));
        assertTrue(game.board.gameOver);
        assertEquals(Color.BLUE, game.board.winner);
    }

    @Test
    void mateIn2Blue() {
        Hand blue = new Hand(Card.ROOSTER, Card.CRANE, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.DRAGON, Color.RED);
        Card middle = Card.TIGER;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUE, Piece.BLUE, Piece.BLUEFIRE, Piece.BLUE, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.BLUE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.REDOX}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        game = game.applyMove(game.negamaxRoot(10));
        game = game.applyMove(game.negamaxRoot(10));
        assertTrue(game.board.gameOver);
        assertEquals(Color.BLUE, game.board.winner);
    }

    @Test
    void unavoidableMateForBlue() {
        Hand blue = new Hand(Card.FROG, Card.HORSE, Color.BLUE);
        Hand red = new Hand(Card.CRAB, Card.RABBIT, Color.RED);
        Card middle = Card.EEL;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUEFIRE, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.RED, Piece.REDOX, Piece.RED, Piece.RED}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        game = game.applyMove(game.negamaxRoot(1));
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void avoidableMateForBlue() {
        Hand blue = new Hand(Card.FROG, Card.OX, Color.BLUE);
        Hand red = new Hand(Card.CRAB, Card.RABBIT, Color.RED);
        Card middle = Card.EEL;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUEFIRE, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.RED, Piece.REDOX, Piece.RED, Piece.RED}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        game = game.applyMove(game.negamaxRoot(10));
        assertFalse(game.board.gameOver);
    }

    @Test
    void mateIn1Red() {
        Hand blue = new Hand(Card.RABBIT, Card.MONKEY, Color.BLUE);
        Hand red = new Hand(Card.GOOSE, Card.COBRA, Color.RED);
        Card middle = Card.DRAGON;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.BLUE, Piece.BLUE, Piece.BLUEFIRE, Piece.EMPTY, Piece.BLUE},
                {Piece.EMPTY, Piece.RED, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUE, Piece.EMPTY, Piece.EMPTY},
                {Piece.RED, Piece.RED, Piece.REDOX, Piece.EMPTY, Piece.RED}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }
}