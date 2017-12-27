package com.dlwx.albumorvideolib.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dlwx.albumorvideolib.R;
import com.dlwx.albumorvideolib.adapter.VideoListeAdapter;
import com.dlwx.albumorvideolib.bean.Video;
import com.dlwx.albumorvideolib.utiles.InitColor;
import com.dlwx.albumorvideolib.utiles.VideoProvider;

import java.util.List;

/**
 * 视频列表
 */
public class VideoListActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title_txt;
    private GridView gv_list;
    private String path;
    private RelativeLayout rl_bottom;
    private TextView tv_cancel,tv_aff;
    private VideoListeAdapter adapter;

    @Override
    public void initDate() {

    }

    @Override
    public void initListener() {

        tv_aff.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);

    }
    @Override
    public void initView() {
        setContentView(R.layout.activity_video_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(InitColor.Tiele_Color);
        title_txt = (TextView) findViewById(R.id.title_txt);
        gv_list = (GridView) findViewById(R.id.gv_list);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_aff = (TextView) findViewById(R.id.tv_aff);
        initToolBar(toolbar);
        title_txt.setText("选择视频");
        VideoProvider videoProvider = new VideoProvider(ctx);
        final List<Video> list = videoProvider.getList();
        adapter = new VideoListeAdapter(ctx,list);
        gv_list.setAdapter(adapter);

        gv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSeletePos(position);
                Video video = list.get(position);
                path = video.getPath();

                rl_bottom.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            adapter.setSeletePos(-1);
            path = "";
            rl_bottom.setVisibility(View.GONE);

        } else if (i == R.id.tv_aff) {
            Intent intent = new Intent();
            intent.putExtra("path", path);

            setResult(4, intent);
            finish();

        }
    }
}
