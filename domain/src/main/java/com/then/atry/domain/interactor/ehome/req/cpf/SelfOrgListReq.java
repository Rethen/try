package com.then.atry.domain.interactor.ehome.req.cpf;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.model.ehome.Org;

import java.util.List;

/**
 * Created by then on 2017/1/17.
 */

public class SelfOrgListReq extends BaseReq<List<Org>> {

    @Expose
    private String sysId;

    @Override
    public String takeUrl() {
        return "ehome.osys.org.self.list.get";
    }

    @Override
    public TypeToken<List<Org>> typeToken() {
        return new TypeToken<List<Org>>() {
        };
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }
}
