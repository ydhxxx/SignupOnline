package com.example.signuponline.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ...
 *
 * @author yudh
 * @date 2021-02-22 10:01:35
 */
@Slf4j
public class MD5Utils {
    /**
     * MD5加密 生成32位MD5码
     */
    public static String md5Encode(String inStr) {

        try {
            byte[] byteArray = inStr.getBytes("UTF-8");
            return md5Encode(byteArray);
        } catch (UnsupportedEncodingException e) {
            log.error("将String类型数据转换为UTF-8编码的字节数组失败！", e);
        }
        return null;
    }

    /**
     * MD5加密 生成32位MD5码
     */
    public static String md5Encode(byte[] byteArray) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (byte md5Byte : md5Bytes) {
                int val = (md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("获取MD5实例失败！", e);
        }
        return null;
    }
}
