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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Context context;
    private List<Integer> cardImages;
    private List<Boolean> flipped;
    private List<Boolean> matched;
    private int numberOfColumns;

    // Define an interface for the click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Variable to hold the listener implementation
    private OnItemClickListener onItemClickListener;

    // Constructor to initialize the adapter with the context and the card images
    public CardAdapter(Context context, ArrayList<Integer> cardImages, int columns) {
        this.context = context;
        this.cardImages = cardImages;
        this.numberOfColumns = columns;
        this.flipped = new ArrayList<>(Collections.nCopies(cardImages.size(), false));
        this.matched = new ArrayList<>(Collections.nCopies(cardImages.size(), false));
    }

    // Method to set the click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int padding = (int) (context.getResources().getDimension(R.dimen.grid_padding) * 2);
        int totalSpacing = padding * (numberOfColumns + 1);
        int cardWidth = (screenWidth - totalSpacing) / numberOfColumns;
        float scale = (float) cardWidth / 500f; // 500 is the original width of the card
        int cardHeight = Math.round(726 * scale); // 726 is the original height of the card

        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = cardWidth;
        layoutParams.height = cardHeight;
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (flipped.get(position) || matched.get(position)) {
            holder.imageView.setImageResource(cardImages.get(position));
        } else {
            holder.imageView.setImageResource(R.drawable.card_back);
        }

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void flipCard(int position) {
        if (position >= 0 && position < flipped.size()) {
            flipped.set(position, !flipped.get(position));
            notifyItemChanged(position);
        }
    }

    public void setMatched(int position) {
        if (position >= 0 && position < matched.size()) {
            matched.set(position, true);
            notifyItemChanged(position);
        }
    }
}
