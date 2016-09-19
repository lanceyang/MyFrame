package com.lance.myframe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lance.myframe.R;
import com.lance.myframe.adapter.TagAdapter;
import com.lance.myframe.widget.TagLayout;

public class TagActivity extends AppCompatActivity {

    private TagLayout tagLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        tagLayout = (TagLayout) findViewById(R.id.test);
        tagLayout.setAdapter(new TagAdapter());
    }
}