package com.jcnetwork.android.jctestapp1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.models.Brainteaser;

public class BrainteaserFragment extends Fragment {

    // Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Variables
    private Brainteaser currentBT;
    private TextView mainTV;
    private int count = 0;

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
        View view = (ViewGroup) inflater.inflate(R.layout.brainteaser_card, container, false);

        // Increase count
        count++;

        // Find view
        this.mainTV = view.findViewById(R.id.brainteaser_question_tv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mainTV.setText(currentBT.getTeaser());
    }
}
