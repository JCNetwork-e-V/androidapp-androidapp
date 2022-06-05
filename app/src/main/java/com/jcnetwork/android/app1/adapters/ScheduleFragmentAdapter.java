package com.jcnetwork.android.app1.adapters;

import static com.jcnetwork.android.app1.R.id;
import static com.jcnetwork.android.app1.R.layout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.app1.R;
import com.jcnetwork.android.app1.conversion.ProgramPointAnalysis;
import com.jcnetwork.android.app1.models.ProgramPoint;
import com.jcnetwork.android.app1.ui.DetailActivity;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.List;

/**
 * This adapter (not to be confused with schedule adapter) is to populate the list (recycleview)
 * with data from the program i.e. Ablaufsplan
 */

public class ScheduleFragmentAdapter extends RecyclerView.Adapter<ScheduleFragmentAdapter.MyViewHolder> {

    // Data that needs to be fed into the adapter
    private final List<ProgramPoint> mProgram; // cached copy of the data


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Set up views
        public final TextView titleTV;
        public final TextView placeTV;
        public final TextView timeTV;
        public final View colorBar;
        public final ImageView goArrowImg;
        public final FrameLayout frameLayout;
        public final View dividingLineView;
        public final CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            // Find views in itemView
            titleTV = (TextView) itemView.findViewById(id.title);
            placeTV = (TextView) itemView.findViewById(id.place);
            timeTV = (TextView) itemView.findViewById(id.time);
            colorBar = (View) itemView.findViewById(id.color_bar);
            goArrowImg = (ImageView) itemView.findViewById(id.go_arrow);
            frameLayout = (FrameLayout) itemView.findViewById(id.frame);
            dividingLineView = (View) itemView.findViewById(id.dividing_line);
            cardView = (CardView) itemView.findViewById(id.card_layout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ScheduleFragmentAdapter(List<ProgramPoint> myDataset) {
        mProgram = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public ScheduleFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        // Set up
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Inflate layout with itemView
        View itemView = inflater.inflate(layout.fragment_plan_event_item, parent, false);

        // create a new view
        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.cardView.setOnClickListener(view -> {
            Log.i("ScheduleFragmentAdapter", "clicked on card view");
            // Open details view
            Intent openDetails = new Intent(view.getContext(), DetailActivity.class);
            // Add data
            Bundle data = new Bundle();
            data.putParcelable("EVENT_KEY", mProgram.get(position));
            openDetails.putExtras(data);
            // Make Options available
            android.util.Pair[] pairs = new android.util.Pair[4];
//                pairs[0] = new android.util.Pair(holder.colorBar, "bar");
            pairs[0] = new android.util.Pair(holder.titleTV, "title");
            pairs[1] = new android.util.Pair(holder.timeTV, "time");
            pairs[2] = new android.util.Pair(holder.placeTV, "place");
            pairs[3] = new android.util.Pair(holder.goArrowImg, "map");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(),
                    pairs);
//                ActivityOptions optionSs = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), pairs);
            // Open detail view
            view.getContext().startActivity(openDetails, options.toBundle());
        });

        // Get current program point
        if (mProgram != null) {
            ProgramPoint currentProgramPoint = mProgram.get(position);

            // Fill item views with current program point data
            holder.titleTV.setText(currentProgramPoint.getTitle());
            holder.placeTV.setText(currentProgramPoint.getPlace());
            try {
                holder.timeTV.setText(ProgramPointAnalysis.getTimeStringFromBeginAndEnd(currentProgramPoint.getBegin(), currentProgramPoint.getEnd()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Set color
            String color = currentProgramPoint.getColor();
            if (color.equals("white")) {
                holder.colorBar.setBackgroundResource(R.color.white);
            }
            if (color.equals("jcblau")) {
                // set background to blue
                holder.colorBar.setBackgroundResource(R.color.colorPrimary);
                holder.dividingLineView.setBackgroundResource(R.color.colorPrimary);
                holder.frameLayout.setBackgroundResource(R.color.colorPrimary);
            }
            if (color.equals("grey")) {
                // set background to gray
                holder.colorBar.setBackgroundResource(R.color.lightGrey);
                holder.dividingLineView.setBackgroundResource(R.color.lightGrey);
                holder.frameLayout.setBackgroundResource(R.color.lightGrey);
            }
            if (color.equals("blau")) {
                // set background to blue
                holder.colorBar.setBackgroundResource(R.color.darkerBlue);
                holder.dividingLineView.setBackgroundResource(R.color.darkerBlue);
                holder.frameLayout.setBackgroundResource(R.color.darkerBlue);
            }
            if (color.equals("partygrey")) {
                // set background to gray
                holder.colorBar.setBackgroundResource(R.color.partyGrey);
                holder.dividingLineView.setBackgroundResource(R.color.partyGrey);
                holder.frameLayout.setBackgroundResource(R.color.partyGrey);
            }

            // Set on click listener on go arrow image to provide directions via Google maps to event
            holder.goArrowImg.setOnClickListener(view -> {
                // Get location from current program
                String address = currentProgramPoint.getAddress();
                // Check if information was provided
                if (!address.isEmpty()) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    view.getContext().startActivity(mapIntent);
                }
            });
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mProgram != null) {
            return mProgram.size();
        }
        return 0;
    }
}
