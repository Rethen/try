package com.then.atry.domain.repository.ehome;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by then on 2016/12/24.
 */

public interface EhomeCommonRepository {

    String PATH = "ehome.rest";

    @POST(PATH)
    Observable<Object> req(@Header("ehome-head-params") String header, @Body RequestBody content);

}
