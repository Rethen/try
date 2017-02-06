package com.then.atry.data.repository.datasource.ehome.org;

import com.then.atry.domain.Org;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface OrgDataStore {

    Observable<Org> orgInfo(String orgId);

    Observable<List<Org>> selfOrgs(String sysId);
}
