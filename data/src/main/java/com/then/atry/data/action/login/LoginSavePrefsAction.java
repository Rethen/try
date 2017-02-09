package com.then.atry.data.action.login;

import com.then.atry.data.pref.RequestPrefs;
import com.then.atry.domain.Oauth;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.functions.Consumer;

/**
 * Created by then on 2017/2/9.
 */
@Singleton
public class LoginSavePrefsAction implements Consumer<Oauth> {

    private final RequestPrefs requestPrefs;

    @Inject
    LoginSavePrefsAction(RequestPrefs requestPrefs) {
        this.requestPrefs = requestPrefs;
    }

    @Override
    public void accept(Oauth oauth) throws Exception {
        requestPrefs.setAk(oauth.getAccessTokenKey());
        requestPrefs.setAt(oauth.getAccessToken());
    }
}
