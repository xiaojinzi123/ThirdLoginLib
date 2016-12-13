package com.example.cxj.thirdloginlib.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cxj.thirdloginlib.QQPlatformExecutor;
import com.example.cxj.thirdloginlib.WeiboPlatformExecutor;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

/**
 * 启动这个Activity,完全是为了提供各个平台需要的环境,其实不是这个问题的话是没有这个Activity的
 */
public class ThirdPlatformAct extends AppCompatActivity {

    private static int flag = 0;

    //================================================QQ================================================
    private static QQPlatformExecutor qqPlatformExecutor;
    private static IUiListener qqIloginListener;
    //================================================QQ================================================

    //================================================微博===============================================
    private static WeiboPlatformExecutor weiboPlatformExecutor;
    private static WeiboAuthListener weiBoIloginListener;
    private static AuthInfo authInfo;
    //================================================微博===============================================

    private SsoHandler ssoHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (flag == 1) {
            qqPlatformExecutor.onLogin(this);
        } else if (flag == 2) {
            ssoHandler = new SsoHandler(this, authInfo);
            ssoHandler.authorize(weiBoIloginListener);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (flag == 1) {
            // 官方文档没没没没没没没没没没没这句代码, 但是很很很很很很重要, 不然不会回调!
            Tencent.onActivityResultData(requestCode, resultCode, data, qqIloginListener);
        }
        if (flag == 2) {
            if (ssoHandler != null) {
                ssoHandler.authorizeCallBack(requestCode, resultCode, data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

    /**
     * 开始登录qq平台,因为需要Activity的引用,所以只能这样子做
     *
     * @param context
     * @param qqPlatformExecutor
     * @param iloginListener
     */
    public static void startQQLogin(Context context, QQPlatformExecutor qqPlatformExecutor, IUiListener iloginListener) {
        //表示qq
        flag = 1;
        ThirdPlatformAct.qqPlatformExecutor = qqPlatformExecutor;
        ThirdPlatformAct.qqIloginListener = iloginListener;
        Intent intent = new Intent(context, ThirdPlatformAct.class);
        context.startActivity(intent);
    }

    public static void startWeiboLogin(Context context, AuthInfo authInfo, WeiboPlatformExecutor weiboPlatformExecutor, WeiboAuthListener iloginListener) {
        //表示微博
        flag = 2;
        ThirdPlatformAct.authInfo = authInfo;
        ThirdPlatformAct.weiboPlatformExecutor = weiboPlatformExecutor;
        ThirdPlatformAct.weiBoIloginListener = iloginListener;
        Intent intent = new Intent(context, ThirdPlatformAct.class);
        context.startActivity(intent);
    }

    @Override
    public void finish() {
        //释放资源
        flag = 0;
        qqPlatformExecutor = null;
        qqIloginListener = null;

        weiboPlatformExecutor = null;
        weiBoIloginListener = null;
        authInfo = null;
        super.finish();
    }
}
