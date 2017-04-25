package com.milesguan.androidstudy.aspect;

import android.util.Log;
import android.widget.TextView;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 参考 http://blog.csdn.net/innost/article/details/49387395
 * <p/>
 * Created by renjieguan on 16/6/27.
 */
@Aspect
public class MyAspect {

    public static final String TEST_CLASS = "com.milesguan.androidstudy.activity.AspectActivity";

    @Pointcut("execution(* *..AspectActivity.on*(..))")
    public void logForActivity2() {
    }

    //查看所有joinPoint
    @Pointcut("within(" + TEST_CLASS + ")") //单独定义Pointcut
    public void LogForAll() {
    }

    @Before("LogForAll()")
    public void log(JoinPoint joinPoint) {
        Log.v("aspect", joinPoint.toShortString());
    }

    //call类型
    @Before("call(* *..TextView.setText(..))")  //Pointcut可以直接写在这里
    public void log2(JoinPoint joinPoint) {
//        Log.i("aspect","log2:" +  joinPoint.toShortString());
    }

    //用AfterReturning获取call的返回值
    @AfterReturning(pointcut = "call(* *..StringBuilder.toString(..)) && within(" + TEST_CLASS + ")",
            returning = "result"
    )
    public void log3(JoinPoint joinPoint, String result) {
        Log.i("aspect", "log3:" + joinPoint.toShortString() + "      result:" + result);
    }

    //获取调用方法的类
    @After("call(* *..TextView.setText(..)) " +
            "&& target(textView)" +
            "&& within(" + TEST_CLASS + ")")
    public void hookSetText(JoinPoint joinPoint, TextView textView) {
        Log.i("aspect", "hookSetText:" + joinPoint.toShortString());
        textView.setText("hookSetText");
    }


}
