package com.dlwx.repast.model.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30/030.
 */

public class VerSuccItemAdapter extends BaseFastAdapter {
    private List<VerSuccBean.BodyBean.DescBean> desc;
    public VerSuccItemAdapter(Context ctx,List<VerSuccBean.BodyBean.DescBean> desc) {
        super(ctx);
        this.desc = desc;
    }

    @Override
    public int getCount() {
        return desc.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_versucc, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        VerSuccBean.BodyBean.DescBean descBean = desc.get(position);
        vh.tv_mename.setText(descBean.getMenu_name());
        vh.tv_price.setText("￥"+descBean.getPrice());
        vh.tv_num.setText("x"+descBean.getNum());
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_mename;
        public TextView tv_price;
        public TextView tv_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_mename = (TextView) rootView.findViewById(R.id.tv_mename);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
        }

    }
}
