package com.tuofu.lenovo.apptwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @Bind(R.id.gr)
    GridView gr;
    @Bind(R.id.activity_main2)
    LinearLayout activityMain2;
    private int[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        list = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher};
        gr.setAdapter(new myadapter());
    }

    private class myadapter extends BaseAdapter {

        private ViewHolder viewHolder;

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int position) {
            return list[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            
            if (convertView == null) {
               
                convertView = getLayoutInflater().inflate(R.layout.layouttwo, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
             viewHolder = ((ViewHolder) convertView.getTag());
                viewHolder.iv.setImageResource(list[position]);
            }
            return convertView;
        }

       
    }
    static class ViewHolder {
        @Bind(R.id.iv)
        ImageView iv;
        @Bind(R.id.tv2)
        TextView tv2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
