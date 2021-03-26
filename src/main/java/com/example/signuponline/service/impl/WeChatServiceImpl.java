package com.example.signuponline.service.impl;

import com.example.signuponline.bean.User;
import com.example.signuponline.mapper.WeChatMapper;
import com.example.signuponline.service.WeChatService;
import com.example.signuponline.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 20:08:38
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private WeChatMapper weChatMapper;



    @Override
    public String getOpenid(String code) {
        return WeChatUtil.getSessionKeyOropenid(code);
    }

    @Override
    public void login(Map<String,String> map) {
        weChatMapper.login(map);
    }
}
