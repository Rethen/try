package com.then.atry.data.repository;

import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Sys;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysInfo;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysList;
import com.then.atry.domain.repository.SysRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class SysDataRepository implements SysRepository {

    private final HttpApiManager httpApiManager;

    @Inject
    SysDataRepository(HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
    }


    @Override
    public Observable<Sys> sys(GetSysInfo.SysInfoParams params) {
        return null;
    }

    @Override
    public Observable<List<Sys>> sysList(GetSysList.SysListParams params) {
        return httpApiManager.request(params);
    }

}
