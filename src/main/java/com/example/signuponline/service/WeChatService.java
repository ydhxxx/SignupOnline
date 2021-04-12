package com.example.signuponline.service;


import com.example.signuponline.bean.User;

import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 20:07:54
 */
public interface WeChatService {

    /**
     * 小程序获取openid
     * @param code code
     * @return openid
     */
    String getOpenid(String code);

    /**
     * 小程序进行登录
     * @param map  登录数据
     */
    void login(Map<String,String> map);

    /**
     * 获取用户信息
     * @param code code
     * @return user
     */
    User saveWeChatUser(String code);

    /**
     * 判断用户是否登录
     * @param openid openid
     * @return user
     */
    User ifLogin(String openid);

    /**
     * 注册web端账号
     * @param map map
     * @return bool
     */
    boolean webSignUp(Map<String, String> map);

    /**
     * 查询是否注册
     * @param openid openid
     * @return number
     */
    String ifSignUp(String openid);

    /**
     * web端登录
     * @param map map
     * @return bool
     */
    String loginWeb(Map<String, String> map);

    /**
     * web端获取用户信息
     * @param openid openid
     * @return user
     */
    User getUser(String openid);
}
