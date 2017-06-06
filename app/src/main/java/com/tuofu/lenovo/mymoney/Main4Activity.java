package com.tuofu.lenovo.mymoney;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main4Activity extends ListActivity {
    private static final int REQUEST_CAMERA_CODE = 2;
    @Bind(R.id.activity_main4)
    RelativeLayout activityMain4;
    @Bind(android.R.id.list)
    ListView list;

    private ArrayList<String> mData = new ArrayList<String>() {
        {
            for (int i = 0; i < 50; i++) {
                add("hello world, hello android  " + i);
            }
        }
    };
    private ArrayList<String> strings;
    private float downx;
    private float upx;
    private Main4Activity.myqqlist myqqlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
        PhotoPickerIntent intent = new PhotoPickerIntent(Main4Activity.this);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCarema(true); // 是否显示拍照， 默认false
        intent.setMaxTotal(9); // 最多选择照片数量，默认为9
        // 已选中的照片地址， 用于回显选中状态
// intent.setImageConfig(config);
//        startActivityForResult(intent, REQUEST_CAMERA_CODE);
//        Intent intent = new Intent(, PhotoPickerActivity.class);
//        intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, showCamera);
//        intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, selectedMode);
//        intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, maxNum);
//        startActivityForResult(intent, PICK_PHOTO);
        startActivityForResult(intent, REQUEST_CAMERA_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CAMERA_CODE) {

            strings = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
            Log.e("meiwei", strings.size() + "");
            myqqlist = new myqqlist();
            list.setAdapter(myqqlist);
            list.setOnTouchListener(new View.OnTouchListener(){
                float x, y, upx, upy;
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        x = event.getX();
                        y = event.getY();
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        upx = event.getX();
                        upy = event.getY();
                        int position1 = ((ListView) view).pointToPosition((int) x, (int) y);
                        int position2 = ((ListView) view).pointToPosition((int) upx,(int) upy);

                        if (position1 == position2 && x-upx > 10) {
                            View v = ((ListView) view).getChildAt(position1);
                            removeListItem(v,position1);
                            
                        }
                    }
                    return false;
                }

            });
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    removeListItem(view,position);
                }
            });
//            qqlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if (qqlist.canClick()) {
//                        Toast.makeText(Main4Activity.this, mData.get(position), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }


    private class myqqlist extends BaseAdapter {
        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // String s = mData.get(position);
            String s = strings.get(position);
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item, null);
            }
            ImageView lv = (ImageView) convertView.findViewById(R.id.lv_one);
            Bitmap bitmap = BitmapFactory.decodeFile(s);
            lv.setImageBitmap(bitmap);
//            final View finalConvertView = convertView;
//            convertView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//                            downx = event.getX();
//                        case MotionEvent.ACTION_UP:
//                            upx = event.getX();
//                        case MotionEvent.ACTION_MOVE:
//                            removeListItem(finalConvertView,position);
//                    }
//                    return true;
//                }
//            });
          
            //TextView tv = (TextView) convertView.findViewById(R.id.tv);
            // TextView delete = (TextView) convertView.findViewById(R.id.delete);
            //TextView atop = (TextView) convertView.findViewById(R.id.atop);

            //  tv.setText(s);   
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mData.remove(position);
//                    notifyDataSetChanged();
//                    qqlist.turntoNormal();
//                }
//            });
            return convertView;
        }

       
    }
    public void removeListItem(View view, final int positon) {
        TranslateAnimation animation = new TranslateAnimation(0, -100, 0, 0);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                strings.remove(positon);
                myqqlist.notifyDataSetChanged();
                animation.cancel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }
}