package com.jcnetwork.android.app1.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jcnetwork.android.app1.models.ProgramPoint;
import com.jcnetwork.android.app1.fragments.ScheduleFragment;

import java.util.List;

/**
 * Adapter for viewpager in schedule activity (not to be confused with ScheduleFragmentAdapter)
 */

public class ScheduleAdapter extends FragmentStateAdapter {

    // Default to one
    private int DAYS;
    private final List<ProgramPoint> mProgram; // Cached copy of programpoints

    public ScheduleAdapter(@NonNull FragmentActivity fragmentActivity, List<ProgramPoint> program, int numDays) {
        super(fragmentActivity);
        // Pass in the data of the events here
        mProgram = program;
        // Set number of days i.e. pages to display
        DAYS = numDays + 1;
        if (mProgram != null) {
            // For logging
            String LOG_TAG = this.getClass().getSimpleName();
            Log.i(LOG_TAG, "mProgram not null");
            Log.i(LOG_TAG, "first title of event" + mProgram.get(0).getTitle()); }
    }

    /**
     * Return a fragment for each day for a specific page
     * @param position position starting at index 0
     * @return fragment containing the schedule for the specific day
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return new fragment
        return ScheduleFragment.newInstance(position, mProgram);
    }

    /**
     * How many pages to create?
     * Here either default 1 or the dynamically loaded total number of days for the "Days"
     */
    @Override
    public int getItemCount() {
        return DAYS;
    }
}
