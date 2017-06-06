package honghu.com.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main4Activity extends AppCompatActivity {

    @Bind(R.id.tv_one)
    TextView tvOne;
    @Bind(R.id.tv_two)
    TextView tvTwo;
    private String iUrl="http://192.168.10.234:8030/WebService1.asmx/GetItemSet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
//        Http http = new Http();
//        String data = http.data(iUrl);
//        tvOne.setText(data);

//        JsonParser jsonParser = new JsonParser();
//        JsonArray asJsonArray = jsonParser.parse(data).getAsJsonArray();
//        Gson gson = new Gson();
//        ArrayList<Cailiao> list = new ArrayList<Cailiao>();
//        for (JsonElement user : asJsonArray) {
//            //使用GSON，直接转成Bean对象
//            Cailiao cailiao = gson.fromJson(user, Cailiao.class);
//            list.add(cailiao);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            Cailiao cailiao = list.get(i);
////                        tv2.setText(cailiao.getItemName());
//        }
    }
}
