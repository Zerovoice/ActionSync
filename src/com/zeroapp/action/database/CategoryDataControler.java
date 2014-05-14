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

package com.zeroapp.action.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;

import com.zeroapp.action.constants.Config;
import com.zeroapp.action.models.CategoryInfo;

/**
 * <p>
 * Title: CategoryDataControler.
 * </p>
 * <p>
 * Description: CategoryDataControler.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-23.
 * @version $Id$
 */

public class CategoryDataControler {

    private static final String TAG = "CategoryDataControler";
    private Context mContext = null;
    private ContentResolver mContentResolver;

    /**
     * <p>
     * Title: CategoryDataControler.
     * </p>
     * <p>
     * Description: CategoryDataControler.
     * </p>
     * 
     */
    public CategoryDataControler(Context context) {
        this.mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    public void insert(CategoryInfo categoryInfo) {
        Log.i(TAG, "CategoryDataControler -> insert()");
        if (Config.useDb) {
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
            mContentResolver.insert(
                    Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                            + DBUtils.getCategoryManager().get(type)), mContentValues);
        }
    }

    /**
     * <p>
     * Title: delete.
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
        if (Config.useDb) {
            if (categoryInfo == null) {
                Log.i(TAG, "delete -> categoryInfo == null");
                return;
            }
            int type = categoryInfo.getType();
            Log.i(TAG, "delete -> type== " + type);
            ContentValues mContentValues = new ContentValues();
            mContentValues.put("islogin", 0);
            mContentResolver.update(
                    Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                            + DBUtils.getCategoryManager().get(type)), mContentValues, null, null);
        }
    }

    public void update() {
        Log.i(TAG, "CategoryDataControler -> update()");
        if (Config.useDb) {

        }
    }

    public CategoryInfo query(int type) {
        return growCategoryInfo(type);
    }

    /**
     * <p>
     * Title: growCategoryInfo.
     * </p>
     * <p>
     * Description: growCategoryInfo.
     * </p>
     * 
     * @param c
     * @return
     */
    private CategoryInfo growCategoryInfo(int type) {
//        Log.i(TAG, "growCategoryInfo: " + type);
        CategoryInfo categoryInfo = new CategoryInfo();
        if (!Config.useDb) {
            Platform p = ShareSDK.getPlatform(mContext, DBUtils.getCategoryManager().get(type));
            if (p != null) {
                String userid = p.getDb().getUserId();
                // Log.i(TAG, "userid is: " + userid);
                if (userid != null && userid.length() != 0) {
                    categoryInfo.setLogin(true);
                    categoryInfo.setUserName(p.getDb().getUserName());
                }
            }
        } else {
            Cursor c = null;
            c = mContentResolver.query(
                    Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                            + DBUtils.getCategoryManager().get(type)), null, null, null, null);
            while (c != null && c.moveToLast()) {
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
        }

        return categoryInfo;
    }

}
