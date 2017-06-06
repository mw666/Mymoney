package honghu.com.test.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

public class Main10Activity extends AppCompatActivity {

    @Bind(R.id.camera_btn)
    Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        ButterKnife.bind(this);
//        cameraBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
//                    Intent intent = new Intent();
//                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                    imageFileUri = getOutFileUri(TYPE_FILE_IMAGE);//得到一个File Uri
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
//                    startActivityForResult(intent, SYSTEM_CAMERA_REQUESTCODE);
//            }
//        });
    }

//    private Uri getOutFileUri(int fileType) {
//        return Uri.fromFile(getOutFile(fileType));
//    }

    //生成输出文件
//    private File getOutFile(int fileType) {
//
//        String storageState = Environment.getExternalStorageState();
//        if (Environment.MEDIA_REMOVED.equals(storageState)) {
//            Toast.makeText(getApplicationContext(), "oh,no, SD卡不存在", Toast.LENGTH_SHORT).show();
//            return null;
//        }
//
//        File mediaStorageDir = new File(Environment
//                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//                , "MyPictures");
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.i("MyPictures", "创建图片存储路径目录失败");
//                Log.i("MyPictures", "mediaStorageDir : " + mediaStorageDir.getPath());
//                return null;
//            }
//        }
//
////        File file = new File(getFilePath(mediaStorageDir, fileType));
//
//        return file;
//    }

    //生成输出文件路径
//    private String getFilePath(File mediaStorageDir, int fileType) {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
//                .format(new Date());
////        String filePath = mediaStorageDir.getPath() + File.separator;
////        if (fileType == TYPE_FILE_IMAGE) {
////            filePath += ("IMG_" + timeStamp + ".jpg");
////        } else if (fileType == TYPE_FILE_VEDIO) {
////            filePath += ("VIDEO_" + timeStamp + ".mp4");
//        } else {
//            return null;
//        }
//        return filePath;
//    }
}
