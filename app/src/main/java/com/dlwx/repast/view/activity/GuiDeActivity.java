package com.dlwx.repast.view.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dlwx.repast.MainActivity;
import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuiDeActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btn_start)
    Button btnStart;
    private int[] pics ={
            R.mipmap.icon_gui1,
            R.mipmap.icon_gui2,
            R.mipmap.icon_gui3,
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_gui_ge);
        ButterKnife.bind(this);

        viewPager.setAdapter(new PageAdapter());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    btnStart.setVisibility(View.VISIBLE);
                }else{
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.btn_start)
    public void onViewClicked() {

        startActivity(new Intent(GuiDeActivity.this, MainActivity.class));
        finish();

    }
    private class PageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pics.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = (ImageView) LinearLayout.inflate(GuiDeActivity.this,R.layout.ite_page,null);
            Glide.with(GuiDeActivity.this).load(pics[position]).into(view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
