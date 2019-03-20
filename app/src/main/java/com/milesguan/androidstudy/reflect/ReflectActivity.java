package com.milesguan.androidstudy.reflect;

import android.app.Activity;
import android.os.Bundle;

import com.milesguan.androidstudy.R;

/**
 * Copyright (c) 2017 BiliBili Inc.
 * Created by renjieguan on 17/5/27.
 */

public class ReflectActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        ReflectHelper.getInstance().getExperiment().test1("aaa");
    }
}
