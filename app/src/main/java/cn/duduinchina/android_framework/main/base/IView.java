package cn.duduinchina.android_framework.main.base;

/**
 * 功能介绍
 * Created by Dzc on 2016/11/28.
 */

public interface IView<T> {

    void setPresenter(T presenter);

    void showLoadingView();

    void dissLoadingView();

}
