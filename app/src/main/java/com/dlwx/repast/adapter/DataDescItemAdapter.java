package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.DataDescBean;
import com.dlwx.repast.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class DataDescItemAdapter extends BaseFastAdapter {
    private List<DataDescBean.BodyBean.ListBeanX> list;
    public DataDescItemAdapter(Context ctx,List<DataDescBean.BodyBean.ListBeanX> list) {
        super(ctx);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_datadesc, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DataDescBean.BodyBean.ListBeanX listBeanX = list.get(position);
        vh.tv_menuName.setText(listBeanX.getMenu_name());
        List<DataDescBean.BodyBean.ListBeanX.ListBean> list = listBeanX.getList();
        DataDescItem2Adapter item2Adapter = new DataDescItem2Adapter(ctx,list);
        vh.lv_list.setAdapter(item2Adapter);
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_menuName;
        public MyListView lv_list;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_menuName = (TextView) rootView.findViewById(R.id.tv_menuName);
            this.lv_list = (MyListView) rootView.findViewById(R.id.lv_list);
        }

    }
}
