package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * 多线程
 * Created by renjieguan on 16/12/27.
 */

public class ThreadTestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                
            }
        });

    }


    private void test1(){
        ThreadFactory myTest = new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
                        r.run();
                    }
                }, "myTest");
            }
        };
        Executors.newCachedThreadPool(myTest);
    }

}
