package com.termproject.memorygame;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
/*
    TODO:
     1. Add flips counter
     2. Nav bar for rules
     3. Difficulty modes (maybe)
     4. Cleanup code and add loads of comments
 */
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Integer> cardImages;
    private int flippedCount = 0;
    private int firstFlippedPosition = -1;
    private int secondFlippedPosition = -1;
    private Handler flipHandler = new Handler();
    private Runnable flipRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        adapter = new CardAdapter(this, cardImages, 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4)); // Set 4 columns in the grid

        // Handling item clicks
        adapter.setOnItemClickListener(position -> {
            // Handle the click event, flip the card
            if (flippedCount < 2) {
                flipCard(position);
            }
        });
    }

    private ArrayList<Integer> getCardImages() {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.threeofclubs);
        images.add(R.drawable.threeofdiamonds);
        images.add(R.drawable.threeofhearts);
        images.add(R.drawable.threeofclubs);
        images.add(R.drawable.threeofclubs);
        images.add(R.drawable.threeofdiamonds);
        images.add(R.drawable.threeofhearts);
        images.add(R.drawable.threeofclubs);
        images.add(R.drawable.twoofclubs);
        images.add(R.drawable.twoofdiamonds);
        images.add(R.drawable.twoofhearts);
        images.add(R.drawable.twoofspades);
        images.add(R.drawable.twoofclubs);
        images.add(R.drawable.twoofdiamonds);
        images.add(R.drawable.twoofhearts);
        images.add(R.drawable.twoofspades);
        return images;
    }

    private void flipCard(int position) {
        // Do nothing if we're already waiting to flip cards back
        if (flippedCount >= 2) {
            return;
        }

        // Flip the card
        adapter.flipCard(position);
        flippedCount++;

        if (flippedCount == 1) {
            firstFlippedPosition = position;
        } else if (flippedCount == 2) {
            secondFlippedPosition = position;

            // Cancel any pending flip-backs
            flipHandler.removeCallbacks(flipRunnable);

            flipRunnable = new Runnable() {
                @Override
                public void run() {
                    // Flip cards back over
                    adapter.flipCard(firstFlippedPosition);
                    adapter.flipCard(secondFlippedPosition);

                    // Reset for the next turn
                    flippedCount = 0;
                    firstFlippedPosition = -1;
                    secondFlippedPosition = -1;
                }
            };

            // If the cards do not match, schedule them to flip back after a delay
            if (!cardImages.get(firstFlippedPosition).equals(cardImages.get(secondFlippedPosition))) {
                flipHandler.postDelayed(flipRunnable, 1500);
            } else {
                // If cards match, no need to flip them back, just reset the flip count
                flippedCount = 0;
            }
        }
    }
}
