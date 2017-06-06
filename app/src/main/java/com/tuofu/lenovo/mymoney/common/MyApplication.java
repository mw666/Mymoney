package com.tuofu.lenovo.mymoney.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by lenovo on 2017/4/16.
 */

public class MyApplication extends Application {
    public  static Context context;
    public  static Handler handler;
    public  static  Thread mainthread;
    public  static   int  mainthreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        handler=new Handler();
        mainthread=Thread.currentThread();
        mainthreadId=android.os.Process.myTid();
       // CrashHandler.getinstance().inidata(context);
    }
}
