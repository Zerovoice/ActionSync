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


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zouxiaobo@hisense.com) 2014-4-22.
 * @version $Id$
 */

public class DBUtils {

    public static final String DATABASE_NAME = "action_user.db";
    public static final int DATABASE_VERSION = 1;

    public static final String AUTHORITY = "com.zeroapp.action.database";

    public static final String TABLE_SINA_WEIBO = "sina_weibo";
    public static final String TABLE_WECHAT_FRIEND = "wechat_friend";
    public static final String TABLE_WECHAT_MOMENT = "wechat_moment";
    public static final String TABLE_QZONE = "qzone";
    public static final String TABLE_TENCENT_WEIBO = "tencent_weibo";
    public static final String TABLE_RENREN = "renren";
    public static final String TABLE_DOUBAN = "douban";
    public static final String TABLE_EVERNOTE = "evernote";
    public static final String TABLE_TWITTER = "twitter";
    public static final String TABLE_FACEBOOK = "facebook";

    public static final int CODE_SINA_WEIBO = 0;
    public static final int CODE_WECHAT_FRIEND = 1;
    public static final int CODE_WECHAT_MOMENT = 2;
    public static final int CODE_QZONE = 3;
    public static final int CODE_TENCENT_WEIBO = 4;
    public static final int CODE_RENREN = 5;
    public static final int CODE_DOUBAN = 6;
    public static final int CODE_EVERNOTE = 7;
    public static final int CODE_TWITTER = 8;
    public static final int CODE_FACEBOOK = 9;

    public static final String _ID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PWD = "pwd";
    public static final String IS_LOGIN = "islogin";// 0-未登陆、1-登陆

}
