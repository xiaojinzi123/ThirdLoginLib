package com.example.cxj.thirdloginlib;

import android.content.Context;

/**
 * Created by cxj on 2016/12/13.
 * 第三方的配置文件
 */
public class ThirdSdkConfig {

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 腾讯平台的AppId
     */
    private String tencentAppId;

    /**
     * 微博平台的AppKey
     */
    private String weiBoAppKey;

    /**
     * 第三方应用可以使用自己的回调页。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * http://www.sina.com
     */
    private String redirectUrl = "https://api.weibo.com/oauth2/default.html";

    /**
     * "email,direct_messages_read,direct_messages_write,"
     * + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
     * + "follow_app_official_microblog," + "invitation_write"
     * 第三方开发者一般不需要这么多，可直接设置成空即可
     */
    private String weiBoScope;

    public Context getContext() {
        return mContext;
    }

    public String getTencentAppId() {
        return tencentAppId;
    }

    public String getWeiBoAppKey() {
        return weiBoAppKey;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getWeiBoScope() {
        return weiBoScope;
    }

    /**
     * 从Build里面拿出配置信息
     *
     * @param build
     */
    private ThirdSdkConfig(Build build) {
        mContext = build.mContext;
        tencentAppId = build.tencentAppId;
        weiBoAppKey = build.weiBoAppKey;
        redirectUrl = build.redirectUrl;
        weiBoScope = build.weiBoScope;
    }

    public static class Build {

        /**
         * 上下文
         */
        private Context mContext;

        /**
         * 腾讯平台的AppId
         */
        private String tencentAppId;

        /**
         * 微博平台的AppKey
         */
        private String weiBoAppKey;

        private String redirectUrl;

        private String weiBoScope;

        public Build(Context mContext) {
            this.mContext = mContext;
        }

        /**
         * 设置腾讯平台的AppId
         *
         * @param tencentAppId
         */
        public Build tencentAppId(String tencentAppId) {
            this.tencentAppId = tencentAppId;
            return this;
        }

        /**
         * 设置微博平台的AppKey
         *
         * @param weiBoAppKey
         * @return
         */
        public Build weiBoAppKey(String weiBoAppKey) {
            this.weiBoAppKey = weiBoAppKey;
            return this;
        }

        /**
         * 设置微博回调页
         *
         * @param redirectUrl
         * @return
         */
        public Build redirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
            return this;
        }

        /**
         * 设置权限,官方说可以直接为空字符串
         *
         * @param weiBoScope
         * @return
         */
        public Build weiBoScope(String weiBoScope) {
            this.weiBoScope = weiBoScope;
            return this;
        }

        /**
         * 返回配置文件
         *
         * @return
         */
        public ThirdSdkConfig build() {
            return new ThirdSdkConfig(this);
        }

    }

}
