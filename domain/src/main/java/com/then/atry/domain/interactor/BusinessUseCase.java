package com.then.atry.domain.interactor;

import com.google.gson.Gson;
import com.then.atry.domain.IBusiness;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by then on 2017/1/17.
 */

@Singleton
public class BusinessUseCase {

    private UseCase cpfUseCase, oauthUseCase;

    private PostExecutionThread postExecutionThread;

    private ThreadExecutor threadExecutor;

    private RequestFilter requestFilter;

    private Gson gson;

    @Inject
    public BusinessUseCase(UseCase cpfUseCase, UseCase oauthUseCase, ThreadExecutor threadExecutor,
                           PostExecutionThread postExecutionThread, RequestFilter requestFilter) {
        this.cpfUseCase = cpfUseCase;
        this.oauthUseCase = oauthUseCase;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.requestFilter = requestFilter;
        gson = new Gson();
    }

    public UseCase getCpfUseCase() {
        return cpfUseCase;
    }

    public UseCase getOauthUseCase() {
        return oauthUseCase;
    }

    public void execute(IBusiness business, Subscriber useCaseSubscriber, Action1... action1s) {
        Observable observable = business.execute(this, threadExecutor, postExecutionThread, gson, requestFilter);
        for (Action1 action1 : action1s) {
            observable = observable.doOnNext(action1);
        }
        observable.subscribe(useCaseSubscriber);
    }
}
