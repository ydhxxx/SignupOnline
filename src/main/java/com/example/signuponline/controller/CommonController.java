package com.example.signuponline.controller;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.Group;
import com.example.signuponline.bean.UserFlag;
import com.example.signuponline.common.IdController;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.CommonService;
import com.example.signuponline.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 21:19:39
 */
@Api(value =  "公共服务")
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;


    @ApiOperation(value = "获取首页普通活动列表")
    @ResponseBody
    @GetMapping("/homePage/getActivity")
    public String getActivityForHome(){
        List<Activity> list= commonService.getActivityForHome();
        return LogResult.success(list);
    }

    @ApiOperation(value = "获取普通报名活动详情")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "Int")
    @ResponseBody
    @GetMapping("/homePage/getActivityDetails")
    public String getActivityDetails(Integer id) throws NullPointerException{
        IdController.idIsNull(id);
        List<Activity> list= commonService.getActivityDetails(id);
        commonService.browse(id);
        return LogResult.success(list);
    }


    @ApiOperation(value = "获取分类活动列表")
    @ApiImplicitParam(name = "type1", value = "活动类型", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/homePage/classifyActivity")
    public String classifyActivity(String type1){
        List<Activity> list=commonService.getClassifyActivity(type1);
        return LogResult.success(list);
    }

    @ApiOperation(value = "获取轮播图列表")
    @ResponseBody
    @GetMapping("/homePage/getCarousel")
    public String getCarousel(){
        List<Object> list=commonService.getCarousel();
        return LogResult.success(list);
    }


    @ApiOperation(value = "获取普通报名活动详情")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/homePage/search/getHotAndHistory")
    public String  getHotAndHistory(String openid){
        List<String> hotList=commonService.getHotSearch();
        List<String> historyList=commonService.getHistorySearch(openid).stream().distinct().collect(Collectors.toList());
        HashMap<String,Object> map=new HashMap<>();
        map.put("hotList",hotList);
        map.put("historyList",historyList);
        return LogResult.success(map);

    }

    @ApiOperation(value = "清空历史搜索数据")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body")
    @ResponseBody
    @PostMapping("/homePage/search/clearHistory")
    public String clearHistory(@RequestBody Map<String,Object> map){
        int booleans=commonService.clearHistory((String)map.get("openid"));
        return LogResult.success(booleans);
    }

    @ApiOperation(value = "搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "key", value = "搜索关键词", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    @PostMapping("/homePage/search")
    public String search(@RequestBody Map<String,Object> map ){

        List<Activity> list=commonService.search(map);
        return LogResult.success(list);
    }

    @ApiOperation(value = "创建群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "name", value = "群组名", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    @PostMapping("/group/createGroup")
    public String createGroup(@RequestBody Map<String,String> map){
        String uid=UUIDUtil.getUUID();
        map.put("id", uid);
        boolean bool=commonService.createGroup(map);
        if(!bool){
            return LogResult.failed(false,"群组名称已存在，请修改");
        }
        boolean boo=commonService.joinGroup(map);
        return LogResult.success(uid);


    }

    @ApiOperation(value = "加入群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "id", value = "群组id", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    @PostMapping("/group/joinGroup")
    public String joinGroup(@RequestBody Map<String,String> map){
        Group group=commonService.queryGroup(map.get("id"));
        if(group==null){
            return LogResult.failed(false,"群组不存在，请重新输入");
        }
        boolean bool=commonService.joinGroup(map);
        if(!bool){
            return LogResult.failed(false,"已加入该群组，请勿重复添加");
        }
        return LogResult.success(group);
    }

    @ApiOperation(value = "退出群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "groupId", value = "群组id", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    @PostMapping("/group/exitGroup")
    public String exitGroup(@RequestBody Map<String,String> map){
        boolean bool=commonService.exitGroup(map);
        if(bool){
            return LogResult.success(bool);
        }
        else{
            return LogResult.failed(false,"未知错误~~");
        }
    }

    @ApiOperation(value = "获取已加入群组")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/group/getGroupByMember")
    public String getGroupByMember(String openid){
        List<Group> list=commonService.getGroupByMember(openid);
        return LogResult.success(list);
    }

    @ApiOperation(value = "获取群组内专属普通活动")
    @ApiImplicitParam(name = "groupId", value = "群组id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/group/getActivityByGroup")
    public String getActivityByGroup(String groupId){
        List<Object> list=commonService.getActivityByGroup(groupId);
        return LogResult.success(list);
    }

    @ApiOperation(value = "获取群组内专属信息收集活动")
    @ApiImplicitParam(name = "groupId", value = "群组id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/group/getGatherByGroup")
    public String getGatherByGroup(String groupId){
        List<Object> list=commonService.getGatherByGroup(groupId);
        return LogResult.success(list);
    }

    @ApiOperation(value = "获取信息收集活动详情信息")
    @ApiImplicitParam(name = "id", value = "信息收集活动id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/homePage/getGatherDetails")
    public String getGatherDetails(String id) throws NullPointerException{
        Map<String,Object> map= commonService.getGatherDetails(id);
        return LogResult.success(map);
    }



}
