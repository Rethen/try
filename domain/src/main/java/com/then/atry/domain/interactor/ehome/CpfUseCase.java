package com.then.atry.domain.interactor.ehome;

import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.net.HttpApiManager;
import com.then.atry.domain.repository.ehome.EhomeCommonRepository;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by then on 2016/12/24.
 */

public class CpfUseCase extends EhomeUseCase {

    private HttpApiManager httpApiManager;

    @Inject
    public CpfUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter, HttpApiManager httpApiManager) {
        super(threadExecutor, postExecutionThread, requestFilter);
        this.httpApiManager = httpApiManager;
    }

    @Override
    protected Observable buildUseCaseObservable(String header, RequestBody content) {

        FormBody formBody=new FormBody.Builder().addEncoded("query","{user(id:\"1\"){name}}").build();

//        MultipartBody multipartBody = new MultipartBody.Builder().addFormDataPart("query", "{user(id:\"1\"){name}}").build();


//        Request request=new Request.Builder().url("http://10.0.2.2:8080/graphql?query={user(id:\"1\"){name}}").build();

        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host("10.0.2.2").port(8080).addEncodedPathSegment("graphql").build();

        Request request = new Request.Builder().url(httpUrl).post(formBody).build();


        OkHttpClient httpClient = new OkHttpClient();
        Call call = httpClient.newCall(request);

        System.out.println("hello");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:" + call.request().url().toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                System.out.println("S:" + s);
            }
        });


        return httpApiManager.getCPFService(EhomeCommonRepository.class).req(header, content);
    }

}
