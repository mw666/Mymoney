package honghu.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import honghu.com.test.jiekou.CallBack;

public class WangActivity extends AppCompatActivity implements CallBack{
    private Li li;
    private String question="1+1";
    private String result;

    public WangActivity(Li li) {
        this.li = li;
    }

    public WangActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang);
        TextView wang_tv = (TextView) findViewById(R.id.wang_tv);
        WangActivity wangActivity = new WangActivity(new Li());
        wangActivity.askQuestion();
        String solve = wangActivity.solve(result);
        wang_tv.setText(solve);
    }

    public void askQuestion(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                li.executeMessage(WangActivity.this,question);
            }
        }).start();
    }

    @Override
    public String solve(String result) {
        Log.d("彭群","-------------result"+result);
        

        return result;
    }
}
