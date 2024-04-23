package com.termproject.memorygame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {

    private TextView rulesTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        rulesTextView = view.findViewById(R.id.rules_text_view);

        // Set the game rules
        String gameRules = "Game Rules:\n\n" +
                "1. The game starts with all cards face down.\n" +
                "2. Flip over two cards.\n" +
                "3. If the two cards match, the player gets a point.\n" +
                "4. If they don't match, they are flipped back over.\n" +
                "5. The game is over when all the cards have been matched.\n" +
                "6. The goal of the game is to match all pairs of cards with the fewest flips possible.";

        rulesTextView.setText(gameRules);

        return view;
    }
}
