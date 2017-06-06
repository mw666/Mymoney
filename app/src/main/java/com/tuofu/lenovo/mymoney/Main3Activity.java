package com.tuofu.lenovo.mymoney;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends Activity implements View.OnClickListener {


    @Bind(R.id.btt)
    Button btt;
    @Bind(R.id.activity_main2)
    LinearLayout activityMain2;
    private PopupWindow pop;
    private View inflate;
    private View goods;
    private View price;
    private View many;
    private View position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        btt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopwindows();
                 

            }
        });
    }

    private void showpopwindows() {
        inflate = getLayoutInflater().inflate(R.layout.activity_main3, null);
        goods = inflate.findViewById(R.id.goods);
        price = inflate.findViewById(R.id.price);
        many = inflate.findViewById(R.id.many);
        position = inflate.findViewById(R.id.position);
        pop = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
       //pop.showAsDropDown(btt);
        pop.showAsDropDown(btt,-50, btt.getHeight());
        //pop.showAtLocation(inflate,Gravity.TOP | Gravity.LEFT, 80, 80);
       // pop.setTouchable(true);
       pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(getDrawable());
        pop.update();
        
//      pop.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                Log.i("mengdd", "onTouch : ");
//                pop.dismiss();
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });这样点击外部区域无法dismiss
        goods.setOnClickListener(this);
        many.setOnClickListener(this);
        price.setOnClickListener(this);
        position.setOnClickListener(this);
    }
    private Drawable getDrawable(){
        ShapeDrawable bgdrawable =new ShapeDrawable(new OvalShape());
        bgdrawable.getPaint().setColor(Main3Activity.this.getResources().getColor(android.R.color.transparent));
        return   bgdrawable;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
// TODO Auto-generated method stub
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
        return super.onTouchEvent(event);
    }
    public void onclick(int view) {
        switch (view) {
            case R.id.goods:
                btt.setText("tt");
                pop.dismiss();
                break;
            case R.id.price:
            //    btt.setText(price1.getText());
                pop.dismiss();
                break;
            case R.id.many:
            //    btt.setText(many1.getText());
                pop.dismiss();
                break;
            case R.id.position:
            //    btt.setText(position1.getText());
                pop.dismiss();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods:
                 btt.setText("yyy");
                pop.dismiss();
                break;
            case R.id.price:
                //    btt.setText(price1.getText());
                pop.dismiss();
                break;
            case R.id.many:
                //    btt.setText(many1.getText());
                pop.dismiss();
                break;
            case R.id.position:
                //    btt.setText(position1.getText());
                pop.dismiss();
                break;
        }
    }
}
