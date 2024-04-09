package com.termproject.memorygame;

public class Card {
    private int id; // Unique identifier for each card
    private int imageResource; // Resource ID for the image on the card
    private boolean isFlipped; // Indicates if the card is flipped face up
    private boolean isMatched; // Indicates if the card has been successfully matched

    // Constructor
    public Card(int id, int imageResource) {
        this.id = id;
        this.imageResource = imageResource;
        this.isFlipped = false; // Cards start face down
        this.isMatched = false; // Cards start unmatched
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    // Setters
    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}

