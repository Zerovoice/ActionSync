/* 
 * Copyright (C) 2011  
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

package com.zeroapp.action.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zeroapp.action.R;
import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.database.CategoryDataControler;
import com.zeroapp.action.models.CategoryInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * Title: CategorySelecteActivity.
 * </p>
 * <p>
 * Description: CategorySelecteActivity.
 * </p>
 * 
 * @author Bobby Zou (zeroapp@126.com) 2014-5-13.
 * @version $Id$
 */

public class CategorySelecteActivity extends Activity implements OnClickListener {

    public static List<CategoryInfo> mDatas;
    private static final String TAG = "CategorySelecteActivity";

    private static CategorySelecteActivity instance;

    private CategoryDataControler categoryDataControler;
    private String categoryString;
    private int[] categoryInt;
    private Button BtnSelected;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategorySelecteActivity.instance = this;
        categoryDataControler = new CategoryDataControler(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("sharedPrefName", 0);
        categoryString = sharedPreferences.getString("categoryString", null);
//        categoryString = "1:2:3:4:8:9:10:16:28";
        if (categoryString == null) {
            Log.i(TAG, "categoryString==null");
            setContentView(R.layout.category_select);
            BtnSelected = (Button) findViewById(R.id.btn_category_selected);
            BtnSelected.setOnClickListener(this);
        } else {
            Log.i(TAG, "categoryString!=null");
            startMainActivity();

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
        Log.i(TAG, "categoryS lenth==" + categoryS.length);
        int[] categoryI = new int[categoryS.length];
        for (int i = 0; i < categoryS.length; i++) {
            Log.i(TAG, "categoryS[" + i + "]==" + categoryS[i]);
            if (categoryS[i] != null && !categoryS[i].equals("")) {
                categoryI[i] = Integer.parseInt(categoryS[i]);
            }
        }
        return categoryI;
    }

    /**
     * <p>
     * Title: startMainActivity.
     * </p>
     * <p>
     * Description: startMainActivity.
     * </p>
     * 
     */
    private void startMainActivity() {
        updateViewDatas();
        Intent i = new Intent(CategorySelecteActivity.this, MainActivity.class);
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * <p>
     * Title: updateViewDatas.
     * </p>
     * <p>
     * Description: updateViewDatas.
     * </p>
     * 
     */
    public void updateViewDatas() {
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
                categoryInfo.setMsg(getString(Constants.category_msg[i]));
                categoryInfo.setType(i);
                mDatas.add(categoryInfo);
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_category_selected:
                categoryString = "1:2:3:4:8:9:10:16:28";
                startMainActivity();
                break;

            default:
                break;
        }

    }

    public static CategorySelecteActivity getInstance() {
        return instance;
    }
}
