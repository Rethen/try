package com.then.atry.data.exception;

/**
 * Created by then on 2017/3/7.
 */
public class EhomeException extends  Exception {


    private String code;

    private  String message;

    private  String sn;

    public EhomeException(String sn,String code,String message){
        this.code=code;
        this.message=message;
        this.sn=sn;
    }

    @Override
    public String getMessage() {
        return sn+"::请求错误，错误码为："+code+"--"+"错误信息为："+message;
    }
}
