package com.vadicindia.business.call_api;


import com.vadicindia.business.model_business.requestmodel.BaseFromToRequest;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.GetWalletBalanceRequest;
import com.vadicindia.business.model_business.requestmodel.IDActivationBalance;
import com.vadicindia.business.model_business.requestmodel.MainWalletReportRequest;
import com.vadicindia.business.model_business.requestmodel.WalletListRequest;
import com.vadicindia.business.model_business.requestmodel.WalletReportRequest;
import com.vadicindia.business.model_business.requestmodel.WalletReqRequest;
import com.vadicindia.business.model_business.requestmodel.WalletRequestBankListRequest;
import com.vadicindia.business.model_business.requestmodel.MainToShopWalletTransferRequest;
import com.vadicindia.business.model_business.requestmodel.WalletTransferRequest;
import com.vadicindia.business.model_business.requestmodel.WithdrawalRequest;
import com.vadicindia.business.model_business.responsemodel.BankListResponse;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.BaseResponseEntity;
import com.vadicindia.business.model_business.responsemodel.Dy_WalletReportReponse;
import com.vadicindia.business.model_business.responsemodel.Dy_WalletRequesDetailResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_WithdrawalDetailResponse;
import com.vadicindia.business.model_business.responsemodel.FundReqDetailResponse;
import com.vadicindia.business.model_business.responsemodel.GetBankDetailResponse;
import com.vadicindia.business.model_business.responsemodel.GetWalleBalanceResponse;
import com.vadicindia.business.model_business.responsemodel.MainWalletReportResponse;
import com.vadicindia.business.model_business.responsemodel.PayModeListResponse;
import com.vadicindia.business.model_business.responsemodel.ToWalletListResponse;
import com.vadicindia.business.model_business.responsemodel.WalletListResponse;
import com.vadicindia.business.model_business.responsemodel.WalletReportResponse;
import com.vadicindia.business.model_business.responsemodel.WalletReqDetailResponse;
import com.vadicindia.business.model_business.responsemodel.WalletRequestDetail;
import com.vadicindia.business.model_business.responsemodel.WithdrawalDetailResponse;
import com.vadicindia.business.model_business.responsemodel.WithdrawlChargeResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WalletServices {

    //for  Main Wallet Transaction Detail Api  (MainWalletReportFragment)
    @POST("/ProccessAPIWithVadic")
    Call<MainWalletReportResponse> fetchMainWalletReport(@Body MainWalletReportRequest request, @Header("apikey")String apikey);

    @POST("/ProcessAPIWithK")
    Call<WalletReportResponse> fetchWalletReport(@Body WalletReportRequest request, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<GetBankDetailResponse> fetchBankDetail(@Body BaseRequest request, @Header("apikey")String apikey);

    //for  BankList Api  (BankWithdrawal Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BankListResponse> fetchBanklist(@Body BaseRequest request, @Header("apikey")String apikey);

    //for  BankWithdrawl Api  (BankWithdrawal Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponseEntity> fetchBankWithdrawlRequest(@Body WithdrawalRequest request, @Header("apikey")String apikey);

    //for  Wallet Api  (BankWithdrawal Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<GetWalleBalanceResponse> fetchWalletBalance(@Body GetWalletBalanceRequest request, @Header("apikey")String apikey);


    //for  ID Activation get Wallet balance Api  (For ID Activation)
    @POST("/ProccessAPIWithVadic")
    Call<GetWalleBalanceResponse> fetchIDActiveBalance(@Body IDActivationBalance request, @Header("apikey")String apikey);

    //for  BankWithdrawlReport Api  (BankWithdrawalReport Fragment)
    @POST("/ProcessAPIWithK")
    Call<FundReqDetailResponse> fetchFundRequestDetail(@Body BaseRequest request, @Header("apikey")String apikey);

    //for  BankWithdrawlRecharge Api  (BankWithdrawalReport Fragment/Activity)
    @POST("/ProccessAPIWithVadic")
    Call<WithdrawlChargeResponse> fetchWithdrawlCharge(@Body BaseRequest request, @Header("apikey")String apikey);


    //for  WalletRequestDetail Api  (WalletRequestDetailFragment)
    @POST(" /ProccessAPIWithVadic")
    Call<WalletRequestDetail> fetchWalletRequestDetail(@Body BaseRequest request, @Header("apikey")String apikey);

    @POST(" /ProccessAPIWithVadic")
    Call<WalletReqDetailResponse> fetchWalletReqDetail(@Body BaseFromToRequest request, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<BankListResponse> fetchWalletRequestBankList(@Body WalletRequestBankListRequest requrest, @Header("apikey")String apikey);

    //for  WalletRequest Api  (WalletRequestFragment)
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponseEntity> fetchWalletRequest(@Body WalletReqRequest request, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<PayModeListResponse> fetchPaymodeList(@Body BaseRequest requrest, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<ToWalletListResponse> fetchTo_WalletList(@Body BaseRequest requrest, @Header("apikey")String apikey);


    @Multipart
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponseEntity> fetchWalletRequestWithImage(@Part MultipartBody.Part body1, @Part("myjson") RequestBody body, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<WalletListResponse> fetchWalletList(@Body WalletListRequest requrest, @Header("apikey")String apikey);

    /* Main To Shopping Wallet Transfer  Api service*/
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchWalletTransfer(@Body MainToShopWalletTransferRequest requrest, @Header("apikey")String apikey);

    /*Wallet Transfer  Api service*/
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchWalletTransferNew(@Body WalletTransferRequest requrest, @Header("apikey")String apikey);

    //for  BankWithdrawlReport Api  (BankWithdrawalReport Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<WithdrawalDetailResponse> fetchWithdrawalDetail(@Body BaseFromToRequest request, @Header("apikey")String apikey);

    @POST("/ProcessAPIWithK")
    Call<WalletListResponse> fetchWalletList2(@Body BaseRequest requrest,@Header("apikey") String apikey);
    @POST("/ProccessAPIWithVadic")
    Call<Dy_WalletReportReponse> fetchDynamicWalletReport(@Body WalletReportRequest request, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<Dy_WalletRequesDetailResponse> fetchDynamicWalletRequestDetail(@Body BaseFromToRequest request, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<Dy_WithdrawalDetailResponse> fetchDynamicWithdrawalDetail(@Body BaseFromToRequest request, @Header("apikey")String apikey);


}
