package com.example.signuponline.util;

import java.util.UUID;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-15 13:41:04
 */
public class UUIDUtil {

    public static String getUUID(){
        String id = UUID.randomUUID().toString();
        String uid = id.replaceAll("-", "");

        return uid;
    }
}
