package com.milesguan.androidstudy.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.milesguan.androidstudy.R;

/**
 * Created by renjieguan on 17/2/15.
 */

public class RxBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.send(new TestEvent("hahaha"));
            }
        });

    }




}
