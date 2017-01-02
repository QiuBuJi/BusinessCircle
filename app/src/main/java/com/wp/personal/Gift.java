package com.wp.personal;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.wp.businesscircle.R;

/**
 * Created by Administrator on 17/1/2/0002.
 */

public class Gift extends Activity {

    private String TAG = "msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_gift);
        Log.d(TAG, "onCreate: ");
    }
}
