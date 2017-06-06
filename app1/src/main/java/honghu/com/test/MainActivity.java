package honghu.com.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StringBuilder append;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String url="http://localhost:55571/WebService1.asmx/GetWorkerSet";
//        String url="http://61.164.45.100:8030/WebService1.asmx/GetItemSet";
//        //请求参数应该是 name1=value1&name2=value2 的形式
//        String param="ItemName=";
//        PrintWriter out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            //new一个URL对象实例
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
//            // 设置通用的请求属性
//            conn.setRequestMethod("POST");// 设置URL请求方法
//            conn.setRequestProperty("connection", "Keep-Alive");// 设置属性
//            conn.setRequestProperty("Content-Type", "text/xml");
//            conn.setRequestProperty("Charset", "utf-8");
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
////            BufferedWriter out1=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//
//            out = new PrintWriter(conn.getOutputStream());
//            // 发送请求参数
//            out.print(param);
//            // flush输出流的缓冲
//            out.flush();
//            // 定义BufferedReader输入流来读取URL的响应
//
//            in = new BufferedReader(
//                    new InputStreamReader(conn.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;//拿到服务器返回来的数据结果
//            }
//            System.out.print(result);
//        } catch (Exception e) {
//            System.out.println("发送 POST 请求出现异常！"+e);
//            e.printStackTrace();
//        }
//        //使用finally块来关闭输出流、输入流
//        finally{
//            try{
//                if(out!=null){
//                    out.close();
//                }
//                if(in!=null){
//                    in.close();
//                }
//            }
//            catch(IOException ex){
//                ex.printStackTrace();
//            }
//        }
//        String Tag="彭群";
//        //将拿到的数据结果进行解析---Gson
//        Gson gson = new Gson();
//        List<Cailiao> list = new ArrayList<Cailiao>();
//        Type listType = new TypeToken<List<Cailiao>>(){}.getType();
//        list = gson.fromJson(result, listType);

new Thread(new Runnable() {
    @Override
    public void run() {
        OutputStreamWriter osw = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL myUrl = new URL("http://61.164.45.100:8030/WebService1.asmx/GetItemSet");
            HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            osw = new OutputStreamWriter(conn.getOutputStream());
            osw.write("Taskid=1");
            osw.flush();
            osw.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String s = null;
            while ((s = br.readLine()) != null) {
                result += s;
            }
            System.out.print(result);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(osw!=null){
                    osw.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
            String Tag="彭群";
            //将拿到的数据结果进行解析---Gson
            

            Gson gson = new Gson();
            List<Cailiao> list = new ArrayList<>();
            Type listType = new TypeToken<List<Cailiao>>(){}.getType();
            Cailiao cailiao = gson.fromJson(result, Cailiao.class);
            list.add(cailiao);
            if(list==null){
                Log.d(Tag,"-----------------list为空");
            }
            if(list!=null&&list.size()>0){
                Log.d(Tag,"-----------------list不为空");
            }
        }
    }
}).start();


//        for (int i = 0; i < list.size(); i++) {
//            StringBuilder sb = new StringBuilder();
//            String s = list.get(i).toString();
//            append = sb.append(s);
//            Log.i(Tag,s);


    }
}
