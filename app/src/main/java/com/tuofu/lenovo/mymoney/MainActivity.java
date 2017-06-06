package com.tuofu.lenovo.mymoney;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.tuofu.lenovo.mymoney.fragment.AssetFragment;
import com.tuofu.lenovo.mymoney.fragment.HomeFragment;
import com.tuofu.lenovo.mymoney.fragment.InvestFragment;
import com.tuofu.lenovo.mymoney.fragment.MoreFragment;
import com.tuofu.lenovo.mymoney.util.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.fl)
    FrameLayout fl;
    @Bind(R.id.shouye)
    LinearLayout shouye;
    @Bind(R.id.invest)
    LinearLayout invest;
    @Bind(R.id.asset)
    LinearLayout asset;
    @Bind(R.id.more)
    LinearLayout more;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.shouye_iv)
    ImageView shouyeIv;
    @Bind(R.id.shou_tv)
    TextView shouTv;
    @Bind(R.id.invest_iv)
    ImageView investIv;
    @Bind(R.id.invest_tv)
    TextView investTv;
    @Bind(R.id.asset_iv)
    ImageView assetIv;
    @Bind(R.id.asset_tv)
    TextView assetTv;
    @Bind(R.id.more_iv)
    ImageView moreIv;
    @Bind(R.id.more_tv)
    TextView moreTv;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        select(0);
    }

    @OnClick({R.id.asset, R.id.shouye, R.id.invest, R.id.more})
    public void clicktab(View view) {
        switch (view.getId()) {
            case R.id.shouye:
                select(0);
                break;
            case R.id.invest:
                select(1);
                break;
            case R.id.asset:
                select(2);
                break;
            case R.id.more:
                select(3);
                break;
        }
    }

    Fragment homefragment =null;
    Fragment investfragment =null;
    Fragment assetfragment =null;
    Fragment morefragment =null;
    private void select(int i) {
        FragmentManager fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hindefragment();
        innidata();
        switch (i) {
            case 0:
                if (homefragment==null){
                    homefragment=new HomeFragment();
                    transaction.add(R.id.fl,homefragment);
                }
                  shouyeIv.setImageResource(R.mipmap.bid01);
                 shouTv.setTextColor(UIUtils.getcolor(R.color.home_back_selected));
                    transaction.show(homefragment);
                break;
            case 1:
                if (investfragment==null){
                    investfragment=new InvestFragment();
                    transaction.add(R.id.fl,investfragment);
                }
                investIv.setImageResource(R.mipmap.bid03);
                investTv.setTextColor(UIUtils.getcolor(R.color.home_back_selected));
                transaction.show(investfragment);
                break;
            case 3:
                if (morefragment==null){
                    morefragment=new MoreFragment();
                    transaction.add(R.id.fl,morefragment);
                }
                moreIv.setImageResource(R.mipmap.bid07);
                moreTv.setTextColor(UIUtils.getcolor(R.color.home_back_selected));
                transaction.show(morefragment);
                break;
            case 2:
                if (assetfragment==null){
                    assetfragment=new AssetFragment();
                    transaction.add(R.id.fl,assetfragment);
                }
                assetIv.setImageResource(R.mipmap.bid05);
                assetTv.setTextColor(UIUtils.getcolor(R.color.home_back_selected));
                transaction.show(assetfragment);
                break;
        }
        transaction.commit();
    }
    

    private void hindefragment() {
        if (homefragment!=null) {
            transaction.hide(homefragment);
        }
        if (investfragment!=null) {
            transaction.hide(investfragment);
        }
        if (assetfragment!=null) {
            transaction.hide(assetfragment);
        }
        if (morefragment!=null) {
            transaction.hide(morefragment);
        }
    }

    private void innidata() {
//        String s=null;
//        if(s.equals("")){
//            
//        }
        shouyeIv.setImageResource(R.mipmap.bid02);
        shouTv.setTextColor(UIUtils.getcolor(R.color.home_back_unselected));
        investIv.setImageResource(R.mipmap.bid04);
        investTv.setTextColor(UIUtils.getcolor(R.color.home_back_unselected));
        assetIv.setImageResource(R.mipmap.bid06);
        assetTv.setTextColor(UIUtils.getcolor(R.color.home_back_unselected));
        moreIv.setImageResource(R.mipmap.bid08);
        moreTv.setTextColor(UIUtils.getcolor(R.color.home_back_unselected));
    }
}
