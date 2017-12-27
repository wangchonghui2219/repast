package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.ResultBean;
import com.dlwx.repast.model.bean.VerSuccBean;
import com.dlwx.repast.model.bean.VerSuccItemAdapter;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifySuccessActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_orderid)
    TextView tvOrderid;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.tv_totalprices)
    TextView tvTotalprices;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_jobnumber)
    TextView tvJobnumber;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.btn_style)
    Button btnStyle;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.ll_lead)
    LinearLayout llLead;
    @BindView(R.id.tv_phone1)
    TextView tvPhone1;
    @BindView(R.id.tv_jobnum)
    TextView tvJobnum;
    @BindView(R.id.tv_pickname)
    TextView tvPickname;
    private String order_id;
    private String pick_code;
    private VerSuccBean.BodyBean body;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        body = (VerSuccBean.BodyBean) intent.getSerializableExtra("body");
        setContentView(R.layout.activity_verify_success);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        getData();
        titleTxt.setText("验证成功");
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private int HttpType;
    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        HttpType = 2;
        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("order_id", order_id);
        mPreenter.fetch(map,false,HttpUtiles.Aff_NeckMEal,"");
    }

    private void getData() {
        tvOrderid.setText(body.getOrder_no());
        tvDate.setText(body.getMeal_time());
        List<VerSuccBean.BodyBean.DescBean> desc = body.getDesc();
        VerSuccItemAdapter itemAdapter = new VerSuccItemAdapter(ctx, desc);
        lvList.setAdapter(itemAdapter);
        tvTotalprices.setText("总价：￥" + body.getTotal_price());
        tvUserName.setText(body.getNickname());
        tvJobnumber.setText(body.getExten_code());
        btnStyle.setText(body.getMeal());
        tvPhone.setText("电话：" + body.getPhone());
        order_id = body.getOrder_id();
        String pick_type = body.getPick_type();
        if (pick_type.equals("2")) {
            llLead.setVisibility(View.VISIBLE);
            tvPhone1.setText(body.getPick_phone());
            tvJobnum.setText(body.getPick_exten());
            tvPickname.setText("代领");
        }else{
            tvPickname.setText("自领");
        }
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();

            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            if (resultBean.getCode() == 200) {
                finish();
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            }
    }
}
