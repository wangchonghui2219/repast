package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.RecordDescBean;
import com.dlwx.repast.view.MyListView;

import java.util.List;

/**
 * 今日纪录
 */

public class RecordDescAdapter extends BaseFastAdapter {
    private List<RecordDescBean.BodyBean.DataBean> data;
    public RecordDescAdapter(Context ctx, List<RecordDescBean.BodyBean.DataBean> data) {
        super(ctx);
        this.data =  data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_record, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        RecordDescBean.BodyBean.DataBean dataBean = data.get(position);
        vh.tv_title.setText(dataBean.getType_name());
        vh.tv_totalnum.setText(dataBean.getTotal_num()+"份");
        List<RecordDescBean.BodyBean.DataBean.ListBean> list = dataBean.getList();
        RecordDescItemAdapter itemAdapter = new RecordDescItemAdapter(ctx,list);
        vh.lv_list.setAdapter(itemAdapter);
        vh.tv_desc.setVisibility(View.GONE);
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public TextView tv_totalnum;
        public MyListView lv_list;
        public TextView tv_desc;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_totalnum = (TextView) rootView.findViewById(R.id.tv_totalnum);
            this.lv_list = (MyListView) rootView.findViewById(R.id.lv_list);
            this.tv_desc = (TextView) rootView.findViewById(R.id.tv_desc);
        }

    }
}
