package honghu.com.test.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class Dao {
    private List<Data> dataList;

    public Dao(List<Data> dataList) {
        this.dataList = dataList;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
}
