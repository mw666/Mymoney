package com.tuofu.lenovo.mymoney.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by lenovo on 2017/5/4.
 */

public class MyList extends ListView {

    boolean isDeleteshown;
    private int widthscreen;
    private int downx;
    private int downy;
    private ViewGroup pointchild;
    private LinearLayout.LayoutParams layoutParams;
    private int deletewidth;
    private int topwidth;
    private int allshow;

    public MyList(Context context) {
        super(context);
    }

    public MyList(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     WindowManager service = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics ms = new DisplayMetrics();
        service.getDefaultDisplay().getMetrics(ms);
        widthscreen = ms.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                performdown(ev);
                break;
            case MotionEvent.ACTION_MOVE:
               return performove(ev);
            case MotionEvent.ACTION_UP:
                performup(ev);
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void performup(MotionEvent ev) {
        if(-layoutParams.leftMargin>allshow/2){
            layoutParams.leftMargin=-allshow;
            isDeleteshown=true;
        }else {
            turntoNormal();
        }
        pointchild.getChildAt(0).setLayoutParams(layoutParams);
    }

    public boolean canClick() {
        return !isDeleteshown;
    }  
    
    
    private boolean performove(MotionEvent ev) {
        int nowx = (int) ev.getX();
        int nowy = (int) ev.getY();
        if(Math.abs(nowx-downx)>Math.abs(nowy-downy)){
            if(nowx<downx){
                int offset = (downx - nowx)/2;
                if(offset>=allshow){
                    offset=allshow;
                }
                layoutParams.leftMargin=-offset;
                pointchild.getChildAt(0).setLayoutParams(layoutParams);
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }

    public void performdown(MotionEvent ev) {
      
        if(isDeleteshown){
            turntoNormal();
        }
        downx = (int) ev.getX();
        downy = (int) ev.getY();
        pointchild = (ViewGroup) getChildAt(pointToPosition(downx, downy) - getFirstVisiblePosition());
        layoutParams =(LinearLayout.LayoutParams)pointchild.getChildAt(0).getLayoutParams();
        deletewidth = pointchild.getChildAt(1).getLayoutParams().width;
        topwidth = pointchild.getChildAt(2).getLayoutParams().width;
        allshow = deletewidth + topwidth;
        layoutParams.width=widthscreen;
        pointchild.getChildAt(0).setLayoutParams(layoutParams);
    }

    public void turntoNormal() {
        layoutParams.leftMargin=0;
        pointchild.getChildAt(0).setLayoutParams(layoutParams);
        isDeleteshown=false;
    }
}
