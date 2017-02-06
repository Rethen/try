package com.then.atry.domain.interactor.ehome.cpf.sys;

import com.then.atry.domain.Sys;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.SysRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetSysInfo extends UseCase<Sys, GetSysInfo.Params> {

    private final SysRepository sysRepository;

    @Inject
    GetSysInfo(SysRepository sysRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.sysRepository = sysRepository;
    }

    @Override
    protected Observable<Sys> buildUseCaseObservable(Params params) {
        return sysRepository.sys(params.getSysId());
    }


    public static class Params {

        private String sysId;

        private Params(String sysId) {
            this.sysId = sysId;
        }

        public String getSysId() {
            return sysId;
        }

        public static GetSysInfo.Params forSys(String sysId) {
            return new GetSysInfo.Params(sysId);
        }

    }


}
