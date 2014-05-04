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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.models.CategoryInfo;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */

public class UserFragment extends Fragment {

    private static final String TAG = "UserFragment";

    private MainActivity mainActivity;

    private CategoryInfo categoryInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainActivity = (MainActivity) getActivity();
        categoryInfo = mainActivity.getFocusCategory();
        View v = inflater.inflate(R.layout.fragment_user, null);
        TextView tv = (TextView) v.findViewById(R.id.user_test);
        String text = "this is test:" + categoryInfo.getMsg();
        tv.setText(text);
        
        
//        if (c.isLogin()) {
//            Platform platform = ShareSDK
//                    .getPlatform(this, DBUtils.categoryManager.get(c.getType()));
//            Log.i(TAG, "platform on " + platform.getName());
//            platform.setPlatformActionListener(new PlatformActionListener() {
//
//                @Override
//                public void onError(Platform arg0, int arg1, Throwable arg2) {
//                    Log.i(TAG, "updateData--->onError");
//
//                }
//
//                @Override
//                public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//                    Log.i(TAG, "updateData--->onComplete");
//
//                }
//
//                @Override
//                public void onCancel(Platform arg0, int arg1) {
//                    Log.i(TAG, "updateData--->onCancel");
//
//                }
//            });
//            platform.showUser(null);
//        }
        return v;
    }

}
