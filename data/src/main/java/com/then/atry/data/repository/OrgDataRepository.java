package com.then.atry.data.repository;

import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Org;
import com.then.atry.domain.repository.OrgRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class OrgDataRepository implements OrgRepository {

    private HttpApiManager httpApiManager;

    @Inject
    OrgDataRepository(HttpApiManager httpApiManager){
        this.httpApiManager=httpApiManager;
    }

    @Override
    public Observable<Org> org(String orgId) {
        return null;
    }

    @Override
    public Observable<List<Org>> selfOrgs(String sysId) {
        return null;
    }
}
