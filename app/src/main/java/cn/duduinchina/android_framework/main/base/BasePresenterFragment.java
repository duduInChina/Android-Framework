package cn.duduinchina.android_framework.main.base;

import android.support.annotation.NonNull;

/**
 * 功能介绍
 * Created by Dzc on 2016/11/26.
 */

public abstract class BasePresenterFragment<P extends IPresenter> extends BaseFragment {

    protected P mPresenter;

    @Override
    public void onResume() {
        super.onResume();

        if(mPresenter != null){
            mPresenter.subscribe();
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        if(mPresenter != null){
            mPresenter.unsubscribe();
        }

    }

    public void setPresenter(@NonNull P presenter) {
        mPresenter = presenter;
        mPresenter.init();
    }

}
