package com.then.atry.domain.interactor.ehome.req.cpf;

import com.google.gson.reflect.TypeToken;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.model.ehome.Sys;

import java.util.List;

/**
 * Created by then on 2017/1/16.
 */

public class SysListReq extends BaseReq<List<Sys>> {

    @Override
    public String takeUrl() {
        return "ehome.osys.self.list.get";
    }

    @Override
    public TypeToken<List<Sys>> typeToken() {
        return new TypeToken<List<Sys>>() {};
    }

}
