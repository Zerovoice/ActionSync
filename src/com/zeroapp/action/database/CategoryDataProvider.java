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

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * <p>
 * Title: CategoryDataProvider.
 * </p>
 * <p>
 * Description: CategoryDataProvider.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-22.
 * @version $Id$
 */

public class CategoryDataProvider extends ContentProvider {

    private static final String TAG = "CategoryDataProvider";
    private DBHelper helper;  
    private SQLiteDatabase db;  

    private static final UriMatcher sMatcher =DBUtils.initUriMatcher();

    /**
     * <p>
     * Title: onCreate.
     * </p>
     * <p>
     * Description: onCreate.
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
     * Title: delete.
     * </p>
     * <p>
     * Description: delete.
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
        db.delete(DBUtils.getCategoryManager().get(type), selection, selectionArgs);
        return 0;
    }

    /**
     * <p>
     * Title: getType.
     * </p>
     * <p>
     * Description: getType.
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
     * Title: insert.
     * </p>
     * <p>
     * Description: insert.
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
        db.insert(DBUtils.getCategoryManager().get(type), null, values);
        return null;
    }


    /**
     * <p>
     * Title: query.
     * </p>
     * <p>
     * Description: query.
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
        c = db.query(DBUtils.getCategoryManager().get(type), projection, selection, selectionArgs,
                null,
                null, sortOrder);
        return c;
    }

    /**
     * <p>
     * Title: update.
     * </p>
     * <p>
     * Description: update.
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
        db.update(DBUtils.getCategoryManager().get(type), values, selection, selectionArgs);
        return 0;
    }

}
