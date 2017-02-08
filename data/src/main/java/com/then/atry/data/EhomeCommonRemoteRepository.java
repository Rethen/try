package com.then.atry.data;

import com.then.atry.data.process.HttpResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by then on 2017/2/7.
 */

public interface EhomeCommonRemoteRepository {

    String PATH = "ehome.rest";

    @POST(PATH)
    Observable<HttpResult> req(@Header("ehome-head-params") String header, @Body RequestBody content);
}
