package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main6Activity extends AppCompatActivity {

    @Bind(R.id.test_tv)
    TextView testTv;
    public static Handler handler;
    private String iUrl = "http://www.imooc.com/api/teacher?type=4&num=30";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);
        Http http = new Http(iUrl);
        http.data();
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String re = data.getString("re");
                Log.d("彭群", "---------------shgdfg----------------" + re);
//                list = new ArrayList<DispatchList>();
//                Gson gson = new Gson();
//                Type type = new TypeToken<ArrayList<DispatchList>>() {
//                }.getType();
//                ArrayList<DispatchList> o = gson.fromJson(re, type);
//                testTv.setText(o.toString());
//                DispatchList dispatchList = gson.fromJson(re, DispatchList.class);
//                testTv.setText(dispatchList.toString());
//                Log.d("彭群", "-------------------------------" + o);
//                //解析拿到的结果
//                JsonParser jsonParser = new JsonParser();
//                JsonArray asJsonArray = jsonParser.parse(re).getAsJsonArray();
//                for (JsonElement jsonElement : asJsonArray) {
//                    DispatchList dispatchList = gson.fromJson(re, DispatchList.class);
//                    testTv.setText(dispatchList.toString());
//                }


            }
        };

    }
}
