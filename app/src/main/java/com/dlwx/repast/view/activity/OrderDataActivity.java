package com.dlwx.repast.view.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.OrderDataAdapter;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.OrderDataBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订餐数据
 */
public class OrderDataActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_list)
    ListView lvList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order_data);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("订餐数据");
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
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        mPreenter.fetch(map, false, HttpUtiles.Meal_Data, "");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        OrderDataBean orderDataBean = gson.fromJson(s, OrderDataBean.class);
        if (orderDataBean.getCode() == 200) {
            List<OrderDataBean.BodyBean> body = orderDataBean.getBody();
            OrderDataAdapter dataAdapter = new OrderDataAdapter(ctx, body,"2","");
            lvList.setAdapter(dataAdapter);
        } else {
            Toast.makeText(ctx, orderDataBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }


}
