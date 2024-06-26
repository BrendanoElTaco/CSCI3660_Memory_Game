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

    private TextView rulesTextView;  // TextView for displaying game rules and other information

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        // Initialize the TextView that will display the rules
        rulesTextView = view.findViewById(R.id.rules_text_view);

        // HTML content to be displayed in the TextView, includes game rules and credits with hyperlinks
        String gameRules = "<b>Game Rules:</b><br><br>" +
                "1. The game starts with all cards face down.<br>" +
                "2. Players take turns to flip over two cards each turn.<br>" +
                "3. If the two cards match, the player scores a point.<br>" +
                "4. If they don't match, they are flipped back over and it's the next player's turn.<br>" +
                "5. The game continues until all cards have been matched.<br>" +
                "6. The player with the most matches at the end of the game wins.<br><br>" +
                "<b>About:</b><br><br>" +
                "This app was created as a term project for the Mobile Application Development course at the University of North Georgia.<br><br>" +
                "<b>Credits:</b><br><br>" +
                "<a href=\"https://github.com/BrendanoElTaco\">Brendan LeGrand </a><br>" +
                "<a href=\"https://github.com/natemvm\">Nathan Lee </a><br>" +
                "<a href=\"https://github.com/JamieGibbs1\">Jamie Gibbs </a><br><br>" +
                "<a href=\"https://www.freepik.com/free-vector/hand-drawn-playing-card-back-design-illustration_94943770.htm#query=playing%20card%20back&position=1&from_view=keyword&track=ais&uuid=15b09624-f718-45fc-9e88-aed49dabc287\">Modern</a> and <a href=\"https://www.freepik.com/free-vector/hand-drawn-playing-card-back-design-illustration_133509631.htm#fromView=search&page=1&position=5&uuid=cd497339-6252-4ab3-8d4a-73c47646c35e\">Futuristic</a> card art by freepik ";


        // Set the formatted HTML text to the TextView based on Android version
        rulesTextView.setText(Html.fromHtml(gameRules, Html.FROM_HTML_MODE_LEGACY));

        // Enable hyperlinks in the TextView to be clickable
        rulesTextView.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
