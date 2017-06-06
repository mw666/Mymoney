package com.tuofu.lenovo.mymoney;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;




import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

public class ZhiFuActivity extends FragmentActivity {

    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.bt)
    Button bt;
    @Bind(R.id.activity_zhi_fu)
    LinearLayout activityZhiFu;
    @Bind(R.id.bb)
    Button bb;

    private String imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu);
        ButterKnife.bind(this);
        ShareSDK.initSDK(this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
               
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare(getApplicationContext(),null,true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();

            if (Build.VERSION.SDK_INT >= 19) {
                handlerkitkat(data);
            } else {
                handlerbeforekitkat(data);
            }

        }
    }

    public static void showShare(Context context, String platformToShare, boolean showContentEdit) {
     //   OnekeyShare oks = new OnekeyShare();
        OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
               oks.disableSSOWhenAuthorize();
        
                // title标题：微信、QQ（新浪微博不需要标题）
                 oks.setTitle("我是分享标题");  //最多30个字符
        
                // text是分享文本：所有平台都需要这个字段
                 oks.setText("我是分享文本，啦啦啦~http://uestcbmi.com/");  //最多40个字符
        
                 // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
                 //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片
        
                 //网络图片的url：所有平台
                oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
        
                 // url：仅在微信（包括好友和朋友圈）中使用
                 oks.setUrl("http://sharesdk.cn");   //网友点进链接后，可以看到分享的详情
        
                 // Url：仅在QQ空间使用
                 oks.setTitleUrl("http://www.baidu.com");  //网友点进链接后，可以看到分享的详情
        
                 // 启动分享GUI
                 oks.show(context);
      //  oks.setSilent(!showContentEdit);
     //  if (platformToShare != null) {
//            oks.setPlatform(platformToShare);
//        }
//        //ShareSDK快捷分享提供两个界面第一个是九宫格 CLASSIC  第二个是SKYBLUE
//       oks.setTheme(OnekeyShareTheme.CLASSIC);
//        // 令编辑页面显示为Dialog模式
//        oks.setDialogMode();
//        // 在自动授权时可以禁用SSO方式
//        oks.disableSSOWhenAuthorize();
//        oks.setAddress("12345678901"); //分享短信的号码和邮件的地址
//        oks.setTitle("ShareSDK--Title");
//        oks.setTitleUrl("http://mob.com");
//        oks.setText(context.getString(R.string.app_share_text));
//     //   oks.setImagePath("/sdcard/test-pic.jpg");  //分享sdcard目录下的图片
//        oks.setImageUrl("http://f1.webshare.mob.com/dimgs/1c950a7b02087bf41bc56f07f7d3572c11dfcf36.jpg");
//        oks.setUrl("http://www.mob.com"); //微信不绕过审核分享链接
//        //oks.setFilePath(testVideo);  //filePath用于视频分享
//       oks.setComment(context.getString(R.string.app_share_comment)); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
//        oks.setSite("ShareSDK");  //QZone分享完之后返回应用时提示框上显示的名称
//        oks.setSiteUrl("http://mob.com");//QZone分享参数
//        oks.setVenueName("ShareSDK");
//        oks.setVenueDescription("This is a beautiful place!");
//        oks.setLatitude(23.169f);
//        oks.setLongitude(112.908f);
    //    oks.show();
        // 将快捷分享的操作结果将通过OneKeyShareCallback回调
      //   oks.setCallback(new OneKeyShareCallback());
        // 去自定义不同平台的字段内容
        // oks.setShareContentCustomizeCallback(new
     //    ShareContentCustomizeDemo());
        // 在九宫格设置自定义的图标
  //     Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
    //  String label = "ShareSDK";
    //   OnClickListener listener = new OnClickListener() {
     //      public void onClick(View v) {

     //     }
      //  };
     //   oks.setCustomerLogo(logo, label, listener);listener;

        // 为EditPage设置一个背景的View
        //oks.setEditPageBackground(getPage());
        // 隐藏九宫格中的新浪微博
        // oks.addHiddenPlatform(SinaWeibo.NAME);

        // String[] AVATARS = {
        // 		"http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339485237265.jpg",
        // 		"http://diy.qqjay.com/u/files/2012/0523/f466c38e1c6c99ee2d6cd7746207a97a.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0422/fadc08459b1ef5fc1ea6b5b8d22e44b4.jpg",
        // 		"http://img1.2345.com/duoteimg/qqTxImg/2012/04/09/13339510584349.jpg",
        // 		"http://diy.qqjay.com/u2/2013/0401/4355c29b30d295b26da6f242a65bcaad.jpg" };
        // oks.setImageArray(AVATARS);              //腾讯微博和twitter用此方法分享多张图片，其他平台不可以

        // 启动分享
        
    }


    private void handlerbeforekitkat(Intent data) {
        Uri data1 = data.getData();
        imagepath = getimages(data1, null);
        displayimage(imagepath);
    }

    private void displayimage(String path) {
        if (path != null) {
            //  Bitmap bitmap = BitmapFactory.decodeFile(path);
            Bitmap bitmap = getimage(path);
            Bitmap circle = toRoundBitmap(bitmap);
            //Bitmap zoom = zoom(bitmap, 1024, 800);
            iv.setImageBitmap(circle);
        } else {
            Toast.makeText(this, "failed to get image ", Toast.LENGTH_SHORT).show();
        }
    }

    private void handlerkitkat(Intent data) {
        Uri data1 = data.getData();
        if (DocumentsContract.isDocumentUri(this, data1)) {
            String documentId = DocumentsContract.getDocumentId(data1);
            if ("com.android.providers.media.documents".equals(data1.getAuthority())) {
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagepath = getimages(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(data1.getAuthority())) {
                Uri contenturi = ContentUris.withAppendedId(data1.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagepath = getimages(contenturi, null);
            }
        } else if ("content".equalsIgnoreCase(data1.getScheme())) {
            //如果是content类型的Uri
            imagepath = getimages(data1, null);
        } else if ("file".equalsIgnoreCase(data1.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径
            imagepath = data1.getPath();
        }
        displayimage(imagepath);
    }


    private String getimages(Uri uri, String selection) {
        String path = null;
        Cursor query = getContentResolver().query(uri, null, selection, null, null);
        if (query.moveToFirst()) {
            path = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));

            query.close();
        }
        return path;
    }

    private Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩        
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 缩放处理
     *
     * @param source
     * @param wf
     * @param hf
     * @return
     */
    public static Bitmap zoom(Bitmap source, float wf, float hf) {
        Matrix matrix = new Matrix();
        float sx = wf / source.getWidth();
        float sy = hf / source.getHeight();
        matrix.postScale(sx, sy);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    ;


    /**
     * 圆形裁剪显示处理
     *
     * @param source
     * @return
     */
    public static Bitmap circle(Bitmap source) {
        int width = source.getWidth();
        Bitmap target = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
