package com.then.atry.domain;

import com.google.gson.Gson;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.BusinessUseCase;

import rx.Observable;

/**
 * Created by then on 2017/1/17.
 */

public interface IBusiness {
    Observable execute(BusinessUseCase businessUseCase, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, Gson gson, RequestFilter requestFilter);
}
