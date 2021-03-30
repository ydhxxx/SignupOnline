package com.example.signuponline.util;


import com.alibaba.fastjson.JSONObject;
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
           * @param jsonStr 字符串
           * @return map
            */
      public static Map<String, Object> jsonToMap(String jsonStr) {
          return JSONObject.parseObject(jsonStr);
     }
}
