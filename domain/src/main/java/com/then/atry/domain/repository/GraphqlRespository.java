package com.then.atry.domain.repository;

import com.then.atry.domain.GraphqlModel;

import io.reactivex.Observable;

/**
 * Created by 42524 on 2017/3/30.
 */

public interface GraphqlRespository {

     Observable<GraphqlModel> getModel(String query);
}
