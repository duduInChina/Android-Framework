package cn.duduinchina.android_framework.main.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import cn.duduinchina.android_framework.R;
import cn.duduinchina.android_framework.util.ActivityUtil;
import cn.duduinchina.android_framework.util.AppManager;

/**
 * 功能介绍
 * Created by Dzc on 2016/11/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityUtil activityUtil;
    protected ProgressDialog pd;
    protected boolean progressShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);// 添加Activity到堆栈
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除标题

    }

    /**
     * finish Activity 提供给xml中"返回"Button的android:Onclick属性调用
     * @param view
     */
    public void finish(View view) {
        finish();
    }

    public ActivityUtil getActivityUtil(){
        if(activityUtil == null){
            activityUtil = new ActivityUtil(this);
        }
        return activityUtil;
    }

    public void showLoadingView(){
        if(!progressShow){
            progressShow = true;
            if(pd == null){
                pd = new ProgressDialog(this);
                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage(getString(R.string.loading));
            }
            pd.show();
        }
    }

    public void dissLoadingView(){
        if(pd != null){
            pd.dismiss();
        }
        progressShow = false;
    }

    /**
     * 获取字符资源
     * @param res
     * @return
     */
    protected String getResString(int res) {
        return getResources().getString(res) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().removeActivityFromStack(this);
    }
}
