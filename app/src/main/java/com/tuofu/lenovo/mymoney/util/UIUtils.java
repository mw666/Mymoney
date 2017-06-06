package com.tuofu.lenovo.mymoney.util;

/**
 * Created by lenovo on 2017/4/16.
 */

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.tuofu.lenovo.mymoney.common.MyApplication;

import java.util.zip.Inflater;

/**
 * 工具栏写静态方法提高代码效率
 */
public class UIUtils {
    public static int dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return Math.round(density*dp);
    }
      public  static  int px2dp(int dx){
          float density = getContext().getResources().getDisplayMetrics().density;
          return Math.round(dx/density);
      }
    //id得到xml文件
    public static View getview(int res){
        View inflate = View.inflate(getContext(), res, null);
        return  inflate;
    }
    // id得到颜色
    public  static int getcolor(int res){
        int color = getContext().getResources().getColor(res);
        return color;
    }
    //id 得到字符串数组
    public static String [] getstring(int res){
        String[] array = getContext().getResources().getStringArray(res);
        return array;
    }
    //保证任务在主线程里面进行
    public  void runonmainthread(Runnable runnable){
        int i = android.os.Process.myTid();
        if(i==MyApplication.mainthreadId){
            runnable.run();
        }else{
            gethandler().post(runnable);
        }
        
    }
    //获取
    private static Context getContext() {
        return MyApplication.context;
    }
    public  static Handler gethandler(){
        return MyApplication.handler;
    }
}
