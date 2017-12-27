package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.RecordDescBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class RecordDescItemAdapter extends BaseFastAdapter {
    private List<RecordDescBean.BodyBean.DataBean.ListBean> list;
    public RecordDescItemAdapter(Context ctx, List<RecordDescBean.BodyBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_item_recore, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        RecordDescBean.BodyBean.DataBean.ListBean listBean = list.get(position);
        vh.tv_name.setText(listBean.getMenu_name());
            vh.tv_num.setText(listBean.getNum()+"");
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
        }

    }
}
