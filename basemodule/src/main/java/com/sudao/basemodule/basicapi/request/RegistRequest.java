package com.sudao.basemodule.basicapi.request;

/**
 * Created by Samuel on 5/27/16 14:58
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class RegistRequest {

    /**
     * cellphone : 13800138000
     * password : 111111
     * phoneCode : 6069
     */

    private String cellphone;
    private String password;
    private String phoneCode;
    private String inviteCode;
    private int userId;
    private int userType;

    public RegistRequest(String cellphone, String password, String phoneCode) {
        this.cellphone = cellphone;
        this.password = password;
        this.phoneCode = phoneCode;
    }

    public RegistRequest(String cellphone, String phoneCode, int userId, int userType) {
        this.cellphone = cellphone;
        this.phoneCode = phoneCode;
        this.userId = userId;
        this.userType = userType;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
