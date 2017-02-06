package com.then.atry.domain.interactor.ehome.req.cpf;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.model.ehome.IconSort;

import java.util.List;

/**
 * Created by then on 2017/1/16.
 */

public class IconSortListGetReq extends BaseReq<List<IconSort>> {

    @Expose
    private  String orgId;

    @Expose
    private  String  module;


    @Override
    public String takeUrl() {
        return "ehome.org.icon.sort.list.get";
    }

    @Override
    public TypeToken<List<IconSort>> typeToken() {
        return new TypeToken<List<IconSort>>() {
        };
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
