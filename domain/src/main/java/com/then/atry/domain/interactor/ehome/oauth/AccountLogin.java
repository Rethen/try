package com.then.atry.domain.interactor.ehome.oauth;

import com.then.atry.domain.Oauth;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.UseCase;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/6.
 */

public class AccountLogin extends UseCase<Oauth, AccountLogin.Params> {


    AccountLogin(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<Oauth> buildUseCaseObservable(AccountLogin.Params params) {
        return null;
    }

    public static final class Params {

        private Params(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public Params forLogin(String userName, String password) {
            return new Params(userName, password);
        }

        private String userName;

        private String password;

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }
}
