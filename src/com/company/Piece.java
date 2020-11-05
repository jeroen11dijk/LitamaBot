package com.company;

enum Piece {
    RED(Color.RED),
    REDOX(Color.RED),
    BLUE(Color.BLUE),
    BLUEFIRE(Color.BLUE),
    EMPTY(Color.PURPLE);

    public Color color;

    Piece(Color color) {
        this.color = color;
    }
}