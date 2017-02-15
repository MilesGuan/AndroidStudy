package com.milesguan.androidstudy.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by renjieguan on 17/2/15.
 */

public class RxBus {

    private final static Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public static void send(Object o) {
        _bus.onNext(o);
    }

    public static Observable<Object> toObserverable() {
        return _bus;
    }

}
