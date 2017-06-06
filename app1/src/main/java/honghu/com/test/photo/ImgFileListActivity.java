package honghu.com.test.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import honghu.com.test.R;

public class ImgFileListActivity extends Activity implements AdapterView.OnItemClickListener {


    ListView listView;
    Util util;
    ImgFileListAdapter listAdapter;
    List<FileTraversal> locallist;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_file_list);
        listView = (ListView) findViewById(R.id.listView1);
        util = new Util(this);
        locallist = util.LocalImgFileList();

        Log.e("蓬裙","===================-------------->>>>>>>"+locallist);
        List<HashMap<String, String>> listdata = new ArrayList<HashMap<String, String>>();
        Bitmap []bitmap = null;
        if (locallist != null) {
            bitmap = new Bitmap[locallist.size()];
            Log.e("小孩子","==============-----====="+bitmap);
            for (int i = 0; i < locallist.size(); i++) {
                map = new HashMap<String, String>();
                map.put("filecount", locallist.get(i).filecontent.size() + "45");
                map.put("imgpath", locallist.get(i).filecontent.get(0) == null ? null : (locallist.get(i).filecontent.get(0)));
                map.put("filename", locallist.get(i).filename);
                listdata.add(map);
            }
            Log.e("蓬裙map","===========------------->"+map);
        }
Log.e("pengqung--====","=====--------=======>"+listdata);
        listAdapter = new ImgFileListAdapter(this, listdata);
        Log.e("pengqunglove","=================------------------->>"+listAdapter);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(this, ImgsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", locallist.get(arg2));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
