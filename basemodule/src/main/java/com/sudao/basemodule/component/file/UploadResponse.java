package com.sudao.basemodule.component.file;

import java.util.List;

/**
 * Created by Samuel on 16/6/21 16:54
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class UploadResponse {
    private String code;
    private String message;
    private List<String> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
