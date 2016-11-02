package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.milesguan.androidstudy.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by renjieguan on 16/9/27.
 */

public class RxJavaActivity extends Activity {

    private static final String TAG = "RxJavaActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        textView = (TextView) findViewById(R.id.textView);
        init2();
    }


    /**
     * 基本api
     * 在 RxJava 的默认规则中，事件的发出和消费都是在同一个线程的。实现出来的只是一个同步的观察者模式。
     */
    private void init1() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "Item: " + s);
            }

            /**
             * 当不会再有新的 onNext() 发出时，需要触发 onCompleted() 方法作为标志
             */
            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed!");
            }

            /**
             * 事件队列异常。在事件处理过程中出异常时，onError() 会被触发，同时队列自动终止，不允许再有事件发出。
             * @param e
             */
            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }
        };


        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);
    }


    //多线程
    private void init2() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //这个方法执行在io线程
                subscriber.onNext("1");
                Log.d(TAG, "subscriber:" + Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.io()) //指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程
                .observeOn(AndroidSchedulers.mainThread()) // observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String number) {
                        //执行在UI线程
                        Log.d(TAG, "subscribe:" + Thread.currentThread().getId() + "  " + Thread.currentThread().getName());
                        Log.d(TAG, "number:" + number);
                    }
                });
    }


}
