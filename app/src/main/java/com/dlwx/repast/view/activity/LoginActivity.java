package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.LoginBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.utiles.Regularutiles;
import com.dlwx.repast.utiles.SpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_findpwd)
    TextView tvFindpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_regis)
    TextView tvRegis;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantoubaise);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.tv_findpwd, R.id.btn_submit, R.id.tv_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_findpwd:
                startActivity(new Intent(ctx,ForgetPwdActivity.class));
                break;
            case R.id.btn_submit:
                    submit();
                break;
            case R.id.tv_regis:
                startActivity(new Intent(ctx,RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Regularutiles.Photo(phone)) {
            etPhone.setText("");
            Toast.makeText(ctx, "手机号格式不正确,请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",pwd);
        mPreenter.fetch(map,false, HttpUtiles.Login,"login");
    }
    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
        if (loginBean.getCode() == 200) {
            LoginBean.BodyBean body = loginBean.getBody();
            sp.edit().putString(SpUtiles.User_Token,body.getToken()).commit();
            sp.edit().putString(SpUtiles.Phone,body.getPhone()).commit();
            sp.edit().putString(SpUtiles.NickName,body.getNickname()).commit();
            sp.edit().putString(SpUtiles.Grade,body.getGrade()).commit();
            sp.edit().putString(SpUtiles.seller_id,body.getSeller_id()).commit();
            sp.edit().putString(SpUtiles.header_pic,body.getHeader_pic()).commit();
            MyApplication.User_Token = body.getToken();
            finish();
        }else{
            Toast.makeText(ctx, loginBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
