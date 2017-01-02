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

public class Attention extends Activity implements BaseActivity,View.OnClickListener{
    private android.widget.TextView MyAttention_ll_rl_tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_my_attention);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        this.MyAttention_ll_rl_tv_back = (TextView) findViewById(R.id.MyAttention_ll_rl_tv_back);

    }

    @Override
    public void initListener() {
        MyAttention_ll_rl_tv_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyAttention_ll_rl_tv_back:
                finish();
                break;
        }
    }
}
