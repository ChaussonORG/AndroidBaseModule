package com.sudao.basemodule.basicapi.response;

/**
 * Created by zhuyijun on 2016/12/6.
 * 登录返回的数据
 */

public class LoginResponse {
    /**
     * "token": "S6ubIRJm%2F8Imtde6fap2td5Dsfi5GnDSWJTsFT5komm1%2FutpLZSzLcvzRuutw8y43U5%2FgpwNhiY95f4noeISVdOvem44li0vs6BQ7Q%2Fw9onvFWsnB91eEhei9XnZ2%2BV%2BgPDtxMcxtZwuf2N2X4AgHQ%3D%3D",
     * "chamberQuantity": 1,
     * "InvitationQuantity": 1,
     * "profile": {}
     */
    private String token;
    private int chamberQuantity; //加入商会的数量(登录的时候判断数量是否>0, >0 说明第一次登录， = 0 说明)
    private int InvitationQuantity; //商会邀请的数量
    private LoginBean profile;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getChamberQuantity() {
        return chamberQuantity;
    }

    public void setChamberQuantity(int chamberQuantity) {
        this.chamberQuantity = chamberQuantity;
    }

    public int getInvitationQuantity() {
        return InvitationQuantity;
    }

    public void setInvitationQuantity(int invitationQuantity) {
        InvitationQuantity = invitationQuantity;
    }

    public LoginBean getProfile() {
        return profile;
    }

    public void setProfile(LoginBean profile) {
        this.profile = profile;
    }
}
