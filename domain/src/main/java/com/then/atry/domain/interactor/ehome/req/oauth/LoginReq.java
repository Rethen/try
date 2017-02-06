package com.then.atry.domain.interactor.ehome.req.oauth;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.model.ehome.Oauth;


public class LoginReq extends BaseReq<Oauth> {

    @Expose
    private String loginName;

    @Expose
    private String pwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public TypeToken<Oauth> typeToken() {
        return new TypeToken<Oauth>() {};
    }

    @Override
    public String takeUrl() {
        return "ehome.ac.login.get";
    }
}
