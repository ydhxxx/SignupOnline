package com.example.signuponline.common;

/**
 * ...
 *
 * @author yudh
 * @date 2020-12-09 15:19:07
 */
public class IdController {

    public static void idIsNull(Integer id) throws NullPointerException{
        if(id==null) {
            throw new NullPointerException("id为空!");
        }
    }
}
