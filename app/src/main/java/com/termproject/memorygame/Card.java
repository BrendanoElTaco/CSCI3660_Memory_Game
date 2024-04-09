package com.termproject.memorygame;

/**
 * Represents a card in the memory game.
 */
public class Card {
    // Unique identifier for each card, useful for comparisons and tracking.
    private int id;

    // Resource ID for the image displayed on the card.
    private int imageResource;

    // Indicates if the card is currently flipped face up.
    private boolean isFlipped;

    // Indicates if the card has been successfully matched with its pair.
    private boolean isMatched;

    /**
     * Constructs a new Card instance.
     *
     * @param id            The unique identifier for this card.
     * @param imageResource The drawable resource ID for the image on this card.
     */
    public Card(int id, int imageResource) {
        this.id = id;
        this.imageResource = imageResource;
        // Cards are initialized as face down and unmatched.
        this.isFlipped = false;
        this.isMatched = false;
    }

    // Getter for the card's unique identifier.
    public int getId() {
        return id;
    }

    // Getter for the card's image resource ID.
    public int getImageResource() {
        return imageResource;
    }

    // Returns true if the card is flipped face up, false otherwise.
    public boolean isFlipped() {
        return isFlipped;
    }

    // Returns true if the card has been matched with its pair, false otherwise.
    public boolean isMatched() {
        return isMatched;
    }

    /**
     * Sets the card's flipped state. True indicates the card is flipped face up.
     *
     * @param flipped The new flipped state of the card.
     */
    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    /**
     * Sets the card's matched state. True indicates the card has been matched with its pair.
     *
     * @param matched The new matched state of the card.
     */
    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
