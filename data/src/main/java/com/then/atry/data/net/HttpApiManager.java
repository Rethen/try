package com.then.atry.data.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/**
 * Created by 42524 on 2015/12/12.
 */
@Singleton
public class HttpApiManager {


    /**
     * 加密前缀
     */
    public final static String SEC = "SEC_";

    /**
     * 错误前缀
     */
    public final static String ERR = "ERR_";

    /**
     * 数据为空
     */
    public final static String NULL = "{}";


    public static final String SCHEME = "http";

    //正式
    public static final String OAUTH_URL = "oauth.7mhome.com";
    public static final String CPF_URL = "cpf.7mhome.com";
    public static final String FILE_SERVICE_URL = "192.168.254.188";
    public static final int CPF_PORT = 9000;
    public static final int OAUTH_PORT = 9200;
    public static final int FILE_SERVICE_PORT = 8003;
    public final static String CHAT_URL = "http://im.7mhome.com:5000";//即时通信IP端口
    public final static String CHAT_RECEIVE_ROUTER_SERVER_URL = "http://im.7mhome.com:6666";//聊天路由服务


    private HttpUrl oauthHttpUrl;

    private HttpUrl cpfHttpUrl;


    private HttpUrl fileHttpUrl;

    private OkHttpClient okHttpClient;

    private OkHttpClient fileOkHttpClient;


    @Inject
    HttpApiManager() {

        oauthHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(OAUTH_URL).port(OAUTH_PORT).build();

        cpfHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(CPF_URL).port(CPF_PORT).build();

        fileHttpUrl = new HttpUrl.Builder().scheme(SCHEME).host(FILE_SERVICE_URL).port(FILE_SERVICE_PORT).build();

        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();

        //文件下载，连接超时时间依然设置为10秒，读取超时设置为2小时
        fileOkHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(120, TimeUnit.MINUTES).build();

    }


    private Retrofit createNewRetrofit(HttpUrl httpUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public <T> T getOauthService(Class<T> clazz) {
        Retrofit retrofit = createNewRetrofit(oauthHttpUrl, okHttpClient);
        return retrofit.create(clazz);
    }

    public <T> T getCPFService(Class<T> clazz) {
        Retrofit retrofit = createNewRetrofit(cpfHttpUrl, okHttpClient);
        return retrofit.create(clazz);
    }

    public <T> T getFileService(Class<T> clazz) {
        Retrofit retrofit = createNewRetrofit(fileHttpUrl, fileOkHttpClient);
        return retrofit.create(clazz);
    }


}
