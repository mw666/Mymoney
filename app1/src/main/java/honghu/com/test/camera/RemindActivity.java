package honghu.com.test.camera;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import honghu.com.test.R;

public class RemindActivity extends Activity {


    private ImageView photo;
    private static final int RESULT = 1;
    Target target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        photo = (ImageView) findViewById(R.id.photo);
        target = new Target() {
            @Override
            public boolean equals(Object obj) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public ElementType[] value() {
                return new ElementType[0];
            }
        };
//
//        showPhoto(photo);

        photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Dialog dialog = new AlertDialog.Builder(RemindActivity.this)
                        .setTitle("从图库里选择照片").setMessage("确定要更换照片？").setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                        startActivityForResult(intent, RESULT);
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

//            target.setInfo(Target.TargetPhotoPath, picturePath);

//            showPhoto(photo);
        }
    }

//    private void showPhoto(ImageView photo) {
//        String picturePath = target.getInfo(Target.TargetPhotoPath);
//        if (picturePath.equals(""))
//            return;
//        // 缩放图片, width, height 按相同比例缩放图片
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
//        options.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
//        int scale = (int) (options.outWidth / (float) 300);
//        if (scale <= 0)
//            scale = 1;
//        options.inSampleSize = scale;
//        options.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeFile(picturePath, options);
//
//        photo.setImageBitmap(bitmap);
//        photo.setMaxHeight(350);
//    }
}

