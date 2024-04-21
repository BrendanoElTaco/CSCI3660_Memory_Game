package com.termproject.memorygame;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
/**
   * TODO:
   *  1. Add flips counter
   *  2. Nav bar for rules
   *  3. Difficulty modes (maybe)
   *  4. Cleanup code and add loads of comments
 */

    // UI component to display cards in a grid
    private RecyclerView recyclerView;
    // Adapter for managing card data in RecyclerView
    private CardAdapter adapter;
    // List to store images for the cards
    private ArrayList<Integer> cardImages;
    // Counter to track how many cards have been flipped in a turn
    private int flippedCount = 0;
    // Index of the first card flipped in the current turn
    private int firstFlippedPosition = -1;
    // Index of the second card flipped in the current turn
    private int secondFlippedPosition = -1;
    // Handler for running delayed tasks
    private Handler flipHandler = new Handler();
    // Runnable task for flipping cards back
    private Runnable flipRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        // Get and shuffle the card images
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        // Setup the adapter and RecyclerView
        adapter = new CardAdapter(this, cardImages, 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4)); // Set the grid to have 4 columns

        // Set up the click listener for the cards
        adapter.setOnItemClickListener(position -> {
            // Only allow a new card to be flipped if less than 2 cards are currently flipped
            if (flippedCount < 2) {
                flipCard(position);
            }
        });
    }

    /**
     * Create and return a list of drawable resources for the cards.
     */
    private ArrayList<Integer> getCardImages() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.threeofclubs);
        images.add(R.drawable.threeofdiamonds);
        images.add(R.drawable.threeofhearts);
        images.add(R.drawable.threeofclubs); // Duplicate for matching
        images.add(R.drawable.threeofclubs); // Duplicate for matching
        images.add(R.drawable.threeofdiamonds); // Duplicate for matching
        images.add(R.drawable.threeofhearts); // Duplicate for matching
        images.add(R.drawable.threeofclubs); // Duplicate for matching
        images.add(R.drawable.twoofclubs);
        images.add(R.drawable.twoofdiamonds);
        images.add(R.drawable.twoofhearts);
        images.add(R.drawable.twoofspades);
        images.add(R.drawable.twoofclubs); // Duplicate for matching
        images.add(R.drawable.twoofdiamonds); // Duplicate for matching
        images.add(R.drawable.twoofhearts); // Duplicate for matching
        images.add(R.drawable.twoofspades); // Duplicate for matching
        return images;
    }

    /**
     * Handles the logic for flipping a card and managing the game state.
     */
    private void flipCard(int position) {
        // Prevent flipping more than two cards
        if (flippedCount >= 2) {
            return;
        }

        // Flip the card at the given position
        adapter.flipCard(position);
        flippedCount++;

        // Handle first and second flips differently
        if (flippedCount == 1) {
            firstFlippedPosition = position; // Record the position of the first flip
        } else if (flippedCount == 2) {
            secondFlippedPosition = position; // Record the position of the second flip

            // Prepare to flip cards back if they don't match
            flipRunnable = () -> {
                adapter.flipCard(firstFlippedPosition);
                adapter.flipCard(secondFlippedPosition);
                // Reset the game state
                flippedCount = 0;
                firstFlippedPosition = -1;
                secondFlippedPosition = -1;
            };

            // Check if the two flipped cards match
            if (!cardImages.get(firstFlippedPosition).equals(cardImages.get(secondFlippedPosition))) {
                // If not a match, schedule the cards to flip back after a delay
                flipHandler.postDelayed(flipRunnable, 1500);
            } else {
                // If a match, reset flippedCount immediately, no need to flip back
                flippedCount = 0;
            }
        }
    }
}
