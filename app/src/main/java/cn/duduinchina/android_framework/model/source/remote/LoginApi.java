package cn.duduinchina.android_framework.model.source.remote;

//region 使用方案

import java.util.Map;

import cn.duduinchina.android_framework.model.entity.LoginData;
import cn.duduinchina.android_framework.model.source.remote.base.RequestObservable;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @GET("xxx/xxx") Observable<XXXModel> getXXX(@Query("id")String id,@QueryMap Map<String,String> map);
 * @FormUrlEncoded
 * @POST("xxx/xxx") Observable<XXXModel> setXXX(@FieldMap Map<String, String> map);
 * (@Field("email")String email , @Field("password")String password)
 * @FormUrlEncoded
 * @PUT("xxx/{id}") Observable<XXXModel> setXXX(@Path("id") String id, @FieldMap Map<String, String> map);
 * @DELETE("xxx/{id}") Observable<XXXModel> getXXX(@Path("id"));
 * <p>
 * 文件下载
 * @Streaming
 * @GET Observable<ResponseBody> downloadFile(@Url String fileUrl);
 * @Streaming
 * @GET Observable<ResponseBody> downloadFile(@Header("Range") String start, @Url String fileUrl);
 */
//endregion

public interface LoginApi {

    @FormUrlEncoded
    @POST("login")
    Observable<LoginData> login(@FieldMap Map<String,String> map);

}
