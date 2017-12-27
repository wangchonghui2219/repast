package com.dlwx.repast.view.activity;

import android.app.Service;
import android.os.Vibrator;
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
import com.dlwx.repast.utiles.SpUtiles;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dlwx.repast.base.MyApplication.User_Token;

/**
 * 修改呢称
 */
public class ChangeNickNameActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_nickName)
    EditText etNickName;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private Vibrator vibrator=null;
    private String nickName;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_nick_name);
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        titleTxt.setText("昵称");
        initTabBar(toolbar);
        etNickName.setText(sp.getString(SpUtiles.NickName,""));
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
        nickName = etNickName.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)) {
            Toast.makeText(ctx, "昵称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nickName.equals(sp.getString(SpUtiles.NickName,""))) {
            vibrator.vibrate(100);
            return;
        }
        Map<String,String> map = new HashMap<>();
        map.put("token",User_Token);
        map.put("nickname", nickName);
        mPreenter.fetch(map,false, HttpUtiles.ChangeNickName,"");
    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        Gson gson = new Gson();
        ResultBean resultBean = gson.fromJson(s, ResultBean.class);
        if (resultBean.getCode() == 200) {
            sp.edit().putString(SpUtiles.NickName,nickName).commit();
         finish();
        }
        Toast.makeText(ctx, resultBean.getResult(), Toast.LENGTH_SHORT).show();
    }


}
