package com.lance.myframe.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lance.myframe.R;
import com.lance.myframe.common.BasicApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 16/5/13.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{


    private List<String> dataList=new ArrayList<>();
    public MyRecyclerViewAdapter() {

        for (int i = 0 ;i<15;i++){
            dataList.add(String.valueOf(i));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        if (parent!=null){
//            return (ViewHolder)parent.getTag();
//        }

        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(BasicApplication.getBasicApp()).inflate(R.layout.my_cell,parent,false));
//        parent.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CellRycAdapter adapter = new CellRycAdapter();
        holder.rycView.setLayoutManager(new LinearLayoutManager(BasicApplication.getBasicApp(),LinearLayoutManager.VERTICAL,false));
        holder.rycView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rycView;

        public ViewHolder(View itemView) {
            super(itemView);

            rycView = (RecyclerView)itemView.findViewById(R.id.cell_ryc);
        }
    }
}
