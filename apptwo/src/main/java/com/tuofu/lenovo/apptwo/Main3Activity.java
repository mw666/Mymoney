package com.tuofu.lenovo.apptwo;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.tuofu.lenovo.apptwo.Util.SelectPicPopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @Bind(R.id.activity_main3)
    LinearLayout activityMain3;
    @Bind(R.id.gv)
    GridView gv;
    private SelectPicPopupWindow popupWindow;
    private File outputimage;
    private Uri imageUri;
    private Bitmap takephoto;
    private String imagepath;
    private ImageView imageView;
    private Myadpter myadpter;
    private ArrayList<Bitmap> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        list = new ArrayList<>();

        myadpter = new Myadpter(list);
        gv.setAdapter(myadpter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==parent.getCount()-1){
                            popupWindow = new SelectPicPopupWindow(Main3Activity.this, clickListener);
                            popupWindow.showAtLocation(activityMain3, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
            }
        });
       
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    takephote();
                    list.add(takephoto);
                    Myadpter myadpter2 = new Myadpter(list);
                    gv.setAdapter(myadpter2);
                   myadpter2.notifyDataSetChanged();
                    break;
                case R.id.btn_pick_photo:
                    getphoto();
                    Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
                    list.add(bitmap);
                    Myadpter myadpter1 = new Myadpter(list);
                    gv.setAdapter(myadpter1);
                    myadpter1.notifyDataSetChanged();
                    break;

            }
        }
    };

    private void getphoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);

    }

    private void handlerbeforekitkat(Intent data) {
        Uri data1 = data.getData();
        imagepath = getimage(data1, null);
        //  displayimage(imagepath);
    }

//    private void displayimage(String path) {
//        if (path!=null){
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
//
//            iv.setImageBitmap(bitmap);
//        }else {
//            Toast.makeText(this,"failed to get image ",Toast.LENGTH_SHORT).show();
//        }
//    }

    private void handlerkitkat(Intent data) {
        Uri data1 = data.getData();
        if (DocumentsContract.isDocumentUri(this, data1)) {
            //如果是document类型的Uri，则通过document id 处理
            String documentId = DocumentsContract.getDocumentId(data1);
            if ("com.android.providers.media.documents".equals(data1.getAuthority())) {
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagepath = getimage(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(data1.getAuthority())) {
                Uri contenturi = ContentUris.withAppendedId(data1.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagepath = getimage(contenturi, null);
            }
        } else if ("content".equalsIgnoreCase(data1.getScheme())) {
            //如果是content类型的Uri
            imagepath = getimage(data1, null);
        } else if ("file".equalsIgnoreCase(data1.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径
            imagepath = data1.getPath();
        }
        //  displayimage(imagepath);
    }


    private String getimage(Uri uri, String selection) {
        String path = null;
        Cursor query = getContentResolver().query(uri, null, selection, null, null);
        if (query.moveToFirst()) {
            path = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));

            query.close();
        }
        return path;
    }


    private void takephote() {

        try {
            outputimage = new File(getExternalCacheDir(), "output_image.jpg");
            if (outputimage.exists()) {
                outputimage.delete();
            }
            outputimage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(Main3Activity.this, "com.example.cameraalbumtest.fileprovider", outputimage);
        } else {
            imageUri = Uri.fromFile(outputimage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 1);
        ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {
                        takephoto = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 100:
                if (resultCode == RESULT_OK) {
                    Uri data1 = data.getData();

                    if (Build.VERSION.SDK_INT >= 19) {
                        handlerkitkat(data);
                    } else {
                        handlerbeforekitkat(data);
                    }
                }
        }
    }

    public class Myadpter extends BaseAdapter {
        ArrayList<Bitmap>list1;
        public Myadpter(ArrayList<Bitmap> list){
           this.list1=list;
        }
        @Override
        public int getCount() {
            return list1.size()+1;
        }

        @Override
        public Object getItem(int position) {
            return list1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                imageView = new ImageView(getApplication());
              
            if (list1.size()>position){
                Bitmap bitmap = list1.get(position);
                imageView.setImageBitmap(bitmap);
            }else {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            convertView=imageView;
           
            return convertView;
        }
    }
}
    


