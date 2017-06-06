package honghu.com.test.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseActivity extends Activity {

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * ��д���෽����������ת�������¿�ʼactivity��������
     * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 4.0��ʱ�򻻻���
         */
        // ���Activity����ջ
        AppManager.getAppManager().addActivity(this);
        init();

    }

    /**
     *
     */
    private void init() {

    }

    /**
     * ����ȫ��
     */

    /**
     * ����
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        // getWindow().getDecorView().setSystemUiVisibility(View.GONE);
        /**
         * ����Ϊ����
         */

        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ����Activity&�Ӷ�ջ���Ƴ�
        AppManager.getAppManager().finishActivity(this);
    }
}
