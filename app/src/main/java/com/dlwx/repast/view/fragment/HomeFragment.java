package com.dlwx.repast.view.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFragment;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.view.activity.OrderDataActivity;
import com.dlwx.repast.view.activity.ScRecordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment<T> extends BaseFragment {


    @BindView(R.id.ll_record)
    LinearLayout llRecord;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    Unbinder unbinder;
    Intent intent;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
    }

    @Override
    protected void initListener() {
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.ll_record)
    public void onLlRecordClicked() {//扫码记录
        startActivity(new Intent(ctx,ScRecordActivity.class));
    }

    @OnClick(R.id.ll_data)
    public void onLlDataClicked() {//订餐数据
        startActivity(new Intent(ctx,OrderDataActivity.class));
    }



}
