package com.then.atry.domain.net;


import com.then.atry.domain.Error;

/**
 * Created by 42524 on 2016/1/22.
 */
public class HttpResult<T> {

    public static final String SEC = "SEC_";

    public static final String NULL = "{}";

    public static final String ERR = "ERR_";

    private Error error;

    private T object;

    private String source;

    private String remoteContent;


    public T getObject() {
        return object;
    }

    public void setObject(T t) {
        this.object = t;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRemoteContent() {
        return remoteContent;
    }

    public void setRemoteContent(String remoteContent) {
        this.remoteContent = remoteContent;
    }
}
