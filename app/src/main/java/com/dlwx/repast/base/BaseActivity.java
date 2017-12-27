package com.dlwx.repast.base;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dlwx.repast.R;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.LogUtiles;
import com.dlwx.repast.utiles.MyProgressLoading;
import com.dlwx.repast.utiles.SpUtiles;
import com.dlwx.repast.view.ViewInterface;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/8/12/012.
 */

public abstract class BaseActivity<V,T extends Presenter<V>> extends AppCompatActivity implements ViewInterface,View.OnClickListener{
    public T mPreenter;
    public Context ctx;
    public MyProgressLoading loading;
    public SharedPreferences sp;
    public Vibrator vibrator=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreenter = createPresenter();
        setImmer(R.color.trans);
        ctx = this;
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        loading = new MyProgressLoading(ctx, R.style.DialogStyle);
        sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        initView();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    protected abstract T createPresenter();


    @Override
    protected void onDestroy() {
        mPreenter.detachView();
        super.onDestroy();

    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void onError() {
        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
        loading.dismiss();
    }

    @Override
    public void showData(String s) {
        wch(s);
        loading.show();
    }

    @Override
    public void onClick(View v) {

    }
    public void wch(Object o){
        LogUtiles.LogI(o+"");
    }

    @Override
    public void disLoading() {
        loading.dismiss();
    }
    public void initTabBar(Toolbar toolbar){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantou);
        setImmer(R.color.trans);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
             switch (item.getItemId()){
                        case android.R.id.home:
                            finish();
                            break;
                    }

        return true;
    }

    public void setImmer(int color){
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                .statusBarAlpha(1.0f)  //状态栏透明度，不写默认0.0f
                .navigationBarAlpha(1.0f)  //导航栏透明度，不写默认0.0F
                .barAlpha(1.0f)  //状态栏和导航栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .fullScreen(false)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .setViewSupportTransformColor(toolbar) //设置支持view变色，支持一个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .statusBarView(view)  //解决状态栏和布局重叠问题
//                .fitsSystemWindows(false)    //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .statusBarColorTransform(color)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView()  //移除通过setViewSupportTransformColor()方法指定的view
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
                .init();  //必须调用方可沉浸式
    }
}
