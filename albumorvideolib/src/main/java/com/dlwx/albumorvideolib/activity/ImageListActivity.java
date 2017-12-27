package com.dlwx.albumorvideolib.activity;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.dlwx.albumorvideolib.R;
import com.dlwx.albumorvideolib.adapter.ImageListAdapter;
import com.dlwx.albumorvideolib.bean.ImgesLisetBean;
import com.dlwx.albumorvideolib.utiles.InitColor;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片列表
 */
public class ImageListActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView title_txt;
    private GridView gv_list;
    private List<ImgesLisetBean.Image> images;
    private View view;

    @Override
    public void initView() {
        setContentView(R.layout.activity_image_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(InitColor.Tiele_Color);
        title_txt = (TextView) findViewById(R.id.title_txt);
        gv_list = (GridView) findViewById(R.id.gv_list);
    }
    @Override
    public void initDate() {
        title_txt.setText("图片列表");
        initToolBar(toolbar);
        images = new ArrayList<>();

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            ImgesLisetBean.Image image = new ImgesLisetBean.Image();
            //获取图片的名称
            String path = cursor
                    .getString(cursor
                            .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
            long aLong = cursor.getLong(cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN));
            //获取图片的详细信息
            String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
            image.setDate(aLong);
            image.setPath(path);
            images.add(image);
        }
        cursor.close();
        for (int i = 0; i < images.size(); i++) {
            String path = images.get(i).getPath();
        }
        ImageListAdapter adapter = new ImageListAdapter(ctx,images);
        gv_list.setAdapter(adapter);

    }

    @Override
    public void initListener() {
        gv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("path",images.get(position).getPath());
                Log.i("wch",images.get(position).getPath());
                setResult(2,intent);
                finish();
            }
        });
    }
}
