package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.VerSuccBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 手动输入
 */
public class InputActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_input);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("餐券验证");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        String pick_code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(pick_code)) {
            vibrator.vibrate(50);
            return;
        }



        Map<String, String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("pick_code", pick_code);
        mPreenter.fetch(map, false, HttpUtiles.Scan_Code, "");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        VerSuccBean verSuccBean = gson.fromJson(s, VerSuccBean.class);
        if (verSuccBean.getCode() == 200) {
            VerSuccBean.BodyBean body = verSuccBean.getBody();
            Intent intent = new Intent(ctx, VerifySuccessActivity.class);
            intent.putExtra("body",body);
            startActivity(intent);
        } else {
            Toast.makeText(ctx, verSuccBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
