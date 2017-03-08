package com.then.atry.domain.repository;

import com.then.atry.domain.Org;
import com.then.atry.domain.interactor.atom.cpf.org.GetOrgInfo;
import com.then.atry.domain.interactor.atom.cpf.org.GetOrgList;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface OrgRepository {

    Observable<Org> org(GetOrgInfo.GetOrgInfoParams params);

    Observable<List<Org>> orgs(GetOrgList.GetOrgListParams params);

}
