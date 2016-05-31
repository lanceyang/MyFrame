package com.lance.myframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lance.myframe.R;
import com.lance.myframe.common.BasicApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 16/5/13.
 */
public class CellRycAdapter extends RecyclerView.Adapter<CellRycAdapter.ViewHolder>{


    private List<String> dataList = new ArrayList<>();

    public CellRycAdapter() {


        for (int i = 0 ;i<10;i++){
            dataList.add(String.valueOf(i));
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(BasicApplication.getBasicApp()).inflate(R.layout.test_cell,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.test);
        }
    }
}
