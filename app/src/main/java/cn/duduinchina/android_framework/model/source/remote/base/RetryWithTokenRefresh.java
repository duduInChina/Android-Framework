package cn.duduinchina.android_framework.model.source.remote.base;

import android.content.Intent;

import java.security.AccessControlException;

import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.LoginRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 刷新token
 * Created by Dzc on 2017/10/23.
 */

public class RetryWithTokenRefresh {

    public <T> Function<Throwable, ObservableSource<? extends T>> refreshTokenAndRetry(final Observable<T> toBeResumed) {

        return new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(@NonNull final Throwable throwable) throws Exception {

                if (throwable instanceof AccessControlException) {

                    Observable<LoginData> loginDataObservable = LoginRepository.getInstance().getToken();

                    if (loginDataObservable == null) {

                        //返回登录界面
//                        AppManager.getAppManager().finishAllActivity();
//                        Intent intent = new Intent();
//                        intent.setClass(MyApplicationLike.context, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        MyApplicationLike.context.startActivity(intent);

                    } else {
                        return loginDataObservable
                                .flatMap(new Function<LoginData, ObservableSource<? extends T>>() {
                                    @Override
                                    public ObservableSource<? extends T> apply(@NonNull LoginData loginData) throws Exception {
                                        if(loginData == null){

                                            //返回登录界面
//                                            AppManager.getAppManager().finishAllActivity();
//                                            Intent intent = new Intent();
//                                            intent.setClass(MyApplicationLike.context, LoginActivity.class);
//                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                            MyApplicationLike.context.startActivity(intent);

                                            return Observable.error(throwable);
                                        }
                                        return toBeResumed;
                                    }
                                })
                                .subscribeOn(Schedulers.io());
                    }

                }

                return Observable.error(throwable);
            }
        };

    }

}
