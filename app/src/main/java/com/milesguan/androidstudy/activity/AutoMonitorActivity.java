package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.milesguan.androidstudy.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 自动埋点测试
 * <p/>
 * Created by renjieguan on 16/6/12.
 */
public class AutoMonitorActivity extends Activity {

    private List<String> pathList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_monitor);
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            pathList = new LinkedList<>();
            View view = searchClickView(getWindow().getDecorView(), event);
            StringBuilder path = new StringBuilder();
            for (String str : pathList) {
                path.append("/").append(str);
            }
            path.append("/").append(getViewName(view));
            Log.i("grj", path.toString());
            Toast.makeText(this, path.toString() , Toast.LENGTH_LONG).show();
        }
        return super.dispatchTouchEvent(event);
    }


    public boolean isInView(View view, MotionEvent event) {
        float clickX = event.getRawX();
        float clickY = event.getRawY();
        //如下的view表示Activity中的子View或者控件
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int width = view.getWidth();
        int height = view.getHeight();
        if (clickX < x || clickX > (x + width) ||
                clickY < y || clickY > (y + height)) {
            return false;
        } else {
            return true;
        }
    }


    private View searchClickView(View view, MotionEvent event) {
        View clickView = null;
        if (isInView(view, event) &&
                view.getVisibility() == View.VISIBLE) {  //这里一定要判断View是可见的
            if (view instanceof ViewGroup) {    //遇到一些Layout之类的ViewGroup，继续遍历它下面的子View
                ViewGroup group = (ViewGroup) view;
                pathList.add(getViewName(group));
                for (int i = 0; i < group.getChildCount(); i++) {
                    View childView = group.getChildAt(i);
                    clickView = searchClickView(childView, event);
                    if (clickView != null) {
                        return clickView;
                    }
                }
                pathList.remove(getViewName(group));
            }
            clickView = view;
        }
        return clickView;
    }


    public String getViewName(View view) {
        return view.getClass().getSimpleName() + "_" + (view.getId() == -1 ? "" : getResources().getResourceEntryName(view.getId()));
    }
}


