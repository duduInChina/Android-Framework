package cn.duduinchina.android_framework.login;

import android.widget.Toast;

import cn.duduinchina.android_framework.R;
import cn.duduinchina.android_framework.main.base.BasePresenterFragment;
import cn.duduinchina.android_framework.model.source.LoginRepository;

/**
 * 登录Fragment
 */
public class LoginFragment extends BasePresenterFragment<LoginContract.Presenter> implements LoginContract.View {

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {

        mPresenter.login();

    }

}
