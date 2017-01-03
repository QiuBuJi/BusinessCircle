package com.wp.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;

/**
 * Created by Administrator on 17/1/3/0003.
 */

public class ReleaseProject extends Activity implements BaseActivity, View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_release_project);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
