package com.lance.myframe.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lance.myframe.R;


/**
 * Created by lance on 16/3/29.
 */
public class CollapsingToolbarLayoutActivity extends Activity{


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_collapsing_toolbar);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
    }
}
