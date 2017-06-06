package honghu.com.test;

import android.os.Bundle;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static honghu.com.test.Main6Activity.handler;

/**
 * Created by Administrator on 2017/4/27.
 */

public class Http {

    private String result;
    private String iUrl=null;

    public Http(String iUrl) {
        this.iUrl = iUrl;
    }
    //"http://192.168.10.234:8030/WebService1.asmx/GetItemSet"

    public void data() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    //指定地址
                    URL url = new URL(iUrl);
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
                    int c = result.length();
                    //将结果返回
                    result = result.substring(a, b + 1);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("re",result);
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    System.out.print("post请求发生异常" + e);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        return result;
    }
}
