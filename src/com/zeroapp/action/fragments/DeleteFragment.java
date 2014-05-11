/* 
 * Copyright (C)  . 
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
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.utils.UIHandler;

import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.database.DBUtils;
import com.zeroapp.action.models.CategoryInfo;

import java.util.HashMap;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zeroapp@126.com) 2014-4-29.
 * @version $Id$
 */

public class DeleteFragment extends Fragment implements OnClickListener, PlatformActionListener {

    private static final String TAG = "DeleteFragment";
    private Button logout;
    private MainActivity mainActivity;
    private CategoryInfo categoryInfo;
    private View v;
    /**
     * if TopFrameLayout is not shown, scroll won't make an animotion.
     */
    
    public DeleteFragment(CategoryInfo c) {
        this.categoryInfo = c;
    }


    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach");
        super.onAttach(activity);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        v = inflater.inflate(R.layout.fragment_delete, null);
        logout = (Button) v.findViewById(R.id.btn_logout);
        logout.setOnClickListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                Log.i(TAG, "onClick:" + "btn_logout");
                Platform p = ShareSDK.getPlatform(mainActivity,
                        DBUtils.categoryManager.get(categoryInfo.getType()));
                p.setPlatformActionListener(this);
                p.removeAccount();
                CategoryDataControler cc = new CategoryDataControler(mainActivity);
                cc.delete(categoryInfo);
                mainActivity.hideDeleteFragment();
                mainActivity.updateCarouselView();
                break;

            default:
                break;
        }
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

    public void setFocusCategory(CategoryInfo c) {
        this.categoryInfo = c;

    }

}
