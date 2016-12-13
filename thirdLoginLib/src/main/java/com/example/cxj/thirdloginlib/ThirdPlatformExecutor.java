package com.example.cxj.thirdloginlib;

import android.support.annotation.Nullable;

import com.example.cxj.thirdloginlib.listener.ILoginListener;
import com.example.cxj.thirdloginlib.listener.IUserInfoListener;

/**
 * Created by cxj on 2016/12/13.
 * 第三方的登录、获取用户信息和分享的执行者
 */
public abstract class ThirdPlatformExecutor {

    /**
     * 上下文信息
     */
    protected static ThirdSdkConfig mConfig;

    /**
     * 初始化
     *
     * @param config
     */
    public static void init(ThirdSdkConfig config) {
        mConfig = config;
    }

    /**
     * 第三方的平台
     */
    public enum Platform {
        qq,
        weixin,
        weibo
    }

    /**
     * 获取第三方的平台
     *
     * @param platform
     * @return
     */
    @Nullable
    public static ThirdPlatformExecutor getPlatformExecutor(Platform platform) {

        if (mConfig == null) {
            throw new RuntimeException("you must call init method befor call getPlatformExecutor method");
        }

        if (platform == Platform.qq) {
            return new QQPlatformExecutor();
        } else if (platform == Platform.weibo) {
            return new WeiboPlatformExecutor();
        }

        return null;
    }

    /**
     * 登录
     *
     * @param loginListener 回调接口
     */
    public abstract void login(ILoginListener loginListener);

    /**
     * 获取用户信息
     *
     * @param userInfoListener 回调接口
     */
    public abstract void getUserInfo(IUserInfoListener userInfoListener);


}
