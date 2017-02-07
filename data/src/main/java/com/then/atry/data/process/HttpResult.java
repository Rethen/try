package com.then.atry.data.process;

/**
 * Created by 42524 on 2016/1/22.
 */
public class HttpResult<T> {

    private SimpleError error;

    private T object;

    private String source;

    public T getObject() {
        return object;
    }

    public void setObject(T t) {
        this.object = t;
    }

    public SimpleError getError() {
        return error;
    }

    public void setError(SimpleError error) {
        this.error = error;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
