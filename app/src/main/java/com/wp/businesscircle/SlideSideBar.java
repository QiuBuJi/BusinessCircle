package com.wp.businesscircle;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 16/12/30/0030.
 */

public class SlideSideBar extends ViewGroup {

    private String TAG = "msg";
    //要操作的控件
    private View mLeftView;
    private View mRightView;

    private float touchX;//手指按下时的x轴向位置
    private float moveX;//手指移动时x轴向位置
    private float mLimitX;//分界位置，“侧滑菜单mLeftView”与“主内容mRightView”之间的交点的那条边的x轴位置。
    private float mMoveOffset;//移动偏移
    private Scroller mScroller;//滚动器，用于模拟滑动效果。
    private double mParkPos;//右边，停靠位置。
    private float mOffsetSum;//每次偏移距离的合。侧滑菜单（mLeftView），滑动有关。
    private long mOffsetCount;//偏移的次数。侧滑菜单（mLeftView），滑动有关。
    private Point mPt;
    private double mDuration = 1.2;//持续时间
    private boolean mShowing;//侧滑菜单显示状态
    private OnShowStateChangeListener mShowStateChangeListener;
    private boolean mIsStartup;

    /**
     * 初始化
     */
    private void init() {
        mScroller = new Scroller(getContext());

//        setOnShowStateChangeListener(new OnShowStateChangeListener() {
//            @Override
//            public void onShowStateChangeListener(boolean ShowState) {
//                Log.d(TAG, "onShowStateChangeListener: " + ShowState);
//            }
//        });
    }

    public SlideSideBar(Context context) {
        super(context);
        init();
    }

    public SlideSideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //取出子控件
        mLeftView = getChildAt(0);
        mRightView = getChildAt(1);

        mLeftView.setClickable(false);

        //测量子控件尺寸
        mLeftView.measure(widthMeasureSpec, heightMeasureSpec);
        mRightView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //取出子控件
        mLeftView = getChildAt(0);
        mRightView = getChildAt(1);

        //为子控件布局（设置子控件摆放位置&尺寸）
        mLeftView.layout(l, t, r, b);
        mRightView.layout(l, t, r, b);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mPt = new Point(((int) ev.getX()), ((int) ev.getY()));
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float xx = mPt.x - ev.getX();
//                float yy = mPt.y - ev.getY();
//
//                if (Math.abs(xx) > Math.abs(yy)) {
//                    return true;
//                }
//                break;
//        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int width = getWidth();//取出本容器的宽度
        mParkPos = width * 0.68;//停靠位置
        double limit = width * 0.4;//mLimitX以此limit为临界值。LimitX>limit侧滑菜单（mLeftView）打开，否则关闭。

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                mScroller.forceFinished(true);//当在动画时，手指点击停止动画。
                break;
            case MotionEvent.ACTION_UP:

                mOffsetSum /= mOffsetCount;//求出平均值
                //惯性滑动
                if (Math.abs(mOffsetSum) > 6) {//看平均值，是否超过阈值。
                    setMenuShowing(mMoveOffset > 0);//mMoveOffset可用于判断手指滑动方向

                    //临界值滑动
                } else {
                    if (mLimitX > limit) {//limit为临界值
                        //向右滑动。→
                        double offsetX = mParkPos - mLimitX;
                        mScroller.startScroll((int) mLimitX, 0, (int) (offsetX), 0, (int) ((int) Math.abs(offsetX) * mDuration));

                        //侧滑菜单，显式状态改变时，调用。
                        if (mShowing == false) mIsStartup = true;
                        mShowing = true;

                    } else {
                        //向左滑动 ←
                        mScroller.startScroll((int) mLimitX, 0, (int) (-mLimitX), 0, (int) ((int) Math.abs(mLimitX) * mDuration));

                        //侧滑菜单，显式状态改变时，调用。
                        if (mShowing == true) mIsStartup = true;
                        mShowing = false;
                    }

                    //这个是mScroller需要
                    invalidate();
                }

                //手指离开后，复原数值。
                mOffsetCount = 0;
                mOffsetSum = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                mMoveOffset = moveX - touchX;//求出，移动偏移。
                touchX = moveX;

                mOffsetSum += mMoveOffset;//把每次的“移动偏移”累计，根据mOffsetCount来求出平均数。
                mOffsetCount++;//计数

                setLimit(mRightView.getTranslationX() + mMoveOffset);//侧滑菜单，滑动“参数”距离。
                break;
        }

        return true;
    }

    public void setLimit(float x) {
        mLimitX = x;
        mRightView.setTranslationX(mLimitX);//移动“主内容mRightView”


        float ratio = 0.4f;//mLeftView与mRightView之间的移动比例
        /*
        * 移动“侧滑菜单mLeftView”
        * mLeftView它的移动距离在mRightView移动的基础上缩小了ratio倍。
        * */
        float leftHideOffset = (float) mParkPos * ratio;//左边隐藏部分的位移
        float shrinkPosX = mLimitX * ratio;//缩小后的x轴向移动位置
        mLeftView.setTranslationX(shrinkPosX - leftHideOffset);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()) {//如果在计算偏移，继续。
            setLimit(mScroller.getCurrX());//以mScroller模拟的位置来实现惯性滑动效果。
            invalidate();
        } else if (mIsStartup) {//当滑动结束时，触发事件。
            mShowStateChangeListener.onShowStateChangeListener(mShowing);
            mIsStartup = false;
        }
    }

    /**
     * 设置菜单显示状态
     *
     * @param isShowing 为true，显示侧滑菜单。false，隐藏侧滑菜单。
     */
    public void setMenuShowing(boolean isShowing) {
        if (isShowing) {//mMoveOffset可用于判断手指滑动方向，
            // 向右滑动。→
            double offsetX = mParkPos - mLimitX;//求出右停靠位置mParkPos与mLimitX之间的位移
            mScroller.startScroll((int) mLimitX, 0, (int) (offsetX), 0, (int) ((int) Math.abs(offsetX) * mDuration));
        } else {
            //向左滑动 ←
            mScroller.startScroll((int) mLimitX, 0, (int) (-mLimitX), 0, (int) ((int) Math.abs(mLimitX) * mDuration));
        }

        //侧滑菜单，显式状态改变时，调用。
        if (mShowing ^ isShowing) mIsStartup = true;

        mShowing = isShowing;//保存状态
        invalidate();
    }

    /**
     * 取菜侧滑单显示状态
     *
     * @return true侧滑菜单显示状态、false侧滑菜单隐藏状态。
     */
    public boolean getMenuShowing() {
        return mShowing;
    }

    /**
     * 侧滑显示状态改变的监听器
     *
     * @param listener 为true显示，false隐藏。
     */
    public void setOnShowStateChangeListener(OnShowStateChangeListener listener) {
        mShowStateChangeListener = listener;
    }

    interface OnShowStateChangeListener {
        void onShowStateChangeListener(boolean ShowState);
    }
}
