package com.milesguan.androidstudy.activity;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.milesguan.androidstudy.R;

/**
 *
 *
 *
 */
public class layoutTransitionActivity extends Activity {

    private LinearLayout parent;
    private View bottomView1;
    private View bottomView2;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transition);
        parent = (LinearLayout) findViewById(R.id.parent);
        bottomView1 = findViewById(R.id.bottomView1);
        bottomView2 = findViewById(R.id.bottomView2);
        setBottomLayoutTransition();
    }

    public void buttonClick(View view) {
        addButtonView();
    }

    public void buttonClick1(View view) {
        removeButtonView();
    }


    int j;

    //底部切换
    public void bottomShowClick(View view) {
        if (j % 3 == 0) {
            bottomView1.setVisibility(View.VISIBLE);
            bottomView2.setVisibility(View.GONE);
        } else if (j % 3 == 1) {
            bottomView1.setVisibility(View.GONE);
            bottomView2.setVisibility(View.VISIBLE);
        } else if (j % 3 == 2) {
            bottomView1.setVisibility(View.GONE);
            bottomView2.setVisibility(View.GONE);
        }
        j++;
    }

    private void addButtonView() {
        i++;
        Button button = new Button(this);
        button.setText("button" + i);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent.removeView(v);
            }
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        parent.addView(button, params);
    }

    private void removeButtonView() {
        if (parent.getChildCount() > 0)
            parent.removeViewAt(0);
    }


    private void setBottomLayoutTransition() {
        ViewGroup bottom = (ViewGroup) findViewById(R.id.bottom);

        LayoutTransition mTransitioner = bottom.getLayoutTransition();
        //view 动画改变时，布局中的每个子view动画的时间间隔
//        mTransitioner.setStagger(LayoutTransition.CHANGE_APPEARING, 30);
//        mTransitioner.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        setupCustomAnimations(mTransitioner);
        mTransitioner.setDuration(500);
    }

    //设置自定义动画的方法
    private void setupCustomAnimations(LayoutTransition mTransitioner) {
        /**
         * view出现时 view自身的动画效果
         */
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "translationY", 0, 200f).
                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "translationY", 0f, -200f).
                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);

//        PropertyValuesHolder pvhLeft =
//                PropertyValuesHolder.ofInt("left", 0, 1);
//        PropertyValuesHolder pvhTop =
//                PropertyValuesHolder.ofInt("top", 0, 1);
//        PropertyValuesHolder pvhRight =
//                PropertyValuesHolder.ofInt("right", 0, 1);
//        PropertyValuesHolder pvhBottom =
//                PropertyValuesHolder.ofInt("bottom", 0, 1);
//
//        /**
//         * view出现时，导致整个布局改变的动画
//         */
//        PropertyValuesHolder animator3 = PropertyValuesHolder.ofFloat("scaleX", 1F, 2F, 1F);
//        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, animator3).
//                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
//        changeIn.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                View view = (View) ((ObjectAnimator) animation).getTarget();
//                view.setScaleX(1.0f);
//            }
//        });
//
//        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
//        /**
//         * view消失，导致整个布局改变时的动画
//         */
//        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
//        Keyframe kf1 = Keyframe.ofFloat(.5f, 2f);
//        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
//        PropertyValuesHolder pvhRotation =
//                PropertyValuesHolder.ofKeyframe("scaleX", kf0, kf1, kf2);
//        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
//                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
//        changeOut.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                View view = (View) ((ObjectAnimator) animation).getTarget();
//                view.setScaleX(1.0f);
//            }
//        });
//        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
    }

//    private void setupCustomAnimations(LayoutTransition mTransitioner;) {
//        // Changing while Adding
//        PropertyValuesHolder pvhLeft =
//                PropertyValuesHolder.ofInt("left", 0, 1);
//        PropertyValuesHolder pvhTop =
//                PropertyValuesHolder.ofInt("top", 0, 1);
//        PropertyValuesHolder pvhRight =
//                PropertyValuesHolder.ofInt("right", 0, 1);
//        PropertyValuesHolder pvhBottom =
//                PropertyValuesHolder.ofInt("bottom", 0, 1);
//        PropertyValuesHolder pvhScaleX =
//                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
//        PropertyValuesHolder pvhScaleY =
//                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
//        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
//                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_APPEARING));
//        mTransitioner.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
//        changeIn.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setScaleX(1f);
//                view.setScaleY(1f);
//            }
//        });
//
//        // Changing while Removing
//        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
//        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
//        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
//        PropertyValuesHolder pvhRotation =
//                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
//        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
//                setDuration(mTransitioner.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
//        mTransitioner.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
//        changeOut.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setRotation(0f);
//            }
//        });
//
//        // Adding
//        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f).
//                setDuration(mTransitioner.getDuration(LayoutTransition.APPEARING));
//        mTransitioner.setAnimator(LayoutTransition.APPEARING, animIn);
//        animIn.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setRotationY(0f);
//            }
//        });
//
//        // Removing
//        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
//                setDuration(mTransitioner.getDuration(LayoutTransition.DISAPPEARING));
//        mTransitioner.setAnimator(LayoutTransition.DISAPPEARING, animOut);
//        animOut.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setRotationX(0f);
//            }
//        });
//    }
}
