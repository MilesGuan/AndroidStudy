package com.milesguan.androidstudy.reflect;

import android.util.Log;

/**
 * Copyright (c) 2017 BiliBili Inc.
 * Created by renjieguan on 17/5/27.
 */

public class ExperimentImp implements Experiment {
    @Override
    public void test1(String name) {
        Log.i("grj", "test1:" + name);
    }
}
