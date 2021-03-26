package com.example.signuponline.mapper;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-11 21:10:04
 */
@Component
public interface PublishMapper {

    /**
     * 发布新的活动
     *  @param map 存储信息
     */
    void newActivity(@Param("activity") Map<String, String> map);

    /**
     * 获取个人群组
     *  @param openid openid
     * @return list 群组
     */
    List<Group> getMyGroup(@Param("openid") String openid);

    /**
     * 创建信息收集表
     * @param map 存储信息
     * @return bool 是否创建成功
     */
    boolean newGather(@Param("map") Map<String, Object> map);

    /**
     * 创建信息收集字段表
     * @param map 存储信息
     * @return bool 是否创建成功
     */
    boolean newGatherField(@Param("map") Map<String, Object> map);

    /**
     * 获取个人发布的普通活动
     * @param openid openid
     * @return list 普通活动列表
     */
    List<Activity> getMyActivity(@Param("openid") String openid);

    /**
     * 获取个人发布的信息收集活动
     * @param openid openid
     * @return list 收集活动列表
     */
    List<GatherActivity> getMyGather(@Param("openid") String openid);

}
