package com.example.signuponline.controller;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.Group;
import com.example.signuponline.common.LogEntity;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.PublishService;
import com.example.signuponline.util.UUIDUtil;
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
@Slf4j
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private PublishService publishService;



    @ResponseBody
    @PostMapping("/newActivity")
    public String newActivity(@RequestBody Map<String,String> map) throws NullPointerException{
        publishService.newActivity(map);
        return LogResult.success();
    }

    @ResponseBody
    @GetMapping("/getMyGroup")
    public String getMyGroup(String openid){
        List<Group> list=publishService.getMyGroup(openid);
        return LogResult.success(list);
    }

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

    @ResponseBody
    @GetMapping("/getMyActivity")
    public String getMyActivity(String openid){
        List<Activity> list=publishService.getMyActivity(openid);

        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/getMyGather")
    public String getMyGather(String openid){
        List<GatherActivity> list=publishService.getMyGather(openid);

        return LogResult.success(list);
    }
}
