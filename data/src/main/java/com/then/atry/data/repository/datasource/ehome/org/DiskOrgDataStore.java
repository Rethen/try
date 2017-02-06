package com.then.atry.data.repository.datasource.ehome.org;

import com.then.atry.domain.Org;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class DiskOrgDataStore implements OrgDataStore{

    @Override
    public Observable<Org> orgInfo(String orgId) {
        return null;
    }

    @Override
    public Observable<List<Org>> selfOrgs(String sysId) {
        return null;
    }
}
