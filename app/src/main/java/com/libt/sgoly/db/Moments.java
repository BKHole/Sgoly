package com.libt.sgoly.db;

import java.sql.Date;

/**
 * 发现界面，动态表
 * Created by Administrator on 2017/4/14 0014.
 */

public class Moments {
    private int comments_id;
    private int user_id;
    private String nickname;
    private int avatarId;//头像id
    private int imageId;//图片id
    private String content;//发布内容
    private String date;
    private int status;//点赞的状态

    public Moments(String nickname, int avatarId, String content, int imageId, String date, int status) {
        this.nickname = nickname;
        this.avatarId = avatarId;
        this.content = content;
        this.imageId=imageId;
        this.date = date;
        this.status = status;
    }

    public int getComments_id() {
        return comments_id;
    }

    public void setComments_id(int comments_id) {
        this.comments_id = comments_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
