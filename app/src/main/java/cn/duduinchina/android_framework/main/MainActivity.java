package cn.duduinchina.android_framework.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import cn.duduinchina.android_framework.R;
import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.LoginRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能介绍
 * Created by Dzc on 2017/4/17.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginRepository.getInstance().login("admin", "123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LoginData>() {
                            @Override
                            public void accept(@NonNull LoginData loginData) throws Exception {
                                Toast.makeText(getApplication(),loginData.getCode()+"",Toast.LENGTH_LONG).show();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {

                            }
                        }
                );

    }
}
