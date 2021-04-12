package com.example.signuponline.controller;

import com.example.signuponline.bean.User;
import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.WeChatService;
import com.example.signuponline.util.JsonToMap;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 19:55:41
 */
@Api(value =  "登录与获取账号信息")
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    private User user;


    @ApiOperation(value = "获取openid")
    @ApiImplicitParam(name = "code", value = "小程序请求得到的官方code", required = true, dataType = "String")
    @ResponseBody
    @GetMapping(value = "/getOpenid")
    public String getOpenid(String code){
        String json=weChatService.getOpenid(code);
        Map<String, Object> map= JsonToMap.jsonToMap(json);
        return LogResult.success(map);
    }

    @ApiOperation(value = "小程序端登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sessionKey", value = "sessionKey", required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "微信名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "avatarUrl", value = "微信头像地址", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> map) throws NullPointerException{
        weChatService.login(map);
        return LogResult.success();
    }


    @ResponseBody
    @PostMapping("/loginWeb")
    public String loginWeb(@RequestBody Map<String,String> map) throws NullPointerException{
        String openid=weChatService.loginWeb(map);
        if(openid==null){
            return LogResult.failed("账号或密码错误");
        }
        else{
            user=weChatService.getUser(openid);
//            model.addAttribute("openid",openid);
//            model.addAttribute("nickName",user.getNickName());
//            model.addAttribute("avatarUrl",user.getAvatarUrl());
            return LogResult.success();
        }
    }

    @GetMapping(value = "/success")
    public String success(Model model){
        model.addAttribute("openid",user.getOpenid());
        model.addAttribute("nickName",user.getNickName());
        model.addAttribute("avatarUrl",user.getAvatarUrl());
        return "success";
    }

    @ApiOperation(value = "判断小程序端是否登录")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/ifLogin")
    public String ifLogin(String openid) throws NullPointerException{
        User user=weChatService.ifLogin(openid);
        return LogResult.success(user);
    }

    @ApiOperation(value = "注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String"),
            @ApiImplicitParam(name = "number", value = "登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, dataType = "String")
    })
    @ResponseBody
    @PostMapping("/webSignUp")
    public String webSignUp(@RequestBody Map<String,String> map){
        boolean bool=weChatService.webSignUp(map);
        if(!bool){
            return LogResult.failed("账号已存在");
        }
        else{
            return LogResult.success();
        }
    }


    @ApiOperation(value = "判断是否注册")
    @ApiImplicitParam(name = "openid", value = "openid", required = true, dataType = "String")
    @ResponseBody
    @GetMapping("/ifSignUp")
    public String ifSignUp(String openid) throws NullPointerException{
        String number=weChatService.ifSignUp(openid);
        if(number==null){
            return LogResult.failed(0,"未注册");
        }
        return LogResult.success(number);
    }
}
