package com.example.signuponline.mapper;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-21 14:27:58
 */
@Component
public interface PartakeMapper {

    /**
     * 收藏或者取消收藏
     * @param map 存储openid 活动id 收藏或取消收藏标志
     * @return bool
     */
    boolean collect(@Param("map") Map<String, Object> map);

    /**
     * 获取收藏列表
     * @param openid openid
     * @return list返回活动列表
     */
    List<Activity> getCollect(@Param("openid") String openid);

    /**
     * 普通活动报名
     * @param map openid、activityId
     * @return bool
     */
    boolean generalPartake(@Param("map") Map<String, Object> map);

    /**
     * 信息收集活动报名
     * @param map 活动id、openid以及表单信息
     * @return bool
     */
    boolean partakeGather(@Param("map") Map<String, Object> map);

    /**
     * 查询用户是否收藏和报名该活动
     * @param openid openid
     * @param activityId 群组id
     * @return userFlag
     */
    String getUserFlagC(@Param("openid")String openid, @Param("activityId")Integer activityId);

    /**
     * 查询用户是否收藏和报名该活动
     * @param openid openid
     * @param activityId 群组id
     * @return userFlag
     */
    String getUserFlagP(@Param("openid")String openid, @Param("activityId")Integer activityId);

    /**
     * 查询用户是否报名该信息收集活动
     * @param openid openid
     * @param gatherId 群组id
     * @return userFlag
     */
    String getGatherFlag(@Param("openid")String openid, @Param("gatherId")String gatherId);

    /**
     * 获取个人发布的信息收集活动
     * @param openid openid
     * @return list 收集活动列表
     */
    List<Activity> getActivity(@Param("openid") String openid);

    /**
     * 查询用户参与的信息收集活动
     * @param openid openid
     * @return list
     */
    List<GatherActivity> getGather(@Param("openid") String openid);
}
