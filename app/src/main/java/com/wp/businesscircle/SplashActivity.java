package com.wp.businesscircle;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 吴攀 on 2017/1/6/006.
 */

public class SplashActivity extends Activity {
    private String mUpdateUrl = "http://192.168.1.4:8080/update/update.json";
    private android.widget.TextView rl_tv_version;
    private UpdateJsonData mUpdateData;
    private PackageInfo mPackageInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.rl_tv_version = (TextView) findViewById(R.id.rl_tv_version);

        mPackageInfo = getPackageInfo();
        rl_tv_version.setText("版本：" + mPackageInfo.versionName);//设置当前版本号

        new Thread() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis();
                mUpdateData = getUpdateData();//得到apk更新数据
                millis = System.currentTimeMillis() - millis;//下载数据耗时
                millis = 2000 - millis;//用于延时

                if (millis > 0) {//延时不够
                    //补上差的延时时间
                    try {
                        sleep(millis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (mUpdateData != null) {//如果得到apk更新数据
                    if (mUpdateData.versionCode > mPackageInfo.versionCode) {//如果有高版本
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showApkUpdateDialog();//显示更新对话框
                            }
                        });
                    } else {//已是最新版本
                        goMainActivity();
                    }
                }
            }
        }.start();

    }

    /**
     * 返回主页面
     */
    private void goMainActivity() {
        setResult(1);
        finish();
    }

    private void showApkUpdateDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        //显示信息
        dialog.setTitle("A new version detected!");
        dialog.setMessage("current version:\n" + mPackageInfo.versionName +
                "\nnew version:\n" + mUpdateData.versionName + "\n\n" + mUpdateData.description);

        //更新按钮
        dialog.setPositiveButton("update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //取消按钮
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goMainActivity();
            }
        });

        //返回键按下
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                goMainActivity();
            }
        });

        //显示对话框
        dialog.show();
    }

    private PackageInfo getPackageInfo() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    /**
     * 取得apk更新数据
     */
    public UpdateJsonData getUpdateData() {
        UpdateJsonData updateJsonData = new UpdateJsonData();
        String json = downloadUpdateJson(mUpdateUrl);//下载更新apk数据的json

        if (json == null) return null;//下载失败

        try {
            JSONObject jo = new JSONObject(json);

            //解析json并保存到自定义结构中
            updateJsonData.versionName = jo.getString("VersionName");
            updateJsonData.versionCode = jo.getInt("VersionCode");
            updateJsonData.description = jo.getString("Description");
            updateJsonData.downloadUrl = jo.getString("DownloadUrl");
        } catch (JSONException e) {
//            e.printStackTrace();
            return null;
        }
        return updateJsonData;
    }

    /**
     * 下载apk更新json
     */
    private String downloadUpdateJson(String updateUrl) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            //得到连接东西
            HttpURLConnection conn = (HttpURLConnection) new URL(updateUrl).openConnection();

            //设置超时
            conn.setReadTimeout(2000);
            conn.setConnectTimeout(2000);
            conn.connect();//连接

            if (conn.getResponseCode() == 200) {//连接成功
                InputStream is = conn.getInputStream();//得到输入流
                int len = 0;
                byte by[] = new byte[1024];

                //读取json数据
                while ((len = is.read(by)) != -1) {
                    byteArrayOutputStream.write(by, 0, len);
                }

                //关闭输入流&断开连接
                is.close();
                conn.disconnect();
            }
        } catch (IOException e) {
//            e.printStackTrace();
            return null;
        }
        return byteArrayOutputStream.toString();
    }

    /**
     * 用于保存更新json数据的
     */
    class UpdateJsonData {
        int versionCode;
        String versionName;
        String description;
        String downloadUrl;
    }

}
