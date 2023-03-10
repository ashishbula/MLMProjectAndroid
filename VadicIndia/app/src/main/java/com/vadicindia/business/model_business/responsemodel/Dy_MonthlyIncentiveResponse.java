package com.vadicindia.business.model_business.responsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Dy_MonthlyIncentiveResponse extends BaseResponse{
    @SerializedName("Monthlyincentive")
    @Expose
    ArrayList<Map<String,String>>Monthlyincentive;
    @SerializedName("recordcount")
    String recordcount;

    public ArrayList<Map<String, String>> getMonthlyincentive() {
        return Monthlyincentive;
    }

    public void setMonthlyincentive(ArrayList<Map<String, String>> monthlyincentive) {
        Monthlyincentive = monthlyincentive;
    }

    public String getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(String recordcount) {
        this.recordcount = recordcount;
    }
}
