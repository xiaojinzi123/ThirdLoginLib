package com.example.cxj.thirdlogindemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cxj.thirdloginlib.ThirdPlatformExecutor;
import com.example.cxj.thirdloginlib.ThirdSdkConfig;
import com.example.cxj.thirdloginlib.bean.LoginInfo;
import com.example.cxj.thirdloginlib.listener.ILoginListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ThirdSdkConfig config =
                new ThirdSdkConfig.Build(this)
                        .tencentAppId("1105882292")
                        .weiBoAppKey("948917296")
                        .redirectUrl("http://write.blog.csdn.net/postlist")
                        .weiBoScope("")
                        .build();

        ThirdPlatformExecutor.init(config);

        final ThirdPlatformExecutor executor = ThirdPlatformExecutor.getPlatformExecutor(ThirdPlatformExecutor.Platform.weibo);

        executor.login(new ILoginListener() {
            @Override
            public void onComplete(LoginInfo loginInfo) {
                System.out.println("openId = " + loginInfo.getOpenId());
                System.out.println("accessToken = " + loginInfo.getAccessToken());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("挂了");
            }

            @Override
            public void onCancel() {
                System.out.println("取消了");
            }
        });

    }


}
