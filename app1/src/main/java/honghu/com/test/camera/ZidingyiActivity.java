package honghu.com.test.camera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

public class ZidingyiActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.surfaceView)
    SurfaceView surfaceView;
    @Bind(R.id.take_photo)
    Button takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zidingyi);
        ButterKnife.bind(this);
        takePhoto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:

                break;
        }
    }
}
