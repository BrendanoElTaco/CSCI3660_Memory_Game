package com.termproject.memorygame;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class GameFragment extends Fragment {

    private RecyclerView recyclerView;  // RecyclerView to display game cards
    private CardAdapter adapter;  // Adapter for the RecyclerView
    private ArrayList<Integer> cardImages;  // List to store card image resources
    private int flippedCount = 0;  // Count of currently flipped cards
    private int firstFlippedPosition = -1;  // Position of the first flipped card
    private int secondFlippedPosition = -1;  // Position of the second flipped card
    private Handler flipHandler = new Handler();  // Handler to manage delayed tasks
    private Runnable flipRunnable;  // Runnable task for handling the delay in flipping cards back

    private Player player1;  // Player 1 object
    private Player player2;  // Player 2 object
    private Player currentPlayer;  // Currently active player

    private TextView player1ScoreTextView;  // TextView for displaying Player 1's score
    private TextView player2ScoreTextView;  // TextView for displaying Player 2's score
    private TextView currentPlayerTextView;  // TextView for indicating the current player

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        // Setting up the RecyclerView for displaying cards
        recyclerView = view.findViewById(R.id.recyclerView);
        cardImages = getCardImages();
        Collections.shuffle(cardImages);  // Shuffle the card images to randomize their positions

        // Initialize players and set the starting player
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        // Setup text views for displaying scores and current player
        player1ScoreTextView = view.findViewById(R.id.player1_score);
        player2ScoreTextView = view.findViewById(R.id.player2_score);
        currentPlayerTextView = view.findViewById(R.id.current_player);

        // Initialize the adapter and configure the RecyclerView
        adapter = new CardAdapter(getActivity(), cardImages, 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        // Set a click listener for the cards
        adapter.setOnItemClickListener(position -> {
            if (flippedCount < 2) {  // Only allow two cards to be flipped at a time
                flipCard(position);
            }
        });

        updatePlayerInfo();  // Update UI with initial player info

        return view;
    }

    private void updatePlayerInfo() {
        // Update the UI with current player and scores
        currentPlayerTextView.setText(currentPlayer.getName() + "'s Turn");
        player1ScoreTextView.setText("Player 1: " + player1.getScore());
        player2ScoreTextView.setText("Player 2: " + player2.getScore());
    }

    private void flipCard(int position) {
        // Prevent flipping more than two cards or re-flipping a matched card
        if (flippedCount >= 2 || adapter.isMatched(position)) {
            return;
        }

        adapter.flipCard(position);  // Flip the card at the specified position
        flippedCount++;  // Increment the count of flipped cards

        // Store the positions of the first and second flipped cards
        if (flippedCount == 1) {
            firstFlippedPosition = position;
        } else if (flippedCount == 2) {
            secondFlippedPosition = position;

            // Define the behavior after two cards have been flipped
            flipRunnable = () -> {
                // Flip both cards back
                adapter.flipCard(firstFlippedPosition);
                adapter.flipCard(secondFlippedPosition);

                // Check if the two cards match
                if (cardImages.get(firstFlippedPosition).equals(cardImages.get(secondFlippedPosition))) {
                    // If cards match, mark them and update score
                    adapter.setMatched(firstFlippedPosition);
                    adapter.setMatched(secondFlippedPosition);
                    currentPlayer.incrementScore();
                    updatePlayerInfo();  // Update score display immediately
                } else {
                    // If cards don't match, switch to the other player
                    currentPlayer = (currentPlayer == player1) ? player2 : player1;
                    updatePlayerInfo();  // Update the display to show the new current player
                }

                // Reset flipped count and positions
                flippedCount = 0;
                firstFlippedPosition = -1;
                secondFlippedPosition = -1;

                // Check if all cards have been matched
                if (allCardsMatched()) {
                    resetGame();  // Reset the game if all cards are matched
                }
            };

            // Post the flipping back with a delay
            flipHandler.postDelayed(flipRunnable, 1500);
        }
    }

    private boolean allCardsMatched() {
        // Check if all cards in the game have been matched
        for (int i = 0; i < cardImages.size(); i++) {
            if (!adapter.isMatched(i)) {
                return false;
            }
        }
        return true;
    }

    public void resetGame() {
        // Reset all game-related variables and UI elements
        flippedCount = 0;
        firstFlippedPosition = -1;
        secondFlippedPosition = -1;
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        // Shuffle the card images again and reset the adapter
        cardImages = getCardImages();
        Collections.shuffle(cardImages);
        adapter = new CardAdapter(getActivity(), cardImages, 4);
        recyclerView.setAdapter(adapter);

        // Update the UI to reflect the reset state
        updatePlayerInfo();
    }

    private ArrayList<Integer> getCardImages() {
        // Generate a list of card image resources including duplicates for matching
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
