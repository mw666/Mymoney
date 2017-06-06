package honghu.com.test.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

public class Camera extends AppCompatActivity {

    @Bind(R.id.shexaing)
    Button shexaing;
    @Bind(R.id.shexiang_iv)
    ImageView shexiangIv;
    @Bind(R.id.shexaing_tv)
    TextView shexaingTv;
    @Bind(R.id.changjian)
    Button changjian;
    private String sdPath;
    private String picPath;
    private Bitmap bitmap;
    private int ab;
    private String currentTime;
    private String absolutePath;
    private int REQUESTCODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        changjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        sdPath = Environment.getExternalStorageDirectory().getPath().toString();
//        String newSdPath = "newimage";
//        File file1 = new File(sdPath, newSdPath);
//        if (!file1.exists()) {
//            boolean mkdir = file1.mkdir();
//
//        }
        //创建的一个png图片的文件路径
        StringBuilder log = new StringBuilder();
        List<String> extSDCardPath = getExtSDCardPath();
        //外置
        List<String> extPaths = getExtSDCardPath();
        for (String path : extPaths) {
            log.append("外置SD卡路径：" + path + "\r\n");
        }
        String s = log.toString();
        Log.d("---------------------", "----------------------" + s);

        //内置
        String inPath = getInnerSDCardPath();
        log.append("内置SD卡路径：" + inPath + "\r\n");
        shexaingTv.setText(s);
        File file = new File("/storage/extSdCard /sdcard/pics");
        if (!file.exists()) {
            file.mkdir();//创建文件夹
        }
        shexaing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //给照片创建一个储存地址，指定文件路径
                //获取系统当前时间作为相片的命名,作为唯一命名
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                Date date = new Date(System.currentTimeMillis());//获取系统当前的时间
                currentTime = sdf.format(date);
                picPath = sdPath + "/" + "IMG_" + currentTime + ".png";
                Uri uri = Uri.fromFile(new File(picPath));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                Log.d("蓬裙", "-----------currenttime-----------" + currentTime);
//                shexaingTv.setText(currentTime);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意
                test();
            } else {
                //用户不同意
            }
        }
    }

    private void test() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int i = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (i != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUESTCODE);
            }
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "newimage");
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            Log.d("发烧回复放假啊平均得分", "--------合法覅哦啊好--" + mkdir);
        }
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    //内置
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    //获取手机外置sd卡路径
    public List<String> getExtSDCardPath() {
        List<String> lResult = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String storageState = Environment.getExternalStorageState();//获取sd卡状态
            if (storageState.equals(Environment.MEDIA_MOUNTED))//sd卡是否可用
            {
                Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                return;
            }
//            String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为Bitmap图片格式,获取相片
            bitmap = (Bitmap) bundle.get("data");
            //相片大小需要调整
            //这里采用采用压缩法和G565的方式处理图片内存

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;//将原图缩为原来的1/2
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeFile(picPath, options);

            FileOutputStream b = null;
            //???????????????????????????????为什么不能直接保存在系统相册位置呢？？？？？？？？？？？？
//            File file = new File("/sdcard/myImage/");
//            file.mkdirs();// 创建文件夹
//            String fileName = "/sdcard/myImage/"+name;

            try {
                b = new FileOutputStream(picPath);//指定写入的地址
                this.bitmap.compress(Bitmap.CompressFormat.JPEG, 50, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
