package com.zeroapp.action.models;

import android.app.Application;
import android.util.Log;

import cn.sharesdk.framework.ShareSDK;

import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.database.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class ZeroAppApplication extends Application {

    private static final String TAG = "ZeroAppApplication";

    private static ZeroAppApplication instance;

    public static List<CategoryInfo> mDatas;

    private CategoryDataControler categoryDataControler;

	@Override
	public void onCreate() {
		super.onCreate();
        Log.d(TAG, "---------------------onCreate start-------------");
        // 初始化ShareSDK
        ShareSDK.initSDK(this);
        categoryDataControler = new CategoryDataControler(this);
        DBUtils.initCategoryManager();// 必须，否则会空指针
        updateViewDatas();
        Log.d(TAG, "---------------------onCreate end-------------");

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     */
    public void updateViewDatas() {
        mDatas = new ArrayList<CategoryInfo>();
        // 初始化支持的社交类型，预置10种，此处可以控制
        for (int i = 0; i < Constants.category_msg.length; i++) {

            CategoryInfo categoryInfo = categoryDataControler.query(i);
            if (categoryInfo.isLogin()) {
                categoryInfo.setIcon(Constants.color_icon[i]);
            } else {
                categoryInfo.setIcon(Constants.gray_icon[i]);
            }
            categoryInfo.setMsg(getString(Constants.category_msg[i]));
            categoryInfo.setType(i);
            mDatas.add(categoryInfo);
        }

    }

    public ZeroAppApplication() {
		ZeroAppApplication.instance = this;
        Log.d(TAG,"---------------------ZeroAppApplication start-------------");
	}

	public static ZeroAppApplication getInstance() {
		return instance;
	}

}
