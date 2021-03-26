package com.example.signuponline.controller;

import com.example.signuponline.bean.User;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.WeChatService;
import com.example.signuponline.util.JsonToMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 19:55:41
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;


    @ResponseBody
    @GetMapping(value = "/getOpenid")
    public String getOpenid(String code){
        String json=weChatService.getOpenid(code);
        Map<String, Object> map=JsonToMap.jsonToMap(json);
        return LogResult.success(map);
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> map) throws NullPointerException{
        weChatService.login(map);
        return LogResult.success();
    }


}
