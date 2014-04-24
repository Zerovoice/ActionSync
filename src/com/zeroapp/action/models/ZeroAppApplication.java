package com.zeroapp.action.models;

import android.app.Application;

import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ZeroAppApplication extends Application {
	private static ZeroAppApplication instance;

    public static List<CategoryInfo> mDatas;

    private CategoryDataControler categoryDataControler;

	@Override
	public void onCreate() {
		super.onCreate();
        Log.d("---------------------onCreate start-------------");
        categoryDataControler = new CategoryDataControler(this);
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
        Log.d("---------------------onCreate end-------------");
		
	}

	public ZeroAppApplication() {
		ZeroAppApplication.instance = this;
        Log.d("---------------------ZeroAppApplication start-------------");
	}

	public static ZeroAppApplication getInstance() {
		return instance;
	}

}
