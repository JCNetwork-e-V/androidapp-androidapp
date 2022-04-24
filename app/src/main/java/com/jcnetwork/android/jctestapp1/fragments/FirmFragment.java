package com.jcnetwork.android.jctestapp1.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.jcnetwork.android.jctestapp1.R;
import com.jcnetwork.android.jctestapp1.models.Firm;

public class FirmFragment extends Fragment {

    // Variables
    private Firm currentFirm;
    private Context mContext;
    // Views
    private TextView descriptionTV;
    private ImageView logoImg;
    private ImageButton homeBtn, linkedinBtn, xingBtn;


    public FirmFragment() {
        // Required empty constructor
    }

    public FirmFragment(Firm currentFirm, Context context) {
        this.currentFirm = currentFirm;
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firm_card, container, false);

        // Find view
        this.descriptionTV = view.findViewById(R.id.firm_description_text);
        this.logoImg = view.findViewById(R.id.logo_img);
        this.homeBtn = view.findViewById(R.id.home_button);
        this.linkedinBtn = view.findViewById(R.id.linkedin_button);
        this.xingBtn = view.findViewById(R.id.xing_button);

        // Load image from internet
        Glide
                .with(mContext)
                .load(currentFirm.getImageLink())
                .into(this.logoImg);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.descriptionTV.setText(currentFirm.getDescription());

        // Open browser link with home, xing, and linkedin
        this.homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open link in browser
                openUrl(currentFirm.getHomePageLink());
            }
        });
        this.linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // Open link in browser
                    openUrl(currentFirm.getLinkedInLink());
            }
        });
        this.xingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open link in browser
                openUrl(currentFirm.getXingLink());
            }
        });
    }

    /**
     * Helper method to open link in browser via intent
     */
    public void openUrl(String url) {
        // Only open link in web browser if url string is not empty
        if (!url.isEmpty()) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }
}
