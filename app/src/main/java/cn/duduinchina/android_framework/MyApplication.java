package cn.duduinchina.android_framework;

/**
 * 功能介绍
 * Created by Dzc on 2017/4/15.
 */

/**
 * 是否是debug模式
 * 根据打包判断,不用特意设置
 */

import android.app.Application;

import cn.duduinchina.android_framework.util.UtilConstant;


public class MyApplication extends Application{

    public static boolean APP_DBG = false;

    @Override
    public void onCreate() {
        super.onCreate();

        initConstant();

    }

    /**
     * 初始化相关常量
     */
    private void initConstant() {
        UtilConstant.instance(this,AppConstant.SP_NAME);
        APP_DBG = UtilConstant.isAppDbg();
    }
}
