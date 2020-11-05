package com.company;

public class Offset {

    public final int x;
    public final int y;

    Offset(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public Offset invert() {
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
