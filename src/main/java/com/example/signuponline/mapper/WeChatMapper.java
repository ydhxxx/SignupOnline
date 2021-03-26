package com.example.signuponline.mapper;

import com.example.signuponline.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 22:48:33
 */
@Component
public interface WeChatMapper {




    /**
     * @param Map<String,String> map
     */
    void login(@Param("user") Map<String,String> user);
}
