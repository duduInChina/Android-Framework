package cn.duduinchina.android_framework.model.source.remote.base;

import java.security.AccessControlException;

import cn.duduinchina.android_framework.model.entity.base.ResultBase;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 功能介绍
 * Created by Dzc on 2017/9/29.
 */

public class ResponseFunction<T> implements Function<T,T> {
    @Override
    public T apply(@NonNull T t) throws Exception {
        //错误码处理
        if (t instanceof ResultBase){

            ResultBase resultBase = (ResultBase) t;

            if(resultBase != null){
                if(resultBase.getCode() == 20000){
                    //请求成功
                    return t;
                }else if(resultBase.getCode() == 40510 || resultBase.getCode() == 40511){
                    //重新登录
                    throw new AccessControlException(resultBase.getCode()+":"+resultBase.getMsg());
                }else{
                    //请求失败
                    throw new IllegalArgumentException(resultBase.getCode()+":"+resultBase.getMsg());
                }
            }
        }else{
            return t;
        }
        return null;
    }
}
