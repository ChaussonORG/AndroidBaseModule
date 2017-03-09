package com.sudao.basemodule.basicapi;

import com.sudao.basemodule.basicapi.request.AdviceRequest;
import com.sudao.basemodule.basicapi.request.LoginRequest;
import com.sudao.basemodule.basicapi.request.PhoneCodeRequest;
import com.sudao.basemodule.basicapi.request.RegistRequest;
import com.sudao.basemodule.basicapi.request.ThirdLoginRequest;
import com.sudao.basemodule.basicapi.request.UpdatePasswordRequest;
import com.sudao.basemodule.component.file.UploadResponse;
import com.sudao.basemodule.http.BaseResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Samuel on 5/25/16 14:55
 * Email:xuzhou40@gmail.com
 * desc:账户相关接口（登录，找回密码，更新密码）
 */
public interface BaseService {
    //注册
    @POST("app/register")
    Call<BaseResponse<Object>> regist(@Body RegistRequest body);

    //登录
    @POST("client/auth/login")
    Call<BaseResponse<Object>> login(@Body LoginRequest body);

    //更改密码
    @PUT("app/profile/password")
    Call<BaseResponse<Object>> updatePassword(@Body UpdatePasswordRequest body);

    //获取手机验证码
    @POST("phoneCode/single")
    Call<BaseResponse<Object>> getPhoneCode(@Body PhoneCodeRequest body);

    //找回密码
    @PUT("app/password")
    Call<BaseResponse<Object>> resetPassword(@Body RegistRequest body);

    //下载文件
    @GET
    @Streaming
    Call<ResponseBody> downloadFile(@Url String url);

    //退出
    @GET("app/auth/logout")
    Call<BaseResponse> logout();

    //意见反馈
    @POST("app/feedback")
    Call<BaseResponse> sendAdvice(@Body AdviceRequest body);

    //绑定手机号码
    @POST("app/register/bind")
    Call<BaseResponse<Object>> bindPhone(@Body RegistRequest body);

    //第三方登录
    @POST("app/auth/{usertype}")
    Call<BaseResponse<Object>> thirdLogin(@Body ThirdLoginRequest body, @Path("usertype") String usertype);

    //上传图片文件
    @Multipart
    @POST("image")
    Call<UploadResponse> uploadImages(@Part List<MultipartBody.Part> partList);

    /**
     * 上传文件
     *
     * @param partList
     * @return
     */
    @Multipart
    @POST("file")
    Call<UploadResponse> uploadFiles(@Part List<MultipartBody.Part> partList);


}
