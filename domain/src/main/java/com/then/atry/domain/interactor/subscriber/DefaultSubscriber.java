package com.then.atry.domain.interactor.subscriber;


public class DefaultSubscriber<T> extends rx.Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
//        e.printStackTrace();
    }

    @Override
    public void onNext(T t) {

    }

}
