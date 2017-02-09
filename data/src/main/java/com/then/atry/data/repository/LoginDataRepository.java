package com.then.atry.data.repository;

import android.util.Log;

import com.then.atry.data.net.HttpApiManager;
import com.then.atry.domain.Oauth;
import com.then.atry.domain.interactor.ehome.oauth.AccountLogin;
import com.then.atry.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/7.
 */

public class LoginDataRepository implements LoginRepository {

    private final HttpApiManager httpApiManager;

    @Inject
    LoginDataRepository(HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
    }

    @Override
    public Observable<Oauth> login(AccountLogin.LoginParams params) {
        return httpApiManager.request(params).doOnNext(o -> Log.d("LoginDataRepository", o.getAccessToken()));
    }


}
