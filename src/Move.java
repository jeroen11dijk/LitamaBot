class Move {

    Card card;
    Offset offset;
    int x;
    int y;

    @Override
    public String toString() {
        return "Move{" +
                "card=" + card +
                ", offset=" + offset +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    Move(Card card, Offset offset, int x, int y) {
        this.card = card;
        this.offset = offset;
        this.x = x;
        this.y = y;
    }
}
