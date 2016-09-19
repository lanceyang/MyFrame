package com.lance.myframe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.lance.myframe.R;
import com.lance.myframe.widget.TagLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TagAdapter implements TagLayout.TagLayoutAdapter<String> {

    private List<String> dataList  = new ArrayList<>(0);

    public TagAdapter() {
        dataList.add("看破红尘");
        dataList.add("度余生");
        dataList.add("潇洒");
        dataList.add("心机婊");
        dataList.add("欲与天公试比高");
        dataList.add("乐得自在");
        dataList.add("淡定");
        dataList.add("望穿秋水");
        dataList.add("回眸一笑");
        dataList.add("众里寻他千百度");
        dataList.add("噫吁戏");
        dataList.add("危乎高哉");
        dataList.add("乐此不疲");
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tag,parent,false);
        CheckBox textView = (CheckBox) v.findViewById(R.id.tv);
        textView.setText(getItem(position));
        return v;
    }

    @Override
    public String getItem(int position) {
        return dataList.get(position);
    }
}
