package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import honghu.com.test.jiekou.DataResult;

public class Main9Activity extends AppCompatActivity implements DataResult {
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
////        PostHttp postHttp = new PostHttp(Main9Activity.this, url, handler);
//        postHttp.getPost();

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String res = data.getString("res");
            if (res.equals("") && res == null){
                Log.d("彭群", "-------res-----" + res);
            }else{
                Log.d("彭群", "-------res-----" + res);
            }
            TextView main9_tv=(TextView)findViewById(R.id.main9_tv);
            Gson gson = new Gson();

            main9_tv.setText(res);

        }
    };

    @Override
    public void rtd(String rtds) {
        Log.d("蓬裙", "--------rtds--------" + rtds);
    }
}
