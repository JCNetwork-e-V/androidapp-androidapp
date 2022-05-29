package com.jcnetwork.android.app1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.app1.models.Brainteaser;
import com.jcnetwork.android.app1.R;

import java.util.List;

public class BrainteaserFragmentAdapter extends RecyclerView.Adapter<BrainteaserFragmentAdapter.MyViewHolder> {

    // Data that needs to be fed into the adapter
    private final List<Brainteaser> bt; // cached copy of the data

    public BrainteaserFragmentAdapter(List<Brainteaser> brainteasers) {
        bt = brainteasers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout with item view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.brainteaser_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Get current brainteaser info
        Brainteaser currentBT = bt.get(position);

        // Fill item views with current program point data
        holder.task.setText(currentBT.getTeaser());

    }

    @Override
    public int getItemCount() {
        return bt.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        // Set up view
        final TextView task;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find views
            task = (TextView) itemView.findViewById(R.id.brainteaser_question_tv);

        }
    }
}
