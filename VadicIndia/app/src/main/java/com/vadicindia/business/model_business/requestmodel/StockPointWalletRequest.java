package com.vadicindia.business.model_business.requestmodel;

/**
 * Created by The Rock on 3/31/2018.
 */

public class StockPointWalletRequest extends BaseRequest {
    //String reqtype;//":"servicewlthistory","
    //String userid;//":"DT009189","
    //String passwd;//":"zcsx19"," +
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
