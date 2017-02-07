package com.then.atry.data.process.json.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.then.atry.data.net.helper.HttpHelper;
import com.then.atry.data.process.HttpResult;
import com.then.atry.data.process.SimpleError;

import net.fjhongda.sdk.ContentCrypt;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by 42524 on 2015/12/12.
 */
public class SimpleResponseJsonConverter<T> implements Converter<ResponseBody, HttpResult> {


    /**
     * 加密前缀
     */
    public final String SEC = "SEC_";

    /**
     * 错误前缀
     */
    public final String ERR = "ERR_";

    /**
     * 数据为空
     */
    public final String NULL = "{}";

    private final Gson gson;
    private final TypeAdapter<SimpleError> errorTypeAdapter;
    private final HttpHelper httpHelper;

    public SimpleResponseJsonConverter(Gson gson, HttpHelper httpHelper) {
        this.gson = gson;
        errorTypeAdapter = gson.getAdapter(SimpleError.class);
        this.httpHelper = httpHelper;
    }

    @Override
    public HttpResult convert(ResponseBody value) throws IOException {
        String valString = value.string();
        String content = httpHelper.contentDecryption(valString, false);
        try {
            HttpResult httpResult = new HttpResult();
            JsonReader jsonReader = null;
            httpResult.setSource(content);
            if (content.startsWith(ERR)) {
                content = content.substring(4, content.length());
                jsonReader = gson.newJsonReader(new StringReader(content));
                httpResult.setError(errorTypeAdapter.read(jsonReader));
                return httpResult;
            } else if (content.startsWith(SEC)) {
                content = content.substring(SEC.length(), content.length());
                content = ContentCrypt.decode(content, httpHelper.getRequestPrefs().getAt(""));
                httpResult.setSource(content);
                return httpResult;
            } else if (NULL.equals(content)) {
                SimpleError error = new SimpleError();
                error.setMsg("空置");
                error.setCode("01");
                httpResult.setError(error);
                return httpResult;
            } else {
                return httpResult;
            }
        } finally {
            value.close();
        }
    }
}
