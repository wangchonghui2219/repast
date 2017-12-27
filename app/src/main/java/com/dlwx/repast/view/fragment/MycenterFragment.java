package com.dlwx.repast.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dlwx.repast.R;
import com.dlwx.repast.adapter.MyCenterAdapter;
import com.dlwx.repast.base.BaseFragment;
import com.dlwx.repast.base.MyApplication;
import com.dlwx.repast.model.bean.VersionBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.utiles.SpUtiles;
import com.dlwx.repast.utiles.UpVersionUtiles;
import com.dlwx.repast.utiles.UploadPicUtiles;
import com.dlwx.repast.view.CircleImageView;
import com.dlwx.repast.view.activity.ChangeNickNameActivity;
import com.dlwx.repast.view.activity.LoginActivity;
import com.dlwx.repast.view.activity.SettCenterActivity;
import com.dlwx.repast.view.activity.WebActivity;
import com.google.gson.Gson;
import com.lzy.okgo.db.CacheManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dlwx.repast.base.MyApplication.User_Token;
/**
 * 我的
 */
public class MycenterFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.ll_sett)
    RelativeLayout llSett;
    @BindView(R.id.iv_photograph)
    ImageView ivPhotograph;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.iv_changeinfo)
    ImageView ivChangeinfo;
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;
    String[] strs = {"关于我们", "清除缓存", "版本更新", "退出登录"};
    int[] pics = {R.mipmap.icon_wdguanyu,R.mipmap.icon_wdqingli,R.mipmap.icon_wdbanben,R.mipmap.icon_wdtuichu};
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    Unbinder unbinder1;
    private String head_pic;
    private SharedPreferences sp;
    private int versionCode;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_mycenter;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initDate() {
        lvList.setAdapter(new MyCenterAdapter(ctx, strs,pics));
    }

    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://关于我们
                startActivity(new Intent(ctx,WebActivity.class).putExtra("url",HttpUtiles.About_We));
                break;
            case 1://清理缓存
                new AlertDialog.Builder(ctx)
                        .setMessage("将删除所有的图片和数据，删除后不可恢复")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                boolean clear = CacheManager.getInstance().clear();
                                if (clear) {
                                    Toast.makeText(ctx, "清除缓存成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消",null);
                break;
            case 2://版本更新
                Version();
                break;
            case 3://退出登录
                showPopu();
                break;
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        sp = ctx.getSharedPreferences(SpUtiles.SP_Mode, Context.MODE_PRIVATE);
        String User_Token = sp.getString(SpUtiles.User_Token, "");
        if (!TextUtils.isEmpty(User_Token)) {
            head_pic = sp.getString(SpUtiles.header_pic, "");

            if (!TextUtils.isEmpty(head_pic)) {
                Glide.with(ctx).load(head_pic).into(ivHead);
                tvUserName.setText(sp.getString(SpUtiles.NickName,""));
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_sett, R.id.iv_photograph, R.id.iv_changeinfo,R.id.iv_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sett:
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx, SettCenterActivity.class));
                break;
            case R.id.iv_photograph:
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                UploadPicUtiles.showDia(ctx);

                break;
            case R.id.iv_changeinfo://修改呢称
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                    return;
                }
                startActivity(new Intent(ctx,ChangeNickNameActivity.class));
                break;
            case R.id.iv_head:
                if (TextUtils.isEmpty(User_Token)) {
                    startActivity(new Intent(ctx, LoginActivity.class));
                }
                break;
        }
    }

    public void setHead(String head) {
        Glide.with(ctx).load(head).into(ivHead);
    }

    private void showPopu(){
        final View view = LayoutInflater.from(ctx).inflate(R.layout.popu_exit,null);
        ImageView iv_exit = (ImageView) view.findViewById(R.id.iv_pic);
        final PopupWindow popu = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popu.setFocusable(true);
        ColorDrawable drawaable = new ColorDrawable(0x77000000);
        popu.setBackgroundDrawable(drawaable);
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.iv_pic).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        popu.dismiss();
                    }
                }
                return true;
            }
        });
        iv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popu.dismiss();
                //TODO
                sp.edit().putString(SpUtiles.User_Token,"").commit();
                sp.edit().putString(SpUtiles.NickName,"").commit();
                sp.edit().putString(SpUtiles.header_pic,"").commit();
                sp.edit().putString(SpUtiles.seller_id,"").commit();
                sp.edit().putString(SpUtiles.Grade,"").commit();
                sp.edit().putString(SpUtiles.Phone,"").commit();
                MyApplication.User_Token = "";
                Glide.with(ctx).load(R.mipmap.icon_wdtouxiang).into(ivHead);
                tvUserName.setText("");
                startActivity(new Intent(ctx,LoginActivity.class));
            }
        });
        popu.showAtLocation(ivHead, Gravity.CENTER,0,0);
    }

    private void Version() {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(ctx.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            Map<String,String> map = new HashMap<>();
            map.put("version_no", versionCode +"");
            mPreenter.fetch(map,true, HttpUtiles.upVersion,"");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void showData(String s) {
        loading.dismiss();
        wch(s);
        Gson gson = new Gson();
        VersionBean version = gson.fromJson(s, VersionBean.class);
        if (version.getCode() == 200) {
            VersionBean.BodyBean body = version.getBody();
            int new_num = body.getNew_num();
            if (new_num > versionCode) {
                UpVersionUtiles versionUtiles = new UpVersionUtiles(ctx);
                versionUtiles.showVersionDia(body.getDownurl());
            }else{
                Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(ctx, version.getResult(), Toast.LENGTH_SHORT).show();
        }
    }
}
