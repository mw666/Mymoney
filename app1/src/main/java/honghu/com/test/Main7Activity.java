package honghu.com.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import honghu.com.test.jiekou.Imooc;

public class Main7Activity extends AppCompatActivity implements Imooc {
    private OkHttp okHttp;
    private TextView main7_tv;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        TextView viewById = (TextView) findViewById(R.id.main7_tv);
        viewById.setText("当军嫂发的");




    }


    private void callHelp() {
        okHttp.post_a();
    }

    @Override
    public String  imoocData(String imoocData) {

        Log.d("蓬裙","imoocData---------------"+imoocData);
        return imoocData;
    }
}
