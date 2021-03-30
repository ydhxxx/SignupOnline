package com.example.signuponline.controller;

import com.example.signuponline.common.LogResult;
import com.example.signuponline.service.WeChatService;
import com.example.signuponline.util.JsonToMap;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;


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

        return LogResult.success();
    }

}
