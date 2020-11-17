public class Move {

    Card card;
    Offset offset;
    int x;
    int y;

    public Move(Card card, Offset offset, int x, int y) {
        this.card = card;
        this.offset = offset;
        this.x = x;
        this.y = y;
    }
}
