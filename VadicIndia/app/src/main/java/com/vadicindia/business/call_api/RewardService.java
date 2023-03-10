package com.vadicindia.business.call_api;



import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.responsemodel.RewardResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RewardService {

    //for Reward List Api  (Reward fragment)
    @POST("/ProccessAPIWithVadic")
    Call<RewardResponse> fetchReward(@Body BaseRequest baseRequest, @Header("apikey") String apikey);
}
