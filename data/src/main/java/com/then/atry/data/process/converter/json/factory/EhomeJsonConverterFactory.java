package com.then.atry.data.process.converter.json.factory;

import com.then.atry.data.EhomeReqHelper;
import com.then.atry.data.process.converter.json.converter.EhomeRequestJsonConverter;
import com.then.atry.data.process.converter.json.converter.EhomeResponseJsonConverter;
import com.then.atry.data.preferences.IPreferencesPrefs;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created by 42524 on 2015/12/12.
 */
public class EhomeJsonConverterFactory extends Converter.Factory {


    private EhomeReqHelper ehomeReqHelper;

    private IPreferencesPrefs preferences;

    public static EhomeJsonConverterFactory create(EhomeReqHelper ehomeReqHelper, IPreferencesPrefs preferences) {

        return new EhomeJsonConverterFactory(new Gson(), ehomeReqHelper, preferences);
    }

    private final Gson gson;


    private EhomeJsonConverterFactory(Gson gson, EhomeReqHelper ehomeReqHelper, IPreferencesPrefs preferences) {
        if (gson == null) {
            throw new NullPointerException("gson == null");
        }
        this.gson = gson;
        this.ehomeReqHelper = ehomeReqHelper;
        this.preferences = preferences;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EhomeResponseJsonConverter<>(gson, adapter, ehomeReqHelper, preferences);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EhomeRequestJsonConverter<>(gson, adapter);
    }
}
