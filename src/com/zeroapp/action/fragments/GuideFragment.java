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

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.models.CategoryInfo;

import java.util.HashMap;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */

public class GuideFragment extends Fragment implements PlatformActionListener, OnClickListener {

    private static final String TAG = "UserFragment";

    private MainActivity mainActivity;
    private CategoryInfo categoryInfo;

    private View mainView;
    private ImageView notLoginImg;
    private TextView tv;
    private Button startShare;

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach");
        super.onAttach(activity);
        mainActivity = (MainActivity) getActivity();
        categoryInfo = mainActivity.getFocusCategory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainView = inflater.inflate(R.layout.fragment_guide, null);
        tv = (TextView) mainView.findViewById(R.id.user_test);
//        String text = "this is test:" + categoryInfo.getMsg();
//        tv.setText(text);
        notLoginImg = (ImageView) mainView.findViewById(R.id.not_login_img);
        startShare = (Button) mainView.findViewById(R.id.btn_start_share);
        startShare.setOnClickListener(this);
        return mainView;
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
//        if (categoryInfo.isLogin()) {
//            notLoginImg.setVisibility(View.GONE);
//            tv.setVisibility(View.VISIBLE);
//            Platform platform = ShareSDK.getPlatform(mainActivity,
//                    DBUtils.categoryManager.get(categoryInfo.getType()));
//            Log.i(TAG, "platform on " + platform.getName());
//            platform.setPlatformActionListener(this);
//            platform.showUser(null);
//        }
    }

    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {
        Log.i(TAG, "updateData--->onError");

    }

    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
        Log.i(TAG, "updateData--->onComplete");
        tv.setVisibility(View.GONE);

    }

    @Override
    public void onCancel(Platform arg0, int arg1) {
        Log.i(TAG, "updateData--->onCancel");

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_share:
                Log.i(TAG, "onClick:" + "btn_start_share");
                FragmentTransaction t = mainActivity.getSupportFragmentManager().beginTransaction();
                t.replace(R.id.fl_container, new ShareFragment()).commit();
                break;

            default:
                break;
        }

    }
}
