package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.RecordAdapter;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.view.fragment.HistoryRecordFragment;
import com.dlwx.repast.view.fragment.TodayRecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 扫码记录
 */
public class ScRecordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tb_title)
    TabLayout tbTitle;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    List<String> tab_titles;
    List<Fragment> fragments;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_sc_record);
        ButterKnife.bind(this);
        initTabBar(toolbar);

    }

    @Override
    protected void initData() {
        titleTxt.setText("扫码记录");
        tvRight.setText("筛选历史纪录");
        tab_titles = new ArrayList<>();
        tab_titles.add("当天记录");
        tab_titles.add("历史纪录");
        fragments = new ArrayList<>();
        fragments.add(new TodayRecordFragment());
        fragments.add(new HistoryRecordFragment());
        RecordAdapter adapter = new RecordAdapter(getSupportFragmentManager(),tab_titles,fragments);
        viewpage.setAdapter(adapter);
        tbTitle.setupWithViewPager(viewpage);
        tbTitle.setTabsFromPagerAdapter(adapter);
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
       startActivity(new Intent(ctx,HistoryRecordActivity.class));
        
    }
}
