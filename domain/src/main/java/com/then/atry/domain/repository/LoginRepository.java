package com.then.atry.domain.repository;

import com.then.atry.domain.Oauth;
import com.then.atry.domain.interactor.ehome.oauth.AccountLogin;

import io.reactivex.Observable;

/**
 * Created by then on 2017/2/7.
 */

public interface LoginRepository {

    Observable<Oauth> login(AccountLogin.LoginParams params);

}
