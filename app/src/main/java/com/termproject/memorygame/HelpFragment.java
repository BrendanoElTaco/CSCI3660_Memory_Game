package com.termproject.memorygame;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HelpFragment extends Fragment {

    private TextView rulesTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        rulesTextView = view.findViewById(R.id.rules_text_view);

        // Set the game rules with HTML formatting for bold text and hyperlinks
        String gameRules = "<b>Game Rules:</b><br><br>" +
                "1. The game starts with all cards face down.<br>" +
                "2. Flip over two cards.<br>" +
                "3. If the two cards match, the player gets a point.<br>" +
                "4. If they don't match, they are flipped back over.<br>" +
                "5. The game is over when all the cards have been matched.<br>" +
                "6. The goal of the game is to match all pairs of cards with the fewest flips possible.<br><br>" +
                "<b>About:</b><br><br>" +
                "This app was made as a term project for the University of North Georgia's Mobile Application Development class.<br><br>" +
                "<b>Credits:</b><br><br>" +
                "<a href=\"https://github.com/BrendanoElTaco\">Brendan LeGrand </a><br>" +
                "<a href=\"https://github.com/natemvm\">Nathan Lee </a><br>" +
                "<a href=\"https://github.com/JamieGibbs1\">Jamie Gibbs </a><br>";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            rulesTextView.setText(Html.fromHtml(gameRules, Html.FROM_HTML_MODE_LEGACY));
        } else {
            rulesTextView.setText(Html.fromHtml(gameRules));
        }

        // Make the hyperlink clickable
        rulesTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
