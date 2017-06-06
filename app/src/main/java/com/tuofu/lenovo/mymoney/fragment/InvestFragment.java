package com.tuofu.lenovo.mymoney.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tuofu.lenovo.mymoney.R;
import com.tuofu.lenovo.mymoney.ZhiFuActivity;
import com.tuofu.lenovo.mymoney.common.thirdlogin.LoginApi;
import com.tuofu.lenovo.mymoney.common.thirdlogin.OnLoginListener;
import com.tuofu.lenovo.mymoney.common.thirdlogin.Tool;
import com.tuofu.lenovo.mymoney.common.thirdlogin.UserInfo;
import com.tuofu.lenovo.mymoney.util.UIUtils;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.CustomPlatform;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qq.QQWebShareAdapter;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by lenovo on 2017/4/16.
 */

public class InvestFragment extends Fragment {
    @Bind(R.id.bt_login)
    Button btLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UIUtils.getview(R.layout.investfragment);
        ButterKnife.bind(this, view);
        initPlatformList();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(QZone.NAME);
            }
        });
        return view;
    }

    private void login(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                return true;
            }

            public boolean onRegister(UserInfo info) {
                
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }
        });
        api.login(getActivity());
    }


    private void initPlatformList() {
        ShareSDK.initSDK(getActivity());
        Platform[] platformlist = ShareSDK.getPlatformList();
        if (platformlist != null) {

            for (Platform platform : platformlist) {
                if (!Tool.canGetUserInfo(platform)) {
                    continue;
                }
                if (platform instanceof CustomPlatform) {
                    continue;
                }
            }
        }
    }

                @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
