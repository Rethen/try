package com.then.atry.domain.interactor.ehome;

import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;

import rx.Observable;

/**
 * Created by then on 2016/12/24.
 */

public abstract class EhomeUseCase extends UseCase {


    protected EhomeUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter) {
        super(threadExecutor, postExecutionThread, requestFilter);
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return null;
    }
}
