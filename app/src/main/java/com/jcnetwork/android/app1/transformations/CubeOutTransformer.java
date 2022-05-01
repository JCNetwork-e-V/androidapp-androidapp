package com.jcnetwork.android.app1.transformations;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

public class CubeOutTransformer implements ViewPager2.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        if (position < -1){    // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);

        }
        else if (position <= 0) {    // [-1,0]
            page.setAlpha(1);
            page.setPivotX(page.getWidth());
            page.setRotationY(-90 * Math.abs(position));

        }
        else if (position <= 1){    // (0,1]
            page.setAlpha(1);
            page.setPivotX(0);
            page.setRotationY(90 * Math.abs(position));

        }
        else {    // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);

        }

        float max = Math.max(0.4f, 1 - Math.abs(position));

        if (Math.abs(position) <= 0.5){
            page.setScaleY(max);
        }
        else if (Math.abs(position) <= 1){
            page.setScaleY(max);
        }
    }
}
