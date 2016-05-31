package com.lance.myframe.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.xutils.x;

/**
 * Created by lance on 16/5/11.
 */
public class BasicActivity extends FragmentActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
