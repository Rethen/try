package com.then.atry.data.repository.datasource.ehome.sys;

import com.then.atry.domain.Sys;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface SysDataStore {

    Observable<Sys> sys(String sysId);
}
