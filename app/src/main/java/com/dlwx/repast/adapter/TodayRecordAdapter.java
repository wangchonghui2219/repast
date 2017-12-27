package com.dlwx.repast.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;
import com.dlwx.repast.model.bean.RecordBean;
import com.dlwx.repast.view.MyListView;
import com.dlwx.repast.view.activity.NumDetailsActivity;

import java.util.List;

/**
 * 今日纪录
 */

public class TodayRecordAdapter extends BaseFastAdapter {
    private List<RecordBean.BodyBean.DataBean> data;
    private String isday;
    private String day_time;
    public TodayRecordAdapter(Context ctx,List<RecordBean.BodyBean.DataBean> data,String isday,String day_time) {
        super(ctx);
        this.data =  data;
        this.isday = isday;
        this.day_time = day_time;
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
        final RecordBean.BodyBean.DataBean dataBean = data.get(position);
        vh.tv_title.setText(dataBean.getTitle());
        vh.tv_totalnum.setText(dataBean.getTotal_num()+"份");
        List<RecordBean.BodyBean.DataBean.ListBean> list = dataBean.getList();
        TodayRecordItemAdapter itemAdapter = new TodayRecordItemAdapter(ctx,list);
        vh.lv_list.setAdapter(itemAdapter);
        vh.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, NumDetailsActivity.class);

                intent.putExtra("is_day",isday);
                intent.putExtra("day_time",day_time);
                intent.putExtra("meal",dataBean.getMeal());
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
