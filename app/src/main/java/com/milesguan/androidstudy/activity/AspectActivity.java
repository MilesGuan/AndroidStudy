package com.milesguan.androidstudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.milesguan.androidstudy.R;

/**
 * Created by renjieguan on 16/6/27.
 */
public class AspectActivity extends Activity {

    TextView btn_setText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);

        btn_setText = (TextView) findViewById(R.id.btn_setText);
        btn_setText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮，然后查看输出日志
                btn_setText.setText(btn_setText.getText() + "_n");
            }
        });
    }

    //如果不手动写onResume 不会被aspect获取
    @Override
    protected void onResume() {
        super.onResume();
    }


}
