package com.example.cxj.thirdloginlib.listener;

import com.example.cxj.thirdloginlib.bean.User;

/**
 * Created by cxj on 2016/12/13.
 * 登录接口的回调
 */
public interface IUserInfoListener {

    /**
     * 正常完成的时候的回调
     *
     * @param user 用户信息
     */
    void onComplete(User user);

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
