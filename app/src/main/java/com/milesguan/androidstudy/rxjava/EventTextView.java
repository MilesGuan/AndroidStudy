package com.milesguan.androidstudy.rxjava;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import rx.functions.Action1;

/**
 * Created by renjieguan on 17/2/15.
 */

public class EventTextView extends TextView {

    public EventTextView(Context context) {
        super(context);
        init();
    }

    public EventTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        RxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof TestEvent) {
                    Toast.makeText(getContext(), "收到TestEvent" + ((TestEvent) o).getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
