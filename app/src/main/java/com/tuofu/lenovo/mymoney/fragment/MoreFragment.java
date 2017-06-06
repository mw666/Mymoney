package com.tuofu.lenovo.mymoney.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuofu.lenovo.mymoney.R;
import com.tuofu.lenovo.mymoney.util.UIUtils;

/**
 * Created by lenovo on 2017/4/16.
 */

public class MoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view= UIUtils.getview(R.layout.morefragment);
        return view ;
    }
}
