package com.then.atry.data.action.login;

import android.util.Log;

import com.then.atry.data.pref.RequestPrefs;
import com.then.atry.domain.Oauth;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by then on 2017/2/9.
 */
public class LoginSavePrefsConsumer implements Consumer<Oauth> {

    private final RequestPrefs requestPrefs;

    @Inject
    LoginSavePrefsConsumer(RequestPrefs requestPrefs) {
        this.requestPrefs = requestPrefs;
    }

    @Override
    public void accept(Oauth oauth) throws Exception {
        Log.d("LoginSavePrefsConsumer", "accept:");
        requestPrefs.setAk(oauth.getAccessTokenKey());
        requestPrefs.setAt(oauth.getAccessToken());
    }
}
