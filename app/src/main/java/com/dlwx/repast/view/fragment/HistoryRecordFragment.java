package com.dlwx.repast.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.HistoryRecordItemAdapter;
import com.dlwx.repast.base.BaseFragment;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.HistoryRecordBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.view.activity.NumDetailsActivity;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 历史纪录
 */
public class HistoryRecordFragment extends BaseFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;
    @BindView(R.id.tv_lookdesc)
    TextView tvLookdesc;
    Unbinder unbinder1;
    private HistoryRecordBean.BodyBean body;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_history_record;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        getData();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map, false, HttpUtiles.History_Record, "");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch("历史" + s);
        Gson gson = new Gson();
        HistoryRecordBean historyRecordBean = gson.fromJson(s, HistoryRecordBean.class);
        if (historyRecordBean.getCode() == 200) {
            body = historyRecordBean.getBody();
            List<HistoryRecordBean.BodyBean.ListBean> list = body.getList();
            tvName.setText(body.getTitle());
            tvNum.setText(body.getTotal_num() + "份");
            HistoryRecordItemAdapter itemAdapter = new HistoryRecordItemAdapter(ctx, list);
            lvList.setAdapter(itemAdapter);
        } else {
            Toast.makeText(ctx, historyRecordBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.tv_lookdesc)
    public void onViewClicked() {//查看详情

                Intent intent = new Intent(ctx, NumDetailsActivity.class);
                intent.putExtra("is_day","2");
                intent.putExtra("day_time","");
                intent.putExtra("meal",body.getMeal());
                ctx.startActivity(intent);
    }
}


