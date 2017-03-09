package com.sudao.basemodule.basicapi.request;

/**
 * Created by Samuel on 6/3/16 10:10
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class AdviceRequest {
    private String title;
    private String body;

    public AdviceRequest() {
    }

    public AdviceRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
