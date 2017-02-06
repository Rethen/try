package com.then.atry.domain.interactor;

import com.google.gson.Gson;
import com.then.atry.domain.RequestFilter;
import com.then.atry.domain.executor.PostExecutionThread;
import com.then.atry.domain.executor.ThreadExecutor;
import com.then.atry.domain.interactor.ehome.req.BaseReq;
import com.then.atry.domain.interactor.subscriber.EhomeDefaultSubscriber;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    protected RequestFilter requestFilter;

    private Gson gson;


    protected UseCase(ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread, RequestFilter requestFilter) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.requestFilter = requestFilter;
        gson = new Gson();
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    protected abstract Observable buildUseCaseObservable(String header, RequestBody content);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     *                          with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    @SuppressWarnings("unchecked")
    public void execute(BaseReq req, Subscriber useCaseSubscriber, Action1... action1s) {
        Observable observable = execute(req, action1s);
        this.subscription = observable.subscribe(useCaseSubscriber);
    }

    @SuppressWarnings("unchecked")
    public Observable execute(BaseReq req, Action1... action1s) {
        requestFilter.filter(req);
        String header = req.getRequestHeader();
        RequestBody content = req.getRequestBody();
        Observable observable = this.buildUseCaseObservable(header, content)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .map(new EhomeDefaultSubscriber(req, gson));
        for (Action1 action1 : action1s) {
            observable = observable.doOnNext(action1);
        }
        return observable;
    }

    /**
     * Unsubscribes from current {@link rx.Subscription}.
     */
    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
