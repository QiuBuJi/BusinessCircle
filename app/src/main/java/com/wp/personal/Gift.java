package com.wp.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;

/**
 * Created by Administrator on 17/1/2/0002.
 */

public class Gift extends Activity implements BaseActivity,View.OnClickListener {

    private String TAG = "msg";
    private android.widget.TextView ll_rl_tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_gift);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        this.ll_rl_tv_back = (TextView) findViewById(R.id.ll_rl_tv_back);

    }

    @Override
    public void initListener() {
        ll_rl_tv_back.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_rl_tv_back:
                finish();
                break;
        }
    }
}
