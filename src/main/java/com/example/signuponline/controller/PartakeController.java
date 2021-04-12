package com.example.signuponline.controller;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.UserFlag;
import com.example.signuponline.common.IdController;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.PartakeService;
import com.example.signuponline.util.PartakeNumber;
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

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-21 14:26:08
 */
@Api(value =  "参与者操作接口")
@Slf4j
@RestController
@RequestMapping("/partake")
public class PartakeController {

    @Autowired
    private PartakeService partakeService;

    @ApiOperation(value = "获取个人创建的群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String",paramType = "body"),
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "String",paramType = "body")
    })
    @ResponseBody
    @PostMapping("/collect")
    public String collect(@RequestBody Map<String,Object> map){
        boolean bool=partakeService.collect(map);
        if(bool){
            return LogResult.success(bool);
        }
        else{
            return LogResult.failed("操作失败，请重试");
        }

    }

    @ApiOperation(value = "获取个人收藏的活动列表")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getCollect")
    public String getCollect(String openid){
        List<Activity> list=partakeService.getCollect(openid);
        return LogResult.success(list);
    }

    @ApiOperation(value = "报名参加普通报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/generalPartake")
    public String generalPartake(@RequestBody Map<String,Object> map){
        boolean bool=partakeService.generalPartake(map);
        if(bool){
            return LogResult.success(bool);
        }
        else{
            return LogResult.failed("操作失败，请重试");
        }
    }

    @ApiOperation(value = "报名信息收集活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gatherId", value = "信息收集活动id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inputList", value = "输入框标题与对应的值", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "selectList", value = "单选框标题与对应的值", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "selectMulList", value = "多选框标题与对应的值", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "areaList", value = "输文本区标题与对应的值", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/gatherPartake")
    public String gatherPartake(@RequestBody Map<String,Object> map){
        boolean bool=partakeService.partakeGather(map);
        if(!bool){
            return LogResult.failed("提交失败，请重试");
        }
        return LogResult.success();
    }

    @ApiOperation(value = "普通活动详情页查询是否收藏和报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/getFlag")
    public String getUserFlag(String openid,Integer activityId){
        IdController.idIsNull(activityId);
        UserFlag userFlag=partakeService.getUserFlag(openid,activityId);
        return LogResult.success(userFlag);
    }

    @ApiOperation(value = "信息收集活动详情页查询是否收藏和报名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gatherId", value = "信息收集活动id", required = true, dataType = "String")
    })
    @ResponseBody
    @GetMapping("/getGatherFlag")
    public String getGatherFlag(String openid,String gatherId){
        UserFlag userFlag=partakeService.getGatherFlag(openid,gatherId);
        return LogResult.success(userFlag);
    }

    @ApiOperation(value = "获取个人参与的活动")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getMyParticipation")
    public String getMyParticipation(String openid){
        List<Activity> activityList=partakeService.getActivity(openid);
        List<GatherActivity> gatherList=partakeService.getGather(openid);
        Map<String,Object> map=new HashMap<>();
        map.put("activityList",activityList);
        map.put("gatherList",gatherList);
        return LogResult.success(map);
    }

    @ApiOperation(value = "获取个人参与的活动数量")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getMyPartakeNumber")
    public String getMyPartakeNumber(String openid){
        List<Activity> activityList=partakeService.getActivity(openid);
        List<GatherActivity> gatherList=partakeService.getGather(openid);
        Map<String,Object> map= PartakeNumber.getNumber(activityList,gatherList);
        return LogResult.success(map);
    }

}
