package com.example.cxj.thirdloginlib.listener;

import com.example.cxj.thirdloginlib.bean.LoginInfo;

/**
 * Created by cxj on 2016/12/13.
 * 登录接口的回调
 */
public interface ILoginListener {

    /**
     * 正常完成的时候的回调
     *
     * @param loginInfo 登录信息的封装的结果l
     */
    void onComplete(LoginInfo loginInfo);

    /**
     * 发生错误的时候回调
     *
     * @param throwable
     */
    void onError(Throwable throwable);

    /**
     * 被取消了
     */
    void onCancel();

}
