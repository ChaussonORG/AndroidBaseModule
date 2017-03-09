package com.sudao.basemodule.basicapi;

/**
 * Created by Samuel on 2017/1/14 21:23
 * Email:xuzhou40@gmail.com
 * desc:
 */

public class UserBean {
    private long userId;
    private String name;
    private String photo;
    private String cellphone;

    public UserBean() {
    }

    public UserBean(long userId, String name, String photo, String cellphone) {
        this.userId = userId;
        this.name = name;
        this.photo = photo;
        this.cellphone = cellphone;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
