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

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.zeroapp.action.R;
import com.zeroapp.action.activity.MainActivity;
import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.models.CategoryInfo;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-29.
 * @version $Id$
 */

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private MainActivity mainActivity;

    private CategoryInfo categoryInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        mainActivity = (MainActivity) getActivity();
        categoryInfo = mainActivity.getFocusCategory();
        View v = inflater.inflate(R.layout.fragment_login, null);
        Log.i(TAG, "onCreateView--->MainActivity focus on "+categoryInfo.getMsg());

        WeiboAuth mWeiboAuth = new WeiboAuth(mainActivity, Constants.WeiboConstants.APP_KEY,
                Constants.WeiboConstants.REDIRECT_URL, Constants.WeiboConstants.SCOPE);
        mWeiboAuth.anthorize(new WeiboAuthListener() {
            
            @Override
            public void onWeiboException(WeiboException arg0) {
                Log.i(TAG, "onWeiboException");
                
            }
            
            @Override
            public void onComplete(Bundle values) {
                Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
                if (mAccessToken.isSessionValid()) {
                    Log.i(TAG, "onComplete>mAccessToken:" + mAccessToken);
                    // 显示 Token
//                    updateTokenView(false);
                    // 保存 Token 到 SharedPreferences
//                    AccessTokenKeeper.writeAccessToken(mainActivity, mAccessToken);
                } else {
                    // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                    String code = values.getString("code", "");
                    Log.i(TAG, "onComplete>code:" + code);
                }
                
            }
            
            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel");
                
            }
        });

//        Platform platform = ShareSDK.getPlatform(mainActivity, SinaWeibo.NAME);
////        platform.setPlatformActionListener(paListener);
//        platform.authorize();
        Log.i(TAG, "onCreateView over");
        return v;
    }

}
