package honghu.com.test;

import honghu.com.test.jiekou.CallBack;

/**
 * Created by Administrator on 2017/5/2.
 */

public class Li {
    public void executeMessage(CallBack callBack, String question) {
        String resolve = "2";
        callBack.solve(resolve);
    }
}
