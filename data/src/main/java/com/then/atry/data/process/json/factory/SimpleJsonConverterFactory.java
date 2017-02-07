package com.then.atry.data.process.json.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.then.atry.data.net.helper.HttpHelper;
import com.then.atry.data.process.json.converter.SimpleRequestJsonConverter;
import com.then.atry.data.process.json.converter.SimpleResponseJsonConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created by 42524 on 2015/12/12.
 */
public class SimpleJsonConverterFactory extends  Converter.Factory  {


    private final HttpHelper httpHelper;

    public  static SimpleJsonConverterFactory create(HttpHelper httpHelper){
        return  new SimpleJsonConverterFactory(new Gson(),httpHelper);
    }

    private final Gson gson;

    private SimpleJsonConverterFactory(Gson gson,HttpHelper httpHelper) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
        this.httpHelper=httpHelper;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter    = gson.getAdapter(TypeToken.get(type));
        return new SimpleResponseJsonConverter<>(gson, httpHelper);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new SimpleRequestJsonConverter<>(gson, adapter);
    }
}
