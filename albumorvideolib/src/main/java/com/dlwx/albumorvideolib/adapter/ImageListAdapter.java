package com.dlwx.albumorvideolib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlwx.albumorvideolib.R;
import com.dlwx.albumorvideolib.bean.ImgesLisetBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 图片列表
 */

public class ImageListAdapter extends BaseAdapter {
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private List<ImgesLisetBean.Image> images;
    private Context ctx;
    public ImageListAdapter(Context ctx, List<ImgesLisetBean.Image> images) {
        this.ctx = ctx;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_image_list, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        ImgesLisetBean.Image image = images.get(position);
        String path = image.getPath();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        String format1 = format.format(new Date(image.getDate()));
        vh.tv_time.setText(format1);
        Picasso.with(ctx).load(path).into(vh.iv_image);
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public ImageView iv_image;
        public TextView tv_time;
        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_image = (ImageView) rootView.findViewById(R.id.iv_image);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
        }

    }
}
