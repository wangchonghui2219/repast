package com.dlwx.albumorvideolib.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlwx.albumorvideolib.R;
import com.dlwx.albumorvideolib.bean.Video;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 视频列表
 */

public class VideoListeAdapter extends BaseAdapter {
    private List<Video> list;
    private Context ctx;
    public VideoListeAdapter(Context ctx, List<Video> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_video, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Video video = list.get(position);
        if (pos == -1) {

            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(video.getPath());
            Bitmap bitmapvido = mmr.getFrameAtTime();
            vh.iv_pic.setImageBitmap(bitmapvido);
            mmr.release();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        String format1 = format.format(new Date(video.getData()));
        vh.time.setText(format1+" "+(video.getSize()/1024/1024)+"MB");
        vh.tv_name.setText(video.getDisplayName());
//        if (position == pos) {
//            vh.cb_check.setBackgroundResource(R.mipmap.icon_xuanzea);
//        }else{
//            vh.cb_check.setBackgroundResource(R.mipmap.icon_xuanze);
//        }


        return convertView;
    }
    private int pos = -1;
    public void setSeletePos(int position) {
        this.pos = position;
        notifyDataSetChanged();
    }


    private class ViewHolder {
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_name;
        public TextView time;
        public TextView id_size;
        public ImageView cb_check;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.time = (TextView) rootView.findViewById(R.id.time);
            this.id_size = (TextView) rootView.findViewById(R.id.id_size);
            this.cb_check = (ImageView) rootView.findViewById(R.id.cb_check);
        }

    }
}
