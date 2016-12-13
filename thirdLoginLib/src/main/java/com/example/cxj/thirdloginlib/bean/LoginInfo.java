package com.example.cxj.thirdloginlib.bean;

/**
 * Created by cxj on 2016/12/13.
 * 封装了登录之后的返回信息
 */
public class LoginInfo {

    /**
     * 此qq的唯一标识
     */
    private String openId;

    /**
     * 此次登录的token,服务器应该在这个字段上传的时候验证这个token的有效性
     */
    private String accessToken;

    public LoginInfo(String openId, String accessToken) {
        this.openId = openId;
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
