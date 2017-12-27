package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.RecordDescAdapter;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.RecordDescBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 详情
 */
public class NumDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;
    private String is_day;
    private String day_time;
    private String meal;
    @Override
    protected void initView() {
        Intent intent = getIntent();
        is_day = intent.getStringExtra("is_day");
        day_time = intent.getStringExtra("day_time");
        meal = intent.getStringExtra("meal");
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("数量");
        getData();
    }


    @Override
    protected void initListener() {

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private void getData() {
        wch(meal+"\n"+day_time+"\n"+is_day);
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("is_day", is_day);
        map.put("day_time", day_time);
        map.put("meal", meal);
        mPreenter.fetch(map, false, HttpUtiles.Recore_desc, "");

    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        RecordDescBean recordDescBean = gson.fromJson(s, RecordDescBean.class);
        if (recordDescBean.getCode() == 200) {
            RecordDescBean.BodyBean body = recordDescBean.getBody();
            titleTxt.setText(body.getTitle());
            List<RecordDescBean.BodyBean.DataBean> data = body.getData();
            RecordDescAdapter descAdapter = new RecordDescAdapter(ctx,data);
            lvList.setAdapter(descAdapter);
        } else {

        }
    }

}
