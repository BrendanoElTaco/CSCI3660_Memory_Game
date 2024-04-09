package com.termproject.memorygame;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        // Set the content view to your XML layout
        setContentView(R.layout.activity_main);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the GridLayout defined in XML
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Initialize the deck of cards
        List<Card> cards = initializeDeck();

        // Add card views to the GridLayout
        addCardsToGridLayout(gridLayout, cards);
    }

    /**
     * Dynamically adds card views to the grid layout.
     * @param gridLayout The GridLayout to add cards to.
     * @param cards The list of Card objects to add to the grid.
     */
    private void addCardsToGridLayout(GridLayout gridLayout, List<Card> cards) {
        for (Card card : cards) {
            ImageView cardView = new ImageView(this);
            // Set the back image for the card
            cardView.setImageResource(R.drawable.card_back);

            // Store the card's front image in the view's tag for easy access
            cardView.setTag(card.getImageResource());

            // Configure layout parameters for the card view
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            params.setMargins(8, 8, 8, 8);

            cardView.setLayoutParams(params);
            cardView.setAdjustViewBounds(true);
            cardView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            // Set onClickListener to handle card flip
            cardView.setOnClickListener(view -> {
                if (!card.isFlipped() && !card.isMatched()) {
                    card.setFlipped(true);
                    cardView.setImageResource((Integer) view.getTag());
                    // TODO: Add logic here to check for a match and handle unmatched cards
                }
            });

            gridLayout.addView(cardView);
        }
    }

    /**
     * Initializes the deck of cards with image resources.
     * @return List of Card objects.
     */
    private List<Card> initializeDeck() {
        List<Card> cards = new ArrayList<>();
        int[] imageResources = {
                R.drawable.card_01, R.drawable.card_02, R.drawable.card_03, R.drawable.card_04,
                R.drawable.card_05, R.drawable.card_06, R.drawable.card_07, R.drawable.card_08,
                R.drawable.card_09, R.drawable.card_10, R.drawable.card_11, R.drawable.card_12,
                R.drawable.card_13, R.drawable.card_14, R.drawable.card_15, R.drawable.card_16
        };

        for (int i = 0; i < imageResources.length; i++) {
            cards.add(new Card(i, imageResources[i]));
        }

        // Shuffle the list of cards to ensure a different game each time
        Collections.shuffle(cards);

        return cards;
    }
}
