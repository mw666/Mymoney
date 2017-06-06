package com.tuofu.lenovo.myone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {


    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.gettext)
    EditText gettext;
    private ArrayList<model> list;
    private MainActivity.myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        indata();
        myadapter = new myadapter();
        lv.setAdapter(myadapter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updata();
                gettext.setText("");
            }
        });

    }

    private void updata() {
        String s = gettext.getText().toString();
        list.add(new model(R.drawable.xiaozao, 1, s));
        myadapter.notifyDataSetChanged();
    }

    private void indata() {
        list = new ArrayList<>();
        list.add(new model(R.drawable.xiaohua, 0, "你好啊！"));
        list.add(new model(R.drawable.xiaohua, 0, "你好丑啊！"));
        list.add(new model(R.drawable.xiaohua, 0, "好吧听说你是个彩笔！"));
        list.add(new model(R.drawable.xiaohua, 0, "你王者荣耀菜的抠脚！"));
        list.add(new model(R.drawable.xiaozao, 1, "你也好啊！"));
        list.add(new model(R.drawable.xiaozao, 1, "你他妈才丑啊！"));
        list.add(new model(R.drawable.xiaozao, 1, "我这么菜了！"));
    }

    private class myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            model model = list.get(position);
            int type = model.getType();
            return type;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            model model = list.get(position);
            int type = model.getType();
            if (type == 0) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.receiver, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.ivHua.setImageResource(model.getPic());
                holder.tvHua.setText(model.getContext());
            } else if (type == 1) {
                MyHolder myHolder;
                if (convertView == null) {
                    convertView = View.inflate(getApplicationContext(), R.layout.send, null);
                    myHolder = new MyHolder(convertView);
                    convertView.setTag(myHolder);
                } else {
                    myHolder = (MyHolder) convertView.getTag();
                }
                myHolder.ivZao.setImageResource(model.getPic());
                myHolder.tvZao.setText(model.getContext());
            }
            return convertView;
        }


    }

    static class MyHolder {
        @Bind(R.id.tv_zao)
        TextView tvZao;
        @Bind(R.id.iv_zao)
        ImageView ivZao;

        MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder {
        @Bind(R.id.iv_hua)
        ImageView ivHua;
        @Bind(R.id.tv_hua)
        TextView tvHua;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
