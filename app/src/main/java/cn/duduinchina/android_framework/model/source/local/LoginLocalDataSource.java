package cn.duduinchina.android_framework.model.source.local;

import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.LoginDataSource;
import io.reactivex.Observable;

public class LoginLocalDataSource implements LoginDataSource {

    private static LoginLocalDataSource INSTANCE;

    private LoginLocalDataSource() {
    }

    private static synchronized void syncInit() {
        if (INSTANCE == null) {
            INSTANCE = new LoginLocalDataSource();
        }
    }

    public static LoginLocalDataSource getInstance() {
        if (INSTANCE == null) {
            syncInit();
        }
        return INSTANCE;
    }

    @Override
    public Observable<LoginData> login(String name, String pwd) {
        return null;
    }

    @Override
    public void saveLoginInfo(LoginData loginData) {

    }


}
