package com.dlwx.repast.view.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.model.bean.ResultBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.ButtonUtils;
import com.dlwx.repast.utiles.CountDownUtiles;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.utiles.Regularutiles;
import com.dlwx.repast.utiles.VerificationCodeUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_auth)
    EditText etAuth;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("忘记密码");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.btn_auth, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Regularutiles.Photo(phone)) {
                    Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
                CountDownUtiles countDownUtiles = new CountDownUtiles(btnAuth);
                if (!ButtonUtils.isFastDoubleClick(R.id.btn_auth,1000)) {
                    if (btnAuth.getText().equals("验证码")||btnAuth.getText().equals("重新发送")) {

                        VerificationCodeUtiles codeUtiles =  new VerificationCodeUtiles(ctx,phone,2,countDownUtiles);
                        codeUtiles.sendVerificationCode();
                    }
                }
                break;
            case R.id.btn_submit:
                submit();
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
            Toast.makeText(ctx, "手机号格式不正确", Toast.LENGTH_SHORT).show();
        }

        String auth = etAuth.getText().toString().trim();
        if (TextUtils.isEmpty(auth)) {
            Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String affpwd = etAffpwd.getText().toString().trim();
        if (TextUtils.isEmpty(affpwd)) {
            Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!affpwd.equals(pwd)) {
            etPwd.setText("");
            etAffpwd.setText("");
            Toast.makeText(ctx, "两次密码不一致 请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",pwd);
        map.put("repwd",affpwd);
        map.put("code",auth);
        mPreenter.fetch(map,false, HttpUtiles.ForgetPwd,"");

    }

    @Override
    public void showData(String s) {
        wch(s);
        loading.dismiss();
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            finish();
        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();

    }
}
