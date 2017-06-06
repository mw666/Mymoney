package honghu.com.test;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

public class TestNewFileActivity extends AppCompatActivity {

    //手机sd卡的路径
    //创建目录要在linux格式下创建
    String sdPath = Environment.getExternalStorageDirectory().getPath()+"/newimages";
    String path="/sdcard/newimages";//想要创建的目录路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_new_file);
        createFile();


    }
    //制定路径创建文件夹
    public boolean createFile(){
        //先判断SD卡的状态,是否可用
        String ess = Environment.getExternalStorageState();

        if (ess.equals(Environment.MEDIA_MOUNTED)) {
            //然后根据sd卡的状态制定目录

            File file = new File(sdPath);
            if ( !file.exists()){
                //如果不存在，创建目录文件夹
                file.mkdir();
            }
            return true;
        } else {
            Toast.makeText(TestNewFileActivity.this,"sd卡状态异常，无法使用",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
