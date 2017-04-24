package cn.duduinchina.android_framework.main.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 功能介绍
 * Created by Dzc on 2016/11/29.
 */

public abstract class BasePresenter {

    private CompositeDisposable mSubscription;

    public void init(){

        mSubscription = new CompositeDisposable();

    }

    public void subscribe() {

    }

    public void unsubscribe() {
        if(mSubscription != null){
            mSubscription.clear();
        }
    }

    private CompositeDisposable getSubscription(){
        return mSubscription;
    }

}
