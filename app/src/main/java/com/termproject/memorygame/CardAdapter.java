package com.termproject.memorygame;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for handling the card data in a RecyclerView.
 * This adapter manages the layout and behavior of each card in the game grid.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context context;  // Context to access application-specific resources
    private List<Integer> cardImages;  // List of image resource IDs for the cards
    private List<Boolean> flipped;  // List to track flipped state of each card
    private List<Boolean> matched;  // List to track matched state of each card
    private int numberOfColumns;  // Number of columns in the grid layout

    /**
     * Interface for handling item click events.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public boolean isMatched(int position) {
        return matched.get(position);
    }


    private OnItemClickListener onItemClickListener;  // Listener for click events

    /**
     * Constructs the CardAdapter.
     * @param context Application context.
     * @param cardImages List of drawable resources representing card faces.
     * @param columns Number of columns in the grid layout.
     */
    public CardAdapter(Context context, ArrayList<Integer> cardImages, int columns) {
        this.context = context;
        this.cardImages = cardImages;
        this.numberOfColumns = columns;
        this.flipped = new ArrayList<>(Collections.nCopies(cardImages.size(), false));
        this.matched = new ArrayList<>(Collections.nCopies(cardImages.size(), false));
    }

    /**
     * Sets the listener for item clicks.
     * @param listener The listener to be notified when an item is clicked.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Calculate card dimensions based on screen width and number of columns
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int padding = (int) (context.getResources().getDimension(R.dimen.grid_padding) * 2);
        int totalSpacing = padding * (numberOfColumns + 1);
        int cardWidth = (screenWidth - totalSpacing) / numberOfColumns;
        float scale = (float) cardWidth / 500f;  // 500px is the assumed original width of the card
        int cardHeight = Math.round(726 * scale);  // 726px is the assumed original height of the card

        // Inflate the layout for each card
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = cardWidth;
        layoutParams.height = cardHeight;
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the card image based on its flipped and matched state
        if (flipped.get(position) || matched.get(position)) {
            holder.imageView.setImageResource(cardImages.get(position));
        } else {
            holder.imageView.setImageResource(R.drawable.card_back);
        }

        // Attach the click listener
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardImages.size();
    }

    /**
     * ViewHolder class that holds the views for each grid item.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    /**
     * Flips a card at a specified position.
     * @param position The position of the card to flip.
     */
    public void flipCard(int position) {
        if (!matched.get(position)) {
            flipped.set(position, !flipped.get(position));
            notifyItemChanged(position);
        }
    }

    /**
     * Sets a card as matched.
     * @param position The position of the card to mark as matched.
     */
    public void setMatched(int position) {
        matched.set(position, true);
        flipped.set(position, false);
        notifyItemChanged(position);
    }
}

