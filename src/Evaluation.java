public class Evaluation {

    int value;
    int depth;

    Evaluation(int score, int depth) {
        this.value = score;
        this.depth = depth;
    }

    boolean compareTo(Evaluation other) {
        return this.value > other.value || (this.value == other.value && this.depth > other.depth);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "value=" + value +
                ", depth=" + depth +
                '}';
    }
}
