package honghu.com.test.testphoto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.hardware.Camera.PictureCallback;
import android.view.SurfaceHolder.Callback;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import honghu.com.test.R;
import honghu.com.test.base.AppManager;
import honghu.com.test.base.BaseActivity;

public class AutoTakePhoto extends BaseActivity {

    private Camera camera;
    private Camera.Parameters parameters = null;
    Bundle bundle = null; // 声明一个Bundle对象，用来存储数据
    //图片存放位置
    public  String name;
    private Button take;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_take_photo);
        take = (Button) findViewById(R.id.take);
        take.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (camera != null) {
                    camera.takePicture(null, null, new MyPictureCallback());


                }
            }
        });
        SurfaceView surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        surfaceView.getHolder().setFixedSize(176, 144);  //设置Surface分辨率
        surfaceView.getHolder().setKeepScreenOn(true);// 屏幕常亮
        surfaceView.getHolder().addCallback(new SurfaceCallback());//为SurfaceView的句柄添加一个回调函数
    }

    private final class MyPictureCallback implements PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                saveToSDCard(data); // 保存图片到SD卡中
                Toast.makeText(getApplicationContext(),"成功拍照",Toast.LENGTH_SHORT).show();
                camera.startPreview(); // 拍完照后，重新开始预览
                //图片更新，很重要的一点，部分手机如果不发送这条广播图片不是实时更新
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                        + name)));
                Intent intent = new Intent();
                System.out.println("拍照"+name);
                intent.putExtra("path", name);
                setResult(-1, intent);
                AppManager.getAppManager().finishActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //将拍下来的照片存放在SD卡中
    @SuppressLint("SimpleDateFormat")
    public   void saveToSDCard(byte[] data) throws IOException {
//	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");// 格式化时间
        String filename = System.currentTimeMillis() + ".jpg";
        File fileFolder = new File(Environment.getExternalStorageDirectory()
                + "/manhole/");
        if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
            fileFolder.mkdir();
        }
        File jpgFile = new File(fileFolder, filename);
        name = Environment.getExternalStorageDirectory() + "/manhole/"+filename;
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流
        outputStream.write(data); // 写入SD卡中
        outputStream.close(); // 关闭输出流
    }

    private final class SurfaceCallback implements Callback {

        // 拍照状态变化时调用该方法
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            parameters = camera.getParameters(); // 获取各项参数
            parameters.setPreviewSize(width, height); // 设置预览大小
            parameters.setPictureSize(width, height); // 设置保存的图片尺寸
            parameters.setJpegQuality(80); // 设置照片质量
        }

        // 开始拍照时调用该方法
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            try {
                camera = Camera.open(); // 打开摄像头
                camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
                camera.setDisplayOrientation(getPreviewDegree(AutoTakePhoto.this));
                camera.startPreview(); // 开始预览
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 停止拍照时调用该方法
        @Override
        public void surfaceDestroyed(SurfaceHolder arg0) {
            // TODO Auto-generated method stub
            if (camera != null) {
                camera.release(); // 释放照相�?
                camera = null;
            }
        }

    }

    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public   int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degree = 0;
        /// 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        System.out.println("放向"+degree);
        return degree;
    }
}
