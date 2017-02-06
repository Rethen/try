package com.then.atry.domain.interactor.ehome.req.chain.serial;

import com.then.atry.domain.interactor.UseCase;
import com.then.atry.domain.interactor.ehome.req.BaseReq;

/**
 * @类名: BaseSerialChainReq
 * @描述: 串行链式请求
 * @作者 then
 * @创建日期 2017/1/17 8:58
 */
public class BaseSerialChainReq {

    private UseCase useCase;

    private BaseReq baseReq;

    public UseCase getUseCases() {
        return useCase;
    }

    public BaseReq getBaseReq() {
        return baseReq;
    }


}
