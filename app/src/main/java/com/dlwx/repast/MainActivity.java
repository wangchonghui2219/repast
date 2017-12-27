package com.dlwx.repast;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dlwx.repast.base.BaseActivity;
import com.dlwx.repast.model.bean.UserHeadBean;
import com.dlwx.repast.presenter.Presenter;
import com.dlwx.repast.scancode.zxing.CaptureActivity;
import com.dlwx.repast.utiles.BottomNavigationViewHelper;
import com.dlwx.repast.utiles.HttpUtiles;
import com.dlwx.repast.utiles.SpUtiles;
import com.dlwx.repast.utiles.UploadPicUtiles;
import com.dlwx.repast.view.activity.VerifySuccessActivity;
import com.dlwx.repast.view.fragment.HomeFragment;
import com.dlwx.repast.view.fragment.MycenterFragment;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.dlwx.repast.base.MyApplication.User_Token;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private List<Fragment> fragments = new ArrayList<>();
    private BottomNavigationView bottom_navigation_container;
    private FrameLayout fl_content;
    private MycenterFragment mycenterFragment;
    private LinearLayout ll_code;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        bottom_navigation_container = (BottomNavigationView) findViewById(R.id.bottom_navigation_container);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation_container);
        bottom_navigation_container.setOnNavigationItemSelectedListener(this);
        bottom_navigation_container.getMenu().getItem(0).setCheckable(true);
        ll_code = (LinearLayout) findViewById(R.id.ll_code);
        sp.edit().putString(SpUtiles.First,"2").commit();
    }

    @Override
    protected void initData() {
        mycenterFragment = new MycenterFragment();
        fragments.add(new HomeFragment());
        fragments.add(mycenterFragment);
        changeFragment(0);
    }

    @Override
    protected void initListener() {
        ll_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions((Activity) ctx, new String[]{Manifest.permission.CAMERA}, 1);

                } else {        //如果已经获取到了权限则直接进行下一步操作

                    Intent intent = new Intent((Activity) ctx, CaptureActivity.class);
                    startActivityForResult(intent, 5);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_home://首页
            changeFragment(0);

                break;
            case R.id.item_release://扫码

                break;
            case R.id.item_message://我的
                    changeFragment(1);
                break;
//            case R.id.item_persion://个人中心
//
//                break;
        }
        return true;
    }
    private Fragment lastFragment;
    private Fragment fragment;
    private void changeFragment(int i) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 上一个不为空 隐藏上一个
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }

        fragment = fragments.get(i);
        // fragment不能重复添加 // 添加过 显示 没有添加过 就隐藏
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.fl_content, fragment);
        }
        transaction.commit();
        lastFragment = fragment;

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);

    }
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String order_id = bundle.getString("result");
            wch(order_id);

            Intent intent = new Intent(ctx, VerifySuccessActivity.class);
            intent.putExtra("order_id",order_id);
            startActivity(intent);
        }
         switch (requestCode){
             case 1:
                 UploadPicUtiles.startZoomPic((Activity) ctx, data,150,150, 1, 1);
                 break;
             case 2:
                 UploadPicUtiles.startZoomPic((Activity) ctx, data,150,150, 1, 1);
                 break;
             case 3:
//                 final Bitmap cropPhoto = UploadPicUtiles.getCropPhoto(data);

                 final File filePath = UploadPicUtiles.getFilePath(data,ctx);
                 wch(filePath+"ssssss");
                 if (filePath != null) {
                     loading.show();
//

                     OkGo.<String>post( HttpUtiles.UpHeadPic)
                             .params("token",User_Token)
                             .params("type","1")
                             .params("header_pic",filePath)
                             .params("sas","sadas")
//                             .params("file",filePath)
                             .execute(new StringCallback() {
                                 @Override
                                 public void onSuccess(Response<String> response) {
                                     wch(response.body());
                                     loading.dismiss();
                                     Gson gson = new Gson();
                                     UserHeadBean userHeadBean = gson.fromJson(response.body(), UserHeadBean.class);
                                     UserHeadBean.BodyBean body = userHeadBean.getBody();
                                    sp.edit().putString(SpUtiles.header_pic,body.getHeader_pic()).commit();

                                     mycenterFragment.setHead(filePath+"");
                                 }

                                 @Override
                                 public void onError(Response<String> response) {
                                     loading.dismiss();
                                     wch("网络连接俄失败");
                                 }
                             });
                 }
                 break;

         }
    }
    /**
     * 请求权限的回调
     * <p>
     * 参数1：requestCode-->是requestPermissions()方法传递过来的请求码。
     * 参数2：permissions-->是requestPermissions()方法传递过来的需要申请权限
     * 参数3：grantResults-->是申请权限后，系统返回的结果，PackageManager.PERMISSION_GRANTED表示授权成功，PackageManager.PERMISSION_DENIED表示授权失败。
     * grantResults和permissions是一一对应的
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent;
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        intent = new Intent(ctx, CaptureActivity.class);
                        startActivityForResult(intent, 5);
                    } else {
                        Toast.makeText(ctx, "您拒绝了获取相机权限，请手动获取或重装软件", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }

    }
    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {


            exitBy2Click();      //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
