package cn.duduinchina.android_framework.util;

import com.orhanobut.logger.Logger;

/**
 * Created by Dzc on 16/2/17.
 */
public class LoggerUtil {

    public static void i(String message){
        if(UtilConstant.isAppDbg()){
            Logger.i(message);
        }
    }

    public static void d(String message){
        if(UtilConstant.isAppDbg()){
            Logger.d(message);
        }
    }

    public static void e(String message){
        if(UtilConstant.isAppDbg()){
            Logger.e(message);
        }
    }

    public static void v(String message){
        if(UtilConstant.isAppDbg()){
            Logger.v(message);
        }
    }

    public static void w(String message){
        if(UtilConstant.isAppDbg()){
            Logger.w(message);
        }
    }

    public static void wtf(String message){
        if(UtilConstant.isAppDbg()){
            Logger.wtf(message);
        }
    }

    public static void json(String message){
        if(UtilConstant.isAppDbg()){
            Logger.json(message);
        }
    }

    public static void xml(String message){
        if(UtilConstant.isAppDbg()){
            Logger.xml(message);
        }
    }

}
