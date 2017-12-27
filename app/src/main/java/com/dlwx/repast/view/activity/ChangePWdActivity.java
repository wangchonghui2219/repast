package com.dlwx.repast.view.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.model.bean.ResultBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.repast.base.MyApplication.User_Token;

public class ChangePWdActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_oldpwd)
    EditText etOldpwd;
    @BindView(R.id.et_newpwd)
    EditText etNewpwd;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("修改登录密码");
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
        String oldpwd = etOldpwd.getText().toString().trim();
        if (TextUtils.isEmpty(oldpwd)) {
            Toast.makeText(ctx, "旧密码不能为空", Toast.LENGTH_SHORT).show();
        }
        String newPwd = etNewpwd.getText().toString().trim();
        if (TextUtils.isEmpty(newPwd)) {
            Toast.makeText(ctx, "新密码不能为空", Toast.LENGTH_SHORT).show();
        }
        String affPwd = etAffpwd.getText().toString().trim();
        if (TextUtils.isEmpty(affPwd)) {
            Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
        }
        if (!affPwd.equals(newPwd)) {
            etAffpwd.setText("");
            etNewpwd.setText("");
            Toast.makeText(ctx, "两次秘密输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("password",oldpwd);
        map.put("new_pwd",newPwd);
        map.put("renew_pwd",affPwd);
        mPreenter.fetch(map,false, HttpUtiles.ChangePwd,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
