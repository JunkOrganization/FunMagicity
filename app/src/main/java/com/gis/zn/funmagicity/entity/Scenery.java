package com.gis.zn.funmagicity.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaoning on 2018/2/26.
 */

public class Scenery extends BmobObject  {
    private int id;
    private String name;
    private String intro;

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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
