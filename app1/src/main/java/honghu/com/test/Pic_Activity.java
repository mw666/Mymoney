package honghu.com.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Pic_Activity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.orig_)
    Button orig;
    @Bind(R.id.suo_)
    Button suo;
    @Bind(R.id.pic)
    ImageView pic;
    private static int REQUEST_THUMBNAIL = 1;// 请求缩略图信号标识
    private static int REQUEST_ORIGINAL = 2;// 请求原图信号标识
    private String sdPath;//SD卡的路径
    private String picPath;//图片存储路径
    String Tag = "彭群";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_);
        ButterKnife.bind(this);
        orig.setOnClickListener(this);
        suo.setOnClickListener(this);
        //获取sd卡的存储路径
        sdPath = Environment.getExternalStorageDirectory().getPath();
        picPath = sdPath + "/" + "temp.png";
        Log.d(Tag, "----------------" + sdPath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orig_:
                //第一种拍摄原图
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//发送启动相机的广播
                Uri uri = Uri.fromFile(new File(picPath));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                //启动相机
                startActivityForResult(intent, REQUEST_ORIGINAL);
                break;
            case R.id.suo_:
                //第二种拍摄缩略图
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent2, REQUEST_THUMBNAIL);
                break;
            default:
                break;
        }
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            if (resultCode == REQUEST_ORIGINAL) {
                /**
                 * 原图拍摄，----通过sd路径获取到原图，字节流--->bitmap图片
                 */
                FileInputStream fileInputStream;
                try {
                    Log.d(Tag, "----------" + picPath);
                    fileInputStream = new FileInputStream(picPath);
                    //从图片储存路经文件字节流读取，转换成图片
                    //Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                    //将图片文件字节流转换成压缩一般大小的图片
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    //通过改变图片色彩模式来减小图片所占有的内存
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inPurgeable=true;
                    options.inInputShareable=true;
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 4;//将图片缩小一半
                    Bitmap bitmap1 = BitmapFactory.decodeStream(fileInputStream, null, options);
                    //将图片显示出来
                    pic.setImageBitmap(bitmap1);
                    if (bitmap1 != null && !bitmap1.isRecycled()) {
                        bitmap1.recycle();
                        bitmap1=null;
                        System.gc();//提醒系统回收bitmap
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == REQUEST_ORIGINAL) {
                /**
                 * 缩略图
                 */
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                pic.setImageBitmap(bitmap);
            }
        }

    }
}
