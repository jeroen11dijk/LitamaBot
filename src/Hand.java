public class Hand {

    Card first;
    Card second;
    private Color color;

    Hand(Card first, Card second, Color color) {
        this.first = first;
        this.second = second;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

