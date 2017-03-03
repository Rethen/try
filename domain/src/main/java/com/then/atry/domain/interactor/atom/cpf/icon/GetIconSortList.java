package com.then.atry.domain.interactor.atom.cpf.icon;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.IconSort;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.IconRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/28.
 */

public class GetIconSortList extends UseCase<List<IconSort>, GetIconSortList.GetIconSortListParams> {


    private final IconRepository repository;

    @Inject
    GetIconSortList(IconRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<IconSort>> buildUseCaseObservable(GetIconSortListParams params) {
        return repository.getIcons(params);
    }


    public static class GetIconSortListParams implements Params<List<IconSort>> {

        private String orgId;

        private String module;

        public GetIconSortListParams(String orgId, String module) {
            this.orgId = orgId;
            this.module = module;
        }

        @Override
        public TypeToken<List<IconSort>> takeTypeToken() {
            return new TypeToken<List<IconSort>>() {
            };
        }

        @Override
        public String takeSn() {
            return "ehome.org.icon.sort.list.get";
        }

        @Override
        public int takeService() {
            return CPF;
        }
    }
}
