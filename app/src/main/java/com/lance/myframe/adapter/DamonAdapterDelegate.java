package com.lance.myframe.adapter;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lance.myframe.R;
import com.lance.myframe.common.BasicApplication;
import com.lance.myframe.model.DemonModel;

/**
 * Created by lance on 16/5/12.
 */
class DamonAdapterDelegate implements AdapterDelegateCallBack<DemonModel,DamonAdapterDelegate.DemonViewHolder> {

    @Override
    public boolean isForViewType(@NonNull DemonModel items) {
        return false;
    }

    @Override
    public DemonViewHolder onCreateHolder(ViewGroup vg) {
        return DemonViewHolder.newInstance(vg);
    }

    @Override
    public void onBindHolder(@NonNull DemonModel items, int position, @NonNull DemonViewHolder holder) {

    }

    public static class DemonViewHolder extends BasicAdapterDelegate.AdapterDelegateViewHolder{

        public TextView tv;

        private static DemonViewHolder mViewHolder;

        private DemonViewHolder(ViewGroup vg) {
            super(vg);
        }

        @Override
        protected void initView(ViewGroup vg){

            views = new SparseArray<>();
            conterView = LayoutInflater.from(BasicApplication.getBasicApp()).inflate(R.layout.test_cell,vg,false);
            conterView.setTag(this);

            tv=(TextView)getView(R.id.test);

        }

        protected static DemonViewHolder newInstance(ViewGroup vg) {
            if (vg==null){
                return new DemonViewHolder(vg);
            }else{
                return  (DemonViewHolder) vg.getTag();
            }
        }
    }
}
