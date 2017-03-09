package com.sudao.basemodule.basicapi.request;

public class ThirdLoginRequest {
    private String openid;
    private String accessToken;

    public ThirdLoginRequest() {
    }

    public ThirdLoginRequest(String openid, String accessToken) {
        this.openid = openid;
        this.accessToken = accessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
