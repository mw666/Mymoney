package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
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

//深度开源：json解析---http://www.open-open.com/lib/view/open1472632967912.html
public class Main2Activity extends AppCompatActivity {
    /**
     * 开启县城网络请求
     * <p>
     * 奇数是子线程发的消息，双数是主线程发的消息
     * <p>
     * =子线程与主线程之间的通信
     *
     * @param savedInstanceState
     */
    Handler handler = new Handler();
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv2)
    TextView tv2;
    private static final String data_url = "http://192.168.10.234:8030/WebService1.asmx/GetItemSet";
    private String result;
    private Handler mhandler;
    private Handler handler1;
    private String tag = "彭群";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //指定地址
//                    URL url = new URL(data_url);
//                    //获取一个连接的实例
//                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
//                    huc.setDoInput(true);
//                    huc.setDoOutput(true);
//                    huc.setRequestMethod("POST");// 设置URL请求方法
//                    huc.setRequestProperty("connection", "Keep-Alive");// 设置属性
//                    huc.setRequestProperty("Content-Type", "text/xml");
//                    huc.setRequestProperty("Charset", "utf-8");
//                    //连接服务
//                    huc.connect();
//
//                    OutputStreamWriter osw = new OutputStreamWriter(huc.getOutputStream());
//
//                    osw.flush();//清空缓存
//                    osw.close();//关闭流
//
//                    //接收返回的数据结果
//                    BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
//                    result = "";
//                    String readLine = null;
//                    while ((readLine = br.readLine()) != null) {
//                        result += readLine;
//                    }
//                    br.close();
//                    //断开连接
//                    huc.disconnect();
//
//                    //拿到数据后进行解析
//                    int a = result.indexOf("[");
//                    int b = result.indexOf("]");
//                    result = result.substring(a, b + 1);
//
//                    System.out.print("---------------------------------------------"+result);
//                    Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是子线程" + result);
//                    /**
//                     * 在这里时result是获取到了值的
//                     */
//                } catch (MalformedURLException e) {
//                    System.out.print("post请求发生异常" + e);
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是子线程-----" + result);
        tv.setText(result);
        //主线程

        handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String re = data.getString("re");
                tv.setText(re);
                Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是主线程-----" + result);

            }
        };
        handler1.sendEmptyMessage(0);//主线程向子线程发送信息


    }

//    class LoopThread implements Runnable {
//
//        private Handler mHander = null;
//
//        @Override
//        public void run() {
//            Looper.prepare();
//            mHander = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                    try {
//                        //指定地址
//                        URL url = new URL(data_url);
//                        //获取一个连接的实例
//                        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
//                        huc.setDoInput(true);
//                        huc.setDoOutput(true);
//                        huc.setRequestMethod("POST");// 设置URL请求方法
//                        huc.setRequestProperty("connection", "Keep-Alive");// 设置属性
//                        huc.setRequestProperty("Content-Type", "text/xml");
//                        huc.setRequestProperty("Charset", "utf-8");
//                        //连接服务
//                        huc.connect();
//
//                        OutputStreamWriter osw = new OutputStreamWriter(huc.getOutputStream());
//
//                        osw.flush();//清空缓存
//                        osw.close();//关闭流
//
//                        //接收返回的数据结果
//                        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
//                        result = "";
//                        String readLine = null;
//                        while ((readLine = br.readLine()) != null) {
//                            result += readLine;
//                        }
//                        br.close();
//                        //断开连接
//                        huc.disconnect();
//
//                        //拿到数据后进行解析
//                        int a = result.indexOf("[");
//                        int b = result.indexOf("]");
//                        result = result.substring(a, b + 1);
//
//                        Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是子线程——--类" + result);
//                    } catch (MalformedURLException e) {
//                        System.out.print("post请求发生异常" + e);
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Looper.loop();
//                    mHander.sendEmptyMessage(1);
//                }
//
//            };
//
//        }
//    }

    public String myData() {


        HandlerThread httpThread = new HandlerThread("httpThread");//实例化一个特殊的子线程

        httpThread.start();

        mhandler = new Handler(httpThread.getLooper()) {
            //在这个子线程里边处理耗时的逻辑代码
            //接收信息，处理逻辑代码
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case 0:
                        //该处可以处理逻辑代码，耗时操作
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
                        //连接服务
                        huc.connect();

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
                        tv.setText(result);
                        System.out.print("---------------------------------------------"+result);
                        Log.d(tag, "----------------------------" + Thread.currentThread() + "-----这是子线程" + result);
                    } catch (MalformedURLException e) {
                        System.out.print("post请求发生异常" + e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("re",result);
                        message.setData(bundle);
                        handler1.sendMessage(message);
                        /**
                         *  子线程向主线程发送消息
                         */
                        break;
                    default:
                        break;
                }
            }
        };
        return result;
    }

}
