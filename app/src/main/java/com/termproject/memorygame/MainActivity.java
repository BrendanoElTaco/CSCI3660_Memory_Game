/**
 * TODO:
 *  1. Add flips counter
 *  2. Nav bar for rules
 *  3. Difficulty modes (maybe)
 *  4. Cleanup code and add loads of comments
 */

package com.termproject.memorygame;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

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

    // Players
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    // UI components
    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private TextView currentPlayerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        player1ScoreTextView = findViewById(R.id.player1_score);
        player2ScoreTextView = findViewById(R.id.player2_score);
        currentPlayerTextView = findViewById(R.id.current_player);

        adapter = new CardAdapter(this, cardImages, 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter.setOnItemClickListener(position -> {
            if (flippedCount < 2) {
                flipCard(position);
            }
        });

        updatePlayerInfo();
    }

    private void updatePlayerInfo() {
        currentPlayerTextView.setText(currentPlayer.getName() + "'s Turn");
        player1ScoreTextView.setText("Player 1: " + player1.getScore());
        player2ScoreTextView.setText("Player 2: " + player2.getScore());
    }

    private void flipCard(int position) {
        if (flippedCount >= 2 || adapter.isMatched(position)) {
            return;
        }

        adapter.flipCard(position);
        flippedCount++;

        if (flippedCount == 1) {
            firstFlippedPosition = position;
        } else if (flippedCount == 2) {
            secondFlippedPosition = position;

            flipRunnable = () -> {
                adapter.flipCard(firstFlippedPosition);
                adapter.flipCard(secondFlippedPosition);

                if (cardImages.get(firstFlippedPosition).equals(cardImages.get(secondFlippedPosition))) {
                    // If cards match, mark them as matched and update player's score
                    adapter.setMatched(firstFlippedPosition);
                    adapter.setMatched(secondFlippedPosition);
                    currentPlayer.incrementScore();
                    updatePlayerInfo(); // Update player's score immediately
                } else {
                    // Switch player if cards don't match
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    updatePlayerInfo();
                }

                flippedCount = 0;
                firstFlippedPosition = -1;
                secondFlippedPosition = -1;
            };

            flipHandler.postDelayed(flipRunnable, 1500);
        }
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
}

