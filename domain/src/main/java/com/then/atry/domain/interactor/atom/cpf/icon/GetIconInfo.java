package com.then.atry.domain.interactor.atom.cpf.icon;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Icon;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.IconRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/28.
 */

public class GetIconInfo extends UseCase<Icon, GetIconInfo.GetIconInfoParams> {

    private final IconRepository repository;

    @Inject
    GetIconInfo(IconRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Icon> buildUseCaseObservable(GetIconInfoParams params) {
        return repository.getIconInfo(params);
    }

    public static final class GetIconInfoParams implements Params<Icon> {

        private String orgId;

        private String iconId;

        public GetIconInfoParams(String orgId, String iconId) {
            this.orgId = orgId;
            this.iconId = iconId;
        }

        @Override
        public TypeToken<Icon> takeTypeToken() {
            return new TypeToken<Icon>() {
            };
        }

        @Override
        public String takeSn() {
            return "ehome.org.icon.get";
        }

        @Override
        public int takeService() {
            return CPF;
        }
    }
}
