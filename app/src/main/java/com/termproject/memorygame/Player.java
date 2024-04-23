package com.termproject.memorygame;

/**
 * Represents a player in the memory game.
 * This class stores player information and score details.
 */
public class Player {
    private String name;  // Player's name
    private int score;    // Player's current score

    /**
     * Constructor to initialize the player with a name.
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;  // Set the player's name
        this.score = 0;    // Initialize score to 0
    }

    /**
     * Returns the player's name.
     * @return A string representing the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the player's current score.
     * @return An integer representing the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the player's score by 1.
     * Used when the player successfully matches a pair of cards.
     */
    public void incrementScore() {
        score++;  // Increment the score by 1
    }
}
