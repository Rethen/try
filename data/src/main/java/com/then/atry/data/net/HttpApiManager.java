package com.then.atry.data.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 42524 on 2015/12/12.
 */
@Singleton
public class HttpApiManager {


    public static final String SCHEME = "http";

    //正式
    public  static  final  String GRAPHQL_URL="192.168.2.152";
    public  static  final  int GRAPHQL_PORT=8080;




    private  HttpUrl graphqlUrl;

    private OkHttpClient okHttpClient;

    private OkHttpClient fileOkHttpClient;


    @Inject
    HttpApiManager() {

        graphqlUrl=new HttpUrl.Builder().scheme(SCHEME).host(GRAPHQL_URL).port(GRAPHQL_PORT).build();

        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();

        //文件下载，连接超时时间依然设置为10秒，读取超时设置为2小时
        fileOkHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(120, TimeUnit.MINUTES).build();

    }


    private Retrofit createNewRetrofit(HttpUrl httpUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T getGraphqlService(Class<T> clazz){
        Retrofit retrofit = createNewRetrofit(graphqlUrl, okHttpClient);
        return retrofit.create(clazz);
    }



}
