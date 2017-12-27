package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.LoadWEBUtiles;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.web_view)
    WebView webView;
    private String url;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
            initTabBar(toolbar);
        titleTxt.setText("关于我们");

    }

    @Override
    protected void initListener() {
        LoadWEBUtiles loadWEBUtiles = new LoadWEBUtiles(ctx);
        loadWEBUtiles.setListViewData(url,webView,null);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

}
