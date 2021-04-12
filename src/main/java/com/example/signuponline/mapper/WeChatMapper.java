package com.example.signuponline.mapper;

import com.example.signuponline.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 22:48:33
 */
@Component
public interface WeChatMapper {




    /**
     * 用户登录
     * @param user user
     */
    void login(@Param("user") Map<String,String> user);

    /**
     * 判断用户是否登录
     * @param openid openid
     * @return user
     */
    User ifLogin(@Param("openid") String openid);

    /**
     * 注册web端账号
     * @param map map
     * @return bool
     */
    boolean webSignUp(@Param("map") Map<String, String> map);

    /**
     * 查询是否注册
     * @param openid openid
     * @return number
     */
    String ifSignUp(@Param("openid") String openid);

    /**
     * web端登录
     * @param map map
     * @return bool
     */
    String loginWeb(@Param("map") Map<String, String> map);

    /**
     * web端获取用户信息
     * @param openid openid
     * @return user
     */
    User getUser(@Param("openid") String openid);
}
