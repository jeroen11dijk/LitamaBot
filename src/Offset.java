public class Offset {

    final int x;
    final int y;

    Offset(int first, int second) {
        this.x = first;
        this.y = second;
    }

    Offset invert() {
        return new Offset(-x, -y);
    }

    @Override
    public String toString() {
        return "Offset{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
