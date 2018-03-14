package cn.duduinchina.android_framework.model.source;

import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.local.LoginLocalDataSource;
import cn.duduinchina.android_framework.model.source.remote.LoginRemoteDataSource;
import cn.duduinchina.android_framework.model.source.remote.base.Transformers;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class LoginRepository implements LoginDataSource {

    private static LoginRepository INSTANCE;

    private final LoginDataSource mLoginLocalDataSource;

    private final LoginDataSource mLoginRemoteDataSource;

    private LoginRepository() {
        mLoginLocalDataSource = LoginLocalDataSource.getInstance();
        mLoginRemoteDataSource = LoginRemoteDataSource.getInstance();
    }

    private static synchronized void syncInit() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
    }

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            syncInit();
        }
        return INSTANCE;
    }

    @Override
    public Observable<LoginData> login(String name, String pwd) {

        return mLoginRemoteDataSource.login(name, pwd)
                .map(new Function<LoginData, LoginData>() {
                    @Override
                    public LoginData apply(@NonNull LoginData loginData) throws Exception {
                        mLoginLocalDataSource.saveLoginInfo(loginData);
                        return loginData;
                    }
                }).compose(Transformers.<LoginData>netWorkSchedulers());
    }

    /**
     * 保存相关数据
     * @param loginData
     */
    @Override
    public void saveLoginInfo(LoginData loginData) {

    }

    public Observable<LoginData> getToken(){
        return mLoginRemoteDataSource.login("admin", "123456")
                .map(new Function<LoginData, LoginData>() {
                    @Override
                    public LoginData apply(@NonNull LoginData loginData) throws Exception {
                        mLoginLocalDataSource.saveLoginInfo(loginData);
                        return loginData;
                    }
                }).compose(Transformers.<LoginData>netWorkSchedulers());
    }

}
