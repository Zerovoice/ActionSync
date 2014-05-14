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
import android.widget.GridView;

import com.zeroapp.action.R;
import com.zeroapp.action.adepter.AllCategroyAdepter;
import com.zeroapp.action.constants.Constants;
import com.zeroapp.action.models.CarouselViewDataControler;

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

    private static final String TAG = "CategorySelecteActivity";

    private String categoryString;
    private Button BtnSelected;
    private GridView gridCategory;
    private AllCategroyAdepter allCategoryAdepter;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getApplicationContext().getSharedPreferences("sharedPrefName", 0);
        categoryString = sharedPreferences.getString("categoryString", null);
//        categoryString = "1:2:3:4:8:9:10:16:28";
        if (categoryString == null) {
            Log.i(TAG, "categoryString==null");
            setContentView(R.layout.category_select);
            BtnSelected = (Button) findViewById(R.id.btn_category_selected);
            BtnSelected.setOnClickListener(this);

            gridCategory = (GridView) findViewById(R.id.grid_category);
            allCategoryAdepter = new AllCategroyAdepter(this, Constants.color_icon);
            gridCategory.setAdapter(allCategoryAdepter);
        } else {
            Log.i(TAG, "categoryString!=null");
            startMainActivity();

        }
        
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
        CarouselViewDataControler.getInstance(this).updateView(categoryString);
        Intent i = new Intent(CategorySelecteActivity.this, MainActivity.class);
        try {
            startActivity(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_category_selected:
                categoryString = "";
                CarouselViewDataControler.getInstance(this).updateView(categoryString);
                for (int i = 0; i < 32; i++) {
                    boolean ischecked = allCategoryAdepter.isEnabled(i);
//                    Log.i(TAG, i + "  ischecked  " + ischecked);
                    if(ischecked){
                        categoryString += i + ":";
                    }
                }
                Log.i(TAG, categoryString);
//                sharedPreferences.edit().putString("categoryString", categoryString).commit();//TODO 测试时，注释掉
                startMainActivity();
                break;

            default:
                break;
        }

    }
}
