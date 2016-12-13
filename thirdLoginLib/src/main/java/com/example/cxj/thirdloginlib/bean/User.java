package com.example.cxj.thirdloginlib.bean;

/**
 * Created by cxj on 2016/12/13.
 * 第三方获取用户信息的时候
 * 无论哪一个平台最终整理成这个对象
 */
public class User {

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 头像地址1,小一点
     */
    private String avatarAddress1;

    /**
     * 头像地址2,大一点
     */
    private String avatarAddress2;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatarAddress1() {
        return avatarAddress1;
    }

    public void setAvatarAddress1(String avatarAddress1) {
        this.avatarAddress1 = avatarAddress1;
    }

    public String getAvatarAddress2() {
        return avatarAddress2;
    }

    public void setAvatarAddress2(String avatarAddress2) {
        this.avatarAddress2 = avatarAddress2;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", avatarAddress1='" + avatarAddress1 + '\'' +
                ", avatarAddress2='" + avatarAddress2 + '\'' +
                '}';
    }
}
