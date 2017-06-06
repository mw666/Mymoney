package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.bean.DataBean;
import honghu.com.test.http.PostHttp;


public class Main13Activity extends AppCompatActivity {

    @Bind(R.id.tv_main13)
    TextView tvMain13;
    private String url = "http://61.164.45.100:8030/WebService1.asmx/GetDispatchList";
    public static ArrayList<DataBean> dataBeanArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        ButterKnife.bind(this);
//            String [] strings={"1","2","3","4","5","6"};
//        for (int i = 0; i <= strings.length; i++) {
//            tvMain13.setText(strings[i]);
//        }
        Main16Activity main16Activity = new Main16Activity(dataBeanArrayList);

        PostHttp postHttp = new PostHttp(url, handler);
        postHttp.getPost();
    }
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String res = data.getString("res");

            //将数据截取一下
            int a=res.indexOf("[");
            int b=res.lastIndexOf("]")+1;
            res=res.substring(a,b);


            Type type = new TypeToken<ArrayList<DataBean>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<DataBean> dataBeanList = gson.fromJson(res, type);
            for (DataBean dataBean : dataBeanList) {
                tvMain13.setText(dataBean.toString());
            }
            DataBean dataBean = dataBeanList.get(0);
            DataBean.WorkerMsg workerMsg = dataBean.getWorkerList().get(0);
            String dispatchID = workerMsg.getDispatchID();
            String address = dataBean.getDispatchList().getAddress();
            tvMain13.setText("pengqun"+"-----------"+dispatchID+"-----"+address);
//            tvMain13.setText();

//            String address = dataBean.getDispatchList().getAddress();
//            WorkerMsg workerMsg = dataBean.getWork_set().get(0);
//            String dispatchID = workerMsg.getDispatchID();
//            tvMain13.setText(address+dispatchID);
        }
    };
}
