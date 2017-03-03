package com.then.atry.domain.interactor.atom.cpf.sys;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Sys;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.SysRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetSysList extends UseCase<List<Sys>, GetSysList.SysListParams> {

    private final SysRepository repository;

    @Inject
    GetSysList(SysRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Sys>> buildUseCaseObservable(SysListParams params) {
        return repository.sysList(params);
    }


    public static class SysListParams implements Params<List<Sys>> {

        @Override
        public TypeToken<List<Sys>> takeTypeToken() {
            return new TypeToken<List<Sys>>() {
            };
        }

        @Override
        public String takeSn() {
            return "ehome.osys.self.list.get";
        }

        @Override
        public int takeService() {
            return CPF;
        }
    }
}
