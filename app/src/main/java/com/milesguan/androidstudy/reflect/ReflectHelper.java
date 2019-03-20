package com.milesguan.androidstudy.reflect;

import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017 BiliBili Inc.
 * Created by renjieguan on 17/5/27.
 */

public class ReflectHelper {


    private static ReflectHelper reflectHelper;

    public static ReflectHelper getInstance() {
        if (reflectHelper == null) {
            reflectHelper = new ReflectHelper();
        }
        return reflectHelper;
    }

    private Experiment experiment;

    public synchronized Experiment getExperiment() {
        if (experiment == null) {
            experiment = (Experiment) Proxy.newProxyInstance(Experiment.class.getClassLoader(),
                    new Class[]{Experiment.class}, new MyInvocationHandler(Experiment.class.getName()));
        }
        return experiment;
    }

    private class MyInvocationHandler implements InvocationHandler {

        String className;

        public MyInvocationHandler(String className) {
            this.className = className;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            Bundle bundle = new Bundle();
            bundle.putString("method_name", method.getName());
            bundle.putString("class_name", className);
            bundle.putInt("args_size", objects.length);
            ArrayList<String> className = new ArrayList<>();
            for (int i = 0; i < objects.length; i++) {
                String name = putBundle(bundle, objects[i], i);
                className.add(name);
            }
            bundle.putStringArrayList("args_class", className);
            try {
                Object handle = handle(bundle);
                return handle;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
//            message.setData(bundle);
//            getMessenger().send(message);
        }
    }


    private String putBundle(Bundle bundle, Object object, int i) {
        if (object instanceof Long) {
            bundle.putLong("arg" + i, (Long) object);
            return "long";
        } else if (object instanceof String) {
            bundle.putString("arg" + i, (String) object);
            return "string";
        } else if (object instanceof Integer) {
            bundle.putInt("arg" + i, (Integer) object);
            return "int";
        } else if (object instanceof Parcelable) {
            bundle.putParcelable("arg" + i, (Parcelable) object);
            return "Parcelable";
        } else if (object instanceof List) {
            ArrayList list = new ArrayList(((List) object).size());
            if (object instanceof ArrayList) {
                list = (ArrayList) object;
            } else {
                for (int j = 0; j < ((List) object).size(); j++) {
                    list.add(((List) object).get(j));
                }
            }
            if (list.size() == 0) {
                return null;
            } else {
                Object item = list.get(0);
                if (item instanceof Parcelable) {
                    bundle.putParcelableArrayList("arg" + i, (ArrayList<? extends Parcelable>) list);
                    return "list/parcelable";
                } else {
                    throw new RuntimeException("不支持的参数类型：" + object.getClass().getName());
                }
            }
        } else {
            throw new RuntimeException("不支持的参数类型：" + object.getClass().getName());
        }
    }


    public static Object handle(Bundle bundle) throws ClassNotFoundException {
        String className = bundle.getString("class_name") + "Imp";
        String methodName = bundle.getString("method_name");
        int argsSize = bundle.getInt("args_size");
        ArrayList<String> argsClassName = bundle.getStringArrayList("args_class");
        Object[] argObj = new Object[argsSize];
        Class[] argsClass = new Class[argsSize];

        for (int i = 0; i < argsSize; i++) {
            String key = "arg" + i;
            String typeName = argsClassName.get(i);
            if (typeName.equals("list/parcelable")) {
                argObj[i] =  bundle.getParcelableArrayList(key);
                argsClass[i] = List.class;
            } else if (typeName.equals("string")) {
                argObj[i] =  bundle.getString(key);
                argsClass[i] = String.class;
            }

        }

        try {
            Class<?> aClass = Class.forName(className);
            Method method = aClass.getMethod(methodName, argsClass);
            return method.invoke(null, argObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }


}
