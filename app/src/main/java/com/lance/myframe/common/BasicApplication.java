package com.lance.myframe.common;

import android.app.Application;

import com.lance.myframe.BuildConfig;

import org.xutils.x;

/**
 * Created by lance on 16/5/11.
 */
public class BasicApplication extends Application{

    private static BasicApplication context;


    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    public static BasicApplication getBasicApp(){
        return context;
    }


}
