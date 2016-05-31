package com.lance.myframe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.lance.myframe.R;
import com.lance.myframe.adapter.MyRecyclerViewAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by lance on 16/5/13.
 */

public class TestActivity extends Activity{

    private RecyclerView rycView;

    private boolean bool = false;

    private FrameLayout vgBox ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    private void testRecyclerView(){
        rycView = (RecyclerView) findViewById(R.id.rycView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        rycView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rycView.setAdapter(adapter);

//        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TestActivity.this,CollapsingToolbarLayoutActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private void tt(){
    float x = rycView.getWidth()*0.1f;
    float y = rycView.getHeight()*0.25f;
    if (bool){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator= ObjectAnimator.ofFloat(rycView, "scaleX", 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(rycView, "scaleY", 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(rycView, "translationX",1f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(rycView, "translationY",1f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rycView.setPadding(0,0,0,0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.setDuration(1000);
        animatorSet.playTogether(animator, animator2, animator3, animator4);
        animatorSet.start();
        bool = false;
    }else{
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator= ObjectAnimator.ofFloat(rycView, "scaleX", 1f, 1.2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(rycView, "scaleY", 1f, 1.5f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(rycView, "translationX", 1f, x);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(rycView, "translationY", 1f, y);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rycView.setPadding(0, 0, (int) (rycView.getWidth() * 0.2f), 0);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.setDuration(1000);
        animatorSet.playTogether(animator, animator2, animator3, animator4);
        animatorSet.start();
//                    animate(rycView).scaleX(1.2f).scaleY(1.5f).translationX(x).translationY(y).start()。se;
        bool=true;
        rycView.smoothScrollBy(rycView.getChildAt(rycView.getChildCount()-1).getRight(),0);
    }
    }

}
