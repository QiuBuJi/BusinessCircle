package com.wp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wp.businesscircle.BaseActivity;
import com.wp.businesscircle.R;
import com.wp.personal.Attention;
import com.wp.personal.Gift;
import com.wp.personal.ModifyData;

/**
 * Created by Administrator on 16/12/24/0024.
 */

public class PagePersonalFragmentActivity extends Fragment implements BaseActivity, View.OnClickListener {

    private View view;
    private LinearLayout mModifyData;
    private String TAG = "msg";
    private LinearLayout mGift;
    private LinearLayout mMyAttention;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_menu_personal, null);

        return view;
    }

    @Override
    public void initView() {
        mModifyData = ((LinearLayout) view.findViewById(R.id.ll_ll_personal_ModifyData));
        mGift = ((LinearLayout) view.findViewById(R.id.ll_ll_personal_Gift));
        mMyAttention = ((LinearLayout) view.findViewById(R.id.ll_ll_personal_MyAttention));
    }

    @Override
    public void initListener() {
        mModifyData.setOnClickListener(this);
        mGift.setOnClickListener(this);
        mMyAttention.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ll_personal_ModifyData:
                startActivity(new Intent(view.getContext(), ModifyData.class));
                break;
            case R.id.ll_ll_personal_Gift:
                startActivity(new Intent(view.getContext(), Gift.class));
                break;
            case R.id.ll_ll_personal_MyAttention:
                startActivity(new Intent(view.getContext(), Attention.class));
                break;
        }
    }
}
