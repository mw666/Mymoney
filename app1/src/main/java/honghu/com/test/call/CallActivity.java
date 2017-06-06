package honghu.com.test.call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

/**
 * 调用系统电话
 */
public class CallActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.call_btn)
    Button callBtn;
    private int REQUESTCODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODE) {
            if (permissions[0].equals(Manifest.permission.CALL_PHONE) && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {
                //用户同意
                call();
            } else {
                //用户不同意

            }
        }
    }

    //拨打电话
    private void call() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkSelfPermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
            if (checkSelfPermission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUESTCODE);
            }
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("15171416706"));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}
