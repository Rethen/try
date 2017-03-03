package com.then.atry.data.repository;

import com.then.atry.data.action.login.LoginSavePrefsConsumer;
import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Oauth;
import com.then.atry.domain.interactor.atom.oauth.AccountLogin;
import com.then.atry.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/7.
 */

public class LoginDataRepository implements LoginRepository {

    private final HttpApiManager httpApiManager;

    private final LoginSavePrefsConsumer loginSavePrefsConsumer;

    @Inject
    LoginDataRepository(LoginSavePrefsConsumer loginSavePrefsConsumer, HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
        this.loginSavePrefsConsumer = loginSavePrefsConsumer;
    }


    @Override
    public Observable<Oauth> login(AccountLogin.LoginParams params) {
        return httpApiManager.request(params).doOnNext(loginSavePrefsConsumer);
    }


}
