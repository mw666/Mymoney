package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import honghu.com.test.jiekou.DataResult;

public class Main8Activity extends AppCompatActivity implements DataResult {

    private TextView main8_tv;
    private String mUrl = "http://www.imooc.com/api/teacher?type=4&num=30";
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        main8_tv = (TextView) findViewById(R.id.main8_tv);
//        PostHttp postHttp = new PostHttp(Main8Activity.this, mUrl, mHandler);


    }

    @Override
    public void rtd(String rtds) {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                main8_tv.setText("你好你好-----");
            }
        };
        Log.d("pengqun","rtsd------------------"+rtds);
    }
}
