package com.doomdev.fybersampel.domain.interactor;


import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * This abstract class represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p/>
 * By convention each UseCase implementation will return the result using a {@link rx.Observer}
 * that will execute its job in a background thread and will post the result in the UI thread.
 * Created by and on 08.01.16.
 */
public abstract class UseCase {


    private Subscription subscription = Subscriptions.empty();
    private int id;
    private Scheduler newThreadScheduler;
    private Scheduler androidThread;

    public int getId() {
        return id;
    }

    public UseCase() {
        this.id = (int) System.currentTimeMillis();
        this.newThreadScheduler = Schedulers.newThread();
        this.androidThread = AndroidSchedulers.mainThread();
    }

    public UseCase(Scheduler newThreadScheduler,Scheduler androidThread) {
        this.id = (int) System.currentTimeMillis();
        this.newThreadScheduler = newThreadScheduler;
        this.androidThread = androidThread;
    }


    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseobserver The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     */
    @SuppressWarnings("unchecked")
    public void execute(Observer useCaseobserver) {
        if (useCaseobserver instanceof DefaultObserver) {
//            Log.d("UseCase","set secase ID: "+id);
            ((DefaultObserver) useCaseobserver).setUseCaseId(id);
        }
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(newThreadScheduler)
                .observeOn(androidThread)
                .subscribe(useCaseobserver);
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