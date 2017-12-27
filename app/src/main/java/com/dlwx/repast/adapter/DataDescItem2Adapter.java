package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.DataDescBean;
import com.dlwx.repast.view.MyGridView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class DataDescItem2Adapter extends BaseFastAdapter {
    private List<DataDescBean.BodyBean.ListBeanX.ListBean> list;

    public DataDescItem2Adapter(Context ctx, List<DataDescBean.BodyBean.ListBeanX.ListBean> list) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_datadesc_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        DataDescBean.BodyBean.ListBeanX.ListBean listBean = list.get(position);
        vh.tv_num.setText(listBean.getNum()+"份");
        String xuancan = listBean.getXuancan();
        String[] xuancans = xuancan.split(",");
        DataDescItemGridViewAdapter gridViewAdapter = new DataDescItemGridViewAdapter(ctx,xuancans);
        vh.gv_list.setAdapter(gridViewAdapter);
        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public MyGridView gv_list;
        public TextView tv_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.gv_list = (MyGridView) rootView.findViewById(R.id.gv_list);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
        }

    }
}
