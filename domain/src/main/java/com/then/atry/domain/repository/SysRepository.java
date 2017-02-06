package com.then.atry.domain.repository;

import com.then.atry.domain.Sys;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface SysRepository {

    Observable<Sys> sys(String sysId);

    Observable<List<Sys>> sysList();
}
