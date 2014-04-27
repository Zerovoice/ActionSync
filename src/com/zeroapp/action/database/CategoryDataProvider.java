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

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-22.
 * @version $Id$
 */

public class CategoryDataProvider extends ContentProvider {

    private static final String TAG = "CategoryDataProvider";
    private DBHelper helper;  
    private SQLiteDatabase db;  

    private static final UriMatcher sMatcher;
    static {
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_SINA_WEIBO, DBUtils.CODE_SINA_WEIBO);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_WECHAT_FRIEND, DBUtils.CODE_WECHAT_FRIEND);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_WECHAT_MOMENT, DBUtils.CODE_WECHAT_MOMENT);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_QZONE, DBUtils.CODE_QZONE);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_TENCENT_WEIBO, DBUtils.CODE_TENCENT_WEIBO);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_RENREN, DBUtils.CODE_RENREN);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_DOUBAN, DBUtils.CODE_DOUBAN);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_EVERNOTE, DBUtils.CODE_EVERNOTE);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_TWITTER, DBUtils.CODE_TWITTER);
        sMatcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_FACEBOOK, DBUtils.CODE_FACEBOOK);
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return
     */
    @Override
    public boolean onCreate() {
        Log.i(TAG, "CategoryDataProvider -> onCreate()");
        try {
            helper = new DBHelper(this.getContext());
            db = helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }
      
    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "CategoryDataProvider -> delete()");

        switch (sMatcher.match(uri)) {
            case DBUtils.CODE_SINA_WEIBO:
                db.delete(DBUtils.TABLE_SINA_WEIBO, selection, selectionArgs);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
                db.delete(DBUtils.TABLE_WECHAT_FRIEND, selection, selectionArgs);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
                db.delete(DBUtils.TABLE_WECHAT_MOMENT, selection, selectionArgs);
                break;
            case DBUtils.CODE_QZONE:
                db.delete(DBUtils.TABLE_QZONE, selection, selectionArgs);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
                db.delete(DBUtils.TABLE_TENCENT_WEIBO, selection, selectionArgs);
                break;
            case DBUtils.CODE_RENREN:
                db.delete(DBUtils.TABLE_RENREN, selection, selectionArgs);
                break;
            case DBUtils.CODE_DOUBAN:
                db.delete(DBUtils.TABLE_DOUBAN, selection, selectionArgs);
                break;
            case DBUtils.CODE_EVERNOTE:
                db.delete(DBUtils.TABLE_EVERNOTE, selection, selectionArgs);
                break;
            case DBUtils.CODE_TWITTER:
                db.delete(DBUtils.TABLE_TWITTER, selection, selectionArgs);
                break;
            case DBUtils.CODE_FACEBOOK:
                db.delete(DBUtils.TABLE_FACEBOOK, selection, selectionArgs);
                break;

            default:
                break;
        }

        return 0;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param uri
     * @return
     */
    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
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
     * @param uri
     * @param values
     * @return
     */
    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        Log.i(TAG, "CategoryDataProvider -> insert()");
        switch (sMatcher.match(uri)) {
            case DBUtils.CODE_SINA_WEIBO:
                db.insert(DBUtils.TABLE_SINA_WEIBO, null, values);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
                db.insert(DBUtils.TABLE_WECHAT_FRIEND, null, values);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
                db.insert(DBUtils.TABLE_WECHAT_MOMENT, null, values);
                break;
            case DBUtils.CODE_QZONE:
                db.insert(DBUtils.TABLE_QZONE, null, values);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
                db.insert(DBUtils.TABLE_TENCENT_WEIBO, null, values);
                break;
            case DBUtils.CODE_RENREN:
                db.insert(DBUtils.TABLE_RENREN, null, values);
                break;
            case DBUtils.CODE_DOUBAN:
                db.insert(DBUtils.TABLE_DOUBAN, null, values);
                break;
            case DBUtils.CODE_EVERNOTE:
                db.insert(DBUtils.TABLE_EVERNOTE, null, values);
                break;
            case DBUtils.CODE_TWITTER:
                db.insert(DBUtils.TABLE_TWITTER, null, values);
                break;
            case DBUtils.CODE_FACEBOOK:
                db.insert(DBUtils.TABLE_FACEBOOK, null, values);
                break;

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
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs,
            String sortOrder) {
        Log.i(TAG, "CategoryDataProvider -> query()");
        Cursor c = null;
        switch (sMatcher.match(uri)) {
            case DBUtils.CODE_SINA_WEIBO:
                c = db.query(DBUtils.TABLE_SINA_WEIBO, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
                c = db.query(DBUtils.TABLE_WECHAT_FRIEND, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
                c = db.query(DBUtils.TABLE_WECHAT_MOMENT, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case DBUtils.CODE_QZONE:
                c = db.query(DBUtils.TABLE_QZONE, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
                c = db.query(DBUtils.TABLE_TENCENT_WEIBO, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case DBUtils.CODE_RENREN:
                c = db.query(DBUtils.TABLE_RENREN, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_DOUBAN:
                c = db.query(DBUtils.TABLE_DOUBAN, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_EVERNOTE:
                c = db.query(DBUtils.TABLE_EVERNOTE, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_TWITTER:
                c = db.query(DBUtils.TABLE_TWITTER, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case DBUtils.CODE_FACEBOOK:
                c = db.query(DBUtils.TABLE_FACEBOOK, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;

            default:
                break;
        }
        return c;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        Log.i(TAG, "CategoryDataProvider -> update()");

        switch (sMatcher.match(uri)) {
            case DBUtils.CODE_SINA_WEIBO:
                db.update(DBUtils.TABLE_SINA_WEIBO, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_WECHAT_FRIEND:
                db.update(DBUtils.TABLE_WECHAT_FRIEND, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_WECHAT_MOMENT:
                db.update(DBUtils.TABLE_WECHAT_MOMENT, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_QZONE:
                db.update(DBUtils.TABLE_QZONE, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_TENCENT_WEIBO:
                db.update(DBUtils.TABLE_TENCENT_WEIBO, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_RENREN:
                db.update(DBUtils.TABLE_RENREN, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_DOUBAN:
                db.update(DBUtils.TABLE_DOUBAN, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_EVERNOTE:
                db.update(DBUtils.TABLE_EVERNOTE, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_TWITTER:
                db.update(DBUtils.TABLE_TWITTER, values, selection, selectionArgs);
                break;
            case DBUtils.CODE_FACEBOOK:
                db.update(DBUtils.TABLE_FACEBOOK, values, selection, selectionArgs);
                break;

            default:
                break;
        }
        return 0;
    }

}
