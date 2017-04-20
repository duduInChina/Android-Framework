package cn.duduinchina.android_framework.model.source.remote.base;

import cn.duduinchina.android_framework.model.entity.base.ResultBase;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 功能介绍
 * Created by Dzc on 2017/4/17.
 */

public class RequestObservable<T> extends Observable<T> {


    @Override
    protected void subscribeActual(Observer<? super T> observer) {
    }
    /**
     * 请求检查
     * @return
     */
    public Observable<T> check() {
        return map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T t) throws Exception {
                //错误码处理
                if (t instanceof ResultBase){

                    ResultBase resultBase = (ResultBase) t;

                    if(resultBase != null){
                        if(resultBase.getCode() == 0){
                            //请求成功
                            return t;
                        }else{
                            //请求失败
                            Observable.error(new Throwable(resultBase.getMsg()));
                        }
                    }
                }else{
                    return t;
                }
                return null;
            }
        });
//        return map(new Func1<T, T>() {
//            @Override
//            public T call(T t) {
//                //错误码处理
//                if (t instanceof ResultBase){
//
//                    ResultBase resultBase = (ResultBase) t;
//
//                    if(resultBase != null){
//                        if(resultBase.getCode() == 0){
//                            //请求成功
//                            return t;
//                        }else{
//                            //请求失败
//                            Observable.error(new Throwable(resultBase.getMsg()));
//                        }
//                    }
//                }else{
//                    return t;
//                }
//                return null;
//            }
//        });
    }


}
