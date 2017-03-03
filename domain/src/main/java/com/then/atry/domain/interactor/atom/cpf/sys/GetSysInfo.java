package com.then.atry.domain.interactor.atom.cpf.sys;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Sys;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.SysRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetSysInfo extends UseCase<Sys, GetSysInfo.SysInfoParams> {

    private final SysRepository sysRepository;

    @Inject
    GetSysInfo(SysRepository sysRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.sysRepository = sysRepository;
    }

    @Override
    protected Observable<Sys> buildUseCaseObservable(SysInfoParams params) {
        return sysRepository.sys(params);
    }

    public static class SysInfoParams implements Params<Sys> {

        private String sysId;

        public SysInfoParams(String sysId) {
            this.sysId = sysId;
        }

        public String getSysId() {
            return sysId;
        }

        @Override
        public TypeToken<Sys> takeTypeToken() {
            return new TypeToken<Sys>(){};
        }

        @Override
        public String takeSn() {
            return "ehome.osys.get";
        }

        @Override
        public int takeService() {
            return 0;
        }
    }


}
