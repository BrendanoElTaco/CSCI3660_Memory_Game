package com.termproject.memorygame;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Integer> cardImages;
    private CardAdapter adapter;
    private int flippedCount = 0;
    private int firstFlippedPosition = -1;
    private int secondFlippedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        cardImages = getCardImages();
        Collections.shuffle(cardImages);

        adapter = new CardAdapter(this, cardImages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (flippedCount < 2) {
                    flipCard(position);
                }
            }
        });

        // Calculate the cell size dynamically based on screen size
        calculateCellSize();
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
                gridView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.flipCard(firstFlippedPosition);
                        adapter.flipCard(secondFlippedPosition);
                    }
                }, 1000);
            }

            // Reset counts and positions
            flippedCount = 0;
            firstFlippedPosition = -1;
            secondFlippedPosition = -1;
        }
    }

    private void calculateCellSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int cellSize = Math.min(screenWidth, screenHeight) / 4; // 4 columns
        gridView.setColumnWidth(cellSize);
    }
}
