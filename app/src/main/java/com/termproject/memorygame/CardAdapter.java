package com.termproject.memorygame;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> cardImages;
    private List<Boolean> flipped;
    private List<Boolean> matched;

    public CardAdapter(Context context, ArrayList<Integer> cardImages) {
        this.context = context;
        this.cardImages = cardImages;
        this.flipped = new ArrayList<>(cardImages.size());
        this.matched = new ArrayList<>(cardImages.size());

        for (int i = 0; i < cardImages.size(); i++) {
            flipped.add(false);
            matched.add(false);
        }
    }

    @Override
    public int getCount() {
        return cardImages.size();
    }

    @Override
    public Object getItem(int position) {
        return cardImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_item, null);
        } else {
            gridView = convertView;
        }

        ImageView imageView = gridView.findViewById(R.id.imageView);
        if (flipped.get(position) || matched.get(position)) {
            imageView.setImageResource(cardImages.get(position));
        } else {
            imageView.setImageResource(R.drawable.card_back);
        }

        return gridView;
    }

    public void flipCard(int position) {
        if (position >= 0 && position < flipped.size()) {
            flipped.set(position, !flipped.get(position));
            notifyDataSetChanged();
        } else {
            // Handle invalid position gracefully (e.g., log error, show toast, etc.)
            Log.e("CardAdapter", "Invalid position: " + position);
        }
    }


    public void setMatched(int position) {
        matched.set(position, true);
        notifyDataSetChanged();
    }
}
