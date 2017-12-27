package com.dlwx.repast.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dlwx.repast.R;
import com.dlwx.repast.model.bean.DataDescBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14/014.
 */

public class MyPageAdapter extends PagerAdapter {
    private Context ctx;
    private String[] strs;//头
    private List<DataDescBean.BodyBean.ListBeanX> list;
    public MyPageAdapter(Context ctx, String[] strs,List<DataDescBean.BodyBean.ListBeanX> list) {
        super();
        this.ctx = ctx;
        this.strs = strs;
        this.list = list;
    }

    @Override
    public int getCount() {
        return strs.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View  view = LayoutInflater.from(ctx).inflate(R.layout.item_page,null);
        ListView listView = (ListView) view.findViewById(R.id.lv_list);
        DataDescItemAdapter itemAdapter = new DataDescItemAdapter(ctx,list);
        listView.setAdapter(itemAdapter);
        container.addView(view);
        return view;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strs[position];
    }

    public void setData(List<DataDescBean.BodyBean.ListBeanX> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}




