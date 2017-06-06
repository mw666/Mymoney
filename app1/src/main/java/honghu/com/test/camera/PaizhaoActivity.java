package honghu.com.test.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

public class PaizhaoActivity extends Activity {

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;
    private boolean preview;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置窗口属性,无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_paizhao);
        ButterKnife.bind(this);
        //设置分辨率率
        surfaceView.getHolder().setFixedSize(176, 144);
        //设置surfaceview不维护自己的缓冲区，而是等待屏幕的渲染引擎经内容推送到用户面前
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //设置surfaceview的回调方法
        surfaceView.getHolder().addCallback(new SurfaceViewCallback());

    }

    private class SurfaceViewCallback implements SurfaceHolder.Callback {
        //surface被创建好后调用此方法，surfaceview创建好后打开摄像头，注意是 android.hardware.Carama
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();
            //为摄像设置参数
            Camera.Parameters parameters = camera.getParameters();
            //获取当前屏幕管理器对象
            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            //获取屏幕信息描述类
            Display defaultDisplay = wm.getDefaultDisplay();
            //设置照片预览的显示大小
            parameters.setPreviewSize(defaultDisplay.getWidth(), defaultDisplay.getHeight());
            //每秒从摄像头捕捉的画面帧数
            parameters.setPreviewFrameRate(6);
            //设置照片输出的格式
            parameters.setPreviewFormat(PixelFormat.JPEG);
            //设置照片的质量
            parameters.set("jpeg-quality", 85);
            //设置照片的大小（例如：此处设置照片为屏幕大小）
            parameters.setPictureSize(defaultDisplay.getWidth(), defaultDisplay.getHeight());
            //设置照片的格式
            parameters.setPictureFormat(PixelFormat.RGB_565);
            //将参数赋予到Camara对象上
            camera.setParameters(parameters);
            preview = true;
        }

        //
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        //surfaceview销毁、释放资源时调用
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null)
                //如果摄像头还在工作，先停掉
                if (preview) {
                    camera.stopPreview();
                    preview=false;
                }
                //释放摄像头资源
            camera.release();
        }
    }
    /**
     * 手机键盘按键事件
     * 返回 true, 将阻止事件继续传递,例如搜索键，他默认会触发和打开系统的搜索引擎。返回true后，将不会触发。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*
         * event.getRepeatCount() 为重复按键的次数，例如，快速地对某个键连续按了两次，则此值为一，表示重复了一次。往上可以累推。
         * 按键只被按了一次，则此值为 0。  (应该就是点击累加的次数对2求余，余数部位1赋值0，余数为2赋值1)
         * 这有点类似于鼠标的 "单击" 和 "双击"。
         */
        if(camera!=null&&event.getRepeatCount()==0){
            switch (keyCode){
                case KeyEvent.KEYCODE_SEARCH://搜索键
                       /* 按下搜索键自动对焦 , 如果要关注它的事件，
                 * 可以实现 AutoFocusCallback 接口，并实例化其对象传入 */
                    camera.autoFocus(null);
                    break;
                case KeyEvent.KEYCODE_CAMERA:

                case KeyEvent.KEYCODE_DPAD_CENTER:

                  /*
                 * @param shutter : 照片被捕获之后的回调对象
                 * @param raw : 此回调对象可以生产为压缩的图片数据
                 * @param jpeg : 此回调对象可以产生压缩后的图片数据，其onPictureTaken将被调用
                 */
                camera.takePicture(null,null,new TakePictureCallback(),null);

                /* 拍完照后回到预览状态，继续取景 -- 错误的方式 */
                // camera.startPreview();必须写在 onPictureTaken 方法内部，因为 takePicture 内部是另开了一条线程异步的完成保存照片等操作。
                // 虽然 takePicture 方法完成了，但是并不代表其内部的工作全部完成，也不代表摄像头以及从上一次“拍照”任务中工作完毕
                break;
                default:
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private final class TakePictureCallback implements Camera.PictureCallback {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

            /* 照片将被保存到  SD 卡跟目录下，文件名为系统时间，后缀名为".jpg" */
            File file = new File(Environment.getExternalStorageState(), System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream fos = new FileOutputStream(file);

                /* 位图格式为JPEG
                 * 参数二位 0-100 的数值，100为最大值，表示无损压缩
                 * 参数三传入一个输出流对象，将图片数据输出到流中
                 */
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();

                /* 拍完照后回到预览状态，继续取景 */
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
