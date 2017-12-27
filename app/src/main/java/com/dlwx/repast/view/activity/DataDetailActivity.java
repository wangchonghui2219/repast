package com.dlwx.repast.view.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.adapter.MyPageAdapter;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.DataDescBean;
import com.dlwx.repast.model.bean.DataDescClassBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订餐数据详情
 */
public class DataDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    List<String> tab_titles;
    @BindView(R.id.sli_tab)
    SlidingTabLayout sliTab;
    private List<DataDescClassBean.BodyBean.ClassifyBean> classify;
    private String meal;
    private MyPageAdapter adapter;
    private List<DataDescBean.BodyBean.ListBeanX> list = new ArrayList<>();

    @Override
    protected void initView() {
        meal = getIntent().getStringExtra("meal");
        setContentView(R.layout.activity_data_detail);
        ButterKnife.bind(this);
    }
    @Override
    protected void initData() {
        initTabBar(toolbar);
        titleTxt.setText("九点餐");
        tab_titles = new ArrayList<>();
        tab_titles.add("套餐");
        tab_titles.add("面食");
        tab_titles.add("小吃");
        getDataTitle();
    }
    private int HttpType;
    private void getDataTitle() {
        HttpType = 1;
        Map<String,String> map = new HashMap<>();
        mPreenter.fetch(map,true, HttpUtiles.Data_Desc_title,"Data_Desc_title");
    }

    @Override
    protected void initListener() {
        sliTab.setOnTabSelectListener(onTabSelectListener);
        viewpage.setOnPageChangeListener(onPageChangeListener);
        }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        if (HttpType == 1) {
            jsonClassTitle(s);
        }else if(HttpType == 2){
            jsonData(s);
        }
    }

    /**
     * 数据内容
     * @param s
     */
    private void jsonData(String s) {
            Gson gson = new Gson();
        DataDescBean dataDescBean = gson.fromJson(s, DataDescBean.class);
        if (dataDescBean.getCode() == 200) {
            DataDescBean.BodyBean body = dataDescBean.getBody();
            list = body.getList();
            adapter.setData(list);
        }else{
            Toast.makeText(ctx, dataDescBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private String[] strs;
    /**
     * 头部分类
     */
    private void jsonClassTitle(String s) {
        Gson gson = new Gson();
        DataDescClassBean dataDescClassBean = gson.fromJson(s, DataDescClassBean.class);
        if (dataDescClassBean.getCode() == 200) {
            DataDescClassBean.BodyBean body = dataDescClassBean.getBody();
            classify = body.getClassify();
            strs = new String[classify.size()];
            for (int i = 0; i < classify.size(); i++) {

                strs[i] = classify.get(i).getType_name();
            }
            String type_id = classify.get(0).getType_id();
            adapter = new MyPageAdapter(ctx, strs,list);
            viewpage.setAdapter(adapter);
            sliTab.setViewPager(viewpage,strs);
            getCountData(type_id);
        }else{
            Toast.makeText(ctx, dataDescClassBean.getResult(), Toast.LENGTH_SHORT).show();
        }
    }

    private OnTabSelectListener onTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelect(int position) {
//            viewpage.setCurrentItem(position);
        }

        @Override
        public void onTabReselect(int position) {

        }
    };
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            sliTab.setCurrentTab(position);
            String type_id = classify.get(position).getType_id();
            getCountData(type_id);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 重新请求数据
     * @param type_id
     */
    private void getCountData(String type_id) {
        HttpType = 2;
        wch(MyApplication.User_Token+"\n"+type_id);
        Map<String,String> map = new HashMap<>();
        map.put("token", MyApplication.User_Token);
        map.put("type_id",type_id);
        map.put("meal",meal);
        mPreenter.fetch(map,false, HttpUtiles.Order_Meal,"");
    }
}
