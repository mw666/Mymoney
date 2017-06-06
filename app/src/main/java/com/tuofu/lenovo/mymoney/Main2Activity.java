package com.tuofu.lenovo.mymoney;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends Activity {

    private Spinner spinner;
    private List<Map<String, String>> data;

    private SimpleAdapter simpleAdapter;
    private TextView tv_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_meituan_demo);
        // 1:spinner控件对象
        // 2:创建一个ArrayAdapter适配器，将数据源绑定给适配器对象
        // 3:spinner.setAdapter()完成数据源通过控件展示

        spinner = (Spinner) findViewById(R.id.spinner);

        data = new ArrayList<Map<String, String>>();
        HashMap<String, String> map0 = new HashMap<String, String>();
        map0.put("name", "11");
        map0.put("number", "22");
        data.add(map0);

        data = new ArrayList<Map<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "全部");
        map1.put("number", "10317");
        data.add(map1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "甜点饮品");
        map2.put("number", "2905");
        data.add(map2);

        HashMap<String, String> map3 = new HashMap<String, String>();
        map3.put("name", "生日蛋糕");
        map3.put("number", "1683");
        data.add(map3);

        setSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * 啥都不选
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Main2Activity.this,"",Toast.LENGTH_SHORT).show();
            }

            /**
             * 选择了其中一个的时候
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String name = data.get(position).get("name");
                tv_spinner.setText(name);
            }
        });

        tv_spinner = (TextView) findViewById(R.id.tv_spinner);
        tv_spinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // // 完成一次控件的点击事件
                spinner.performClick();
            }
        });

        // ArrayAdapter<CharSequence> adapter =
        // ArrayAdapter.createFromResource(this, R.array.email_address_type,
        // android.R.layout.simple_spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner.setAdapter(adapter);
    }
    private void setSpinner() {
        simpleAdapter = new SimpleAdapter(Main2Activity.this, data, R.layout.spinner_meituan_item,
                new String[] { "name", "number" }, new int[] { R.id.tv_huoguotype, R.id.tv_huoguonumber });
        spinner.setAdapter(simpleAdapter);
    }
}
