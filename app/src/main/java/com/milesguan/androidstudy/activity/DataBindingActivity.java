package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.milesguan.androidstudy.R;
import com.milesguan.androidstudy.databinding.ActivityDataBindingBinding;
import com.milesguan.androidstudy.entity.School;
import com.milesguan.androidstudy.entity.User;

/**
 * 数据绑定 官方Guide见 http://developer.android.com/tools/data-binding/guide.html
 */
public class DataBindingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        User user1 = new User("Miles");
        user1.setSchool(new School("ECUST"));
        //同一class不同object
        activityDataBindingBinding.setUser1(user1);
        activityDataBindingBinding.setUser2(new User("Peter"));
        //设置点击事件
        activityDataBindingBinding.setHandlers(this);
    }

    /**
     * 处理点击事件的方法
     * 注意参数必须是View
     */
    public void onClickChangeData(View view) {
        Toast.makeText(this, "onChangeDataClick", Toast.LENGTH_SHORT).show();
        Log.i("grj" , "1111");
    }

}
