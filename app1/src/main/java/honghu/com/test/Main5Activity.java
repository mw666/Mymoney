package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 子线程中完成耗时操作，并将结果返回给主线程
 */
public class Main5Activity extends AppCompatActivity {

    @Bind(R.id.tv_tv)
    TextView tvTv;
    private String result;
    private String tag = "彭群";
    private String data_url = "http://192.168.10.234:8030/WebService1.asmx/GetItemSet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);
        new Thread(runnable).start();

//        //子线程
//        HandlerThread son = new HandlerThread("Son");
//        son.start();
//        sonHandler = new Handler() {
//
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                parentHandler.sendEmptyMessage(1);
//                switch (msg.what) {
//                    case 1:
//                        //该处可以处理逻辑代码，耗时操作
//
//                        break;
//                }
//            }
//        };

    }
    //主线程（UI线程）
    Handler parentHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resu = data.getString("re");
            Log.d(tag, "----------------" + resu + "------");
            tvTv.setText(resu);
        }
    };

    //子线程
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                //指定地址
                URL url = new URL(data_url);
                //获取一个连接的实例
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.setDoInput(true);
                huc.setDoOutput(true);
                huc.setRequestMethod("POST");// 设置URL请求方法
                huc.setRequestProperty("connection", "Keep-Alive");// 设置属性
                huc.setRequestProperty("Content-Type", "text/xml");
                huc.setRequestProperty("Charset", "utf-8");
                //连接网络服务
                huc.connect();
                //根据指定的地址到达指定的为网络内容
                OutputStreamWriter osw = new OutputStreamWriter(huc.getOutputStream());
                osw.flush();//清空缓存
                osw.close();//关闭流

                //接收返回的数据结果
                BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                result = "";
                String readLine = null;
                while ((readLine = br.readLine()) != null) {
                    result += readLine;
                }
                br.close();
                //断开连接
                huc.disconnect();
                //拿到数据后进行解析
                int a = result.indexOf("[");
                int b = result.indexOf("]");
                result = result.substring(a, b + 1);
//                tvTv.setText(result);//请求结果有值
                Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是子线程" + result);
            } catch (MalformedURLException e) {
                System.out.print("post请求发生异常" + e);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
//完成网络请求，将请求结果返回给主线程，并更新UI
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("re", result);
            message.setData(bundle);
            parentHandler.sendMessage(message);
        }
    };
}
