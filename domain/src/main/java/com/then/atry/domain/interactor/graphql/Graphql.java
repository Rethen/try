package com.then.atry.domain.interactor.graphql;

import com.then.atry.domain.GraphqlModel;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.GraphqlRespository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by 42524 on 2017/3/30.
 */

public class Graphql extends UseCase<GraphqlModel,String> {

    GraphqlRespository respository;

    @Inject
     Graphql(GraphqlRespository respository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.respository=respository;
    }

    @Override
    protected Observable<GraphqlModel> buildUseCaseObservable(String query) {
        return respository.getModel(query);
    }
}
