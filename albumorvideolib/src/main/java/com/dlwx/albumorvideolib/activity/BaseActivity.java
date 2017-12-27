package com.dlwx.albumorvideolib.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dlwx.albumorvideolib.R;
import com.dlwx.albumorvideolib.utiles.InitColor;

/**
 * @作者 wch
 * @create at 2017/1/12 0012 下午 4:24
 * @name 父类activity
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Context ctx;
    public Bundle savedInstanceState;
    private Display display;
    private int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.savedInstanceState = savedInstanceState;
        ctx = this;

        //为了防止底部菜单上移
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        display = getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        initView();
        initDate();
        initListener();
        wch("手机型号："+Build.MODEL);
    }

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initDate();

    /**
     * 时间监听
     */
    public abstract void initListener();

    @Override
    public void onClick(View v) {

    }

    /**
     * 谈吐司
     *
     * @param message
     */
    public void toast(Object message) {
        Toast toast = Toast.makeText(ctx, message + "", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP, 0, height / 2);
        toast.show();

    }

    /**
     * 调试弹吐司
     */
    public void debugToast(Object message) {
        Toast.makeText(ctx, message + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * log信息
     *
     * @param message
     */
    public void logmess(Object message) {
        Log.i("Tag", message + "");
    }

    /**
     * wchlog信息
     *
     * @param message
     */
    public void wch(Object message) {
        Log.i("wch", message + "");
    }


    /**
     * 初始化头布局
     *
     * @param toolbar
     */
    public void initToolBar(Toolbar toolbar) {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(InitColor.Tiele_Color);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantou);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    // 写一个广播的内部类，当收到动作时，结束activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    public void register() {
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("base_finish_activity");
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    public void close() {
        Intent intent = new Intent();
        intent.setAction("base_finish_activity"); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
        finish();
    }


    //定义请求
    private static final int READ_CONTACTS_REQUEST = 1;
    public boolean isget = false;

    //当用户执行的操作需要权限时候进行询问
    public void getPermissionToReadUserContacts() {
        /**
         * 1)使用ContextCompat.chefkSelfPermission(),因为Context.permission
         * 只在棒棒糖系统中使用
         * 2）总是检查权限（即使权限被授予）因为用户可能会在设置中移除你的权限*/
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //权限为获取，检查用户是否被询问过并且拒绝了，如果是这样的话，给予更多
            //解释
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS))
//            {
//                //在界面上展示为什么需要读取联系人
//                Toast.makeText(this, "需要读取联系人和调用摄像头才能正常工作", Toast.LENGTH_SHORT).show();
//            }
            //发起请求获得用户许可,可以在此请求多个权限
            ActivityCompat.requestPermissions(this, new String[]{

                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,

            }, READ_CONTACTS_REQUEST);
        } else {
            isget = true;
        }

    }
    //从requestPermissions()方法回调结果


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //确保是我们的请求
        if (requestCode == READ_CONTACTS_REQUEST) {
            if (grantResults.length == 3 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "读取联系人权限获得", Toast.LENGTH_SHORT).show();


            } else if (grantResults.length == 3 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                isget = false;
//                Toast.makeText(this, "读取联系人失败", Toast.LENGTH_SHORT).show();
            } else if (grantResults.length == 3 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                isget = true;
//                Toast.makeText(this, "获取摄像头权限成功", Toast.LENGTH_SHORT).show();
            } else {
                isget = true;
//                Toast.makeText(this, "获得摄像头权限失败", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (requestCode == 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    //用户勾选了不再询问
                    //提示用户手动打开权限
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        Toast.makeText(this, "定位权限已被禁止", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    //获取定位数据
    public void LocationCity() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
            // 向用户解释为什么需要这个权限
            //申请定位权限
            ActivityCompat.requestPermissions(BaseActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.LOCATION_HARDWARE)) {
                new AlertDialog.Builder(this)
                        .setMessage("申请定位权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        } else {

        }
    }





    @Override
    protected void onStart() {

        super.onStart();
    }
}
