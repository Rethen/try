package com.then.atry.domain.interactor.ehome.cpf.sys;

import com.then.atry.domain.Sys;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.SysRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetSysList extends UseCase<List<Sys>,Void>{

    private  final SysRepository repository;

    @Inject
    GetSysList(SysRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository=repository;
    }

    @Override
    protected Observable<List<Sys>> buildUseCaseObservable(Void aVoid) {
        return repository.sysList();
    }
}
