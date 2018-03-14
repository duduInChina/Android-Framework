package cn.duduinchina.android_framework.login;

import cn.duduinchina.android_framework.main.base.IPresenter;
import cn.duduinchina.android_framework.main.base.IView;

/**
 * 登录操作处理层
 */
public interface LoginContract {
    interface View extends IView<Presenter> {

    }

    interface Presenter extends IPresenter {
        void login();
    }
}
