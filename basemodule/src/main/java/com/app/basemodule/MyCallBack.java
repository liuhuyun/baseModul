package com.app.basemodule;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;

/**
 * @author lhy 1083005240@qq.com
 * @time 2017/10/24 0024  下午 3:58
 * @explain
 */

public abstract class MyCallBack<T> extends StringCallback {


    private Class<T> mClass;

    public MyCallBack (Class<T> aClass) {
        mClass = aClass;
    }

    private static Gson create () {
        return new Gson ();
    }


    @Override
    public void onSuccess (Response<String> response) {

        if (response.isSuccessful ()) {
            try {
                T t = create ().fromJson (response.body (), mClass);
                onSuccess (t);
            } catch (Exception e) {
                Logger.e (e.getMessage ());
            }
        } else {
            Logger.e ("请求失败");
        }

    }


    @Override
    public void onError (Response<String> response) {
        super.onError (response);
        Logger.e ("请求失败");
    }

    protected abstract void onSuccess (T t);
}
