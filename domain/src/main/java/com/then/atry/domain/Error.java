package com.then.atry.domain;

/**
 * Created by then on 2016/3/31.
 */
public class Error {

    public static final String REQUEST_TIME_OUT = "REQUEST_TIME_OUT";

    public static final String UNKOWN_HOST = "UNKOWN_HOST";

    public static final String ERROR_CODE_NULL_OBJECT = "ERROR_CODE_NULL_OBJECT";

    private String code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
