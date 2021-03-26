package com.example.signuponline.controller;

import com.example.signuponline.bean.Activity;
import com.example.signuponline.bean.Group;
import com.example.signuponline.bean.UserFlag;
import com.example.signuponline.common.IdController;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.CommonService;
import com.example.signuponline.util.UUIDUtil;
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
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;


    @ResponseBody
    @GetMapping("/homePage/getActivity")
    public String getActivityForHome(){
        List<Activity> list= commonService.getActivityForHome();
        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/homePage/getActivityDetails")
    public String getActivityDetails(Integer id) throws NullPointerException{
        IdController.idIsNull(id);
        List<Activity> list= commonService.getActivityDetails(id);
        commonService.browse(id);
        return LogResult.success(list);
    }



    @ResponseBody
    @GetMapping("/homePage/classifyActivity")
    public String classifyActivity(String type1){
        List<Activity> list=commonService.getClassifyActivity(type1);
        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/homePage/getCarousel")
    public String getCarousel(){
        List<Object> list=commonService.getCarousel();
        return LogResult.success(list);
    }


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

    @ResponseBody
    @PostMapping("/homePage/search/clearHistory")
    public String clearHistory(@RequestBody Map<String,Object> map){
        int booleans=commonService.clearHistory((String)map.get("openid"));
        return LogResult.success(booleans);
    }

    @ResponseBody
    @PostMapping("/homePage/search")
    public String search(@RequestBody Map<String,Object> map ){

        List<Activity> list=commonService.search(map);
        return LogResult.success(list);
    }

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

    @ResponseBody
    @GetMapping("/group/getGroupByMember")
    public String getGroupByMember(String openid){
        List<Group> list=commonService.getGroupByMember(openid);
        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/group/getActivityByGroup")
    public String getActivityByGroup(String groupId){
        List<Object> list=commonService.getActivityByGroup(groupId);
        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/group/getGatherByGroup")
    public String getGatherByGroup(String groupId){
        List<Object> list=commonService.getGatherByGroup(groupId);
        return LogResult.success(list);
    }

    @ResponseBody
    @GetMapping("/homePage/getGatherDetails")
    public String getGatherDetails(String id) throws NullPointerException{
        Map<String,Object> map= commonService.getGatherDetails(id);
        return LogResult.success(map);
    }



}
