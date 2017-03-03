package com.then.atry.domain.repository;

import com.then.atry.domain.Sys;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysInfo;
import com.then.atry.domain.interactor.atom.cpf.sys.GetSysList;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface SysRepository {

    Observable<Sys> sys(GetSysInfo.SysInfoParams params);

    Observable<List<Sys>> sysList(GetSysList.SysListParams params);

}
