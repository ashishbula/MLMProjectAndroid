package com.vadicindia.business.call_api;


import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.EPinDetailReqEntity;
import com.vadicindia.business.model_business.requestmodel.PinGeneratePackage;
import com.vadicindia.business.model_business.requestmodel.PinGenerateRequest;
import com.vadicindia.business.model_business.requestmodel.PinReceiveDetailRequest;
import com.vadicindia.business.model_business.requestmodel.PinTransferDetailRequest;
import com.vadicindia.business.model_business.requestmodel.PinTransferWithOtpRequest;
import com.vadicindia.business.model_business.requestmodel.TopupRequest;
import com.vadicindia.business.model_business.requestmodel.ValidateKitRequest;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.EpinDetailResponse;
import com.vadicindia.business.model_business.responsemodel.PackageListResponse;
import com.vadicindia.business.model_business.responsemodel.PinReceiveDetailResponse;
import com.vadicindia.business.model_business.responsemodel.PinTransferDetailReaponse;
import com.vadicindia.business.model_business.responsemodel.ShoppingBalanceResponse;
import com.vadicindia.business.model_business.responsemodel.ValidateKitResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface EpinServices {

    //for Package List Detail
    @POST("/processapi.aspx")
    Call<PackageListResponse> fetchPackageList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Pin Detail
    @POST("/Dtprocess.aspx")
    Call<EpinDetailResponse> fetchPinDetail(@Body EPinDetailReqEntity ePinDetailReqEntity);

    //for Validate Kit Detail (Topup Fragment)
    @POST("/Dtprocess.aspx")
    Call<ValidateKitResponse> fetchValidateKit(@Body ValidateKitRequest validateKitRequest);

    //for Topup Detail (Confirm Topup Fragment)
    @POST("/Dtprocess.aspx")
    Call<BaseResponse> fetchTopupDetail(@Body TopupRequest topupRequest);

    //for PinTransferDetail Api(PinTransferDetail Fragment)
    @POST("/Dtprocess.aspx")
    Call<PinTransferDetailReaponse> fetchPinTransferDetail(@Body PinTransferDetailRequest pinTransferDetailRequest);

    //for PinReceiveDetail Api(PinReceiveDetail Fragment)
    @POST("/Dtprocess.aspx")
    Call<PinReceiveDetailResponse> fetchPinReceiveDetail(@Body PinReceiveDetailRequest pinReceiveDetailRequest);

/*Pin Transfer Fragment*/
    //for PinTransfer OTP Api
    @POST("/Dtprocess.aspx")
    Call<BaseResponse> fetchPinTransOTP(@Body BaseRequest pinReceiveDetailRequest, @Header("apikey")String apikey);

    //for PinTransfer  Api
    @POST("/Dtprocess.aspx")
    Call<BaseResponse> fetchPinTrans(@Body PinTransferWithOtpRequest request, @Header("apikey")String apikey);

    //for Shopping/Services Bal  Api
    @POST("/Dtprocess.aspx")
    Call<ShoppingBalanceResponse> fetchShopingBal(@Body BaseRequest request);

    //for Pin Genearate Package List Api
  /*  @POST("/Dtprocess.aspx")
    Call<PackageListResponse> fetchPinGeneratePackageList(@Body BaseRequest baseRequest);

    //for Pin Genearate Api
    @POST("/Dtprocess.aspx")
    Call<BaseResponse> fetchPinGenerate(@Body PinGenerateRequest baseRequest);
*/
    @POST("/Dtprocess.aspx")
    Call<PinGeneratePackage> fetchPinGeneratePackageList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for Pin Genearate Api
    @POST("/Dtprocess.aspx")
    Call<BaseResponse> fetchPinGenerate(@Body PinGenerateRequest baseRequest, @Header("apikey")String apikey);
}
