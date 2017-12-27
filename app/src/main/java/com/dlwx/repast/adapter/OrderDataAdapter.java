package com.dlwx.repast.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.OrderDataBean;
import com.dlwx.repast.view.MyListView;
import com.dlwx.repast.view.activity.DataDetailActivity;

import java.util.List;

/**
 * 今日纪录
 */

public class OrderDataAdapter extends BaseFastAdapter {
    private List<OrderDataBean.BodyBean> body;
    private String isday;
    private String day_time;
    public OrderDataAdapter(Context ctx, List<OrderDataBean.BodyBean> body, String isday, String day_time) {
        super(ctx);
        this.body =  body;
        this.isday = isday;
        this.day_time = day_time;
    }

    @Override
    public int getCount() {
        return body.size();
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
        final OrderDataBean.BodyBean bodyBean = body.get(position);
        vh.tv_title.setText(bodyBean.getTitle());
        vh.tv_totalnum.setText(bodyBean.getTotal_num()+"份");
        final List<OrderDataBean.BodyBean.ListBean> list = bodyBean.getList();
        OrderDataItemAdapter itemAdapter = new OrderDataItemAdapter(ctx,list);
        vh.lv_list.setAdapter(itemAdapter);
        vh.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DataDetailActivity.class);
                intent.putExtra("meal",bodyBean.getMeal());
                ctx.startActivity(intent);
            }
        });
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
