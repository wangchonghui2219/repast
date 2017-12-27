package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;

/**
 * Created by Administrator on 2017/8/31/031.
 */

public class DataDescItemGridViewAdapter extends BaseFastAdapter {
    private String[] xuancans;
    public DataDescItemGridViewAdapter(Context ctx,String[] xuancans) {
        super(ctx);
        this.xuancans = xuancans;
    }

    @Override
    public int getCount() {
        return xuancans.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_desc_gridview, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        String xuancan = xuancans[position];
        vh.tv_menuName.setText(xuancan);
        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public TextView tv_menuName;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_menuName = (TextView) rootView.findViewById(R.id.tv_menuName);
        }

    }
}
