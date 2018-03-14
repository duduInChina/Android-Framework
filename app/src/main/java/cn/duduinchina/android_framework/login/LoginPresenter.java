package cn.duduinchina.android_framework.login;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import cn.duduinchina.android_framework.main.base.BasePresenter;
import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.LoginRepository;
import cn.duduinchina.android_framework.util.LoggerUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 登录Presenter
 */
public class LoginPresenter extends BasePresenter implements LoginContract.Presenter {

    @NonNull
    private final LoginContract.View mLoginView;

    private LoginRepository mLoginRepository;

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull LoginRepository repository) {

        mLoginView = checkNotNull(view);

        mLoginView.setPresenter(this);

        mLoginRepository = checkNotNull(repository);
    }

    @Override
    public void login() {
        //注意添加，以免内存泄漏
        getSubscription().add(

                mLoginRepository.login("admin","123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<LoginData>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull LoginData loginData) throws Exception {
                                LoggerUtil.i("true");
                            }
                        },

                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                                LoggerUtil.i("false");
                            }
                        }

                )

        );

    }
}
