package com.jcnetwork.android.jctestapp1.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.adapters.ScheduleFragmentAdapter;
import com.jcnetwork.android.jctestapp1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.jctestapp1.models.ProgramPoint;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Schedule fragment i.e. one day schedule to be used in schedule activity
 */

public class ScheduleFragment extends Fragment {
    // Variables
    private static final String POSITION_KEY = "position";
    private int position;
    private RecyclerView mRecyclerView;

    // Logging
    private final String LOG_TAG = this.getClass().getSimpleName();

    // Data
    private List<ProgramPoint> mDailyProgram;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance(Integer pos, List<ProgramPoint> program) {
        ScheduleFragment scheduleFragment = new ScheduleFragment();
        Bundle data = new Bundle();
        data.putInt(POSITION_KEY, pos);
        data.putParcelableArrayList("SCHEDULE_KEY", (ArrayList<? extends Parcelable>) program);
        scheduleFragment.setArguments(data);
        return scheduleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION_KEY);
            Log.i(LOG_TAG, "Position" + String.valueOf(position));
            List<ProgramPoint> mProgram = getArguments().getParcelableArrayList("SCHEDULE_KEY");
            if (mProgram != null) {
                // Set default DailyProgram to full program
                mDailyProgram = mProgram;
                // Get specific daily program based on position - calculations in ProgramPointAnalysis
                try {
                    mDailyProgram = ProgramPointAnalysis.getProgramForDayY(mProgram, position);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find views
        TextView tv = view.findViewById(R.id.tv);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        // Check position and make textview only visible for first position 0; otherwise invisible
        switch (position) {
            case 0:
                tv.setVisibility(View.VISIBLE);
                tv.setText(R.string.ablaufplan_caution_text);
                break;
            default:
                tv.setVisibility(View.GONE);
        }

        // Set adapter to fill in data
        ScheduleFragmentAdapter mAdapter = new ScheduleFragmentAdapter(mDailyProgram);
        mRecyclerView.setAdapter(mAdapter);

        // Layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }
}


