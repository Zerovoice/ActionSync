package com.zeroapp.action.constants;

import com.zeroapp.action.R;

public class Constants {

    public static int[] color_icon = new int[] { R.drawable.logo_sinaweibo,// -0,空位
            R.drawable.logo_sinaweibo,// 1
            R.drawable.logo_tencentweibo,// 2
            R.drawable.logo_qzone, // 3
            R.drawable.logo_wechat, // 4
            R.drawable.logo_wechatmoments,// 5
            R.drawable.logo_wechatfavorite,// 6
            R.drawable.logo_qq,// 7
            R.drawable.logo_facebook,// 8
            R.drawable.logo_twitter,// 9
            R.drawable.logo_renren,// 10
            R.drawable.logo_kaixin,// 11
            R.drawable.logo_email,// 12
            R.drawable.logo_shortmessage,// 13
            R.drawable.logo_sohumicroblog,// 14
            R.drawable.logo_neteasemicroblog,// 15--NetEaseMicroBlog
            R.drawable.logo_douban,// 16
            R.drawable.logo_youdao,// 17
            R.drawable.logo_sohusuishenkan,// 18
            R.drawable.logo_evernote,// 19
            R.drawable.logo_linkedin,// 20
            R.drawable.logo_googleplus,// 21
            R.drawable.logo_foursquare,// 22 FourSquare
            R.drawable.logo_pinterest,// 23 Pinterest
            R.drawable.logo_flickr,// 24
            R.drawable.logo_tumblr,// 25
            R.drawable.logo_dropbox,// 26
            R.drawable.logo_vkontakte,// 27
            R.drawable.logo_instagram,// 28
            R.drawable.logo_yixin,// 29
            R.drawable.logo_yixinmoments,// 30
            R.drawable.logo_mingdao,// 31

    };
    public static int[] gray_icon = new int[] {
            R.drawable.logo_sinaweibo_gray,// -0,空位
            R.drawable.logo_sinaweibo_gray,// 1
            R.drawable.logo_tencentweibo_gray,// 2
            R.drawable.logo_qzone_gray, // 3
            R.drawable.logo_wechat_gray, // 4
            R.drawable.logo_wechatmoments_gray,// 5
            R.drawable.logo_wechatfavorite_gray,// 6
            R.drawable.logo_qq_gray,// 7
            R.drawable.logo_facebook_gray,// 8
            R.drawable.logo_twitter_gray,// 9
            R.drawable.logo_renren_gray,// 10
            R.drawable.logo_kaixin_gray,// 11
            R.drawable.logo_email_gray,// 12
            R.drawable.logo_shortmessage_gray,// 13
            R.drawable.logo_sohumicroblog_gray,// 14
            R.drawable.logo_neteasemicroblog_gray,// 15--NetEaseMicroBlog
            R.drawable.logo_douban_gray,// 16
            R.drawable.logo_youdao_gray,// 17
            R.drawable.logo_sohusuishenkan_gray,// 18
            R.drawable.logo_evernote_gray,// 19
            R.drawable.logo_linkedin_gray,// 20
            R.drawable.logo_googleplus_gray,// 21
            R.drawable.logo_foursquare_gray,// 22 FourSquare
            R.drawable.logo_pinterest_gray,// 23 Pinterest
            R.drawable.logo_flickr_gray,// 24
            R.drawable.logo_tumblr_gray,// 25
            R.drawable.logo_dropbox_gray,// 26
            R.drawable.logo_vkontakte_gray,// 27
            R.drawable.logo_instagram_gray,// 28
            R.drawable.logo_yixin_gray,// 29
            R.drawable.logo_yixinmoments_gray,// 30
            R.drawable.logo_mingdao_gray,// 31

    };
    public static int[] category_msg = new int[] { R.string.string_sinaweibo,// -0,空位
            R.string.string_sinaweibo,// 1
            R.string.string_tencentweibo,// 2
            R.string.string_qzone, // 3
            R.string.string_wechat, // 4
            R.string.string_wechatmoments,// 5
            R.string.string_wechatfavorite,// 6
            R.string.string_qq,// 7
            R.string.string_facebook,// 8
            R.string.string_twitter,// 9
            R.string.string_renren,// 10
            R.string.string_kaixin,// 11
            R.string.string_email,// 12
            R.string.string_shortmessage,// 13
            R.string.string_sohumicroblog,// 14
            R.string.string_neteasemicroblog,// 15--NetEaseMicroBlog
            R.string.string_douban,// 16
            R.string.string_youdao,// 17
            R.string.string_sohusuishenkan,// 18
            R.string.string_evernote,// 19
            R.string.string_linkedin,// 20
            R.string.string_googleplus,// 21
            R.string.string_foursquare,// 22 FourSquare
            R.string.string_pinterest,// 23 Pinterest
            R.string.string_flickr,// 24
            R.string.string_tumblr,// 25
            R.string.string_dropbox,// 26
            R.string.string_vkontakte,// 27
            R.string.string_instagram,// 28
            R.string.string_yixin,// 29
            R.string.string_yixinmoments,// 30
            R.string.string_mingdao,// 31

    };


    public class WeiboConstants {

        /** 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
        public static final String APP_KEY = "2045436852";
        /**
         * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
         * 
         * <p>
         * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响， 但是没有定义将无法使用 SDK 认证登录。
         * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
         * </p>
         */
        public static final String REDIRECT_URL = "http://www.sina.com";
        /**
         * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的
         * 微博核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权
         * 页中有权利选择赋予应用的功能。
         * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接
         * 口的使用权限，高级权限需要进行申请。
         * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
         * 
         * 有关哪些 OpenAPI
         * 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
         * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
         */
        public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
                + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                + "follow_app_official_microblog," + "invitation_write";
    }
}
