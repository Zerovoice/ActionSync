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

import android.content.UriMatcher;
import android.util.Log;

import cn.sharesdk.douban.Douban;
import cn.sharesdk.dropbox.Dropbox;
import cn.sharesdk.evernote.Evernote;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.flickr.Flickr;
import cn.sharesdk.foursquare.FourSquare;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.instagram.Instagram;
import cn.sharesdk.kaixin.KaiXin;
import cn.sharesdk.linkedin.LinkedIn;
import cn.sharesdk.mingdao.Mingdao;
import cn.sharesdk.netease.microblog.NetEaseMicroBlog;
import cn.sharesdk.pinterest.Pinterest;
import cn.sharesdk.renren.Renren;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sohu.microblog.SohuMicroBlog;
import cn.sharesdk.sohu.suishenkan.SohuSuishenkan;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.tencent.weibo.TencentWeibo;
import cn.sharesdk.tumblr.Tumblr;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.vkontakte.VKontakte;
import cn.sharesdk.wechat.favorite.WechatFavorite;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.yixin.friends.Yixin;
import cn.sharesdk.yixin.moments.YixinMoments;
import cn.sharesdk.youdao.YouDao;

import java.util.HashMap;



/**
 * <p>
 * Title: DBUtils.
 * </p>
 * <p>
 * Description: DBUtils.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-22.
 * @version $Id$
 */

public class DBUtils {

    private static final String TAG = "DBUtils";

    public static final String DATABASE_NAME = "action_user.db";
    public static final int DATABASE_VERSION = 1;

    public static final String AUTHORITY = "com.zeroapp.action.database";

    public static final int CODE_SINA_WEIBO = 1;
    public static final int CODE_TENCENT_WEIBO = 2;
    public static final int CODE_QZONE = 3;
    public static final int CODE_WECHAT = 4;
    public static final int CODE_WECHAT_MOMENT = 5;
    public static final int CODE_WECHAT_FAVOURITE = 6;
    public static final int CODE_QQ = 7;
    public static final int CODE_FACEBOOK = 8;
    public static final int CODE_TWITTER = 9;
    public static final int CODE_RENREN = 10;
    public static final int CODE_KAIXIN = 11;
    public static final int CODE_EMAIL = 12;
    public static final int CODE_SHORT_MSG = 13;
    public static final int CODE_SOHU_WEIBO = 14;
    public static final int CODE_NETEASE_WEIBO = 15;
    public static final int CODE_DOUBAN = 16;
    public static final int CODE_YOUDAO = 17;
    public static final int CODE_SOHU_SUISHENKAN = 18;
    public static final int CODE_EVERNOTE = 19;
    public static final int CODE_LINKEDIN = 20;
    public static final int CODE_GOOGLE_PLUS = 21;
    public static final int CODE_FOUR_SQUARE = 22;
    public static final int CODE_PINTEREST = 23;
    public static final int CODE_FLICKER = 24;
    public static final int CODE_TUMBLR = 25;
    public static final int CODE_DROPBOX = 26;
    public static final int CODE_VKONTAKTE = 27;
    public static final int CODE_INSTAGRAM = 28;
    public static final int CODE_YIXIN = 29;
    public static final int CODE_YIXIN_MOMENTS = 30;
    public static final int CODE_MINGDAO = 31;

    
    
    public static final String TABLE_SINA_WEIBO = SinaWeibo.NAME;
    public static final String TABLE_TENCENT_WEIBO = TencentWeibo.NAME;
    public static final String TABLE_QZONE = QZone.NAME;
    public static final String TABLE_WECHAT = Wechat.NAME;
    public static final String TABLE_WECHAT_MOMENT = WechatMoments.NAME;
    public static final String TABLE_WECHAT_FAVOURITE = WechatFavorite.NAME;
    public static final String TABLE_QQ = QQ.NAME;
    public static final String TABLE_FACEBOOK = Facebook.NAME;
    public static final String TABLE_TWITTER = Twitter.NAME;
    public static final String TABLE_RENREN = Renren.NAME;
    public static final String TABLE_KAIXIN = KaiXin.NAME;
    public static final String TABLE_EMAIL = Email.NAME;
    public static final String TABLE_SHORT_MSG = ShortMessage.NAME;
    public static final String TABLE_SOHU_WEIBO = SohuMicroBlog.NAME;
    public static final String TABLE_NETEASE_WEIBO = NetEaseMicroBlog.NAME;
    public static final String TABLE_DOUBAN = Douban.NAME;
    public static final String TABLE_YOUDAO = YouDao.NAME;
    public static final String TABLE_SOHU_SUISHENKAN = SohuSuishenkan.NAME;
    public static final String TABLE_EVERNOTE = Evernote.NAME;
    public static final String TABLE_LINKEDIN = LinkedIn.NAME;
    public static final String TABLE_GOOGLE_PLUS = GooglePlus.NAME;
    public static final String TABLE_FOUR_SQUARE = FourSquare.NAME;
    public static final String TABLE_PINTEREST = Pinterest.NAME;
    public static final String TABLE_FLICKER = Flickr.NAME;
    public static final String TABLE_TUMBLR = Tumblr.NAME;
    public static final String TABLE_DROPBOX = Dropbox.NAME;
    public static final String TABLE_VKONTAKTE = VKontakte.NAME;
    public static final String TABLE_INSTAGRAM = Instagram.NAME;
    public static final String TABLE_YIXIN = Yixin.NAME;
    public static final String TABLE_YIXIN_MOMENTS = YixinMoments.NAME;
    public static final String TABLE_MINGDAO = Mingdao.NAME;


    public static final String _ID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PWD = "pwd";
    public static final String IS_LOGIN = "islogin";// 0-未登陆、1-登陆
    

    private static HashMap<Integer, String> categoryManager;
    
    public static HashMap<Integer, String> getCategoryManager() {
        if (categoryManager == null) {
            Log.e(TAG, "Init CategoryManager!");
            categoryManager = new HashMap<Integer, String>();
            // 将分类编号和分类名称加入HashMap进行管理；
            categoryManager.put(CODE_SINA_WEIBO, TABLE_SINA_WEIBO);
            categoryManager.put(CODE_TENCENT_WEIBO, TABLE_TENCENT_WEIBO);
            categoryManager.put(CODE_QZONE, TABLE_QZONE);
            categoryManager.put(CODE_WECHAT, TABLE_WECHAT);
            categoryManager.put(CODE_WECHAT_MOMENT, TABLE_WECHAT_MOMENT);
            categoryManager.put(CODE_WECHAT_FAVOURITE, TABLE_WECHAT_FAVOURITE);
            categoryManager.put(CODE_QQ, TABLE_QQ);
            categoryManager.put(CODE_FACEBOOK, TABLE_FACEBOOK);
            categoryManager.put(CODE_TWITTER, TABLE_TWITTER);
            categoryManager.put(CODE_RENREN, TABLE_RENREN);
            categoryManager.put(CODE_KAIXIN, TABLE_KAIXIN);
            categoryManager.put(CODE_EMAIL, TABLE_EMAIL);
            categoryManager.put(CODE_SHORT_MSG, TABLE_SHORT_MSG);
            categoryManager.put(CODE_SOHU_WEIBO, TABLE_SOHU_WEIBO);
            categoryManager.put(CODE_NETEASE_WEIBO, TABLE_NETEASE_WEIBO);
            categoryManager.put(CODE_DOUBAN, TABLE_DOUBAN);
            categoryManager.put(CODE_YOUDAO, TABLE_YOUDAO);
            categoryManager.put(CODE_SOHU_SUISHENKAN, TABLE_SOHU_SUISHENKAN);
            categoryManager.put(CODE_EVERNOTE, TABLE_EVERNOTE);
            categoryManager.put(CODE_LINKEDIN, TABLE_LINKEDIN);
            categoryManager.put(CODE_GOOGLE_PLUS, TABLE_GOOGLE_PLUS);
            categoryManager.put(CODE_FOUR_SQUARE, TABLE_FOUR_SQUARE);
            categoryManager.put(CODE_PINTEREST, TABLE_PINTEREST);
            categoryManager.put(CODE_FLICKER, TABLE_FLICKER);
            categoryManager.put(CODE_TUMBLR, TABLE_TUMBLR);
            categoryManager.put(CODE_DROPBOX, TABLE_DROPBOX);
            categoryManager.put(CODE_VKONTAKTE, TABLE_VKONTAKTE);
            categoryManager.put(CODE_INSTAGRAM, TABLE_INSTAGRAM);
            categoryManager.put(CODE_YIXIN, TABLE_YIXIN);
            categoryManager.put(CODE_YIXIN_MOMENTS, TABLE_YIXIN_MOMENTS);
            categoryManager.put(CODE_MINGDAO, TABLE_MINGDAO);
        }
        return categoryManager;
    	
	}
    public static UriMatcher initUriMatcher() {
    	UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    	matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_SINA_WEIBO, DBUtils.CODE_SINA_WEIBO);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_TENCENT_WEIBO, DBUtils.CODE_TENCENT_WEIBO);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_QZONE, DBUtils.CODE_QZONE);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_WECHAT, DBUtils.CODE_WECHAT);
    	matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_WECHAT_MOMENT, DBUtils.CODE_WECHAT_MOMENT);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_WECHAT_FAVOURITE,
                DBUtils.CODE_WECHAT_FAVOURITE);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_QQ, DBUtils.CODE_QQ);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_FACEBOOK, DBUtils.CODE_FACEBOOK);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_TWITTER, DBUtils.CODE_TWITTER);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_RENREN, DBUtils.CODE_RENREN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_KAIXIN, DBUtils.CODE_KAIXIN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_EMAIL, DBUtils.CODE_EMAIL);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_SHORT_MSG, DBUtils.CODE_SHORT_MSG);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_SOHU_WEIBO, DBUtils.CODE_SOHU_WEIBO);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_NETEASE_WEIBO, DBUtils.CODE_NETEASE_WEIBO);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_DOUBAN, DBUtils.CODE_DOUBAN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_YOUDAO, DBUtils.CODE_YOUDAO);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_SOHU_SUISHENKAN,
                DBUtils.CODE_SOHU_SUISHENKAN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_EVERNOTE, DBUtils.CODE_EVERNOTE);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_LINKEDIN, DBUtils.CODE_LINKEDIN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_GOOGLE_PLUS, DBUtils.CODE_GOOGLE_PLUS);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_FOUR_SQUARE, DBUtils.CODE_FOUR_SQUARE);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_PINTEREST, DBUtils.CODE_PINTEREST);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_FLICKER, DBUtils.CODE_FLICKER);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_TUMBLR, DBUtils.CODE_TUMBLR);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_DROPBOX, DBUtils.CODE_DROPBOX);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_VKONTAKTE, DBUtils.CODE_VKONTAKTE);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_INSTAGRAM, DBUtils.CODE_INSTAGRAM);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_YIXIN, DBUtils.CODE_YIXIN);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_YIXIN_MOMENTS, DBUtils.CODE_YIXIN_MOMENTS);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_MINGDAO, DBUtils.CODE_MINGDAO);

        return matcher;
    }
}
