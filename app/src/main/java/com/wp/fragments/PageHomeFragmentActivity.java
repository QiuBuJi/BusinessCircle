package com.wp.fragments;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.MainActivity;
import com.wp.businesscircle.PicturePagerAdapter;
import com.wp.businesscircle.R;
import com.wp.businesscircle.SlidePageChange;
import com.wp.home.CEOSchool;
import com.wp.home.MemberAdvice;
import com.wp.home.ReleaseProject;
import com.wp.home.WarCooperative;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.wp.businesscircle.R.mipmap.pic_1;
import static com.wp.businesscircle.R.mipmap.pic_2;
import static com.wp.businesscircle.R.mipmap.pic_3;
import static com.wp.businesscircle.R.mipmap.pic_4;
import static com.wp.businesscircle.R.mipmap.pic_5;

/**
 * Created by Administrator on 16/12/24/0024.
 */

public class PageHomeFragmentActivity extends Fragment implements BaseActivity, View.OnClickListener {

    public View view = null;
    public LinearLayout slide_dot_group;
    public ViewPager slide_Pager;
    public TextView slide_text;
    ImageView ll_rl_iv_SwitchSlideMenu;
    private Timer timer;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    slide_Pager.setCurrentItem(slide_Pager.getCurrentItem() + 1);
                    break;
                case 1:
                    onShowHeadLine(mHeadLine);
                    int width = mHeadLine.getWidth();
                    int width1 = width;
                    float TotalSize = mHeadLine.getText().length() * mHeadLine.getTextSize();//文字总像素长度
                    if (TotalSize < width) width1 = (int) TotalSize;

                    ObjectAnimator obj = ObjectAnimator.ofFloat(mHeadLine, "TranslationX", width, -width1);
                    obj.setDuration(mHeadLineDuration);
                    obj.start();
                    break;
            }
        }
    };
    private int mHeadPos = 1;
    private ImageView mHome_ll_ll_iv_ReleaseProject;
    private ImageView mHome_ll_ll_iv_MemberAdvice;
    private ImageView mHome_ll_ll_iv_CEO_School;
    private ImageView mHome_ll_ll_iv_WarCooperative;

    /**
     * mHeadLine要显式文字时，调用。
     *
     * @param headLine 设置要显式的文字的控件。
     */
    private void onShowHeadLine(TextView headLine) {
        String[] HeadNews = {
                "This is the test text,it can showing on the screen.",
                "two two two two two two two",
                "three three",
                "fourfourfourfourfourfourfourfourfour"};
        headLine.setText(HeadNews[mHeadPos]);
        mHeadPos++;
        if (mHeadPos > HeadNews.length - 1) {
            mHeadPos = 0;
        }
    }


    public String[] texts;
    private MainActivity mMainActivity;
    private TextView mHeadLine;
    private int mPosX;
    private int mHeadLineDuration = 9000;


    public PageHomeFragmentActivity() {

    }

    @SuppressLint("ValidFragment")
    public PageHomeFragmentActivity(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_menu_home, null);
        initView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    public void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slide_dot_group = (LinearLayout) view.findViewById(R.id.slide_dot_group);
        slide_Pager = (ViewPager) view.findViewById(R.id.slide_Pager);
        slide_text = (TextView) view.findViewById(R.id.slide_text);
        ll_rl_iv_SwitchSlideMenu = (ImageView) view.findViewById(R.id.ll_rl_iv_SwitchSlideMenu);
        mHeadLine = (TextView) view.findViewById(R.id.Home_ll_ll_tv_head_line);
        mHome_ll_ll_iv_ReleaseProject = ((ImageView) view.findViewById(R.id.Home_ll_ll_iv_ReleaseProject));
        mHome_ll_ll_iv_CEO_School = ((ImageView) view.findViewById(R.id.Home_ll_ll_iv_CEO_School));
        mHome_ll_ll_iv_MemberAdvice = ((ImageView) view.findViewById(R.id.Home_ll_ll_iv_MemberAdvice));
        mHome_ll_ll_iv_WarCooperative = ((ImageView) view.findViewById(R.id.Home_ll_ll_iv_WarCooperative));
    }

    @Override
    public void initView() {


    }

    @Override
    public void initListener() {
        slide_Pager.addOnPageChangeListener(new SlidePageChange(this));
        ll_rl_iv_SwitchSlideMenu.setOnClickListener(this);
        mHome_ll_ll_iv_ReleaseProject.setOnClickListener(this);
        mHome_ll_ll_iv_CEO_School.setOnClickListener(this);
        mHome_ll_ll_iv_MemberAdvice.setOnClickListener(this);
        mHome_ll_ll_iv_WarCooperative.setOnClickListener(this);
    }

    @Override
    public void initData() {

        int[] res = new int[]{pic_1, pic_2, pic_3, pic_4, pic_5};
        texts = new String[]{"one", "two", "three", "four", "five"};
        ArrayList<ImageView> imgs = new ArrayList<>();

        for (int i = 0; i < res.length; i++) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageResource(res[i]);
            imgs.add(imageView);

            TextView dot = new TextView(view.getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(28, 28);
            params.setMargins(8, 0, 0, 0);
            dot.setLayoutParams(params);
            dot.setBackgroundResource(R.drawable.selector_bg_dot);
            dot.setTextSize(10);
            dot.setTextColor(Color.BLACK);
            dot.setGravity(Gravity.CENTER);

            if (i == 0) {
                dot.setText(String.valueOf(i + 1));
                dot.setEnabled(true);
                slide_text.setText(texts[i]);
            } else {
                dot.setEnabled(false);
            }
            slide_dot_group.addView(dot);
        }

        slide_Pager.setAdapter(new PicturePagerAdapter(imgs));

//        幻灯片，周期换页。
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 2000, 3000);


//        幻灯片，设置滚动时间
        try {
            msclr sc = new msclr(view.getContext());
            sc.setmDuration(1200);

            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(slide_Pager, sc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //启动“商圈头条”动画
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 500, mHeadLineDuration + 700);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_rl_iv_SwitchSlideMenu:
                mMainActivity.rl_ssb_SlideSideBar.setMenuShowing(!mMainActivity.rl_ssb_SlideSideBar.getMenuShowing());
                break;
            case R.id.Home_ll_ll_iv_ReleaseProject:
                startActivity(new Intent(view.getContext(), ReleaseProject.class));
                break;
            case R.id.Home_ll_ll_iv_CEO_School:
                startActivity(new Intent(view.getContext(), CEOSchool.class));
                break;
            case R.id.Home_ll_ll_iv_MemberAdvice:
                startActivity(new Intent(view.getContext(), MemberAdvice.class));
                break;
            case R.id.Home_ll_ll_iv_WarCooperative:
                startActivity(new Intent(view.getContext(), WarCooperative.class));
                break;
        }

    }
}

class msclr extends Scroller {

    public int mDuration = 250;

    public msclr(Context context) {
        super(context);
    }

    public msclr(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public msclr(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }
}
