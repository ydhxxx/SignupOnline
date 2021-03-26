package com.example.signuponline.controller;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.GatherActivity;
import com.example.signuponline.bean.UserFlag;
import com.example.signuponline.common.IdController;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.PartakeService;
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
@Slf4j
@RestController
@RequestMapping("/partake")
public class PartakeController {

    @Autowired
    private PartakeService partakeService;

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

    @ResponseBody
    @GetMapping("/getCollect")
    public String getCollect(String openid){
        List<Activity> list=partakeService.getCollect(openid);
        return LogResult.success(list);
    }

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

    @ResponseBody
    @PostMapping("/gatherPartake")
    public String gatherPartake(@RequestBody Map<String,Object> map){
        boolean bool=partakeService.partakeGather(map);
        if(!bool){
            return LogResult.failed("提交失败，请重试");
        }
        return LogResult.success();
    }

    @ResponseBody
    @GetMapping("/getFlag")
    public String getUserFlag(String openid,Integer activityId){
        IdController.idIsNull(activityId);
        UserFlag userFlag=partakeService.getUserFlag(openid,activityId);
        return LogResult.success(userFlag);
    }

    @ResponseBody
    @GetMapping("/getGatherFlag")
    public String getGatherFlag(String openid,String gatherId){
        UserFlag userFlag=partakeService.getGatherFlag(openid,gatherId);
        return LogResult.success(userFlag);
    }

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

}
