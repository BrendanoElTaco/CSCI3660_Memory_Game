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

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Integer> cardImages;
    private int flippedCount = 0;
    private int firstFlippedPosition = -1;
    private int secondFlippedPosition = -1;
    private Handler flipHandler = new Handler();
    private Runnable flipRunnable;

    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private TextView currentPlayerTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        player1ScoreTextView = view.findViewById(R.id.player1_score);
        player2ScoreTextView = view.findViewById(R.id.player2_score);
        currentPlayerTextView = view.findViewById(R.id.current_player);

        adapter = new CardAdapter(getActivity(), cardImages, 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        adapter.setOnItemClickListener(position -> {
            if (flippedCount < 2) {
                flipCard(position);
            }
        });

        updatePlayerInfo();

        return view;
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

                // Check if all cards have been matched
                if (allCardsMatched()) {
                    resetGame();
                }
            };

            flipHandler.postDelayed(flipRunnable, 1500);
        }
    }

    private boolean allCardsMatched() {
        // Check if all cards have been matched
        for (int i = 0; i < cardImages.size(); i++) {
            if (!adapter.isMatched(i)) {
                return false;
            }
        }
        return true;
    }
    public void resetGame() {
        // Reset all game variables here
        flippedCount = 0;
        firstFlippedPosition = -1;
        secondFlippedPosition = -1;
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;

        // Reset the card images and shuffle them
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        // Reset the adapter with the shuffled images
        adapter = new CardAdapter(getActivity(), cardImages, 4);
        recyclerView.setAdapter(adapter);

        // Update the UI to reflect the reset state
        updatePlayerInfo();
    }

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
