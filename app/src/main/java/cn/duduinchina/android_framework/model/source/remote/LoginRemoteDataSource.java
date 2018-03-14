package cn.duduinchina.android_framework.model.source.remote;

import java.util.HashMap;
import java.util.Map;

import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.LoginDataSource;
import cn.duduinchina.android_framework.model.source.remote.base.ApiManager;
import io.reactivex.Observable;

public class LoginRemoteDataSource implements LoginDataSource {

    private static LoginRemoteDataSource INSTANCE;

    private final LoginApi mLoginApi;

    private LoginRemoteDataSource() {
        mLoginApi = ApiManager.getInstance().getLoginApi();
    }

    private static synchronized void syncInit() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRemoteDataSource();
        }
    }

    public static LoginRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            syncInit();
        }
        return INSTANCE;
    }

    @Override
    public Observable<LoginData> login(String name, String pwd) {

        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",pwd);

        return mLoginApi.login(map);
    }

    @Override
    public void saveLoginInfo(LoginData loginData) {

    }
}
