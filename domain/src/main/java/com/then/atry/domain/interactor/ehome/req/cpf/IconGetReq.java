package com.then.atry.domain.interactor.ehome.req.cpf;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.model.ehome.Icon;

/**
 * Created by then on 2016/10/13.
 */

public class IconGetReq extends BaseReq<Icon> {

    @Expose
    private String orgId;

    @Expose
    private String iconId;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    @Override
    public String takeUrl() {
        return "ehome.org.icon.get";
    }

    @Override
    public TypeToken<Icon> typeToken() {
        return new TypeToken<Icon>() {
        };
    }
}
