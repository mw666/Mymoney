package honghu.com.test.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

/**
 * 调用系统拍照功能
 * 拍照后直接将照片在试图界面显示或打开相册将照片上传至界面显示
 */
public class Pic_Activity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.shangchuan_btn)
    Button shangchuanBtn;
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.shangchuan_pics)
    View shangchuanPics;
    @Bind(R.id.ori_btn)
    Button oriBtn;
    @Bind(R.id.suo_btn)
    Button suoBtn;
    private int ori = 1;//原画质--原图储存在sd卡中
    private int suo = 0;//缩略图--通过bundle实现
    private String sdPath;
    private String picPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        //获取sd卡的路径
        sdPath = Environment.getExternalStorageDirectory().getPath();
        //创建的一个png图片的文件路径
        picPath = sdPath + "/" + "temp.png";
        oriBtn.setOnClickListener(this);
        suoBtn.setOnClickListener(this);
        shangchuanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ori_btn://原图
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开相机系统
                //给原图一个存储路径
                Uri uri = Uri.fromFile(new File(picPath));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, ori);
                break;
            case R.id.suo_btn://缩略图，对图片进行压缩处理
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开相机系统
                startActivityForResult(intent1, suo);
                break;
            case R.id.shangchuan_btn:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ori) {
                //原图的大小
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(picPath);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds=true;
                    //将文件字节流转换成bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    //关闭流
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == suo) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                //对拍摄得到的照片进行压缩处理

                iv.setImageBitmap(bitmap);
            }
        }

    }
}
