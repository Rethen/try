package com.then.atry.data.process.converter.json.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.domain.Error;
import com.then.atry.domain.net.HttpResult;

import net.fjhongda.sdk.ContentCrypt;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by 42524 on 2015/12/12.
 */
public class EhomeResponseJsonConverter<T> implements Converter<ResponseBody, HttpResult> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private final TypeAdapter<Error> errorTypeAdapter;

    private EhomeReqHelper ehomeReqHelper;

    private IPreferencesPrefs preferences;


    public EhomeResponseJsonConverter(Gson gson, TypeAdapter<T> adapter, EhomeReqHelper ehomeReqHelper, IPreferencesPrefs preferences) {
        this.gson = gson;
        this.adapter = adapter;
        errorTypeAdapter = gson.getAdapter(Error.class);
        this.ehomeReqHelper = ehomeReqHelper;
        this.preferences = preferences;
    }


    @Override
    public HttpResult convert(ResponseBody value) throws IOException {
        String remoteContent = value.string();
        String content = ehomeReqHelper.contentDecryption(remoteContent, false);
        Log.d("EhomeResponseJsonConver", "content:"+content);
        try {
            HttpResult httpResult = new HttpResult();
            JsonReader jsonReader = null;
            httpResult.setSource(content);
            if (content.startsWith(HttpResult.ERR)) {
                content = content.substring(4, content.length());
                jsonReader = gson.newJsonReader(new StringReader(content));
                httpResult.setError(errorTypeAdapter.read(jsonReader));
                return httpResult;
            } else if (content.startsWith(HttpResult.SEC)) {
                content = content.substring(HttpResult.SEC.length(), content.length());
                content = ContentCrypt.decode(content, preferences.getAt());
                jsonReader = gson.newJsonReader(new StringReader(content));
                httpResult.setSource(content);
                httpResult.setObject(adapter.read(jsonReader));
                return httpResult;
            } else if (HttpResult.NULL.equals(content)) {
                Error error = new Error();
                error.setMsg("空置");
                error.setCode(Error.ERROR_CODE_NULL_OBJECT + "");
                httpResult.setError(error);
                return httpResult;
            } else {
                jsonReader = gson.newJsonReader(new StringReader(content));
                httpResult.setObject(adapter.read(jsonReader));
                return httpResult;
            }
        } finally {
            value.close();
        }
    }
}
