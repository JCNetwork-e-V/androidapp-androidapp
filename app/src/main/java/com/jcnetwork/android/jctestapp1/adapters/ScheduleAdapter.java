package com.jcnetwork.android.jctestapp1.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jcnetwork.android.jctestapp1.fragments.ScheduleFragment;
import com.jcnetwork.android.jctestapp1.models.ProgramPoint;

import java.util.List;

/**
 * Adapter for viewpager in schedule activity (not to be confused with ScheduleFragmentAdapter)
 */

public class ScheduleAdapter extends FragmentStateAdapter {

    //TODO Default to one
    private int DAYS = 1;
    private List<ProgramPoint> mProgram; // Cached copy of programpoints

    // For logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    public ScheduleAdapter(@NonNull FragmentActivity fragmentActivity, List<ProgramPoint> program, int numDays) {
        super(fragmentActivity);
        // Pass in the data of the events here
        mProgram = program;
        // Set number of days i.e. pages to display
        DAYS = numDays + 1;
        if (mProgram != null) {
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

//        // TODO New attempt
//        return ScheduleFragment.newInstance(position);

//        ScheduleFragment scheduleFragment = new ScheduleFragment();
//        // Pass in data
//        Bundle bundle = new Bundle();
//        bundle.putInt("POSITION", position);
//        // Depending on the position get the specific schedule to pass along
//        switch (position) {
//            case 0: // Put Day One Schedule
//                Log.i(LOG_TAG, "position 0");
//                // TODO Fix function
//                try {
//                    List<ProgramPoint> dayOneSchedule = ProgramPointAnalysis.getProgramForDayX(mProgram, 0, "2019-11-21");
//                    bundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) dayOneSchedule);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            case 1: // Put Day Two Schedule
//                Log.i(LOG_TAG, "position 1");
//
//                try {
//                    List<ProgramPoint> dayTwoSchedule = ProgramPointAnalysis.getProgramForDayX(mProgram, 1, "2019-11-22");
//                    bundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) dayTwoSchedule);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            case 2: // Put Day Three Schedule
//                try {
//                    List<ProgramPoint> dayThreeSchedule = ProgramPointAnalysis.getProgramForDayX(mProgram, 2, "2019-11-23");
//                    bundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) dayThreeSchedule);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            case 3: // Put Day Four Schedule
//                try {
//                    List<ProgramPoint> dayFourSchedule = ProgramPointAnalysis.getProgramForDayX(mProgram, 3, "2019-11-24");
//                    bundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) dayFourSchedule);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            default:
//                // Put everything
//                bundle.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) mProgram);
//        }
//        // Set data to fragment to send to each fragment
//        scheduleFragment.setArguments(bundle);
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
