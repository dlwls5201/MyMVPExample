package com.tistory.black_jin0427.myandroidarchitecture.rxeventbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEventBus {

    private static RxEventBus instance;
    private PublishSubject<Object> mSubject;

    private RxEventBus() {
        mSubject = PublishSubject.create();
    }

    public static RxEventBus getInstance() {
        if(instance == null) instance = new RxEventBus();
        return instance;
    }

    public void sendEvent(Object object) { mSubject.onNext(object); }

    public Observable<Object> getObservable() { return mSubject; }

}
