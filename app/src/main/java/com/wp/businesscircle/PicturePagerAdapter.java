package com.wp.businesscircle;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 16/12/24/0024.
 */

public class PicturePagerAdapter extends PagerAdapter {
    List<ImageView> images;

    public PicturePagerAdapter(List<ImageView> datas) {
        this.images = datas;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE / 2;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= images.size();
        ImageView view = images.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
