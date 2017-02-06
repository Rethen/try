package com.then.atry.data.repository.datasource.ehome;

import com.then.atry.data.cache.ehome.DataCache;
import com.then.atry.domain.model.ehome.Sys;

import java.util.List;

import rx.Observable;

/**
 * Created by then on 2017/1/24.
 */

public class CloudSysDataStore implements SysDataStore {


    private DataCache dataCache;

    @Override
    public Observable<Sys> sysInfo(String sysId) {
        return null;
    }

    @Override
    public Observable<List<Sys>> sysList() {
        return null;
    }
}
