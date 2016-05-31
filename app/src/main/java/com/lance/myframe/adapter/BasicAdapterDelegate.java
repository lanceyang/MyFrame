package com.lance.myframe.adapter;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.lance.myframe.adapter.AdapterDelegateCallBack;

/**
 * Created by lance on 16/5/12.
 */
public abstract class BasicAdapterDelegate implements AdapterDelegateCallBack {

    public static abstract class AdapterDelegateViewHolder{

        public View conterView;

        protected SparseArray<View> views;

        protected abstract void initView(ViewGroup vg);

        protected AdapterDelegateViewHolder(ViewGroup vg){
            initView(vg);
        }

        protected  <V extends View> V getView(int resId) throws NullPointerException{

            if (views.get(resId) != null ){
                return (V)views.get(resId);
            }

            View v= conterView.findViewById(resId);
            views.put(resId, v);

            return (V)v;
        }
    }
}
