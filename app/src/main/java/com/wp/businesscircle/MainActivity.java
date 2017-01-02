package com.wp.businesscircle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wp.fragments.PageContactFragmentActivity;
import com.wp.fragments.PageConversationFragmentActivity;
import com.wp.fragments.PageHomeFragmentActivity;
import com.wp.fragments.PagePersonalFragmentActivity;
import com.wp.slide_menu.JewelBox;
import com.wp.slide_menu.MyCollection;
import static com.wp.businesscircle.R.mipmap.icon_gerenzhongxin;
import static com.wp.businesscircle.R.mipmap.icon_gerenzhongxin_click;
import static com.wp.businesscircle.R.mipmap.icon_liaotian;
import static com.wp.businesscircle.R.mipmap.icon_liaotian_click;
import static com.wp.businesscircle.R.mipmap.icon_shouye;
import static com.wp.businesscircle.R.mipmap.icon_shouye_click;
import static com.wp.businesscircle.R.mipmap.icon_tongxunlu;
import static com.wp.businesscircle.R.mipmap.icon_tongxunlu_click;

/*
 * 软件  名：生意圈(Business Circle)
-- 改编  者：吴攀 --- ---
 * 年    龄：21
-- 籍    贯：重庆 --- ---
 * 开发平台：Android studio v2.2.2
 * 托管平台：GitHub
 * 经历时间：
 *     2016年12月15日      想好项目名称，创建工程。
 *            ~           学习ing...
 *     2016年12月19日      开始编辑界面，探索“幻灯片”，怎么实现这个功能。
 *     2016年12月20日~21日 看视频，学习幻灯片、简单自定义组件原理。
 *     2016年12月22日~24日 学习反射
 *     2016年12月25日      实现“幻灯片”功能，用反射设置幻灯片翻页速度。
 *     2016年12月26日~27日 学习ListView，刷新&加载，数据原理。
 *     2016年12月28日~29日 学习侧滑原理。晚上，基本侧滑界面完成。
 *     2016年12月30日      侧滑完善中...
 *     2017年12月31日~01日 放元旦
 *     2017年01月02日      添加页面...
 *
 * */
public class MainActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener {

    public FrameLayout ll_fl_main_display;
    public ImageView ll_ll_iv_home;
    public ImageView ll_ll_iv_conv;
    public ImageView ll_ll_iv_cont;
    public ImageView ll_ll_iv_pers;
    public FragmentTransaction fbt;
    public FragmentManager fm;
    private LinearLayout mll_main;
    private LinearLayout mll_SlideMenu;
    private android.widget.RelativeLayout mActivityMain;
    public SlideSideBar rl_ssb_SlideSideBar;
    private LinearLayout mSlideMenu_ll_ll_ll_jewelBox;
    private LinearLayout mSlideMenu_ll_ll_ll_myCollection;
    private boolean IsPressOnce;
    private long mBackPressMillis;

    enum MenuBar {
        home, conversation, contact, personal
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        ll_fl_main_display = (FrameLayout) findViewById(R.id.ll_fl_main_display);

//        底部菜单
        ll_ll_iv_home = (ImageView) findViewById(R.id.ll_ll_iv_home);
        ll_ll_iv_conv = (ImageView) findViewById(R.id.ll_ll_iv_conv);
        ll_ll_iv_cont = (ImageView) findViewById(R.id.ll_ll_iv_cont);
        ll_ll_iv_pers = (ImageView) findViewById(R.id.ll_ll_iv_pers);

        mll_main = (LinearLayout) findViewById(R.id.ll_Main);
        mll_SlideMenu = (LinearLayout) findViewById(R.id.ll_SlideMenu);
        mActivityMain = (RelativeLayout) findViewById(R.id.activity_main);
        rl_ssb_SlideSideBar = (SlideSideBar) findViewById(R.id.rl_ssb_SlideSideBar);

        mSlideMenu_ll_ll_ll_jewelBox = (LinearLayout) findViewById(R.id.SlideMenu_ll_ll_ll_JewelBox);
        mSlideMenu_ll_ll_ll_myCollection = (LinearLayout) findViewById(R.id.SlideMenu_ll_ll_ll_MyCollection);
    }

    @Override
    public void initListener() {
        ll_ll_iv_home.setOnClickListener(this);
        ll_ll_iv_conv.setOnClickListener(this);
        ll_ll_iv_cont.setOnClickListener(this);
        ll_ll_iv_pers.setOnClickListener(this);

        mSlideMenu_ll_ll_ll_jewelBox.setOnClickListener(this);
        mSlideMenu_ll_ll_ll_myCollection.setOnClickListener(this);
    }

    @Override
    public void initData() {
        fm = getFragmentManager();

        switchPage(MenuBar.home);
        mll_SlideMenu.setTranslationX(-mll_SlideMenu.getWidth() * 0.6f);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ll_iv_home:
                switchPage(MenuBar.home);

                break;
            case R.id.ll_ll_iv_conv:
                switchPage(MenuBar.conversation);

                break;
            case R.id.ll_ll_iv_cont:
                switchPage(MenuBar.contact);

                break;
            case R.id.ll_ll_iv_pers:
                switchPage(MenuBar.personal);

                break;
            case R.id.SlideMenu_ll_ll_ll_JewelBox:
                startActivity(new Intent(this, JewelBox.class));
                break;
            case R.id.SlideMenu_ll_ll_ll_MyCollection:
                startActivity(new Intent(this, MyCollection.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (IsPressOnce) {
            IsPressOnce = false;
            mBackPressMillis = System.currentTimeMillis() - mBackPressMillis;
            // TODO: 17/1/2/0002  


        } else {
            IsPressOnce = true;
            mBackPressMillis = System.currentTimeMillis();
        }

    }

    public void switchPage(MenuBar menuBar) {

        ll_ll_iv_home.setImageResource(icon_shouye);
        ll_ll_iv_conv.setImageResource(icon_liaotian);
        ll_ll_iv_cont.setImageResource(icon_tongxunlu);
        ll_ll_iv_pers.setImageResource(icon_gerenzhongxin);

        switch (menuBar) {
            case home:
                PageHomeFragmentActivity f1 = new PageHomeFragmentActivity(this);

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f1);
                fbt.commit();

                ll_ll_iv_home.setImageResource(icon_shouye_click);
                break;
            case conversation:
                PageConversationFragmentActivity f2 = new PageConversationFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f2);
                fbt.commit();

                ll_ll_iv_conv.setImageResource(icon_liaotian_click);
                break;
            case contact:
                PageContactFragmentActivity f3 = new PageContactFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f3);
                fbt.commit();

                ll_ll_iv_cont.setImageResource(icon_tongxunlu_click);
                break;
            case personal:
                PagePersonalFragmentActivity f4 = new PagePersonalFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f4);
                fbt.commit();

                ll_ll_iv_pers.setImageResource(icon_gerenzhongxin_click);
                break;
        }
    }

}
