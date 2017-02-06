package com.then.atry.domain.interactor.ehome.cpf.org;

import com.then.atry.domain.Org;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.OrgRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetOrgInfo extends UseCase<Org, GetOrgInfo.Params> {

    private final OrgRepository repository;

    @Inject
    GetOrgInfo(OrgRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Org> buildUseCaseObservable(Params params) {
        return repository.org(params.getOrgId());
    }


    public static final class Params {

        private String orgId;

        public String getOrgId() {
            return orgId;
        }

        private Params(String orgId) {
            this.orgId = orgId;
        }

        public static Params forOrg(String orgId) {
            return new Params(orgId);
        }
    }
}
