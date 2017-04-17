package com.libt.sgoly.db;

import org.litepal.crud.DataSupport;

/**
 * 水果表
 * Created by Administrator on 2017/4/12 0012.
 */

public class Fruit extends DataSupport{
    private int id;
    private String name;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private int imageId;
}
