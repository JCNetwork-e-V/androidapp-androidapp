package com.jcnetwork.android.app1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jcnetwork.android.app1.models.Brainteaser;
import com.jcnetwork.android.app1.fragments.BrainteaserFragment;

import java.util.ArrayList;
import java.util.List;

public class BrainteaserAdapter extends FragmentStateAdapter {

    // Variables
    private final List<Brainteaser> bt;

    /**
     * Constructor
     * @param fragmentActivity fragment activity from super class
     */
    public BrainteaserAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        bt = new ArrayList<>();
    }

    // Method to add a list of brainteasers
    public void addBTList(List<Brainteaser> brainteasers) {
        bt.addAll(brainteasers);
    }

    // Method to update adapter with new data
    public void updateBTList(List<Brainteaser> newbrainteasers) {
        // Clear of cached data
        bt.clear();
        // Add new data
        bt.addAll(newbrainteasers);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Get brainteaser based on position
        Brainteaser currentBT = bt.get(position);
        return new BrainteaserFragment(currentBT);
    }

    @Override
    public int getItemCount() {
        return  bt.size();
    }
}
