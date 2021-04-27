package com.example.signuponline.service;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.Group;
import com.example.signuponline.bean.UserFlag;

import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 21:20:27
 */
public interface CommonService {

    /**
     * 为首页获取活动列表
     * @return List<Activity>
     */
    List<Activity> getActivityForHome();

    /**
     * 获取活动详情
     * @param id id
     * @return List<Activity>
     */
    Activity getActivityDetails(Integer id);

    /**
     * 根据id进行浏览记录
     * @param  id 群组id
     */
    void browse(Integer id);



    /**
     * 获取分类活动列表
     * @param  type1 类型
     * @return List<Activity>
     */
    List<Activity> getClassifyActivity(String type1);

    /**
     * 搜索活动
     * @param  map 数据集合
     * @return List<Activity> list
     */
    List<Activity> search(Map<String,Object> map);

    /**
     * 获取热门搜索
     * @return List<String>
     */
    List<String> getHotSearch();

    /**
     * 获取历史搜索
     * @param  openid openid
     * @return List<String>
     */
    List<String> getHistorySearch(String openid);

    /**
     * 清空历史搜索
     * @param  openid openid
     * @return int
     */
    int clearHistory(String openid);

    /**
     * 创建群组
     * @param  map 数据集合
     * @return int
     */
    boolean createGroup(Map<String, String> map);

    /**
     * 加入群组
     * @param  map 数据集合
     * @return int
     */
    boolean joinGroup(Map<String, String> map);

    /**
     * 查询群组是否存在
     * @param  id 群组id
     * @return group
     */
    Group queryGroup(String id);

    /**
     * 查询个人加入的群组
     * @param  openid 用户id
     * @return group
     */
    List<Group> getGroupByMember(String openid);

    /**
     * 根据群组查询所有活动
     * @param  groupId 群组id
     * @return Activity
     */
    List<Object> getActivityByGroup(String groupId);

    /**
     * 退出群组
     * @param  map openid和 群组id
     * @return boolean
     */
    boolean exitGroup(Map<String, String> map);

    /**
     * 获取轮播图热门活动
     * @return list 轮播图
     */
    List<Object> getCarousel();

    /**
     * 根据群组查询信息收集活动
     * @param  groupId 群组id
     * @return list
     */
    List<Object> getGatherByGroup(String groupId);

    /**
     * 根据活动id查询信息收集活动具体表单
     * @param  id 群组id
     * @return list
     */
    Map<String,Object> getGatherDetails(String id);

    /**
     * 获取活动浏览量
     * @param  id id
     * @return int
     */
    int getBrows(Integer id);
}
