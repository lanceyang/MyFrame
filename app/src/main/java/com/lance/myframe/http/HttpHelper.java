package com.lance.myframe.http;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by lance on 16/5/11.
 */
public class HttpHelper {


    public static Callback.Cancelable post(RequestParams params,Callback.CommonCallback callback){
        return x.http().post(params,callback);
    }

    public static Callback.Cancelable get(RequestParams params,Callback.CommonCallback callback){
        return x.http().get(params,callback);
    }


}
