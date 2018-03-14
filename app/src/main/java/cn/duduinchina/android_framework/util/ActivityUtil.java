package cn.duduinchina.android_framework.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 界面工具类
 * Created by Dzc on 16/4/26.
 */
public class ActivityUtil {

    private Context context;

    protected InputMethodManager inputMethodManager;

    public ActivityUtil(Context context){
        this.context = context;
    }


    public void go2Activity(Context context, Class<?> activity){
        go2Activity(context,activity,null);
    }

    /**
     * 跳转界面
     * @param context
     * @param activity
     * @param bundle
     */
    public void go2Activity(Context context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void go2ActivityForResult(Context context, Class<?> activity, int requestCode) {
        go2ActivityForResult(context,activity,null,requestCode);
    }

    /**
     * 跳转界面回调
     * @param context
     * @param activity
     * @param bundle
     * @param requestCode
     */
    public void go2ActivityForResult(Context context, Class<?> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public void setResult(Bundle bundle){
        Intent intent = new Intent();
        if(bundle != null){
            intent.putExtras(bundle);
        }
        ((Activity)context).setResult(Activity.RESULT_OK,intent);
    }

    public void setResult(){
        Intent intent = new Intent();
        ((Activity)context).setResult(Activity.RESULT_OK,intent);
    }

    /**
     * 显示键盘
     */
    public void showKeyBoard(View view) {
        view.requestFocus();
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        inputMethodManager.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 隐藏键盘
     */
    public void hideSoftKeyboard() {
        if (((Activity) context).getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (((Activity) context).getCurrentFocus() != null)

                if(inputMethodManager == null){
                    inputMethodManager = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
                }

                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 界面结束
     * @param context
     */
    public void finish(Context context){
        ((Activity) context).finish();
    }


    /**
     * 添加Fragment
      * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static <T extends Fragment> T addFragmentToActivity(
            @NonNull FragmentManager fragmentManager, Class<T> cls, int contentFrame, Bundle bundle){

        checkNotNull(fragmentManager);
        T fragment = (T) fragmentManager.findFragmentById(contentFrame);
        if(fragment == null){
            try {
                fragment = cls.newInstance();
                if(bundle != null){
                    fragment.setArguments(bundle);
                }
                addFragmentToActivity(fragmentManager,fragment,contentFrame);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return fragment;

    }

}
