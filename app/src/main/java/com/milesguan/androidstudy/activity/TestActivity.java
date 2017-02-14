package com.milesguan.androidstudy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.protobuf.InvalidProtocolBufferException;
import com.milesguan.androidstudy.R;
import com.milesguan.androidstudy.proto.AddressBookProtos;
import com.milesguan.androidstudy.proto.ProtoDemo;

/**
 * Created by renjieguan on 16/11/16.
 */

public class TestActivity extends Activity {

    View viewById;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_test);

        viewById = findViewById(R.id.test);

//        viewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//            }
//        });
//        viewById.setBackground(getDrawable(0x000000 , 0xffffff));
        GradientDrawable mDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0x44000000,
                0x00000000, 0x00000000});
        mDrawable.setShape(GradientDrawable.RECTANGLE);
        viewById.setBackground(mDrawable);


        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] bytes = ProtoDemo.serializeTest();

                try {
                    AddressBookProtos.Person person = AddressBookProtos.Person.parseFrom(bytes);
                    Log.i("grj", person.toString());

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    private PaintDrawable getDrawable(final int startColor, final int endColor) {
        PaintDrawable paintDrawable = new PaintDrawable();
        paintDrawable.setShape(new RectShape());
        paintDrawable.setShaderFactory(new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                return new LinearGradient(
                        0, 0, 0, 4000,
                        new int[]{startColor, endColor}, null, Shader.TileMode.CLAMP);
            }
        });
        return paintDrawable;
    }

}
