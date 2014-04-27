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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-22.
 * @version $Id$
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final String CREATE_SINA_WEIBO_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_SINA_WEIBO + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_WECHAT_FRIEND_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_WECHAT_FRIEND + "(" + DBUtils._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.KEY_USERNAME + " TEXT,"
            + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN + " INTERGER)";
    private static final String CREATE_WECHAT_MOMENT_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_WECHAT_MOMENT + "(" + DBUtils._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.KEY_USERNAME + " TEXT,"
            + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN + " INTERGER)";
    private static final String CREATE_QZONE_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_QZONE + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_TENCENT_WEIBO_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_TENCENT_WEIBO + "(" + DBUtils._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBUtils.KEY_USERNAME + " TEXT,"
            + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN + " INTERGER)";
    private static final String CREATE_RENREN_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_RENREN + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_DOUBAN_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_DOUBAN + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_EVERNOTE_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_EVERNOTE + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_TWITTER_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_TWITTER + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    private static final String CREATE_FACEBOOK_DB = "CREATE TABLE IF NOT EXISTS "
            + DBUtils.TABLE_FACEBOOK + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT," + DBUtils.IS_LOGIN
            + " INTERGER)";
    public static String[] createTables = new String[] { CREATE_SINA_WEIBO_DB,
            CREATE_WECHAT_FRIEND_DB, CREATE_WECHAT_MOMENT_DB, CREATE_QZONE_DB,
            CREATE_TENCENT_WEIBO_DB, CREATE_RENREN_DB, CREATE_DOUBAN_DB, CREATE_EVERNOTE_DB,
            CREATE_TWITTER_DB, CREATE_FACEBOOK_DB };

    public DBHelper(Context context) {

        // CursorFactory设置为null,使用默认值
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        Log.i(TAG, "DBHelper");
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DBHelper-->onCreate");
        for (String s : createTables) {
            db.execSQL(s);
        }

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        Log.i(TAG, "DBHelper-->onUpgrade");

    }

}
