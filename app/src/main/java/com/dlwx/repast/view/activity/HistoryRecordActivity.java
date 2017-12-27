package com.dlwx.repast.view.activity;

import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.TodayRecordAdapter;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.interfac.publicInterface;
import com.dlwx.repast.model.bean.RecordBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.DataSeleteUtiles;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 帅选历史纪录
 */
public class HistoryRecordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.ll_entry)
    LinearLayout llEntry;
    private List<RecordBean.BodyBean.DataBean> data;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_history_record);
        ButterKnife.bind(this);
    }
    @Override
    protected void initData() {
        titleTxt.setText("扫码历史记录");
        initTabBar(toolbar);
        tvRight.setText("日历");
        Map<String,String> map =  new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map,false, HttpUtiles.Record,"");
    }
    @Override
    protected void initListener() {

    }
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @OnClick(R.id.tv_right)
    public void onViewClicked() {
        DataSeleteUtiles utiles = new DataSeleteUtiles();
        utiles.showDialog(ctx);
        utiles.setDate(new publicInterface.DateInterface() {
            @Override
            public void getDate(String date) {
                //TODO
                data.clear();
                tvData.setText(date);
                Map<String,String> map =  new HashMap<>();
                map.put("token", MyApplication.User_Token);
                map.put("day_time",date);
                mPreenter.fetch(map,false, HttpUtiles.Record,"");
            }
        });
    }
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        RecordBean recordBean = gson.fromJson(s, RecordBean.class);
        if (recordBean.getCode() == 200) {
            RecordBean.BodyBean body = recordBean.getBody();
            tvData.setText(body.getDay_time());
            data = body.getData();
            TodayRecordAdapter recordAdapter = new TodayRecordAdapter(ctx, data,"1",body.getDay_time());
            lvList.setAdapter(recordAdapter);

        }
    }
}
