package com.then.atry.domain.interactor.atom.cpf.org;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Org;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.OrgRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/28.
 */

public class GetOrgList extends UseCase<List<Org>, GetOrgList.GetOrgListParams> {


    private final OrgRepository repository;


    protected GetOrgList(OrgRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Org>> buildUseCaseObservable(GetOrgListParams params) {
        return repository.orgs(params);
    }

    public static final class GetOrgListParams implements Params<List<Org>> {

        private  String sysId;

        public String getSysId() {
            return sysId;
        }

        public  GetOrgListParams(String sysId){
            this.sysId=sysId;
        }

        @Override
        public TypeToken<List<Org>> takeTypeToken() {
            return new TypeToken<List<Org>>() {
            };
        }

        @Override
        public String takeSn() {
            return "ehome.osys.org.list.get";
        }

        @Override
        public int takeService() {
            return CPF;
        }
    }
}
