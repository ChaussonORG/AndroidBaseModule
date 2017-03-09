package com.sudao.basemodule.basicapi.request;

/**
 * Created by Samuel on 5/26/16 16:18
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class UpdatePasswordRequest {


    /**
     * password : 1234567
     * newPassword : 123456
     */

    private String password;
    private String newPassword;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
