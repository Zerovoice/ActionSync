/* 
 * Copyright (C)  . 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY zeroapp ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH zeroapp ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.action.models;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Bobby Zou(zeroapp@126.com) 2014-5-6.
 * @version $Id$
 */
public class ShareDataBuilder {

    private HashMap<String, Object> reqMap;
    
    public ShareDataBuilder() {
        super();
        reqMap = new HashMap<String, Object>();
    }

    /** 分享时Notification的图标和文字 */
    public void setNotification(int icon, String title) {
//        notifyIcon = icon;
//        notifyTitle = title;
    }

    /** address是接收人地址，仅在信息和邮件使用，否则可以不提供 */
    public void setAddress(String address) {
        reqMap.put("address", address);
    }

    /**
     * title标题，在印象笔记、邮箱、信息、微信（包括好友、朋友圈和收藏）、
     * 易信（包括好友、朋友圈）、人人网和QQ空间使用，否则可以不提供
     */
    public void setTitle(String title) {
        if (title == null || title.equals("")) {
            title = "Title";
        }
        reqMap.put("title", title);
    }

    /** titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供 */
    public void setTitleUrl(String titleUrl) {
        reqMap.put("titleUrl", titleUrl);
    }

    /** text是分享文本，所有平台都需要这个字段 */
    public void setText(String text) {
        reqMap.put("text", text);
    }

    /** 获取text字段的值 */
    public String getText() {
        return reqMap.containsKey("text") ? String.valueOf(reqMap.get("text")) : null;
    }

    /** imagePath是本地的图片路径，除Linked-In外的所有平台都支持这个字段 */
    public void setImagePath(String imagePath) {
        if(!TextUtils.isEmpty(imagePath))
            reqMap.put("imagePath", imagePath);
    }

    /** imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段 */
    public void setImageUrl(String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl))
            reqMap.put("imageUrl", imageUrl);
    }

    /** url在微信（包括好友、朋友圈收藏）和易信（包括好友和朋友圈）中使用，否则可以不提供 */
    public void setUrl(String url) {
        reqMap.put("url", url);
    }

    /** filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供 */
    public void setFilePath(String filePath) {
        reqMap.put("filePath", filePath);
    }

    /** comment是我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供 */
    public void setComment(String comment) {
        reqMap.put("comment", comment);
    }

    /** site是分享此内容的网站名称，仅在QQ空间使用，否则可以不提供 */
    public void setSite(String site) {
        reqMap.put("site", site);
    }

    /** siteUrl是分享此内容的网站地址，仅在QQ空间使用，否则可以不提供 */
    public void setSiteUrl(String siteUrl) {
        reqMap.put("siteUrl", siteUrl);
    }

    /** foursquare分享时的地方名 */
    public void setVenueName(String venueName) {
        reqMap.put("venueName", venueName);
    }

    /** foursquare分享时的地方描述 */
    public void setVenueDescription(String venueDescription) {
        reqMap.put("venueDescription", venueDescription);
    }

    /** 分享地纬度，新浪微博、腾讯微博和foursquare支持此字段 */
    public void setLatitude(float latitude) {
        reqMap.put("latitude", latitude);
    }

    /** 分享地经度，新浪微博、腾讯微博和foursquare支持此字段 */
    public void setLongitude(float longitude) {
        reqMap.put("longitude", longitude);
    }

    /** 设置编辑页的初始化选中平台 */
    public void setPlatform(String platform) {
        reqMap.put("platform", platform);
    }

    public HashMap<String, Object> getMap() {
        return reqMap;
    }
}
