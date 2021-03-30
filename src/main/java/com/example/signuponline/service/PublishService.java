package com.example.signuponline.service;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.Group;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-11 21:07:51
 */
public interface PublishService {


    /**
     * 发布新活动
     *  @param map 数据
     */
    void newActivity(Map<String, String> map);

    /**
     * 获取个人群组
     *  @param openid openid
     * @return list 群组
     */
    List<Group> getMyGroup(String openid);

    /**
     * 创建信息收集表
     * @param map 存储信息
     * @return bool 是否创建成功
     */
    boolean newGather(Map<String, Object> map);

    /**
     * 创建信息收集字段表
     * @param map 存储信息
     * @return bool 是否创建成功
     */
    boolean newGatherField(Map<String, Object> map);

    /**
     * 获取个人发布的普通活动
     * @param openid openid
     * @param isAll 是否为网站加载全部数据
     * @return list 普通活动列表
     */
    List<Activity> getMyActivity(String openid,boolean isAll);


    /**
     * 获取个人发布的信息收集活动
     * @param openid openid
     * @return list 收集活动列表
     */
    List<GatherActivity> getMyGather(String openid,boolean isAll);

    /**
     * 删除普通报名活动
     * @param id 活动id
     * @return bool 是否删除成功
     */
    boolean delActivity(Integer id);

    /**
     * 修改普通报名活动
     * @param map 活动信息
     * @return bool 是否删除成功
     */
    boolean updateActivity(Map<String, String> map);

    /**
     * 删除信息收集报名活动
     * @param id 活动id
     * @return bool 是否删除成功
     */
    boolean delGather(String id);

    /**
     * 修改信息收集报名活动
     * @param map 活动信息
     * @return bool 是否删除成功
     */
    boolean updateGather(Map<String, String> map);
}
