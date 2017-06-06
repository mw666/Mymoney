package honghu.com.test;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import honghu.com.test.jiekou.Imooc;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/2.
 */

public class OkHttp {

    //get请求
//    private String mUrl = "http://www.imooc.com/api/teacher?type=4&num=30";
    private String mUrl;
    private String json;
    private OkHttpClient mOkHttpClient;
    private Imooc imooc;
    private String imoocData;

    public OkHttp(Imooc imooc) {
        this.imooc = imooc;
    }

    //异步get请求
    public void asyncGet() {
        mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(mUrl).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            private String str;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    str = response.cacheResponse().toString();
                    Log.i("------彭群", "cache------------------------" + str);
                } else {
                    response.body().toString();
                    String str = response.networkResponse().toString();
                    Log.i("------彭群", "network------------------------" + str);
                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

        });
    }

    //post请求
    public void post_a() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("size", "10").build();
                Request request = new Request.Builder().url(mUrl).post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        imoocData = response.body().string();
                        Log.i("彭群----", "s------------" + imoocData);

                    }
                });
            }
        }).start();
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("data",imoocData);
        msg.setData(bundle);

        imooc.imoocData(imoocData);
    }

}




