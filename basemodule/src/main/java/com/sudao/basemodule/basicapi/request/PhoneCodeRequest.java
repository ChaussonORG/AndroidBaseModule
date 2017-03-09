package com.sudao.basemodule.basicapi.request;

/**
 * Created by Samuel on 5/27/16 14:16
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class PhoneCodeRequest {

    /**
     * cellphone : 15068722769
     */

    private String cellphone;

    public PhoneCodeRequest(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
