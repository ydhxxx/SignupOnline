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
     * @param String code
     * @return String
     */
    String getOpenid(String code);

    /**
     * @param Map<String,String> map
     */
    void login(Map<String,String> map);
}
