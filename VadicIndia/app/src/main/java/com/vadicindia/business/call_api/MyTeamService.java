package com.vadicindia.business.call_api;


import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.DownlineDetailRequest;
import com.vadicindia.business.model_business.requestmodel.DownlinePurchaseRequest;
import com.vadicindia.business.model_business.requestmodel.DownlineRankRequest;
import com.vadicindia.business.model_business.requestmodel.DownlineStatusRequest;
import com.vadicindia.business.model_business.requestmodel.IDActivationRequest;
import com.vadicindia.business.model_business.requestmodel.LevelWiseCountRequest;
import com.vadicindia.business.model_business.requestmodel.LevelWiseReportRequest;
import com.vadicindia.business.model_business.requestmodel.MyDirectRequest;
import com.vadicindia.business.model_business.requestmodel.MyPurchaseRequest;
import com.vadicindia.business.model_business.requestmodel.OuterTeamReportRequest;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.DownlineDetailResponse;
import com.vadicindia.business.model_business.responsemodel.DownlinePurchaseResponse;
import com.vadicindia.business.model_business.responsemodel.DownlineStatusResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_DownlineDetailResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_DownlinePurchaseResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_MyDirectResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_MyRewardResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_UpgradeReportResponse;
import com.vadicindia.business.model_business.responsemodel.GeneologyResponse;
import com.vadicindia.business.model_business.responsemodel.IDActivePackageList;
import com.vadicindia.business.model_business.responsemodel.LevelListResponse;
import com.vadicindia.business.model_business.responsemodel.LevelWiseCountReport;
import com.vadicindia.business.model_business.responsemodel.LevelWiseCountResponse;
import com.vadicindia.business.model_business.responsemodel.LevelWiseReportResponse;
import com.vadicindia.business.model_business.responsemodel.MyPurchaseResponse;
import com.vadicindia.business.model_business.responsemodel.NewsResponse;
import com.vadicindia.business.model_business.responsemodel.OuterTeamReportResponse;
import com.vadicindia.business.model_business.responsemodel.RankListResponse;
import com.vadicindia.business.model_business.responsemodel.RankReportResponse;
import com.vadicindia.business.model_business.responsemodel.SponsorGeneologyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MyTeamService {
    /*WebviewFragment Api*/
    @POST("/ProccessAPIWithVadic")
    Call<GeneologyResponse> fetchGeneology(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for My Direct Fragment
    @POST("/ProccessAPIWithVadic")
    Call<SponsorGeneologyResponse> fetchSponserGeneology(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    /*Downline Fraggment*/
    /*Downline status*/
    @POST("/ProccessAPIWithVadic")
    Call<DownlineStatusResponse> fetchDownlineStatus(@Body DownlineStatusRequest downlineStatusRequest, @Header("apikey")String apikey);

    /*Downline Detail*/
    @POST("/ProccessAPIWithVadic")
    Call<DownlineDetailResponse> fetchDownlineDetails(@Body DownlineDetailRequest downlineDetailRequest, @Header("apikey")String apikey);

    // For Level Wise Direct Report Fragment
    @POST("/ProccessAPIWithVadic")
    Call<LevelListResponse> fetchLevelList(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<LevelWiseReportResponse> fetchLevelWiseReport(@Body LevelWiseReportRequest levelWiseReportRequest, @Header("apikey")String apikey);
    @POST("/ProccessAPIWithVadic")
    Call<DownlinePurchaseResponse> fetchDownlinePurchaseDetails(@Body DownlinePurchaseRequest downlinePurchaseRequest, @Header("apikey")String apikey);

    //for  MyPurchase Api  (MyPurchase Fragment)
    @POST("/ProcessAPIWithK")
    Call<MyPurchaseResponse> fetchMyPurchaseDetail(@Body MyPurchaseRequest request, @Header("apikey")String apikey);

    //for  MyPurchase Api  (MyPurchase Fragment)
    @POST("/ProcessAPIWithK")
    Call<OuterTeamReportResponse> fetchOuterTeamReport(@Body OuterTeamReportRequest request, @Header("apikey")String apikey);
    //for  My Direct Api  ( My Direct Fragment)
    @POST("/ProcessAPIWithK")
    Call<Dy_MyDirectResponse> fetchMyDirectDetail(@Body MyDirectRequest request, @Header("apikey")String apikey);


    //for Level Wise Count api  ( Level Wise Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<LevelWiseCountResponse> fetchLevelWiseCount(@Body BaseRequest request, @Header("apikey")String apikey);

    //for Rank List api
    @POST("/ProccessAPIWithVadic")
    Call<RankListResponse> fetchRankList(@Body BaseRequest request, @Header("apikey")String apikey);

    //for Rank List api
    @POST("/ProccessAPIWithVadic")
    Call<RankReportResponse> fetchDownlineRankReport(@Body DownlineRankRequest request, @Header("apikey")String apikey);


    //for  News Api  ( NewsEvent Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<NewsResponse> fetchNews(@Body BaseRequest request, @Header("apikey")String apikey);

    //for  ID Activation Package List api
    @POST("/ProcessAPIWithK")
    Call<IDActivePackageList> fetchIDPackgeList(@Body BaseRequest request, @Header("apikey")String apikey);

    //for Package List Detail
    @POST("/ProcessAPIWithK")
    Call<BaseResponse> fetchIDActivate(@Body IDActivationRequest baseRequest, @Header("apikey")String apikey);

    //for  ID Activation Package List api
    @POST("/ProcessAPIWithK")
    Call<LevelWiseCountReport> fetchLevelWiseCountReport(@Body LevelWiseCountRequest request, @Header("apikey")String apikey);

   /* //for  Upliner Tree Api  (Upliner Tree Fragment)
    @POST("/Dtprocess.aspx")
    Call<UplinerTreeResponse> fetchUplinerTree(@Body UplinerTreeRequest request);



    //for  FV Report Api  (FV Report Fragment)
    @POST("/Dtprocess.aspx")
    Call<FVReportResponse> fetchFvReport(@Body FvReportRequest request);*/

    /*Downline Detail*/
    @POST("/ProccessAPIWithVadic")
    Call<Dy_DownlineDetailResponse> fetchDy_DownlineDetails(@Body DownlineDetailRequest downlineDetailRequest , @Header("apikey")String apikey);

    //for  My Direct Api  ( My Direct Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<Dy_MyDirectResponse> fetchDynamicMyDirectDetail(@Body MyDirectRequest request,@Header("apikey")String apikey);

    //for  My Direct Api  ( My Direct Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<Dy_DownlinePurchaseResponse> fetchDynamicDownlinePurchaseDetail(@Body DownlinePurchaseRequest request, @Header("apikey")String apikey);

    //for  CHECK ID ACTIVATION
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchCheckIdForActivation(@Body BaseRequest request,@Header("apikey") String apikey);
    //for Reward List Api  (Reward fragment)
    @POST("/ProcessAPIWithK")
    Call<Dy_MyRewardResponse> fetchReward(@Body BaseRequest baseRequest, @Header("apikey")String apikey);

    //for  My Direct Api  ( My Direct Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<Dy_UpgradeReportResponse> fetchDynamicUpgradeReport(@Body BaseRequest request, @Header("apikey")String apikey);


}
