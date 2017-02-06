package com.then.atry.data.repository.datasource.ehome;

import com.then.atry.domain.model.ehome.Sys;

import java.util.List;

import rx.Observable;

/**
 * Created by then on 2017/1/24.
 */

public interface SysDataStore {

    Observable<Sys> sysInfo(String sysId);

    Observable<List<Sys>> sysList();
}
