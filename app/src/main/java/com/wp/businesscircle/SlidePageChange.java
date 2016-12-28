package com.wp.businesscircle;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.wp.fragments.PageHomeFragmentActivity;

/**
 * Created by 吴攀 on 2016/12/24/024.
 */

public class SlidePageChange implements ViewPager.OnPageChangeListener {
    PageHomeFragmentActivity mActivity;

    public SlidePageChange(PageHomeFragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int mCount = mActivity.slide_dot_group.getChildCount();
        position %= mCount;
        for (int i = 0; i < mCount; i++) {
            TextView mChild = (TextView) mActivity.slide_dot_group.getChildAt(i);
            if (i == position) {
                mChild.setText(i + 1 + "");
                mActivity.slide_text.setText(mActivity.texts[i]);
                mChild.setEnabled(true);
            } else {
                mChild.setEnabled(false);
                mChild.setText("");
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
