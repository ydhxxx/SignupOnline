package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 00:21:25
 */
@Data
public class User {

    private String openid;
    private String sessionKey;
    private String createTime;
    private String nickName;
    private String avatarUrl;

    public User(String openid, String sessionKey, String createTime, String nickName, String avatarUrl) {
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.createTime = createTime;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", createTime='" + createTime + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public User(){}


}
