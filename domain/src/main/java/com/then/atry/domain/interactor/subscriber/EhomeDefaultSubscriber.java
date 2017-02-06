package com.then.atry.domain.interactor.subscriber;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.then.atry.domain.net.HttpResult;
import com.then.atry.domain.exception.EhomeException;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.Error;
import java.io.IOException;
import java.io.StringReader;

import rx.functions.Func1;

/**
 * Created by then on 2016/12/29.
 */

public class EhomeDefaultSubscriber implements Func1<HttpResult, Object> {

    private BaseReq baseReq;

    private Gson gson;

    public EhomeDefaultSubscriber(BaseReq baseReq, Gson gson) {
        this.baseReq = baseReq;
        this.gson = gson;
    }

    @Override
    public Object call(HttpResult httpResult) throws EhomeException {
        String source = httpResult.getSource();
        Error error = httpResult.getError();
        if (error != null) {
            throw new EhomeException(error.getCode(), error.getMsg());
        }
        TypeToken typeToken = baseReq.typeToken();
        TypeAdapter adapter = gson.getAdapter(typeToken);
        JsonReader jsonReader = gson.newJsonReader(new StringReader(source));
        Object result = null;
        try {
            result = adapter.read(jsonReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
