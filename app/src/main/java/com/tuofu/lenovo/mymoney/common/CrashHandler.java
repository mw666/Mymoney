package com.tuofu.lenovo.mymoney.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by lenovo on 2017/4/17.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler=new CrashHandler();
    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private CrashHandler() {
    }
 public  static CrashHandler getinstance(){
     return crashHandler;
 }
public  void inidata(Context context){
    this.context=context;
    defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
}
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context,"抱歉程序出现异常。。。。",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
        collectinfo(e);
        try {
            Thread.sleep(3000);
            AppManage.getinstance().clear();
            android.os.Process.killProcess(  android.os.Process.myPid());
            //关掉虚拟机，释放资源
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void collectinfo(Throwable e) {
        final  String device= Build.DEVICE+"--"+Build.BRAND+"---"+Build.MODEL+"---"+Build.VERSION.SDK_INT;
        final String message = e.getMessage();
        new Thread(new Runnable() {
        @Override
        public void run() {
            Log.e("meiwei",device+"/n"+message);
        }
    }).start();
    }

}
