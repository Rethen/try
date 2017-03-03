package com.then.atry.data.repository;

import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Org;
import com.then.atry.domain.interactor.atom.cpf.org.GetOrgInfo;
import com.then.atry.domain.interactor.atom.cpf.org.GetOrgList;
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
    OrgDataRepository(HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
    }

    @Override
    public Observable<Org> org(GetOrgInfo.GetOrgInfoParams params) {
        return httpApiManager.request(params);
    }

    @Override
    public Observable<List<Org>> orgs(GetOrgList.GetOrgListParams params) {
        return httpApiManager.request(params);
    }


}
