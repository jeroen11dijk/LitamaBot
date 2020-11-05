package com.company;

public class Hand {

    public Card first;
    public Card second;

    Hand(Card first, Card second, Color color) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

