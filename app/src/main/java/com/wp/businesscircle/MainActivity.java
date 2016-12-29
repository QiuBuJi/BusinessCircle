package com.wp.businesscircle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wp.fragments.PageContactFragmentActivity;
import com.wp.fragments.PageConversationFragmentActivity;
import com.wp.fragments.PageHomeFragmentActivity;
import com.wp.fragments.PagePersonalFragmentActivity;

import java.util.ArrayList;

import static com.wp.businesscircle.R.mipmap.*;

/*
 * 软件  名：生意圈(Business Circle)
-- 改编  者：吴攀 --- ---
 * 年    龄：21
-- 籍    贯：重庆 --- ---
 * 开发平台：Android studio v2.2.2
 * 经历时间：
 *     2016年12月15日      想好项目名称，创建工程。
 *            ~           学习ing...
 *     2016年12月19日      开始编辑界面，探索“幻灯片”，怎么实现这个功能。
 *     2016年12月20日~21日 看视频，学习幻灯片、简单自定义组件原理。
 *     2016年12月22日~24日 学习反射
 *     2016年12月25日      实现“幻灯片”功能，用反射设置幻灯片翻页速度。
 *     2016年12月26日~27日 学习ListView，刷新&加载，数据原理。
 *
 * */
public class MainActivity extends AppCompatActivity implements BaseActivity, View.OnClickListener {

    public TextView ll_tv_bs_head_line;
    public FrameLayout ll_fl_main_display;
    public ImageView ll_ll_iv_home;
    public ImageView ll_ll_iv_conv;
    public ImageView ll_ll_iv_cont;
    public ImageView ll_ll_iv_pers;
    public FragmentTransaction fbt;
    public FragmentManager fm;

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
        ll_tv_bs_head_line = (TextView) findViewById(R.id.ll_tv_bs_head_line);
        ll_fl_main_display = (FrameLayout) findViewById(R.id.ll_fl_main_display);

//        底部菜单
        ll_ll_iv_home = (ImageView) findViewById(R.id.ll_ll_iv_home);
        ll_ll_iv_conv = (ImageView) findViewById(R.id.ll_ll_iv_conv);
        ll_ll_iv_cont = (ImageView) findViewById(R.id.ll_ll_iv_cont);
        ll_ll_iv_pers = (ImageView) findViewById(R.id.ll_ll_iv_pers);

    }

    @Override
    public void initListener() {
        ll_ll_iv_home.setOnClickListener(this);
        ll_ll_iv_conv.setOnClickListener(this);
        ll_ll_iv_cont.setOnClickListener(this);
        ll_ll_iv_pers.setOnClickListener(this);

    }

    @Override
    public void initData() {
        fm = getFragmentManager();

        switchPage(MenuBar.home);

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
        }
    }

    public void switchPage(MenuBar menuBar) {
        switch (menuBar) {
            case home:
                PageHomeFragmentActivity f1 = new PageHomeFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f1);
                fbt.commit();

                ll_ll_iv_home.setImageResource(icon_shouye_click);
                ll_ll_iv_conv.setImageResource(icon_liaotian);
                ll_ll_iv_cont.setImageResource(icon_tongxunlu);
                ll_ll_iv_pers.setImageResource(icon_gerenzhongxin);
                break;
            case conversation:
                PageConversationFragmentActivity f2 = new PageConversationFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f2);
                fbt.commit();

                ll_ll_iv_home.setImageResource(icon_shouye);
                ll_ll_iv_conv.setImageResource(icon_liaotian_click);
                ll_ll_iv_cont.setImageResource(icon_tongxunlu);
                ll_ll_iv_pers.setImageResource(icon_gerenzhongxin);
                break;
            case contact:
                PageContactFragmentActivity f3 = new PageContactFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f3);
                fbt.commit();

                ll_ll_iv_home.setImageResource(icon_shouye);
                ll_ll_iv_conv.setImageResource(icon_liaotian);
                ll_ll_iv_cont.setImageResource(icon_tongxunlu_click);
                ll_ll_iv_pers.setImageResource(icon_gerenzhongxin);
                break;
            case personal:
                PagePersonalFragmentActivity f4 = new PagePersonalFragmentActivity();

                fbt = fm.beginTransaction();
                fbt.replace(R.id.ll_fl_main_display, f4);
                fbt.commit();

                ll_ll_iv_home.setImageResource(icon_shouye);
                ll_ll_iv_conv.setImageResource(icon_liaotian);
                ll_ll_iv_cont.setImageResource(icon_tongxunlu);
                ll_ll_iv_pers.setImageResource(icon_gerenzhongxin_click);
                break;
        }
    }

    enum MenuBar {
        home, conversation, contact, personal
    }

}
