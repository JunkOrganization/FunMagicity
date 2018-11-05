package com.gis.zn.funmagicity.entity;

import java.io.Serializable;
import java.util.Date;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by zhaoning on 2018/11/5.
 */

public class Picture implements Serializable {
    private Date date;
    private String description;
    private int id;
    private  int status;
    private String username;
    private BmobFile image;



    public Date getDate() {
        return date;
    }

    public Picture setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Picture setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getId() {
        return id;
    }

    public Picture setId(int id) {
        this.id = id;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Picture setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Picture setUsername(String username) {
        this.username = username;
        return this;
    }

    public BmobFile getImage() {
        return image;
    }

    public Picture setImage(BmobFile image) {
        this.image = image;
        return this;
    }
}
