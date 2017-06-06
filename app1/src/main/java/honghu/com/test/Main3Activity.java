package honghu.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

public class Main3Activity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
//    @Bind(R.id.tv)
//    TextView tv;
    private String tag="彭群";
    private ArrayList<Cailiao> list;
    private String data_url = "http://192.168.10.234:8030/WebService1.asmx/GetItemSet";
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
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

//                    tv.setText(result);

                    TextView tv = (TextView)findViewById(R.id.tv);
                    tv.setText(result);
//                    Gson gson = new Gson();
//                    Cailiao cailiao = gson.fromJson(result, Cailiao.class);
//                    System.out.print(cailiao);


//                    JsonParser jsonParser = new JsonParser();
//                    JsonArray asJsonArray = jsonParser.parse(result).getAsJsonArray();
//                    Gson gson = new Gson();

                    Log.e(tag,"-----------------------------------------------------------------"+result);
                } catch (MalformedURLException e) {
                    System.out.print("post请求发生异常" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}