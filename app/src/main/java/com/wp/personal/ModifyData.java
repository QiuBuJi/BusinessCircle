package com.wp.personal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;

/**
 * Created by 吴攀 on 2017/1/2/002.
 */

public class ModifyData extends Activity implements BaseActivity, View.OnClickListener {
    private android.widget.TextView ModifyData_ll_rl_tv_back;
    private android.widget.EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_modify_data);
        initView();
        initListener();
        initData();
    }

    @Override
    public void initView() {
        this.ModifyData_ll_rl_tv_back = (TextView) findViewById(R.id.ModifyData_ll_rl_tv_back);

    }

    @Override
    public void initListener() {
        ModifyData_ll_rl_tv_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ModifyData_ll_rl_tv_back:
                finish();
                break;
        }
    }
}
