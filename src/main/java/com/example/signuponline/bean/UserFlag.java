package com.example.signuponline.bean;

import lombok.Data;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-21 15:46:03
 */
@Data
public class UserFlag {

    private boolean isCollect;
    private boolean isPartake;


    public UserFlag(boolean isCollect, boolean isPartake) {
        this.isCollect = isCollect;
        this.isPartake = isPartake;
    }

    public UserFlag(){}
}
