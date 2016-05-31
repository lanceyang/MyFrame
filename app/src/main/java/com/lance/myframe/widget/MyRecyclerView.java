package com.lance.myframe.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Toast;

import com.lance.myframe.common.BasicApplication;

/**
 * Created by lance on 16/5/13.
 */
public class MyRecyclerView extends RecyclerView{


    float x_tmp1;

    private boolean isTouchAfter = false;

    private boolean isTouch =false;

    private boolean scrollDirection;//true:左滑

    private int scrollDistance;

    private int screenWidth;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenWidth =pxToDp(context.getResources().getDisplayMetrics().widthPixels);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }

    private int pxToDp(int pix){
        return Math.round(pix/getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Toast.makeText(BasicApplication.getBasicApp(),getChildLayoutPosition(getChildAt(0))+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return super.onTouchEvent(e);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        float x = ev.getX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                isTouchAfter = true;
                x_tmp1 = x;
                break;
            case MotionEvent.ACTION_UP:

                scrollDirection = x-x_tmp1<0?true:false;
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (isTouch){
            if (AbsListView.OnScrollListener.SCROLL_STATE_IDLE == state){
                isTouch =false;
                int position = ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
                Log.d("onScrollStateChanged",String.valueOf(position));

                int itemWidth=getChildAt(0).getWidth();
                int tmp =scrollDistance - (position+1)*itemWidth;

                if(scrollDirection){
                    smoothScrollToPosition(position+1);
//                    scrollTo((position+1)*screenWidth,0);
                    Toast.makeText(getContext(),position+"大于",Toast.LENGTH_SHORT).show();
                }else{
                    smoothScrollToPosition(position);
//                    scrollTo(position*screenWidth,0);
                    Toast.makeText(getContext(),position+"小于",Toast.LENGTH_SHORT).show();
                }


            }
        }else if(isTouchAfter){
//                smoothScrollBy(10,0);

            isTouchAfter = false;
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        Log.d("onScrolled-------", String.valueOf(scrollDistance+=dx));

    }
}
