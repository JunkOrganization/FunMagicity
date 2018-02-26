package com.gis.zn.funmagicity.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaoning on 2018/2/26.
 */

public class Label1 extends BmobObject {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
