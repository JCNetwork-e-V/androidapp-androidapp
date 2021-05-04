package com.jcnetwork.android.jctestapp1.adapters;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.hiddenactivities.FAQsActivity;
import com.jcnetwork.android.jctestapp1.models.QandA;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FAQExpandableCardViewAdapter extends RecyclerView.Adapter<FAQExpandableCardViewAdapter.ViewHolder> {

    /** Variables **/
    private List<QandA> qandAdata;
    private SparseBooleanArray isExpandedArray = new SparseBooleanArray(); // to tell if a cardview at position i is expanded or not

    /** Constructor **/
    public FAQExpandableCardViewAdapter(List<QandA> list) {
        qandAdata = list;
        // Set initial expanded state to false for all items in list
        for (int i = 0; i < list.size(); i++) {
            isExpandedArray.append(i, false);
        }
    }

    @NonNull
    @Override
    public FAQExpandableCardViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout with the card view from faq_item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQExpandableCardViewAdapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        // Set to data
        holder.questionTV.setText(qandAdata.get(position).getQuestion());
        holder.answerTV.setText(qandAdata.get(position).getAnswer());

        // Set up on click listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExpanded = isExpandedArray.get(position);
                Log.i("FAQ Adapter", "is expanded" + String.valueOf(isExpanded));
                if (isExpanded) {
                    // Hide views (answer)
                    holder.answerTV.setVisibility(View.GONE);
                    holder.shadowView.setVisibility(View.GONE);
                    // And rotate arrow
                    createRotateAnimator(holder.arrowBtn, 180f, 0f).start();
                    // Set expanded state to false
                    isExpandedArray.put(position, false);
                } else {
                    // Show views (answer)
                    holder.answerTV.setVisibility(View.VISIBLE);
                    holder.shadowView.setVisibility(View.VISIBLE);
                    // And rotate arrow
                    createRotateAnimator(holder.arrowBtn, 0f, 180f).start();
                    // Set expanded state to true
                    isExpandedArray.put(position, true);
                }
            }
        });
    }

    /** To rotate **/
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    @Override
    public int getItemCount() {
        return qandAdata.size();
    }

    /**
     * Filter method for searching within the questions for a match
     * @param text of the user query
     */
    public void filter(String text) {
        // Convert query text to lower case
        text = text.toLowerCase(Locale.getDefault());

        // Create a new empty list
        List<QandA> filteredQs = new ArrayList<>();

        // If no query has been entered yet, display all questions
        if (text.length() == 0) {
            filteredQs.addAll(qandAdata);
        } else {
            // If the query text has a word that is contained by one+ of the questions, display them
            for (QandA matchingQs : qandAdata) {
                // Checking for each questions if the question text contains the query text
                if (matchingQs.getQuestion().toLowerCase(Locale.getDefault()).contains(text)) {
                    filteredQs.add(matchingQs);
                }
            }
        }

        // Set this created filteredQs as new data and notify of the change
        qandAdata = filteredQs;
        notifyDataSetChanged();
    }

    /** Viewholder class **/
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView questionTV,answerTV;
        private ImageButton arrowBtn;
        private View shadowView;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);
            questionTV = (TextView)view.findViewById(R.id.question);
            answerTV = (TextView)view.findViewById(R.id.answer);
            arrowBtn = (ImageButton) view.findViewById(R.id.arrow_button);
            shadowView = (View)view.findViewById(R.id.shadow);
            cardView = (CardView)view.findViewById(R.id.card_layout);
        }
    }

}
