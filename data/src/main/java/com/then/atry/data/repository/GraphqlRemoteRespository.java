package com.then.atry.data.repository;


import com.then.atry.domain.GraphqlModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 42524 on 2017/3/30.
 */

public interface GraphqlRemoteRespository  {

    @GET("/graphql/book")
    Observable<GraphqlModel> req(@QueryMap Map<String,String> query);
}
