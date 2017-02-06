package com.then.atry.domain;

import com.then.atry.domain.interactor.ehome.req.BaseReq;

/**
 * Created by then on 2017/1/16.
 */

public interface RequestFilter {

    void filter(BaseReq baseReq);
}
