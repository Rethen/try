package com.then.atry.domain.repository;

import com.then.atry.domain.Org;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public interface OrgRepository {

    Observable<Org> org(final String orgId);

    Observable<List<Org>> selfOrgs(final String sysId);
}
