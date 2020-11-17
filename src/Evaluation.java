public class Evaluation {

    Move move;
    int score;

    public Evaluation(Move move, int score) {
        this.move = move;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "move=" + move +
                ", score=" + score +
                '}';
    }
}
