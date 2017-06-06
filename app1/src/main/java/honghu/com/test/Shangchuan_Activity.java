package honghu.com.test;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Shangchuan_Activity extends Activity {
    Button btn_start;
    GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new Myadapter();
        iniLisner();
        inidata();
    }

    private void refreshAdapter() {
        if (list == null) {
            list = new ArrayList<String>();
        }
        if (adapter == null) {
            adapter = new Myadapter();
        }
        if (list.size() == ON) {
            list.remove(list.size() - 1);
        }
        adapter.notifyDataSetChanged();
    }

    private final int ON = 5;  //允许的图片数量 + 1

    private void inidata() {
        if (list == null) {
            list = new ArrayList<String>();
            list.add("a");  //添加默认图片
        }
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).equals("a")) {
                    setClick();
                }
            }
        });

        refreshAdapter();
    }

    Myadapter adapter;

    class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item, null);
                vh = new ViewHolder();
                vh.iamge = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            if (list.get(position).equals("a")) {  //添加默认图片
                vh.iamge.setImageResource(R.drawable.timg);
            } else {
                Bitmap loacalBitmap = getLoacalBitmap(list.get(position));
                if (loacalBitmap != null) {
                    vh.iamge.setImageBitmap(loacalBitmap);
                }
            }
            return convertView;
        }

    }

    class ViewHolder {
        ImageView iamge;
    }

    private void iniLisner() {
        if (btn_start != null) {
            btn_start.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    setClick();
                }
            });

        }

    }

    private void setClick() {
        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        getImage.addCategory(Intent.CATEGORY_OPENABLE);
        getImage.setType("image/jpeg");
        startActivityForResult(getImage, 0);

    }

    private byte[] mContent;
    private List<String> list;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ContentResolver resolver = getContentResolver();
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            try {
                Uri originalUri = data.getData();
                mContent = readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
                String path = originalUri.toString();
                ContentResolver testcr = getContentResolver();
                Cursor cur = testcr.query(originalUri, null, null, null, null);
                if (list.size() == ON) {
                    removeItem();
                    refreshAdapter();
                    return;
                }
                removeItem();
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    int dataColumn = cur.getColumnIndex(Thumbnails.DATA);
                    String image_path = cur.getString(dataColumn);
                    list.add(image_path);
                }
                list.add("a");
                refreshAdapter();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }

    private void removeItem() {
        if (list.size() - 1 != ON) {
            if (list.size() != 0) {  //删除默认图片
                list.remove(list.size() - 1);
            }
        }

    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;

    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}