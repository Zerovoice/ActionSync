package com.zeroapp.action.models;

import android.app.Application;
import android.util.Log;

import cn.sharesdk.framework.ShareSDK;

import com.zeroapp.action.database.DBUtils;

public class ZeroAppApplication extends Application {

    private static final String TAG = "ZeroAppApplication";
	@Override
	public void onCreate() {
		super.onCreate();
        Log.d(TAG, "onCreate start-------------");
        // 初始化ShareSDK
        ShareSDK.initSDK(this);
        DBUtils.getCategoryManager();// 必须，否则会空指针
        Log.d(TAG, "onCreate end-------------");

    }

    @Override
    public void onTerminate() {
        // 结束ShareSDK的统计功能并释放资源
        ShareSDK.stopSDK(this);
        super.onTerminate();
    }

}
