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
        switch (type) {
            case DBUtils.CODE_SINA_WEIBO:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                        + DBUtils.TABLE_SINA_WEIBO), mContentValues);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_FRIEND), mContentValues);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_MOMENT), mContentValues);
                break;
            case DBUtils.CODE_QZONE:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_QZONE),
                        mContentValues);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_TENCENT_WEIBO), mContentValues);
                break;
            case DBUtils.CODE_RENREN:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_RENREN),
                        mContentValues);
                break;
            case DBUtils.CODE_DOUBAN:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_DOUBAN),
                        mContentValues);
                break;
            case DBUtils.CODE_EVERNOTE:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_EVERNOTE),
                        mContentValues);
                break;
            case DBUtils.CODE_TWITTER:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_TWITTER),
                        mContentValues);
                break;
            case DBUtils.CODE_FACEBOOK:
                mContentValues.put("username", categoryInfo.getUserName());
                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 1);
                mContentResolver.insert(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_FACEBOOK),
                        mContentValues);
                break;

            default:
                break;
        }
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
        switch (type) {
            case DBUtils.CODE_SINA_WEIBO:
//                mContentValues.put("username", categoryInfo.getUserName());
//                mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                        + DBUtils.TABLE_SINA_WEIBO), mContentValues, null, null);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_FRIEND), mContentValues, null, null);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_MOMENT), mContentValues, null, null);
                break;
            case DBUtils.CODE_QZONE:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_QZONE),
                        mContentValues, null, null);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_TENCENT_WEIBO), mContentValues, null, null);
                break;
            case DBUtils.CODE_RENREN:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_RENREN),
                        mContentValues, null, null);
                break;
            case DBUtils.CODE_DOUBAN:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_DOUBAN),
                        mContentValues, null, null);
                break;
            case DBUtils.CODE_EVERNOTE:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_EVERNOTE),
                        mContentValues, null, null);
                break;
            case DBUtils.CODE_TWITTER:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_TWITTER),
                        mContentValues, null, null);
                break;
            case DBUtils.CODE_FACEBOOK:
//              mContentValues.put("username", categoryInfo.getUserName());
//              mContentValues.put("pwd", categoryInfo.getPwd());
                mContentValues.put("islogin", 0);
                mContentResolver.update(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_FACEBOOK),
                        mContentValues, null, null);
                break;

            default:
                break;
        }

    }

    public void update() {
    }

    public CategoryInfo query(int type) {
        Cursor c = null;

        switch (type) {
            case DBUtils.CODE_SINA_WEIBO:
                c = mContentResolver.query(Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                        + DBUtils.TABLE_SINA_WEIBO), null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_SINA_WEIBO);
            case DBUtils.CODE_WECHAT_FRIEND:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_FRIEND), null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_WECHAT_FRIEND);
            case DBUtils.CODE_WECHAT_MOMENT:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_WECHAT_MOMENT), null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_WECHAT_MOMENT);
            case DBUtils.CODE_QZONE:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_QZONE),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_QZONE);
            case DBUtils.CODE_TENCENT_WEIBO:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/"
                                + DBUtils.TABLE_TENCENT_WEIBO), null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_TENCENT_WEIBO);
            case DBUtils.CODE_RENREN:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_RENREN),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_RENREN);
            case DBUtils.CODE_DOUBAN:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_DOUBAN),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_DOUBAN);
            case DBUtils.CODE_EVERNOTE:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_EVERNOTE),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_EVERNOTE);
            case DBUtils.CODE_TWITTER:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_TWITTER),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_TWITTER);
            case DBUtils.CODE_FACEBOOK:
                c = mContentResolver.query(
                        Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.TABLE_FACEBOOK),
                        null, null, null, null);
                return growCategoryInfo(c, DBUtils.CODE_FACEBOOK);

            default:
                break;
        }

        return null;
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
    private CategoryInfo growCategoryInfo(Cursor c, int type) {
        CategoryInfo categoryInfo = new CategoryInfo();
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
