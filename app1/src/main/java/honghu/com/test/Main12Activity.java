package honghu.com.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main12Activity extends AppCompatActivity {
    private final int REQUESTCODE = 101;
    @Bind(R.id.btnChuangjian)
    Button btnChuangjian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        ButterKnife.bind(this);
        /**
         * android 6.0创建文件夹
         */
        btnChuangjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();//执行创建新文件夹的方法
            }
        });

    }

    private void test() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
