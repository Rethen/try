package com.then.atry.domain.interactor.atom.cpf.org;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Org;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.OrgRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class GetOrgInfo extends UseCase<Org, GetOrgInfo.GetOrgInfoParams> {

    private final OrgRepository repository;

    @Inject
    GetOrgInfo(OrgRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Org> buildUseCaseObservable(GetOrgInfoParams params) {
        return repository.org(params);
    }


    public static final class GetOrgInfoParams implements Params<Org> {

        private String orgId;

        public String getOrgId() {
            return orgId;
        }

        public GetOrgInfoParams(String orgId) {
            this.orgId = orgId;
        }

        @Override
        public TypeToken<Org> takeTypeToken() {
            return new TypeToken<Org>() {
            };
        }

        @Override
        public String takeSn() {
            return "ehome.org.get";
        }

        @Override
        public int takeService() {
            return CPF;
        }
    }

}
