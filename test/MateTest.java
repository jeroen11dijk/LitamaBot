import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MateTest {


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
        game = game.applyMove(game.negamaxRoot(10));
        assertTrue(game.board.gameOver);
        assertEquals(Color.BLUE, game.board.winner);
    }

    @Test
    void mateIn1BlueTemple() {
        Hand blue = new Hand(Card.ROOSTER, Card.CRANE, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.FROG, Color.RED);
        Card middle = Card.TIGER;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.REDOX},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUEFIRE, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
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
    void mateIn2BlueTemple() {
        Hand blue = new Hand(Card.ROOSTER, Card.CRANE, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.FROG, Color.RED);
        Card middle = Card.TIGER;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.REDOX},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUEFIRE, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.BLUE, Piece.EMPTY, Piece.EMPTY}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.BLUE, red, blue, middle);
        Move move = new Move(Card.ROOSTER, Card.ROOSTER.offsets[1], 2, 4);
        game = game.applyMove(move);
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
        game = game.applyMove(game.negamaxRoot(10));
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

    @Test
    void mateIn1RedTemple() {
        Hand blue = new Hand(Card.RABBIT, Card.MONKEY, Color.BLUE);
        Hand red = new Hand(Card.GOOSE, Card.COBRA, Color.RED);
        Card middle = Card.DRAGON;
        Color turn = middle.color;
        Piece[][] boardPiece = new Piece[][]{
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.REDOX, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.BLUEFIRE},
                {Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY, Piece.EMPTY}};
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }
}
