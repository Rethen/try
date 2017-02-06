package com.then.atry.domain.interactor.ehome;

import com.then.atry.domain.net.HttpApiManager;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.repository.ehome.EhomeCommonRepository;

import javax.inject.Inject;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by then on 2016/12/24.
 */

public class OauthUseCase extends EhomeUseCase {


    private HttpApiManager httpApiManager;

    @Inject
    public OauthUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, RequestFilter requestFilter, HttpApiManager httpApiManager) {
        super(threadExecutor, postExecutionThread,requestFilter);
        this.httpApiManager = httpApiManager;
    }

    @Override
    protected Observable buildUseCaseObservable(String header, RequestBody content) {
        return httpApiManager.getOauthService(EhomeCommonRepository.class).req(header, content);
    }


}
