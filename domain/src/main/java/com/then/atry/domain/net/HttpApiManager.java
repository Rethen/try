package com.then.atry.domain.net;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * Created by 42524 on 2015/12/12.
 */
@Singleton
public class HttpApiManager {


    public static final String SCHEME = "http";

    public static final String OAUTH_URL = "oauth.7mhome.com";

    public static final String CPF_URL = "cpf.7mhome.com";

    public  static final  String TEST_URL="localhost";


    public static final int  CPF_PORT = 9000;

    public static final int OAUTH_PORT = 9200;

    public  static  final  int  TEST_PORT=8080;

    private Retrofit retrofit;

    private HttpUrl oauthHttpUrl;

    private HttpUrl cpfHttpUrl;

    private  HttpUrl testHttpUrl;

    private OkHttpClient okHttpClient;

    private Converter.Factory factory;


    @Inject
    public HttpApiManager() {

        oauthHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(OAUTH_URL).port(OAUTH_PORT).build();

        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();

        cpfHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(CPF_URL).port(CPF_PORT).build();

        testHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(TEST_URL).port(TEST_PORT).build();
    }

    public HttpApiManager(Converter.Factory factory) {
        this();
        this.factory = factory;
        retrofit = createNewRetrofit(factory, oauthHttpUrl);
    }


    private Retrofit createNewRetrofit(Converter.Factory factory, HttpUrl httpUrl) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private Retrofit createNewRetrofit(HttpUrl httpUrl) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private Retrofit createNewRetrofit(String httpUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T getOauthService(Class<T> clazz) {
        return retrofit.create(clazz);
    }


    public <T> T getCPFService(Class<T> clazz) {
        Retrofit retrofit = createNewRetrofit(cpfHttpUrl);
        return retrofit.create(clazz);
    }


}
