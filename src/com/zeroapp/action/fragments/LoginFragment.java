/* 
 * Copyright (C) 2011 Hisense Electric Co., Ltd. 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY HISENSE ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH HISENSE ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.action.fragments;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;

import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.database.DBUtils;
import com.zeroapp.action.models.CategoryInfo;

import java.util.HashMap;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */

public class LoginFragment extends Fragment implements PlatformActionListener {

    private static final String TAG = "LoginFragment";

    private MainActivity mainActivity;

    private CategoryInfo categoryInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainActivity = (MainActivity) getActivity();
        categoryInfo = mainActivity.getFocusCategory();
        View v = null;
//      v = inflater.inflate(R.layout.fragment_login, null);
        Log.i(TAG, "onCreateView--->MainActivity focus on "+categoryInfo.getMsg());
        Platform platform = ShareSDK.getPlatform(mainActivity,
                DBUtils.categoryManager.get(categoryInfo.getType()));
        Log.i(TAG, "platform on " + platform.getName());
        platform.setPlatformActionListener(this);

        if (categoryInfo.isLogin()) {
//            platform.showUser(null);
        } else {
            platform.authorize();
        }
        Log.i(TAG, "onCreateView over");
        return v;
    }

    public void onComplete(Platform plat, int action, HashMap<String, Object> res) {

        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, mainActivity);
    }

    public void onError(Platform plat, int action, Throwable t) {
        t.printStackTrace();

        Message msg = new Message();
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, mainActivity);
    }

    public void onCancel(Platform plat, int action) {
        Message msg = new Message();
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = plat;
        UIHandler.sendMessage(msg, mainActivity);
    }
}
