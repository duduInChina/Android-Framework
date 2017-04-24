package cn.duduinchina.android_framework.main.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import cn.duduinchina.android_framework.util.ActivityUtil;


/**
 * 功能介绍
 * Created by Dzc on 2016/11/26.
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected View mFragmentView;
    protected InputMethodManager inputMethodManager;

    public ActivityUtil activityUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(getLayoutID(),container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mActivity = getActivity();
        mFragmentView = this.getView();
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        init();

    }

    protected abstract int getLayoutID();
    public abstract void init();

    /**
     * finish Activity 提供给xml中"返回"Button的android:Onclick属性调用
     * @param view
     */
    public void finish(View view) {
        finish();
    }

    public void finish(){
        mActivity.finish();
    }

    public ActivityUtil getActivityUtil(){
        if(activityUtil == null){
            if(mActivity instanceof BaseActivity){
                activityUtil = ((BaseActivity) mActivity).getActivityUtil();
            }else{
                activityUtil = new ActivityUtil(mActivity);
            }
        }
        return activityUtil;
    }

    public void showLoadingView(){

    }

    public void dissLoadingView(){

    }

}
