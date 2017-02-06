package com.then.atry.data.business;

import com.google.gson.Gson;
import com.then.atry.domain.IBusiness;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.BusinessUseCase;
import com.then.atry.domain.interactor.ehome.req.cpf.IconGetReq;
import com.then.atry.domain.interactor.ehome.req.cpf.IconSortListGetReq;
import com.then.atry.domain.interactor.ehome.req.cpf.SelfOrgListReq;
import com.then.atry.domain.interactor.ehome.req.cpf.SysListReq;
import com.then.atry.domain.model.ehome.IconSort;
import com.then.atry.domain.model.ehome.Org;
import com.then.atry.domain.model.ehome.Sys;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by then on 2017/1/17.
 */

public class GetFirstOrg implements IBusiness {

    @Override
    public Observable execute(BusinessUseCase businessUseCase,
                              ThreadExecutor threadExecutor,
                              PostExecutionThread postExecutionThread, Gson gson, RequestFilter requestFilter) {
        SysListReq sysListReq = new SysListReq();
        SelfOrgListReq selfOrgListReq = new SelfOrgListReq();
        IconSortListGetReq iconSortListGetReq = new IconSortListGetReq();
        return businessUseCase.getCpfUseCase().execute(sysListReq).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .flatMap(new Func1<List<Sys>, Observable>() {
                    @Override
                    public Observable call(List<Sys> syses) {
                        Sys sys = syses.get(0);
                        selfOrgListReq.setSysId(sys.getSysId());
                        return businessUseCase.getCpfUseCase().execute(selfOrgListReq);
                    }
                })
                .flatMap(new Func1<List<Org>, Observable>() {
                    @Override
                    public Observable call(List<Org> orgs) {
                        String orgId = orgs.get(1).getOrgId();
                        iconSortListGetReq.setOrgId(orgId);
                        iconSortListGetReq.setModule("1");
                        return businessUseCase.getCpfUseCase().execute(iconSortListGetReq);
                    }
                })
                .flatMap(new Func1<List<IconSort>, Observable>() {
                    @Override
                    public Observable call(List<IconSort> iconSorts) {
                        return Observable.from(iconSorts);
                    }
                })
                .flatMap(new Func1<IconSort, Object>() {
                    @Override
                    public Object call(IconSort iconSort) {
                        String iconId = iconSort.getIconId();
                        String orgId = iconSortListGetReq.getOrgId();
                        IconGetReq iconGetReq = new IconGetReq();
                        iconGetReq.setOrgId(orgId);
                        iconGetReq.setIconId(iconId);
                        Observable observable = businessUseCase.getCpfUseCase().execute(iconGetReq);
                        return observable;
                    }
                });
    }

}
