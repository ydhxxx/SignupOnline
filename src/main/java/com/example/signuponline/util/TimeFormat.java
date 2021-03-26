package com.example.signuponline.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-11 13:38:44
 */
public class TimeFormat {


    public static String transForHome(String startTime,String endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat justDateTime = new SimpleDateFormat("MM-dd HH:mm");

        try {
            Date date = simpleDateFormat.parse(startTime);
            Date date1 = simpleDateFormat.parse(endTime);
            String dateString=justDateTime.format(date);
            String dateString1=justDateTime.format(date1);
            if(date1.before(new Date())){
                return "已结束";
            }
            else if (date.after(new Date())) {
                return dateString;
            } else {
                return "进行中";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
