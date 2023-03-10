package com.vadicindia.business.call_api;


import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.PincodeDetailRequest;
import com.vadicindia.business.model_business.requestmodel.SaveKvcRequest;
import com.vadicindia.business.model_business.requestmodel.UpdateAddressProofRequest;
import com.vadicindia.business.model_business.requestmodel.UpdateBankProofRequest;
import com.vadicindia.business.model_business.requestmodel.UploadPanCardRequest;
import com.vadicindia.business.model_business.responsemodel.AccountTypeListResponse;
import com.vadicindia.business.model_business.responsemodel.BankListResponse;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.GetAddressProofResponse;
import com.vadicindia.business.model_business.responsemodel.GetBankProofResponse;
import com.vadicindia.business.model_business.responsemodel.GetPanCardDetailResponse;
import com.vadicindia.business.model_business.responsemodel.IdProofListResponse;
import com.vadicindia.business.model_business.responsemodel.KycDetailResponse;
import com.vadicindia.business.model_business.responsemodel.KycImageResponse;
import com.vadicindia.business.model_business.responsemodel.PincodeDetailRespose;
import com.vadicindia.business.model_business.responsemodel.UploadImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadKYCServices {
/*Address Proof Fragment*/
    //for IDProof List Api  (AddressProof Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<IdProofListResponse> fetchIdProofList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);


    //Pincode Area Detail API
    @POST("/ProccessAPIWithVadic")
    Call<PincodeDetailRespose> fetchPincodeAreaDetail(@Body PincodeDetailRequest pincodeDetailRequest, @Header("apikey")String apikey);

    //for GetAddressDetail  Api  (AddressProof Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<GetAddressProofResponse> fetchAddressDetail(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Update Address Proof Detail Api (Without Image)(AddressProof Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdateAddress(@Body UpdateAddressProofRequest request, @Header("apikey")String apikey);

    //For Update Address Proof Detail api (With Image)
    //@Multipart
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdateAddressWithImage(@Body RequestBody request, @Header("apikey")String apikey);

/*Bank Proof Fragment*/

    //for Account Type List Api  (BankProog Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<AccountTypeListResponse> fetchAccountTypeList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for  BankList Api
    @POST("/ProccessAPIWithVadic")
    Call<BankListResponse> fetchBanklist(@Body BaseRequest request, @Header("apikey")String apikey);
    //for GetBank Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<GetBankProofResponse> fetchBankProofDetail(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Update Bank Proof Detail api (without image)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdateBankProofDetail(@Body UpdateBankProofRequest baseRequest, @Header("apikey")String apikey);

    //Update Bank Proof Detail Api  (With Image)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdateBankProofWithImage(@Body RequestBody request, @Header("apikey")String apikey);

    //Update Bank Proof Detail api (Using Mulitpart With Image)
    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdateBankWithImageMultipart(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey")String apikey);

/*Pan Card Fragment*/
    // for get Pan card Detail Api request and response
    @POST("/ProccessAPIWithVadic")
    Call<GetPanCardDetailResponse> fetchPanCardDetail(@Body BaseRequest request, @Header("apikey")String apikey);

    //Update Pan Card Detail api (Using Mulitpart With Image)
    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdatePanDetailWithImageMultipart(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey")String apikey);

    //for Pan CardDetail api (without image)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchUpdatePanCardDetail(@Body UploadPanCardRequest baseRequest, @Header("apikey")String apikey);


    //Upload kyc image api
    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<KycImageResponse> fetchKycImage(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey")String apikey);

    //for Get Kyc Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<KycDetailResponse> fetchKycDetail(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Save Kyc Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchSaveKyc(@Body SaveKvcRequest baseRequest, @Header("apikey")String apikey);

    //Upload Upload image api
    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<UploadImageResponse> fetchUploadImage(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey") String apikey);




}
