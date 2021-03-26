package com.example.signuponline.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-09 21:48:37
 */
public class JsonToMap {
        /**
           * json字符串转map
           *
           * @param jsonStr
           * @return
            */
      public static Map<String, Object> jsonToMap(String jsonStr) {
          Map stringToMap =  JSONObject.parseObject(jsonStr);
          return stringToMap;
     }
}
