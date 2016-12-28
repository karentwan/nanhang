package cn.karent.nanhang.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by wan on 2016/12/17.
 * 获取全局Context
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
