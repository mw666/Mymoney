package honghu.com.test.photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import honghu.com.test.R;

public class SelPicsActivity extends AppCompatActivity {

    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.button1)
    Button button1;
    @Bind(R.id.listView1)
    ListView listView;
    ArrayList<String> listfile=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_pics);
        ButterKnife.bind(this);

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null) {
            if (bundle.getStringArrayList("files")!=null) {
                listfile= bundle.getStringArrayList("files");
                listView.setVisibility(View.VISIBLE);
                ArrayAdapter<String> arryAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listfile);
                listView.setAdapter(arryAdapter);
            }
        }
    }

    public void chise(View v){
        Intent intent = new Intent();
        intent.setClass(this,ImgFileListActivity.class);
        startActivity(intent);
    }
}
