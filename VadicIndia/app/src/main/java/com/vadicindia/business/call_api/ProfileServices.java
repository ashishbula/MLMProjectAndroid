package com.vadicindia.business.call_api;

import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.ChangePasswordRequest;
import com.vadicindia.business.model_business.requestmodel.ChangeTransatoinPassREquest;
import com.vadicindia.business.model_business.requestmodel.CheckUserNameRequest;
import com.vadicindia.business.model_business.requestmodel.CheckValideSideRequest;
import com.vadicindia.business.model_business.requestmodel.ForgotPassRequest;
import com.vadicindia.business.model_business.requestmodel.NewJoinOtpRequest;
import com.vadicindia.business.model_business.requestmodel.NewRegistrationRequest;
import com.vadicindia.business.model_business.requestmodel.SetProfileRequest;
import com.vadicindia.business.model_business.requestmodel.TestimonialRequest;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.DashboardResponse;
import com.vadicindia.business.model_business.responsemodel.ForgotPasswordResponse;
import com.vadicindia.business.model_business.responsemodel.GetMemberNameResponse;
import com.vadicindia.business.model_business.responsemodel.GetProfileResponse;
import com.vadicindia.business.model_business.responsemodel.LoginResponseEntity;
import com.vadicindia.business.model_business.responsemodel.MemberRelationListResponse;
import com.vadicindia.business.model_business.responsemodel.NewRegisterResponse;
import com.vadicindia.business.model_business.responsemodel.StateListResponse;
import com.vadicindia.business.model_business.responsemodel.UploadImageResponse;
import com.vadicindia.business.model_business.responsemodel.WelcomeBannerResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ProfileServices {

    @POST("/ProccessAPIWithVadic")
    Call<WelcomeBannerResponse> fetchWelcomeBanner(@Body BaseRequest baseRequest);

    /*Login Api*/
    @POST("/ProccessAPIWithVadic")
    Call<LoginResponseEntity> fetchLogin(@Body BaseRequest baseRequest);

    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchOtp(@Body NewJoinOtpRequest baseRequest, @Header("apikey")String apikey);//

    /*For New Registration Api*/
    //for State List Api  (New Registration Activity)
    @POST("/ProccessAPIWithVadic")
    Call<StateListResponse> fetchStateList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Member Relation Api  (New Registration Activity)
    @POST("/ProccessAPIWithVadic")
    Call<MemberRelationListResponse> fetchMemberRelationList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for MemberName Api  (New Registration Activity)
    @POST("/ProccessAPIWithVadic")
    Call<GetMemberNameResponse> fetchMemberName(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for check user name Api  (New Registration Activity)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchChecjUserName(@Body CheckUserNameRequest baseRequest, @Header("apikey")String apikey);

    //for NewRegistration Api  (New Registration Activity)
    @POST("/ProccessAPIWithVadic")
    Call<NewRegisterResponse> fetchNewRegistration(@Body NewRegistrationRequest registrationRequest, @Header("apikey")String apikey);

    /*Edit Profile*/
    //for GetProfile Detail Api  (Edit Profile Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<GetProfileResponse> fetchGetProfileDetail(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for EditProfile  Api  (Edit Profile Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchEditProfileDetail(@Body SetProfileRequest baseRequest, @Header("apikey")String apikey);

    //ChangePassword Api   (ChangePassword Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fatchChangePassword(@Body ChangePasswordRequest changePasswordRequest, @Header("apikey")String apikey);

    @POST("/ProcessAPIWithK")
    Call<BaseResponse> fatchCheckSide(@Body CheckValideSideRequest request, @Header("apikey")String apikey);

    //ChangeTransactionPassword Api   (ChangeTransactionPassword Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fatchChangeTransactionPassword(@Body ChangeTransatoinPassREquest changePasswordRequest, @Header("apikey")String apikey);

    //Forgot Password api
    @POST("/ProccessAPIWithVadic")
    Call<ForgotPasswordResponse> fetchForgotPassword(@Body ForgotPassRequest request, @Header("apikey")String apikey);

    //Testimonial  api
    @POST("/ProcessAPIWithK")
    Call<BaseResponse> fetchTestimonial(@Body TestimonialRequest request);
    @POST("/ProccessAPIWithVadic")
    Call<DashboardResponse> fetchDashboardDetail(@Body BaseRequest request, @Header("apikey")String apikey);


    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<UploadImageResponse> fetchProfileImageMultipart(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey")String apikey);
}
