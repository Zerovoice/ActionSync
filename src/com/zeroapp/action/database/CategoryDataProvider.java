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

    private static final UriMatcher sMatcher =DBUtils.initUriMatcher();

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
		int type = sMatcher.match(uri);
		Log.i(TAG, "CategoryDataProvider -> delete()，type is " + type);
		db.delete(DBUtils.categoryManager.get(type), selection, selectionArgs);
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
    	int type = sMatcher.match(uri);
        Log.i(TAG, "CategoryDataProvider -> insert(),type is "+type);
        db.insert(DBUtils.categoryManager.get(type), null, values);
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
    	int type = sMatcher.match(uri);
        Log.i(TAG, "CategoryDataProvider -> query()，type is "+type);
        Cursor c = null;
        c = db.query(DBUtils.categoryManager.get(type), projection, selection, selectionArgs, null,
                null, sortOrder);
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
    	int type = sMatcher.match(uri);
        Log.i(TAG, "CategoryDataProvider -> update()，type is "+type);
        db.update(DBUtils.categoryManager.get(type), values, selection, selectionArgs);
        return 0;
    }

}
