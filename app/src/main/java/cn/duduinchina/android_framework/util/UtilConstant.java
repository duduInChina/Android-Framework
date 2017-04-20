package cn.duduinchina.android_framework.util;

import android.content.Context;
import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by Dzc on 16/2/17.
 * 初始化Uitl共享属性,属性主要用于Util内部
 * 还没初始化抛出异常
 */
public class UtilConstant {

    public static Context context;

    /**
     * 是否是debug模式
     */
    private static boolean APP_DBG = false;

    /**
     * 是否已经初始化
     */
    public static boolean INSTANCE=false;

    /**
     * SharedPreferences名字
     */
    public static String SP_NAME;

    public static final String SDCARD_PATH = SDCardUtil.getSDCardPath();	//sd卡路径
    public static final String DATA_PATH = Environment.getDataDirectory().getAbsolutePath();//内存路径
//    public static final String APP_FOLDER = SDCARD_PATH+ File.separator + "teamlock_executive" + File.separator;	//客户端文件夹路径

//    public static final String LOG_PATH = UtilConstant.APP_FOLDER + "/error.log";

    //SharedPreferences,统一以sp_开头命名
//    public static final String SP_NAME= "sp_teamlock_executive"; //客户端sp名字
    public static final String SP_CONF_APP_UNIQUEID= "sp_teamlock_executive_uniqueid"; //应用生成的app id标识

    public static final String DATABASE_NAME = "teamlock_executive";
    public static final int DATABASE_VERSION = 1;

    /**
     * 初始化属性
     */
    public static void instance(Context context, String spName){

        Logger.init("appLog");

        UtilConstant.context = context;

        APP_DBG = AppUtil.isApkDebugable(context);

        INSTANCE=true;

        SP_NAME=spName;
    }

    public static boolean isAppDbg() {
        if(INSTANCE){
            return APP_DBG;
        }else{
            throw new IllegalArgumentException("UtilConstant还没初始化");
        }
    }

}
