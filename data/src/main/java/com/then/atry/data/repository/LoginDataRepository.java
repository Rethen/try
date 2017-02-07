package com.then.atry.data.repository;

import com.then.atry.data.EhomeCommonRemoteRepository;
import com.then.atry.data.net.HttpApiManager;
import com.then.atry.data.net.helper.HttpHelper;
import com.then.atry.domain.interactor.ehome.oauth.AccountLogin;
import com.then.atry.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by then on 2017/2/7.
 */

public class LoginDataRepository implements LoginRepository {


    private final HttpApiManager httpApiManager;

    private final HttpHelper httpHelper;

    @Inject
    LoginDataRepository(HttpHelper httpHelper, HttpApiManager httpApiManager) {
        this.httpApiManager = httpApiManager;
        this.httpHelper = httpHelper;
    }

    @Override
    public Observable login(AccountLogin.Params params) {
        String header= httpHelper.createHttpHeader("ehome.ac.login.get",params);
        RequestBody requestBody=httpHelper.createRequestBody(params);
        return httpApiManager.getOauthService(EhomeCommonRemoteRepository.class).req(header, requestBody);
    }
}
