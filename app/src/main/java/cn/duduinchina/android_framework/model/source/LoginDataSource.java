package cn.duduinchina.android_framework.model.source;

import cn.duduinchina.android_framework.model.entity.LoginData;
import io.reactivex.Observable;

public interface LoginDataSource {
    Observable<LoginData> login(String name,String pwd);
    //保存登录信息
    void saveLoginInfo(LoginData loginData);
}
