package cn.duduinchina.android_framework.model.source.remote.base;

import android.text.TextUtils;

import java.io.IOException;

import cn.duduinchina.android_framework.MyApplication;
import cn.duduinchina.android_framework.util.JsonUtil;
import cn.duduinchina.android_framework.util.LoggerUtil;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Http拦截器,用于打印Log,增加系统级参数,加密等操作
 * Created by Dzc on 16/5/3.
 */
public class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();

        RequestBody body = oldRequest.body();


        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        // 添加新的参数,如Token,如果是不同请求可能不同情况
//        if (oldRequest.method().equals("POST")) {
//            if (body != null && body instanceof FormBody) {
//                FormBody.Builder build = new FormBody.Builder();
//                FormBody formBody = (FormBody) body;
//                for (int i = 0; i < formBody.size(); i++) {
//                    build.add(formBody.encodedName(i), formBody.value(i));
//                }
//                // 这里可进行 RSA Post请求value的加密
//                body = build.build();
//            } else {
//
//                new IllegalAccessException("POST 请求非Form表单提交");
//
//            }
//        }
//        else if (oldRequest.method().equals("GET")) {
//
//            authorizedUrlBuilder.addQueryParameter("token", TeamlockApplication.getToken());
//
//        }

        //系统级参数统一放在url后面
//        if(TeamlockApplication.getToken() != null && TeamlockApplication.getToken() != "") {
//            authorizedUrlBuilder.addQueryParameter("token", TeamlockApplication.getToken());
//        }


        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), body)
                .url(authorizedUrlBuilder.build())
                .build();

        //设置请求的Log
        if (MyApplication.APP_DBG) {

//            LogUtil.i(String.format(" %s \n %s",
//                    newRequest.toString(),
//                    bodyToString(newRequest)));

            long t1 = System.nanoTime();
            Response response;
            try {
                response = chain.proceed(newRequest);
            } catch (IOException e) {
                throw e;
            }
            long t2 = System.nanoTime();

            MediaType contentType = null;
            String responseContent = null;
            if (response.body() != null) {
                contentType = response.body().contentType();
                responseContent = response.body().string();
            }

            //TODO 原本可以用Logger.json()去解决Json格式化问题,但出现log不显示了
            String json = "";
            if (TextUtils.isEmpty(responseContent)) {
                json = "Body Empty";
            } else {
                json = JsonUtil.jsonFormat(responseContent);
            }

            LoggerUtil.i(String.format(" %s \n %s \n %s \n %.1fms%n %s \n %s \n %s",
                    response.request().url(),
                    newRequest.toString(),
                    bodyToString(newRequest),
                    (t2 - t1) / 1e6d,
                    "Response Code:" + response.code(),
                    json,responseContent));

            if (response.body() != null) {
                ResponseBody wrappedBody = ResponseBody.create(contentType, responseContent);
                response = response.newBuilder().body(wrappedBody).build();
            }

            return response;
        } else {
            return chain.proceed(newRequest);
        }
    }

    private String bodyToString(Request request) {
        try {
            if (request.body() == null) {
                return "(no body)";
            }
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            String requestContent = buffer.readUtf8();
            if (requestContent.length() == 0) {
                requestContent = "(empty body)";
            }
            return requestContent;
        } catch (final Throwable e) {
            return "(body not printable)";
        }
    }


}
