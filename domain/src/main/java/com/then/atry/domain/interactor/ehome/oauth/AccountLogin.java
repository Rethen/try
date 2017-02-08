package com.then.atry.domain.interactor.ehome.oauth;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.Oauth;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.params.Params;
import com.then.atry.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class AccountLogin extends UseCase<Oauth, AccountLogin.LoginParams> {


    private LoginRepository loginRepository;

    @Inject
    AccountLogin(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable<Oauth> buildUseCaseObservable(AccountLogin.LoginParams params) {
        return loginRepository.login(params);
    }


    public static final class LoginParams implements Params {

        private String loginName;

        private String pwd;

        private LoginParams(String loginName, String pwd) {
            this.loginName = loginName;
            this.pwd = pwd;
        }

        public static LoginParams create(String userName, String password) {
            return new LoginParams(userName, password);
        }

        public TypeToken takeTypeToken() {
            return new TypeToken<Oauth>(){};
        }

        @Override
        public String takeSn() {
            return "ehome.ac.login.get";
        }

        @Override
        public int takeService() {
            return OAUTH;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getPwd() {
            return pwd;
        }
    }
}
