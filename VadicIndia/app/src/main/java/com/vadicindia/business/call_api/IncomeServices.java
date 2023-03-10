package com.vadicindia.business.call_api;



import com.vadicindia.business.model_business.requestmodel.BaseFromToRequest;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.BinaryIncomeRequest;
import com.vadicindia.business.model_business.requestmodel.DailyIncentiveRequest;
import com.vadicindia.business.model_business.requestmodel.DirectIncomeRequest;
import com.vadicindia.business.model_business.requestmodel.DownBusinessRequest;
import com.vadicindia.business.model_business.requestmodel.LeadershipBonusRequest;
import com.vadicindia.business.model_business.requestmodel.MonthlyIncentiveRequest;
import com.vadicindia.business.model_business.requestmodel.MyBusinessRequest;
import com.vadicindia.business.model_business.requestmodel.RetailIncentiveRequest;
import com.vadicindia.business.model_business.requestmodel.WeeklyIncentiveRequest;
import com.vadicindia.business.model_business.requestmodel.YearlyReardRequest;
import com.vadicindia.business.model_business.responsemodel.BinaryIncomeResponse;
import com.vadicindia.business.model_business.responsemodel.DailyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.DirectIncomeReponse;
import com.vadicindia.business.model_business.responsemodel.DownBusinessResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_DailyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_MFADetailResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_MonthlyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.Dy_MonthlyRewardPoints;
import com.vadicindia.business.model_business.responsemodel.Dy_WeeklyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.FPVReportResponse;
import com.vadicindia.business.model_business.responsemodel.LeadershipBonusResponse;
import com.vadicindia.business.model_business.responsemodel.MonthSessionResponse;
import com.vadicindia.business.model_business.responsemodel.MonthlyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.MyBusinessResponse;
import com.vadicindia.business.model_business.responsemodel.RetailIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.WeeklyIncentiveResponse;
import com.vadicindia.business.model_business.responsemodel.YearlyRewardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IncomeServices {
    //for Direct Income Api Detail (Direct Income Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<DirectIncomeReponse> fetchDirectIncomeDetail(@Body DirectIncomeRequest directIncomeRequest);

    //for Weekly Income Api Detail (Weekly Incentive Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<WeeklyIncentiveResponse> fetchWeeklyIncomeDetail(@Body WeeklyIncentiveRequest weeklyIncentiveRequest);

    //for BinaryIncome Api Detail (Binary Income Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<BinaryIncomeResponse> fetchBinaryIncomeDetail(@Body BinaryIncomeRequest binaryIncomeRequest);

    //for LeaderShip Bonus Income Api Detail (LeaderShip Bonus Income Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<LeadershipBonusResponse> fetchLeadershipBonusDetail(@Body LeadershipBonusRequest request);

    //for YearlyReward  Api Detail (YearlyReward  Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<YearlyRewardResponse> fetchYearlyRewardDetail(@Body YearlyReardRequest request);

    //for RetailIncentive  Api Detail (RetailIncentive Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<RetailIncentiveResponse> fetchRetailIncentiveDetail(@Body RetailIncentiveRequest request);

    //for Session Api Detail (MyBusiness Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<MonthSessionResponse> fetchSessionList(@Body BaseRequest request, @Header("apikey") String apikey);

    //for Self Business  Api  (MyBusiness Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<MyBusinessResponse> fetchMyBusinessDetail(@Body MyBusinessRequest request, @Header("apikey") String apikey);

    //for MyBusinessDown  Api  (MyBusinessDown Fragment)
    @POST("/ProccessAPIWithVadic")
    Call<DownBusinessResponse> fetchDownBusinessDetail(@Body DownBusinessRequest request, @Header("apikey") String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<MonthlyIncentiveResponse> fetchMonthlyIncomeDetail(@Body MonthlyIncentiveRequest weeklyIncentiveRequest);

    @POST("/ProccessAPIWithVadic")
    Call<FPVReportResponse> fetchFPVReportDetail(@Body BaseFromToRequest request);

    @POST("/ProccessAPIWithVadic")
    Call<DailyIncentiveResponse> fetchDailyIncentiveDetail(@Body DailyIncentiveRequest request);


    @POST("/ProcessAPIWithK")
    Call<Dy_DailyIncentiveResponse> fetchDailyIncentive(@Body DailyIncentiveRequest request);


    @POST("/ProccessAPIWithVadic")
    Call<Dy_DailyIncentiveResponse> fetchDynamicDailyIncentive(@Body DailyIncentiveRequest request, @Header("apikey") String apikey);

    @POST("/ProccessAPIWithVadic")
    Call<Dy_MonthlyIncentiveResponse> fetchDynamicMonthlyIncentive(@Body DailyIncentiveRequest request, @Header("apikey") String apikey);


    @POST("/ProccessAPIWithVadic")
    Call<Dy_WeeklyIncentiveResponse> fetchDynamicWeeklyIncentive(@Body WeeklyIncentiveRequest request, @Header("apikey") String apikey);


    @POST("/ProccessAPIWithVadic")
    Call<Dy_MFADetailResponse> fetchDynamicMFAReport(@Body BaseRequest request, @Header("apikey") String apikey);


    @POST("/ProccessAPIWithVadic")
    Call<Dy_MonthlyRewardPoints> fetchDynamicMonthlyRewardPoint(@Body BaseFromToRequest request, @Header("apikey") String apikey);





}
