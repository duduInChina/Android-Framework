package cn.duduinchina.android_framework.model.source.remote.base;

import cn.duduinchina.android_framework.AppConstant;
import cn.duduinchina.android_framework.model.source.remote.LoginApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Api管理类，Retrofit创建配置
 * Created by Dzc on 2017/4/16.
 */

public class ApiManager {

    private static ApiManager INSTANCEl;
    private Retrofit retrofit;

    private ApiManager() {
        //创建Http过滤器
        Interceptor loggingInterceptor=new HttpInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static synchronized void syncInit() {
        if (INSTANCEl == null) {
            INSTANCEl = new ApiManager();
        }
    }

    public static ApiManager getInstance() {
        if (INSTANCEl == null) {
            syncInit();
        }
        return INSTANCEl;
    }

    /**
     * @param serviceClass 创建网络请求的接口对象
     * @param <T>          返回该对象
     * @return
     */
    private <T> T creare(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private LoginApi loginApi;
    public LoginApi getLoginApi(){
        if(loginApi == null){
            loginApi = creare(LoginApi.class);
        }
        return loginApi;
    }

}
