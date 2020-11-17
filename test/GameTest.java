import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameTest {

    Game setUp(){
        Hand blue = new Hand(Card.OX, Card.BOAR, Color.BLUE);
        Hand red = new Hand(Card.HORSE, Card.ELEPHANT, Color.RED);
        Card middle = Card.CRAB;
        Color turn = middle.color;
        Board board = new Board();
        return new Game(board, turn, red, blue, middle);
    }

    @Test
    public void TestPerft0() {
        Game game = setUp();
        assertEquals(1, game.perft(0));
    }

    @Test
    public void TestPerft1() {
        Game game = setUp();
        assertEquals(10, game.perft(1));
    }

    @Test
    public void TestPerft2() {
        Game game = setUp();
        assertEquals(130, game.perft(2));
    }

    @Test
    public void TestPerft3() {
        Game game = setUp();
        assertEquals(1989, game.perft(3));
    }

    @Test
    public void TestPerft4() {
        Game game = setUp();
        assertEquals(28509, game.perft(4));
    }

    @Test
    public void TestPerft5() {
        Game game = setUp();
        assertEquals(487780, game.perft(5));
    }

    @Test
    public void TestPerft6() {
        Game game = setUp();
        assertEquals(7748422, game.perft(6));
    }
}