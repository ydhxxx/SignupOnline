package com.example.signuponline.controller;

import com.example.signuponline.bean.*;
import com.example.signuponline.common.IdController;
import com.example.signuponline.common.LogEntity;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.PublishService;
import com.example.signuponline.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-10 21:16:44
 */
@Api(value =  "发布者操作接口")
@Slf4j
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private PublishService publishService;



    @ApiOperation(value = "发布普通报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "活动内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type1", value = "活动类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type2", value = "活动子类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "活动地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "posterUrl", value = "活动海报地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detailUrl", value = "活动详情图片地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "绑定群组id", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/newActivity")
    public String newActivity(@RequestBody Map<String,String> map) throws NullPointerException{
        publishService.newActivity(map);
        return LogResult.success();
    }

    @ApiOperation(value = "获取个人创建的群组")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getMyGroup")
    public String getMyGroup(String openid){
        List<Group> list=publishService.getMyGroup(openid);
        return LogResult.success(list);
    }

    @ApiOperation(value = "创建信息收集活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "posterUrl", value = "活动海报地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "绑定群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inputList", value = "输入框标题列表", required = true, dataType = "ArrayList"),
            @ApiImplicitParam(name = "selectList", value = "单选框列表", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "selectMulList", value = "多选框列表", required = true, dataType = "Map"),
            @ApiImplicitParam(name = "areaList", value = "文本区标题列表", required = true, dataType = "ArrayList"),
            @ApiImplicitParam(name = "isAddress", value = "是否需要地址", required = true, dataType = "boolean")
            })
    @ResponseBody
    @PostMapping("/newGather")
    public String newGather(@RequestBody Map<String,Object> map){

        String uid= UUIDUtil.getUUID();
        map.put("id", uid);
        boolean bool=publishService.newGather(map);
        if(!bool){
            return LogResult.failed(false,"发布失败，请重试");
        }
        boolean bool1=publishService.newGatherField(map);
        if(!bool1){
            return LogResult.failed(false,"发布失败，请重试");
        }
        return LogResult.success();
    }

    @ApiOperation(value = "获取自己发布的普通活动")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getMyActivity")
    public String getMyActivity(String openid,boolean isAll){
        List<Activity> list=publishService.getMyActivity(openid,isAll);

        return LogResult.success(list);
    }

    @ApiOperation(value = "获取自己发布的信息收集活动")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getMyGather")
    public String getMyGather(String openid,boolean isAll){
        List<GatherActivity> list=publishService.getMyGather(openid,isAll);

        return LogResult.success(list);
    }


    @ApiOperation(value = "删除普通活动")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "Integer")
    @ResponseBody
    @PostMapping("/delActivity")
    public String delActivity(Integer id){
        IdController.idIsNull(id);
        boolean bool=publishService.delActivity(id);
        if(bool){
            return LogResult.success();
        }
        else{
            return LogResult.failed("删除失败，请重试");
        }

    }

    @ApiOperation(value = "修改普通报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "活动内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type1", value = "活动类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type2", value = "活动子类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "活动地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "posterUrl", value = "活动海报地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "detailUrl", value = "活动详情图片地址", required = true, dataType = "String"),
    })
    @ResponseBody
    @PostMapping("/updateActivity")
    public String updateActivity(@RequestBody Map<String,String> map) throws NullPointerException{
        boolean bool=publishService.updateActivity(map);
        if(bool){
            return LogResult.success();
        }
        else{
            return LogResult.failed("修改失败，请重试");
        }
    }


    @ApiOperation(value = "删除信息收集报名活动")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "String")
    @ResponseBody
    @PostMapping("/delGather")
    public String delGather(String id){
        boolean bool=publishService.delGather(id);
        if(bool){
            return LogResult.success();
        }
        else{
            return LogResult.failed("删除失败，请重试");
        }

    }

    @ApiOperation(value = "修改信息收集报名活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "活动标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "posterUrl", value = "活动海报地址", required = true, dataType = "String"),
    })
    @ResponseBody
    @PostMapping("/updateGather")
    public String updateGather(@RequestBody Map<String,String> map) throws NullPointerException{
        boolean bool=publishService.updateGather(map);
        if(bool){
            return LogResult.success();
        }
        else{
            return LogResult.failed("修改失败，请重试");
        }
    }

    @ApiOperation(value = "获取个人已发布的所有报名活动")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getAllActivity")
    public String getAllActivity(String openid){
        List<Activity> activityList=publishService.getMyActivity(openid,true);
        List<GatherActivity> gatherList=publishService.getMyGather(openid,true);
        Map<String,Object> map=new HashMap<>();
        List<Object> nodes=new ArrayList<>();

        map.put("id","4");
        map.put("text","活动参与详情");
        map.put("selectable",false);
        int i=5;
        for(Activity activity :activityList){
            Map<String,Object> node=new HashMap<>(4);
            node.put("id",i++);
            node.put("text",activity.getTitle());
            node.put("url","partakes?type="+1+"&title="+activity.getTitle()+"&id="+activity.getId());
            nodes.add(node);
        }
        for(GatherActivity gather :gatherList){
            Map<String,Object> node=new HashMap<>(4);
            node.put("id",i++);
            node.put("text",gather.getTitle());
            node.put("url","partakes?type="+2+"&title="+gather.getTitle()+"&id="+gather.getId());
            nodes.add(node);
        }
        map.put("nodes",nodes);
        return LogResult.success(map);
    }


    @ApiOperation(value = "获取普通活动报名信息")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getAcPartake")
    public String getAcPartake(String id){
        List<Object> list=publishService.getAcPartake(Integer.parseInt(id));
        return LogResult.success(list);
    }


    @ApiOperation(value = "获取信息收集活动字段")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getField")
    public String getField(String id){
        List<Object> list=publishService.getField(id);
        return LogResult.success(list);
    }


    @ApiOperation(value = "获取信息收集活动报名信息")
    @ApiImplicitParam(name = "id", value = "活动id", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/getGaPartake")
    public String getGaPartake(String id){
        List<Object> list=publishService.getGaPartake(id);
        return LogResult.success(list);
    }


}
