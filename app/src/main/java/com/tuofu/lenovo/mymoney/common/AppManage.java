package com.tuofu.lenovo.mymoney.common;

import android.app.Activity;

import java.util.IdentityHashMap;
import java.util.Stack;

/**
 * Created by lenovo on 2017/4/16.
 */

public class AppManage {
    static AppManage appManage = null;

    private AppManage() {
    }

    public static AppManage getinstance() {
        if (appManage == null) {
            appManage = new AppManage();
        }
        return appManage;
    }

    private Stack<Activity> stack = new Stack<>();

    public void addactivity(Activity activity) {
        stack.add(activity);
    }

    public void removecurrent() {
        Activity activity = stack.lastElement();
        activity.finish();
        stack.remove(activity);
    }

    public void removeactivity(Activity activity) {
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (stack.get(i).getClass().equals(activity.getClass())) {
                activity.finish();
                stack.remove(i);
                break;
            }
        }
    }

    public void clear() {
        for (int i = stack.size() - 1; i >= 0; i--) {
            Activity activity = stack.get(i);
            activity.finish();
        }
        stack.clear();
    }
}