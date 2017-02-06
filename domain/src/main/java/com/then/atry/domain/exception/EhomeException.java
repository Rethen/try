package com.then.atry.domain.exception;

/**
 * Created by then on 2016/12/29.
 */

public class EhomeException extends RuntimeException{

    private String code;

    private  String msg;

    public EhomeException(String code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
