package com.example.cxj.thirdloginlib;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.cxj.thirdloginlib.activity.ThirdPlatformAct;
import com.example.cxj.thirdloginlib.bean.LoginInfo;
import com.example.cxj.thirdloginlib.bean.User;
import com.example.cxj.thirdloginlib.listener.ILoginListener;
import com.example.cxj.thirdloginlib.listener.IUserInfoListener;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Created by cxj on 2016/12/13.
 */
public class QQPlatformExecutor extends ThirdPlatformExecutor {

    private Tencent tencent;

    /**
     * 同意的回调接口
     */
    private ILoginListener loginListener;

    private IUiListener iloginListener = new IUiListener() {
        @Override
        public void onComplete(Object value) {
            try {
                JSONObject jo = (JSONObject) value;
                int ret = jo.getInt("ret");
                if (ret == 0) {
                    String openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expiresIn = jo.getString("expires_in");
                    String pay_token = jo.getString("pay_token");
                    String pfkey = jo.getString("pfkey");
                    tencent.setOpenId(openID);
                    tencent.setAccessToken(accessToken, expiresIn);

                    loginListener.onComplete(new LoginInfo(openID, accessToken));

                } else {
                    loginListener.onError(new RuntimeException(jo.getString("msg")));
                }
            } catch (Exception e) {
                loginListener.onError(e);
            }
        }

        @Override
        public void onError(UiError uiError) {
            loginListener.onError(new RuntimeException(uiError.errorMessage));
        }

        @Override
        public void onCancel() {
            loginListener.onCancel();
        }
    };

    private IUserInfoListener userInfoListener;

    private IUiListener iGetUserInfoListener = new IUiListener() {
        @Override
        public void onComplete(Object value) {
            try {
                JSONObject jo = (JSONObject) value;
                int ret = jo.getInt("ret");
                if (ret == 0) {
                    User user = new User();
                    String nickname = jo.getString("nickname");
                    user.setNickname(nickname);
                    String gender = jo.getString("gender");
                    user.setGender(gender);
                    String province = jo.getString("province");
                    user.setProvince(province);
                    String city = jo.getString("city");
                    user.setCity(city);
                    String figureurl_qq_1 = jo.getString("figureurl_qq_1");
                    String figureurl_qq_2 = jo.getString("figureurl_qq_2");
                    user.setAvatarAddress1(figureurl_qq_1);
                    user.setAvatarAddress2(figureurl_qq_2);

                    userInfoListener.onComplete(user);

                } else {
                    userInfoListener.onError(new RuntimeException(jo.getString("msg")));
                }
            } catch (Exception e) {
                userInfoListener.onError(e);
            }
        }

        @Override
        public void onError(UiError uiError) {
            userInfoListener.onError(new RuntimeException(uiError.errorMessage));
        }

        @Override
        public void onCancel() {
            userInfoListener.onCancel();
        }
    };

    @Override
    public void login(@NonNull ILoginListener loginListener) {
        this.loginListener = loginListener;
        tencent = Tencent.createInstance(mConfig.getTencentAppId(), mConfig.getContext());
        if (!tencent.isSessionValid()) { //如果session失效了
            ThirdPlatformAct.startQQLogin(mConfig.getContext(), this, iloginListener);
        } else {
            loginListener.onComplete(new LoginInfo(tencent.getOpenId(), tencent.getAccessToken()));
        }
    }

    @Override
    public void getUserInfo(@NonNull IUserInfoListener userInfoListener) {
        this.userInfoListener = userInfoListener;
        if (tencent != null && tencent.isSessionValid()) {
            UserInfo userInfo = new UserInfo(mConfig.getContext(), tencent.getQQToken());
            userInfo.getUserInfo(iGetUserInfoListener);
        } else {
            throw new RuntimeException("you must call login method first");
        }
    }

    /**
     * 真正登录的逻辑
     *
     * @param activity
     */
    public void onLogin(Activity activity) {
        tencent.login(activity, "all", iloginListener);
    }

}
