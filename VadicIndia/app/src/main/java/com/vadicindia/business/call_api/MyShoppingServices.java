package com.vadicindia.business.call_api;


import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.responsemodel.MyPurchaseDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyShoppingServices {
    //@POST("/Dtprocess.aspx")
    //Call<RepurchaseProductResponse> fetchRepurchaseProduct(@Body BaseRequest request);

    @POST("/ProccessAPIWithVadic")
    Call<MyPurchaseDetailResponse> fetchPurchaseDetail(@Body BaseRequest request);
}
