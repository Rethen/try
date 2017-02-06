package com.then.atry.data.process.action;

import com.then.atry.data.preferences.IPreferencesPrefs;
import com.then.atry.domain.model.ehome.Oauth;

import rx.functions.Action1;

/**
 * Created by then on 2017/1/16.
 */

public class OauthInfoSaveAction implements Action1<Oauth> {


    private IPreferencesPrefs preferencesPrefs;


    public OauthInfoSaveAction(IPreferencesPrefs preferencesPrefs) {
        this.preferencesPrefs = preferencesPrefs;
    }

    @Override
    public void call(Oauth oauth) {
        preferencesPrefs.setAk(oauth.getAccessTokenKey());
        preferencesPrefs.setAt(oauth.getAccessToken());
    }
}
