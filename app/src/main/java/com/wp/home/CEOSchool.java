package com.wp.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;

/**
 * Created by Administrator on 17/1/4/0004.
 */

public class CEOSchool extends Activity implements BaseActivity, View.OnClickListener {
    private android.widget.TextView mCEOSchool_ll_rl_tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ceo_school);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        this.mCEOSchool_ll_rl_tv_back = (TextView) findViewById(R.id.CEOSchool_ll_rl_tv_back);

    }

    @Override
    public void initListener() {
        mCEOSchool_ll_rl_tv_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.CEOSchool_ll_rl_tv_back:
                finish();
                break;

        }

    }
}
