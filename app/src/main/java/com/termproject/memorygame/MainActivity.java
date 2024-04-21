package com.termproject.memorygame;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<Integer> cardImages;
    private int flippedCount = 0;
    private int firstFlippedPosition = -1;
    private int secondFlippedPosition = -1;

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
        adapter.flipCard(position);
        flippedCount++;

        if (flippedCount == 1) {
            firstFlippedPosition = position;
        } else if (flippedCount == 2) {
            secondFlippedPosition = position;

            if (cardImages.get(firstFlippedPosition).equals(cardImages.get(secondFlippedPosition))) {
                // Match found
                adapter.setMatched(firstFlippedPosition);
                adapter.setMatched(secondFlippedPosition);
            } else {
                // No match, flip cards back
                recyclerView.postDelayed(() -> {
                    adapter.flipCard(firstFlippedPosition);
                    adapter.flipCard(secondFlippedPosition);
                }, 1000);
            }

            // Reset counts and positions
            flippedCount = 0;
            firstFlippedPosition = -1;
            secondFlippedPosition = -1;
        }
    }

}
