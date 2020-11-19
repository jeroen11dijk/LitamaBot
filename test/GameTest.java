import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    void mateIn1Red() {
        Hand blue = new Hand(Card.EEL, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.RABBIT, Card.ELEPHANT, Color.RED);
        Card middle = Card.MANTIS;
        Color turn = Color.RED;
        Piece[][] boardPiece = Main.parseString("0000023041000000000000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        game = game.applyMove(game.negamaxRoot(10));
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void mateIn2RedBlueTurn() {
        Hand blue = new Hand(Card.MANTIS, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.RABBIT, Card.ELEPHANT, Color.RED);
        Card middle = Card.EEL;
        Color turn = Color.BLUE;
        Piece[][] boardPiece = Main.parseString("0200003041000000000000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 3; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void mateIn3Red() {
        Hand blue = new Hand(Card.EEL, Card.ELEPHANT, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.TIGER, Color.RED);
        Card middle = Card.RABBIT;
        Color turn = Color.RED;
        Piece[][] boardPiece = Main.parseString("0200003001004000000000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 4; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void mateIn4RedBlueTurn() {
        Hand blue = new Hand(Card.RABBIT, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.EEL, Card.ELEPHANT, Color.RED);
        Card middle = Card.MANTIS;
        Color turn = Color.BLUE;
        Piece[][] boardPiece = Main.parseString("0201003000004000000000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 5; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }


    @Test
    void mateIn5Red() {
        Hand blue = new Hand(Card.RABBIT, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.ELEPHANT, Color.RED);
        Card middle = Card.EEL;
        Color turn = Color.RED;
        Piece[][] boardPiece = Main.parseString("0201003000000000400000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 6; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void mateIn6RedBlueTurn() {
        Hand blue = new Hand(Card.EEL, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.MANTIS, Card.ELEPHANT, Color.RED);
        Card middle = Card.RABBIT;
        Color turn = Color.BLUE;
        Piece[][] boardPiece = Main.parseString("2001003000000000400000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 7; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

    @Test
    void mateIn7Red() {
        Hand blue = new Hand(Card.EEL, Card.TIGER, Color.BLUE);
        Hand red = new Hand(Card.RABBIT, Card.ELEPHANT, Color.RED);
        Card middle = Card.MANTIS;
        Color turn = Color.RED;
        Piece[][] boardPiece = Main.parseString("2001000000003000400000000");
        Board board = new Board(boardPiece);
        Game game = new Game(board, turn, Color.RED, red, blue, middle);
        for (int i = 1; i < 8; i++) {
            game = game.applyMove(game.negamaxRoot(10));
        }
        assertTrue(game.board.gameOver);
        assertEquals(Color.RED, game.board.winner);
    }

}
