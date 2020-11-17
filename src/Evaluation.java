public class Evaluation {

    private Move move;
    int score;

    Evaluation(Move move, int score) {
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
