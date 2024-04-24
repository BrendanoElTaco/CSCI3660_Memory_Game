package com.termproject.memorygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private ImageView cardBackPreview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        cardBackPreview = view.findViewById(R.id.imageViewCardBackPreview);
        RadioGroup radioGroup = view.findViewById(R.id.radioGroupCardBack);

        // Load the saved radio button ID from SharedPreferences or use default
        SharedPreferences preferences = getActivity().getSharedPreferences("GameSettings", Context.MODE_PRIVATE);
        int savedRadioId = preferences.getInt("selectedCardBack", R.id.radioCardBack1);  // Default to R.id.radioCardBack1
        radioGroup.check(savedRadioId);  // Set the saved radio button as checked

        updatePreviewImage(savedRadioId);  // Update the preview based on the saved selection

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Save the checked ID in SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("selectedCardBack", checkedId);
            editor.apply();

            updatePreviewImage(checkedId);  // Update the preview image when a new option is selected
        });

        return view;
    }

    private void updatePreviewImage(int checkedId) {
        // Update the card back preview image based on the selected radio button using if-else statements
        if (checkedId == R.id.radioCardBack1) {
            cardBackPreview.setImageResource(R.drawable.classic_card_back);
        } else if (checkedId == R.id.radioCardBack2) {
            cardBackPreview.setImageResource(R.drawable.modern_card_back);
        } else if (checkedId == R.id.radioCardBack3) {
            cardBackPreview.setImageResource(R.drawable.vintage_card_back);
        } else if (checkedId == R.id.radioCardBack4) {
            cardBackPreview.setImageResource(R.drawable.futuristic_card_back);
        }
    }

}
