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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;

/**
 * <p>
 * Title: GuideFragment.
 * </p>
 * <p>
 * Description: GuideFragment.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-29.
 * @version $Id$
 */

public class GuideFragment extends Fragment implements OnClickListener {

    private static final String TAG = "UserFragment";

    private MainActivity mainActivity;

    private View mainView;
    private ImageView notLoginImg;
    private Button startShare;

    @Override
    public void onAttach(Activity activity) {
        Log.i(TAG, "onAttach");
        super.onAttach(activity);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainView = inflater.inflate(R.layout.fragment_guide, null);
        notLoginImg = (ImageView) mainView.findViewById(R.id.not_login_img);
        startShare = (Button) mainView.findViewById(R.id.btn_start_share);
        startShare.setOnClickListener(this);
        return mainView;
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

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
