package com.then.atry.data;

import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.interactor.ehome.req.BaseReq;

import okhttp3.RequestBody;

/**
 * Created by then on 2016/12/30.
 */

public class EhomeRequestFilter implements RequestFilter {

    private EhomeReqHelper ehomeReqHelper;

    public EhomeRequestFilter(EhomeReqHelper ehomeReqHelper) {
        this.ehomeReqHelper = ehomeReqHelper;
    }

    public void filter(BaseReq baseReq) {
        String header = ehomeReqHelper.createHeader(baseReq.takeUrl(), baseReq);
        RequestBody requestBody = ehomeReqHelper.createRequestBody(baseReq);
        baseReq.setRequestBody(requestBody);
        baseReq.setRequestHeader(header);
    }
}
