package honghu.com.test.photo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2017/5/9.
 */

public class Util {
    Context context;

    public Util(Context context) {
        this.context = context;
    }

    /**
     * ???????????
     *
     * @return
     */
    public ArrayList<String> listAlldir() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Uri uri = intent.getData();
//        String uri = Environment.getExternalStorageState(new File("/DCIM/Camera"));
        ArrayList<String> list = new ArrayList<String>();
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);//managedQuery(uri, proj, null, null, null);
        while (cursor.moveToNext()) {
            String path = cursor.getString(0);
            /**
             * 给一个获取图片的path
             */
            list.add(new File(path).getAbsolutePath());
        }
        return list;
    }

    //从本地文件列表获取图片文件
    public List<FileTraversal> LocalImgFileList() {
        List<FileTraversal> data = new ArrayList<FileTraversal>();
        String filename = "";
        List<String> allimglist = listAlldir();//所有的文件

        List<String> retulist = new ArrayList<String>();
        if (allimglist != null) {
            Set set = new TreeSet();
            String[] str;
            // 对所有的文件遍历，获取文件名字，返回到另外一个集合
            for (int i = 0; i < allimglist.size(); i++) {
                retulist.add(getfileinfo(allimglist.get(i)));
            }

            for (int i = 0; i < retulist.size(); i++) {
                set.add(retulist.get(i));
            }
            Log.e("Set=======","==============--------------->"+set);
            str = (String[]) set.toArray(new String[0]);
            for (int i = 0; i < str.length; i++) {
                filename = str[i];
                FileTraversal ftl = new FileTraversal();
                ftl.filename = filename;
                data.add(ftl);
            }

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < allimglist.size(); j++) {
                    if (data.get(i).filename.equals(getfileinfo(allimglist.get(j)))) {
                        data.get(i).filecontent.add(allimglist.get(j));
                    }
                }
            }
        }
        Log.e("蓬裙","==============---------------------"+data);
        return data;
    }

    //????????????��
    public Bitmap getPathBitmap(Uri imageFilePath, int dw, int dh) throws FileNotFoundException {
        //???????????
        /**
         * ???????????????????????????????????��????????
         * BitmapFactory.Options??????????????????inJustDecodeBounds???????????true
         * ????????????????????????��?????��????????
         * ???????????????????????????????BitmapFactory.Options??outWidth??outHeight?��?????
         */
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        //?????????MediaStore?��?????????URI??????????????
        Bitmap pic = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageFilePath),
                null, op);

        int wRatio = (int) Math.ceil(op.outWidth / (float) dw); //?????????
        int hRatio = (int) Math.ceil(op.outHeight / (float) dh); //?????????

        /**
         * ?????????????????��???????????????????????????????
         * ???????????????????????????????????
         * ??????????????????��?????????????????
         * ??????��?wRatio??hRatio???��
         * ???????????????????????????��??????????????????????
         * ???????????inSampleSize????
         */
        if (wRatio > 1 && hRatio > 1) {
            if (wRatio > hRatio) {
                op.inSampleSize = wRatio;
            } else {
                op.inSampleSize = hRatio;
            }
        }
        op.inJustDecodeBounds = false; //????????????????false?????????????????????true????????????
        pic = BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(imageFilePath), null, op);

        return pic;
    }

    public String getfileinfo(String data) {
        String filename[] = data.split("/");
        if (filename != null) {
            return filename[filename.length - 2];
        }
        return null;
    }

    public void imgExcute(ImageView imageView, ImgCallBack icb, String... params) {
        LoadBitAsynk loadBitAsynk = new LoadBitAsynk(imageView, icb);
        loadBitAsynk.execute(params);
    }

    public class LoadBitAsynk extends AsyncTask<String, Integer, Bitmap> {

        ImageView imageView;
        ImgCallBack icb;

        LoadBitAsynk(ImageView imageView, ImgCallBack icb) {
            this.imageView = imageView;
            this.icb = icb;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                if (params != null) {
                    for (int i = 0; i < params.length; i++) {
                        bitmap = getPathBitmap(Uri.fromFile(new File(params[i])), 200, 200);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {
//				imageView.setImageBitmap(result);
                icb.resultImgCall(imageView, result);
            }
        }
    }
}
