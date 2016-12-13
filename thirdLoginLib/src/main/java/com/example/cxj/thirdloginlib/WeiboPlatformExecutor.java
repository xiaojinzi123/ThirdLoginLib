package com.example.cxj.thirdloginlib;

import android.os.Bundle;

import com.example.cxj.thirdloginlib.activity.ThirdPlatformAct;
import com.example.cxj.thirdloginlib.bean.LoginInfo;
import com.example.cxj.thirdloginlib.listener.ILoginListener;
import com.example.cxj.thirdloginlib.listener.IUserInfoListener;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by cxj on 2016/12/13.
 * 微博登录的执行器
 */
public class WeiboPlatformExecutor extends ThirdPlatformExecutor {

    private AuthInfo authInfo;

    //统一的登录监听回调接口
    private ILoginListener loginListener;

    //微博登录的原生的监听回调接口
    private WeiboAuthListener iloginListener = new WeiboAuthListener() {
        @Override
        public void onComplete(Bundle bundle) {
            Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(bundle);
            loginListener.onComplete(new LoginInfo(accessToken.getUid(), accessToken.getToken()));
        }

        @Override
        public void onWeiboException(WeiboException e) {
            loginListener.onError(e);
        }

        @Override
        public void onCancel() {
            loginListener.onCancel();
        }
    };

    @Override
    public void login(ILoginListener loginListener) {
        this.loginListener = loginListener;
        authInfo = new AuthInfo(mConfig.getContext(), mConfig.getWeiBoAppKey(), mConfig.getRedirectUrl(), mConfig.getWeiBoScope());
        ThirdPlatformAct.startWeiboLogin(mConfig.getContext(), authInfo, this, iloginListener);
    }


    /**
     * 由于没有找到响应的获取用户信息的接口,所以这里不写了,如果使用者有需要,可以自行请求网络
     *
     * @param userInfoListener 回调接口
     */
    @Override
    public void getUserInfo(IUserInfoListener userInfoListener) {
    }


}
