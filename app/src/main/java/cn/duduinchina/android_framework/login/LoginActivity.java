package cn.duduinchina.android_framework.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.duduinchina.android_framework.R;
import cn.duduinchina.android_framework.main.base.BasePresenterActivity;
import cn.duduinchina.android_framework.model.source.LoginRepository;

/**
 * 登录主界面
 */
public class LoginActivity extends BasePresenterActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        LoginFragment mLoginFragment = getActivityUtil().addFragmentToActivity(
                getSupportFragmentManager(), LoginFragment.class, R.id.contentFrame, null);

        LoginPresenter mLoginPresenter = new LoginPresenter(mLoginFragment, LoginRepository.getInstance());

    }
}
