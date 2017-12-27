package com.dlwx.repast.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.LogUtiles;
import com.dlwx.repast.utiles.MyProgressLoading;
import com.dlwx.repast.utiles.SpUtiles;
import com.dlwx.repast.view.ViewInterface;

/**
 * Created by Administrator on 2017/8/12/012.
 */

public abstract class BaseFragment<V,T extends Presenter<V>> extends Fragment implements ViewInterface {
    public Context ctx;
    public T mPreenter;
    public MyProgressLoading loading;
    public SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ctx = getActivity();
        mPreenter = createPresenter();
        loading = new MyProgressLoading(ctx, R.style.DialogStyle);
        sp = ctx.getSharedPreferences(SpUtiles.SP_Mode,Context.MODE_PRIVATE);
        View view = inflater.inflate(getLayoutID(),null);




        initView(view);
        initDate();
        initListener();

        return view;
    }
    /**
     * 获得布局id
     * @return
     */
    public abstract int getLayoutID();

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initDate();

    /**
     * 时间监听
     */
    protected abstract void initListener();

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void showData(String s) {

    }
    public void wch(Object o){
        LogUtiles.LogI(o+"");
    }
    @Override
    public void disLoading() {
        loading.dismiss();
    }
    protected abstract T createPresenter();
    @Override
    public void onError() {
        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
        loading.dismiss();
    }
}
