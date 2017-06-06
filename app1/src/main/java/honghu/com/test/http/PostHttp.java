package honghu.com.test.http;

        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;

        import java.io.IOException;

        import honghu.com.test.jiekou.DataResult;
        import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.FormBody;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/2.
 */

public class PostHttp {
    private DataResult dataResult;
    private String url;
    private Handler handler;

    public PostHttp(String mUrl, Handler handler) {
        this.url = mUrl;
        this.handler = handler;
    }


    public void getPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("size", "10").build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        //在子线程里边开启任务
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到的请求结果
                String string = response.body().string();

                Log.d("pengqun","string------------"+string);
                Message msg = new Message().obtain();
                Bundle bundle = new Bundle();
                bundle.putString("res", string);
                msg.setData(bundle);
                handler.sendMessage(msg);
                //回调
//                dataResult.rtd(string);
            }
        });
    }
}
