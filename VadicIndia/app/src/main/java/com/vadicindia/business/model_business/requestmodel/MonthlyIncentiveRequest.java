package com.vadicindia.business.model_business.requestmodel;

/**
 * Created by The Rock on 3/30/2018.
 */

public class MonthlyIncentiveRequest extends BaseRequest {
    //String reqtype;//":"monthlyincentive","
    //String userid;//":"dt144176","
    //String passwd;//":"ani123","
    String from;//":"1","
    String to;//":"5"}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
