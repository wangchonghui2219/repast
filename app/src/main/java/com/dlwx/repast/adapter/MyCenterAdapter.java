package com.dlwx.repast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlwx.repast.R;
import com.dlwx.repast.base.BaseFastAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人中心
 */

public class MyCenterAdapter extends BaseFastAdapter {
    String [] strs;
    int[] pics;
    public MyCenterAdapter(Context ctx,String [] strs,int[] pics) {
        super(ctx);
        this.pics = pics;
        this.strs = strs;
    }

    @Override
    public int getCount() {
        return strs.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_mycenter, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tvText.setText(strs[position]);
        Glide.with(ctx).load(pics[position]).into(vh.ivPic);
        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
