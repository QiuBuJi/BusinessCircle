package com.wp.slide_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;

/**
 * Created by Administrator on 17/1/2/0002.
 */

public class MyCollection extends Activity implements BaseActivity, View.OnClickListener {
    private android.widget.TextView MyCollection_ll_rl_tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu_my_collection);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        this.MyCollection_ll_rl_tv_back = (TextView) findViewById(R.id.MyCollection_ll_rl_tv_back);

    }

    @Override
    public void initListener() {
        MyCollection_ll_rl_tv_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyCollection_ll_rl_tv_back:
                finish();
                break;
        }

    }
}
