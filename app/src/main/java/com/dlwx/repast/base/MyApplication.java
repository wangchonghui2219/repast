package com.dlwx.repast.base;

import android.app.Application;
import android.content.SharedPreferences;

import com.dlwx.repast.utiles.SpUtiles;
import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2017/8/14/014.
 */

public class MyApplication extends Application{
    public static String User_Token;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        User_Token = sp.getString(SpUtiles.User_Token, "");
        OkGo.getInstance().init(this);
    }
}
