package com.then.atry.domain.interactor.ehome.req;

import com.google.gson.reflect.TypeToken;

import okhttp3.RequestBody;

/**
 * Created by then on 2016/12/29.
 */

public abstract class BaseReq<T> {

    protected String requestHeader;

    protected RequestBody requestBody;

    public abstract String takeUrl();

    public abstract TypeToken<T> typeToken() ;

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
}
