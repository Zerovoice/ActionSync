/* 
 * Copyright (C)
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

package com.zeroapp.action.models;

import android.content.Context;
import android.util.Log;

import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.database.CategoryDataControler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * <p>
 * Title: CarouselViewDataControler.
 * </p>
 * <p>
 * Description: CarouselViewDataControler.
 * </p>
 * 
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-5-14.
 * @version $Id$
 */

public class CarouselViewDataControler {

    private static final String TAG = "CarouselViewDataControler";
    private static CarouselViewDataControler instance;
    public static List<CategoryInfo> mDatas;
    private CategoryDataControler categoryDataControler;
    private String categoryString;
    private int[] categoryInt;
    private Context context;

    public CarouselViewDataControler(Context context) {
        this.context = context;
        categoryDataControler = new CategoryDataControler(context);
    }

    public static CarouselViewDataControler getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "newInstance!");
            instance = new CarouselViewDataControler(context);
        }
        return instance;
    }

    /**
     * <p>
     * Title: updateViewDatas.
     * </p>
     * <p>
     * Description: updateViewDatas.
     * </p>
     * 
     * @param categoryString
     * 
     */
    public void updateView(String categoryStringComing) {
        if (categoryStringComing != null) {
            this.categoryString = categoryStringComing;
        }
        if (categoryString == null) {
            Log.e(TAG, "categoryString==null");
            return;
        }
        categoryInt = getcategoryList(categoryString);
        mDatas = new ArrayList<CategoryInfo>();
        for (int i = 0; i < Constants.category_msg.length; i++) {
//            int b = Arrays.binarySearch(ca, i);
//            Log.i(TAG, "i==" + i + ",    b==" + b);
            if (Arrays.binarySearch(categoryInt, i) >= 0) {
                CategoryInfo categoryInfo = categoryDataControler.query(i);
                if (categoryInfo.isLogin()) {
                    categoryInfo.setIcon(Constants.color_icon[i]);
                } else {
                    categoryInfo.setIcon(Constants.gray_icon[i]);
                }
                categoryInfo.setMsg(context.getString(Constants.category_msg[i]));
                categoryInfo.setType(i);
                mDatas.add(categoryInfo);
            }
        }
    }

    /**
     * <p>
     * Title: getcategoryList.
     * </p>
     * <p>
     * Description: getcategoryList.
     * </p>
     * 
     * @param s
     * @return
     */
    private int[] getcategoryList(String s) {
        String[] categoryS = s.split(":");
//        Log.i(TAG, "categoryS lenth==" + categoryS.length);
        int[] categoryI = new int[categoryS.length];
        for (int i = 0; i < categoryS.length; i++) {
//            Log.i(TAG, "categoryS[" + i + "]==" + categoryS[i]);
            if (categoryS[i] != null && !categoryS[i].equals("")) {
                categoryI[i] = Integer.parseInt(categoryS[i]);
            }
        }
        return categoryI;
    }
}
