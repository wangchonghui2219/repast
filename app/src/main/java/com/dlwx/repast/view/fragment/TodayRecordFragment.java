package com.dlwx.repast.view.fragment;


import android.view.View;
import android.widget.ListView;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.TodayRecordAdapter;
import com.dlwx.repast.base.BaseFragment;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.RecordBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * s当天纪录
 */
public class TodayRecordFragment extends BaseFragment {
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_blank;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        getToday();
    }

    private void getToday() {
        Map<String,String> map =  new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,false, HttpUtiles.Record,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch("今天"+s);
        Gson gson = new Gson();
        RecordBean recordBean = gson.fromJson(s, RecordBean.class);
        if (recordBean.getCode() == 200) {
            RecordBean.BodyBean body = recordBean.getBody();
            List<RecordBean.BodyBean.DataBean> data = body.getData();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            TodayRecordAdapter recordAdapter = new TodayRecordAdapter(ctx,data,"1",format.format(date));
            lvList.setAdapter(recordAdapter);

        }

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
}
