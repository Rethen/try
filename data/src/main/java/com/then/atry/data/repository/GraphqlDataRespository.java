package com.then.atry.data.repository;

import com.then.atry.data.net.pull.HttpApiManager;
import com.then.atry.domain.GraphqlModel;
import com.then.atry.domain.repository.GraphqlRespository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by 42524 on 2017/3/30.
 */

public class GraphqlDataRespository implements GraphqlRespository {

    private HttpApiManager httpApiManager;

    @Inject
    GraphqlDataRespository(HttpApiManager httpApiManager) {
      this.httpApiManager=httpApiManager;
    }

    @Override
    public Observable<GraphqlModel> getModel(String query) {
        Map<String,String> queryMap=new HashMap<>();
        queryMap.put("query","{b(bookname:\"Dan\"){id}}");
        return httpApiManager.getGraphqlService(GraphqlRemoteRespository.class).req(queryMap);
    }
}
