package com.vadicindia.business.call_api;

import com.vadicindia.business.model_business.requestmodel.BaseFromToRequest;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.ComplaintReplyRequest;
import com.vadicindia.business.model_business.requestmodel.ComplaintRequest;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.ComplaintDetailItemViewResponse;
import com.vadicindia.business.model_business.responsemodel.ComplaintDetailResponse;
import com.vadicindia.business.model_business.responsemodel.ComplaintTypeListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ComplaintServices {

    //for Complaint List Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<ComplaintTypeListResponse> fetchComplaintList(@Body BaseRequest baseRequest, @Header("apikey") String apikey);

    //for Complaint  Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<BaseResponse> fetchComplaint(@Body ComplaintRequest complaintRequest, @Header("apikey") String apikey);

    //for Complaint  Detail Api
    @POST("/ProccessAPIWithVadic")
    Call<ComplaintDetailResponse> fetchComplaintDetail(@Body BaseFromToRequest complaintRequest, @Header("apikey") String apikey);

    //for Complaint  Reply Api
    @POST("/ProccessAPIWithVadic")
    Call<ComplaintDetailItemViewResponse> fetchComplaintDetailView(@Body ComplaintReplyRequest complaintRequest, @Header("apikey") String apikey);
}
