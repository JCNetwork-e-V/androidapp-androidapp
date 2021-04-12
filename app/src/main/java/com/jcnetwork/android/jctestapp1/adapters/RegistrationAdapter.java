package com.jcnetwork.android.jctestapp1.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jcnetwork.android.jctestapp1.fragments.RegistrationFragment;

public class RegistrationAdapter  extends FragmentStateAdapter {

    public RegistrationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Return a fragment for each day for a specific page
     * @param position position starting at index 0
     * @return fragment containing the registration webview for the specific days e.g. executive
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return new fragment
        return RegistrationFragment.newInstance(position);
    }

    /**
     * How many pages to create?
     */
    @Override
    public int getItemCount() {
        // How many tabs? -> 3 for each of the three types of events i.e. normal, executive, development
        return 3;
    }

}
