package honghu.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.bean.DataBean;

public class Main16Activity extends AppCompatActivity {

    @Bind(R.id.textview)
    TextView textview;
    private List<DataBean> dataBeanList;

    public Main16Activity(List<DataBean> list) {
        this.dataBeanList=list;
    }

    public Main16Activity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        ButterKnife.bind(this);
        Main13Activity main13Activity = new Main13Activity();
//        ArrayList<DataBean> dataBeanArrayList = main13Activity.dataBeanArrayList;
        DataBean bean = (DataBean) getApplication();
        DataBean.DispatchList dispatchList = bean.getDispatchList();
        String address = dispatchList.getAddress();
        textview.setText("pengqun"+address+"------");
    }
}
