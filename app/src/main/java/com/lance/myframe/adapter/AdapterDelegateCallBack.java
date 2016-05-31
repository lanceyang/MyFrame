package com.lance.myframe.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * Created by lance on 16/5/12.
 */
public interface AdapterDelegateCallBack<T,V extends BasicAdapterDelegate.AdapterDelegateViewHolder> {

     boolean isForViewType(@NonNull T items);

     V onCreateHolder(ViewGroup vg);

     void onBindHolder(@NonNull T items, int position,@NonNull V holder);

}
