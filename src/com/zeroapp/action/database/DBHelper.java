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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Iterator;

/**
 * <p>
 * Title: DBHelper.
 * </p>
 * <p>
 * Description: DBHelper.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-22.
 * @version $Id$
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {

        // CursorFactory设置为null,使用默认值
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        Log.i(TAG, "DBHelper");
    }

    /**
     * <p>
     * Title: onCreate.
     * </p>
     * <p>
     * Description: onCreate.
     * </p>
     * 
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DBHelper-->onCreate");
        Iterator<Integer> it = DBUtils.getCategoryManager().keySet().iterator();
        while (it.hasNext()) {
            int i = it.next();
            String CREATE_DB = "CREATE TABLE IF NOT EXISTS " + DBUtils.getCategoryManager().get(i)
                    + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT,"
                    + DBUtils.IS_LOGIN + " INTERGER)";
//            Log.i(TAG, "execSQL------" + i + "------:" + CREATE_DB);
            db.execSQL(CREATE_DB);
        }
    }

    /**
     * <p>
     * Title: onUpgrade.
     * </p>
     * <p>
     * Description: onUpgrade.
     * </p>
     * 
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "DBHelper-->onUpgrade");

    }

}
