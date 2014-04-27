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

package com.zeroapp.action.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.zeroapp.action.models.CategoryInfo;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-23.
 * @version $Id$
 */

public class CategoryDataControler {

    private static final String TAG = "CategoryDataControler";
    private Context mContext = null;
    private ContentResolver mContentResolver;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     */
    public CategoryDataControler(Context context) {
        this.mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    public void insert(CategoryInfo categoryInfo) {
        Log.i(TAG, "CategoryDataControler -> insert()");
        if (categoryInfo == null) {
            Log.i(TAG, "insert -> categoryInfo == null");
            return;
        }
        int type = categoryInfo.getType();
        Log.i(TAG, "insert -> type== " + type);
        ContentValues mContentValues = new ContentValues();
        
        mContentValues.put("username", categoryInfo.getUserName());
        mContentValues.put("pwd", categoryInfo.getPwd());
        mContentValues.put("islogin", 1);
        mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                + DBUtils.categoryManager.get(type)), mContentValues);
        categoryInfo.setLogin(true);// TODO 数据库更新成功时要更新应用数据,但是要放到UI里，不能在数据层做这个事情
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description:
     * 删除操作并非是真的删除数据，而是将登陆标记改为0.因此，此时数据库中仍然保留了用户登陆的信息，只是在UI调用是要先检查登陆标记.
     * </p>
     * 
     * @param categoryInfo
     */
    public void delete(CategoryInfo categoryInfo) {
        Log.i(TAG, "CategoryDataControler -> delete()");
        if (categoryInfo == null) {
            Log.i(TAG, "delete -> categoryInfo == null");
            return;
        }
        int type = categoryInfo.getType();
        Log.i(TAG, "delete -> type== " + type);
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("islogin", 0);
        mContentResolver.update(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                + DBUtils.categoryManager.get(type)), mContentValues, null, null);
    }

    public void update() {
    }

    public CategoryInfo query(int type) {
        return growCategoryInfo(type);
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param c
     * @return
     */
    private CategoryInfo growCategoryInfo(int type) {
        CategoryInfo categoryInfo = new CategoryInfo();
        Cursor c = null;
        c = mContentResolver.query(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                + DBUtils.categoryManager.get(type)), null, null, null, null);
        while (c.moveToLast()) {
            String username = c.getString(c.getColumnIndex("username"));
            String pwd = c.getString(c.getColumnIndex("pwd"));
            int islogin = c.getInt(c.getColumnIndex("islogin"));
            Log.d(TAG, "username === " + username);
            Log.d(TAG, "pwd === " + pwd);
            Log.d(TAG, "islogin === " + islogin);
            Log.d(TAG, "type === " + type);
            categoryInfo.setUserName(username);
            categoryInfo.setPwd(pwd);
            categoryInfo.setType(type);
            if (islogin == 1) {// 1-登陆、0-未登录
                categoryInfo.setLogin(true);
            } else {
                categoryInfo.setLogin(false);
            }
            return categoryInfo;
        }

        return categoryInfo;
    }

}
