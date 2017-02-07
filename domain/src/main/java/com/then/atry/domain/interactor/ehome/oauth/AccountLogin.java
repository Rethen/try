package com.then.atry.domain.interactor.ehome.oauth;

import com.then.atry.domain.Oauth;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class AccountLogin extends UseCase<Oauth, AccountLogin.Params> {


    private LoginRepository loginRepository;

    @Inject
    AccountLogin(LoginRepository loginRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.loginRepository = loginRepository;
    }

    @Override
    protected Observable<Oauth> buildUseCaseObservable(AccountLogin.Params params) {
        return loginRepository.login(params);
    }


    public static final class Params {

        private Params(String loginName, String pwd) {
            this.loginName = loginName;
            this.pwd = pwd;
        }

        public static Params forLogin(String userName, String password) {
            return new Params(userName, password);
        }

        private String loginName;

        private String pwd;

        public String getLoginName() {
            return loginName;
        }

        public String getPwd() {
            return pwd;
        }
    }
}
