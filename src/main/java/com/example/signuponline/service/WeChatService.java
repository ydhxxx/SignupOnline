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
}
