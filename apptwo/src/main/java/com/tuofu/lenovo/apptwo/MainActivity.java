package com.tuofu.lenovo.apptwo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutone);
        ButterKnife.bind(this);
        
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        View inflate = getLayoutInflater().inflate(R.layout.activity_main, null);
        PopupWindow popupWindow=new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(bt,-50,0);
        LinearLayout id = (LinearLayout) inflate.findViewById(R.id.lt);
        final TextView textView = (TextView) inflate.findViewById(R.id.tx);
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("你好");
            }
        });
//        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        View inflate = getLayoutInflater().inflate(R.layout.activity_main, null);
//        builder.setView(inflate);
//        builder.create().show();
    }
});
    }
}
