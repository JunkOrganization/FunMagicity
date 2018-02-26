package com.gis.zn.funmagicity.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhaoning on 2018/2/26.
 */

public class Label2Mapping extends BmobObject {
    private int id;
    private int sceneryId;
    private int labelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSceneryId() {
        return sceneryId;
    }

    public void setSceneryId(int sceneryId) {
        this.sceneryId = sceneryId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}
