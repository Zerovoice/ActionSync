/* 
 * Copyright (C)  . 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY zeroapp ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH zeroapp ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.action.adepter;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import cn.sharesdk.framework.authorize.AuthorizeAdapter;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zeroapp@126.com) 2014-4-30.
 * @version $Id$
 */

public class MyAuthPageAdapter extends AuthorizeAdapter {

    public void onCreate() {
        // 隐藏标题栏右部的ShareSDK Logo
        hideShareSDKLogo();

//        getActivity().setTheme(android.R.style.Theme_Dialog);
//        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置标题栏文字
//        TitleLayout llTitle = getTitleLayout();
//        llTitle.getTvTitle().setText("nihao");

        // 使弹出动画失效，只能在onCreate中调用，否则无法起作用
        disablePopUpAnimation();


        // 下面的代码演示如何设置自定义的授权页面打开动画(顶端向下)
        if (true) {
            View rv = (View) getBodyView().getParent();
            TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1,
                    Animation.RELATIVE_TO_SELF, 0);
            ta.setDuration(500);
            rv.setAnimation(ta);
        }
    }

//    public boolean onFinish() {
//        // 下面的代码演示如何设置自定义的授权页面退出动画
//        final View rv = (View) getBodyView().getParent();
//        rv.clearAnimation();
//
//        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, -1);
//        ta.setFillAfter(true);
//        ta.setDuration(500);
//        ta.setAnimationListener(new AnimationListener() {
//
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//
//            public void onAnimationEnd(Animation animation) {
////                getActivity().finish();
//            }
//        });
//        rv.setAnimation(ta);
//        return super.onFinish();
//    }

}
