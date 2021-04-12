package com.example.signuponline.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *  页面请求
 *
 * @author yudh
 * @date 2020-12-03 16:31:14
 */
@Controller
@Slf4j
public class PageController {


    @GetMapping(value = "/")
    public String homeHtml(){
        return "login";
    }


//    @GetMapping(value = "/success")
//    public String success(){
//        return "success";
//    }



    @GetMapping(value ="/activity")
    public String showActivity(Model model,String openid){
        model.addAttribute("openid",openid);
        return "activity";
    }

    @GetMapping(value ="/gather")
    public String showGather(Model model,String openid){
        model.addAttribute("openid",openid);
        return "gather";}


    @GetMapping(value ="/partakes")
    public String partakes(Model model,int type,String title,String id){
        model.addAttribute("title",title);
        model.addAttribute("id",id);
        if(type==1){
            return "activityPartake";
        }
        else{
            return "gatherPartake";
        }
    }

    @GetMapping(value = "/404")
    public String pageNotFound(){
        log.warn("404: 请求资源不存在！");
        return "404";
    }

    @GetMapping(value = "/500")
    public String pageError(){
        log.error("500: 服务器出错！");
        return "500";
    }
}
