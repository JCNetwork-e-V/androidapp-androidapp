package com.jcnetwork.android.jctestapp1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.models.Brainteaser;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class BrainteaserFragment extends Fragment {

    // Variables
    private Brainteaser currentBT;
    private TextView mainTV, titelTV;
    private ImageButton brainBtn;
    private boolean isRevealed = FALSE;


    public BrainteaserFragment() {
        // Required empty public constructor
    }

    public BrainteaserFragment(Brainteaser bt) {
        // Constructor for current bt
        currentBT = bt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brainteaser_card, container, false);

        // Find view
        this.mainTV = view.findViewById(R.id.brainteaser_question_tv);
        this.brainBtn = view.findViewById(R.id.brain_button);
        this.titelTV = view.findViewById(R.id.titel);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mainTV.setText(currentBT.getTeaser());

        this.titelTV.setText(currentBT.getTitel());

        this.brainBtn.setOnClickListener(view1 -> Toast.makeText(getContext(), "Wische horizontal, um andere Brainteaser zu sehen. Drücke lange auf den Knopf oben, um die Antwort zu sehen.", Toast.LENGTH_LONG).show());

        this.brainBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (isRevealed) {
                    // If resolution is revealed, go back to question if user long presses the button
                    mainTV.setText(currentBT.getTeaser());
                    mainTV.setTextColor(getResources().getColor((R.color.black)));
                    isRevealed = FALSE;
                } else {
                    // By default the solution is not revealed, so reveal it
                    mainTV.setText(currentBT.getResolution());
                    mainTV.setTextColor(getResources().getColor((R.color.colorPrimary)));
                    isRevealed = TRUE;
                }
                return true;
            }
        });
    }
}
