package com.vadicindia.business.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.base.network.NetworkClient1;
import com.vadicindia.R;
import com.vadicindia.business.business_constants.ApiConstants;
import com.vadicindia.business.call_api.WalletServices;
import com.vadicindia.business.model_business.requestmodel.MainWalletReportRequest;
import com.vadicindia.business.model_business.responsemodel.MainWalletReportResponse;
import com.vadicindia.business.model_business.responsemodel.MatchingRecognitionResponse;
import com.vadicindia.business.shared_pref.SharedPrefrence_Business;
import com.vadicindia.business.utility.ConnectivityUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainWalletReportActivity extends AppCompatActivity {

    TableLayout tableLayoutWalletRecord;
    ProgressDialog pDialog;

    TextView textViewDepositBal;
    TextView textViewUsedBal;
    TextView textViewMainBal;
    TextView textViewNext;

    LinearLayout linearLayoutNext;
    LinearLayout linearLayoutNoRecord;
    LinearLayout linearLayoutTable;
    int from;
    int to;
    int totalRecord;
    String strApiKey="";


    ArrayList<MainWalletReportResponse.MainWallet> mainWalletArrayList;

    MatchingRecognitionResponse matchingRecognitionResponse;
    MainWalletReportResponse.MainWallet[] mainWallets;

    Context context;

    /*Constructor*/
    public MainWalletReportActivity(){

        //empty constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_main_wallet_report_fragment);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            tableLayoutWalletRecord = (TableLayout) findViewById(R.id.main_wallet_report_frag_tablelayout_wallet_record);
            context = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Main Wallet Report");

        /* Get api key value from Shared Preference */
        strApiKey=SharedPrefrence_Business.getApiKey(this);

            textViewDepositBal = (TextView) findViewById(R.id.main_wallet_report_frag_textView_deposit);
            textViewUsedBal = (TextView) findViewById(R.id.main_wallet_report_frag_textView_used);
            textViewMainBal = (TextView) findViewById(R.id.main_wallet_report_frag_textView_bal);
            textViewNext = (TextView) findViewById(R.id.main_wallet_report_frag_textView_next);
            linearLayoutNext = (LinearLayout) findViewById(R.id.main_wallet_report_frag_layout_next);
            linearLayoutNoRecord = (LinearLayout) findViewById(R.id.main_wallet_report_frag_layout_noRecord);
            linearLayoutTable = (LinearLayout) findViewById(R.id.main_wallet_report_frag_layout_table);

            from = 1;
            to = 10;
            //Call Save Complaint  APi
            if (!ConnectivityUtils.isNetworkEnabled(context)) {
                Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            } else {
                getMainWalletReport();
            }

            linearLayoutNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    from = from + 10;
                    to = to + 10;
                    if (from <= totalRecord) {

                        //Call SAve Complaint  APi
                        if (!ConnectivityUtils.isNetworkEnabled(context)) {
                            Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        } else {
                            getMainWalletReport();
                        }
                    } else {
                        Toast.makeText(context, "Record complete", Toast.LENGTH_SHORT).show();
                        linearLayoutNext.setVisibility(View.GONE);
                    }

                }
            });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /* Get Main Wallet Transaction Report Api*/
    public void getMainWalletReport(){
        pDialog=new ProgressDialog(MainWalletReportActivity.this);
        pDialog.setMessage("please wait..");
        pDialog.setCancelable(false);
        pDialog.show();
        MainWalletReportRequest mainWalletReportRequest = new MainWalletReportRequest();

        /*Pos Method*/
        String Get_Paramenter = "";
        try {

            /*Set value in Entity class*/
            mainWalletReportRequest.setReqtype(ApiConstants.REQUEST_MAIN_WALLET_REPORT);
            mainWalletReportRequest.setPasswd(SharedPrefrence_Business.getPassword(context));
            mainWalletReportRequest.setUserid(SharedPrefrence_Business.getUserID(context));
            mainWalletReportRequest.setIslogin(SharedPrefrence_Business.getIsLogin(context));
            mainWalletReportRequest.setFrom(String.valueOf(from));
            mainWalletReportRequest.setTo(String.valueOf(to));

            //1. Convert object to JSON string
            Gson gson = new Gson();
            Get_Paramenter = gson.toJson(mainWalletReportRequest);
            Log.e("ReqMainWalletReport:", Get_Paramenter);

        } catch (Exception e) {
        }

        Call<MainWalletReportResponse> walletReportResponseCall=
                NetworkClient1.getInstance(context).create(WalletServices.class).fetchMainWalletReport(mainWalletReportRequest,strApiKey);

        walletReportResponseCall.enqueue(new Callback<MainWalletReportResponse>() {
            @Override
            public void onResponse(Call<MainWalletReportResponse> call, Response<MainWalletReportResponse> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                try {

                    //string = jsonObject.toString();
                   MainWalletReportResponse mainWalletReportResponse = new  MainWalletReportResponse();
                   mainWalletReportResponse=response.body();

                    if (mainWalletReportResponse.getResponse().equals("OK")) {

                        mainWallets= mainWalletReportResponse.getAchistory();
                        totalRecord= Integer.parseInt(mainWalletReportResponse.getRecordcount());

                        textViewDepositBal.setText(mainWalletReportResponse.getCredit());
                        textViewMainBal.setText(mainWalletReportResponse.getBalance());
                        textViewUsedBal.setText(mainWalletReportResponse.getDebit());

                        if(totalRecord > 0 &&  mainWallets.length > 0) {
                            linearLayoutNext.setVisibility(View.VISIBLE);
                            linearLayoutNoRecord.setVisibility(View.GONE);
                            linearLayoutTable.setVisibility(View.VISIBLE);
                            initTable(mainWallets);

                        }
                        else {
                            Toast.makeText(context, "No Main Wallet Report Record Found", Toast.LENGTH_SHORT).show();
                            linearLayoutNext.setVisibility(View.GONE);
                            linearLayoutNoRecord.setVisibility(View.VISIBLE);
                            linearLayoutTable.setVisibility(View.GONE);
                            mainWalletArrayList=new ArrayList<MainWalletReportResponse.MainWallet>(Arrays.asList(mainWallets));
                            mainWalletArrayList.clear();
                            initTable(mainWallets);

                        }

                        //ArrayList<Element> arrayList = new ArrayList<Element>(Arrays.asList(array));


                    }
                    else {
                        Toast.makeText(context, mainWalletReportResponse.getResponse(), Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MainWalletReportResponse> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                Toast.makeText(MainWalletReportActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initTable( MainWalletReportResponse.MainWallet [] walletDetails) {
        //remove all rows if exist already

        if(from==1){

            tableLayoutWalletRecord.setVisibility(View.VISIBLE);

            tableLayoutWalletRecord.removeAllViews();


            TableRow tbrow0 = new TableRow(context);

            TextView tvh_1 = new TextView(context);

            tvh_1.setText(" ");

            tvh_1.setGravity(Gravity.CENTER);

            tvh_1.setPadding(10, 10, 10, 10);

            tbrow0.addView(tvh_1);

        /*Heading Text S No*/
            TextView tvh_2 = new TextView(context);

            tvh_2.setText("S.No");

            tvh_2.setTextColor(getResources().getColor(R.color.black));

            tvh_2.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_2.setGravity(Gravity.CENTER);

            tvh_2.setPadding(10, 10, 10, 10);

            tvh_2.setTypeface(null, Typeface.BOLD);

            tbrow0.addView(tvh_2);

        /*Heading Text Date*/
            TextView tvh_3 = new TextView(context);


            tvh_3.setText("Date");

            tvh_3.setTextColor(getResources().getColor(R.color.black));

            tvh_3.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_3.setGravity(Gravity.CENTER);

            tvh_3.setPadding(10, 10, 10, 10);

            tvh_3.setTypeface(null, Typeface.BOLD);

            tbrow0.addView(tvh_3);

        /*Heading Text Remark*/
            TextView tvh_4 = new TextView(context);

            tvh_4.setText("Remark");

            tvh_4.setTextColor(getResources().getColor(R.color.black));

            tvh_4.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_4.setGravity(Gravity.CENTER);

            tvh_4.setPadding(10, 10, 10, 10);

            tvh_4.setTypeface(null, Typeface.BOLD);

            tbrow0.addView(tvh_4);

        /*Heading Text Deposit*/
            TextView tvh_5 = new TextView(context);

            tvh_5.setText("Deposit");

            tvh_5.setTextColor(getResources().getColor(R.color.black));

            tvh_5.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_5.setGravity(Gravity.CENTER);

            tvh_5.setPadding(10, 10, 10, 10);

            tvh_5.setTypeface(null, Typeface.BOLD);

            tbrow0.addView(tvh_5);

        /*Heading Text Used*/
            TextView tvh_6 = new TextView(context);

            tvh_6.setText("Used");

            tvh_6.setTextColor(getResources().getColor(R.color.black));

            tvh_6.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_6.setGravity(Gravity.CENTER);

            tvh_6.setPadding(10, 10, 10, 10);

            tvh_6.setTypeface(null, Typeface.BOLD);

            tbrow0.addView(tvh_6);

        /*Heading Text Balance*/
            TextView tvh_7 = new TextView(context);

            tvh_7.setText("Balance");

            tvh_7.setTextColor(getResources().getColor(R.color.black));

            tvh_7.setBackgroundResource(R.drawable.white_bg_box_black_border);

            tvh_7.setGravity(Gravity.CENTER);

            tvh_7.setPadding(10, 10, 10, 10);

            tvh_7.setTypeface(null, Typeface.BOLD);

            //tbrow0.addView(tvh_7);



            tableLayoutWalletRecord.addView(tbrow0);

        /*Add Data*/
            for (int i = 0; i < walletDetails.length; i++) {

                TableRow tbrow1 = new TableRow(context);

                final int index = i;

                TextView tvd_1 = new TextView(context);
                tvd_1.setText(" ");
                tvd_1.setGravity(Gravity.CENTER);
                tvd_1.setPadding(10, 10, 10, 10);
                tbrow1.addView(tvd_1);

            /*Serial Numaber*/
                try{
                    TextView tvd_2 = new TextView(context);
                    tvd_2.setEllipsize(TextUtils.TruncateAt.END);
                    tvd_2.setGravity(Gravity.CENTER);

                    //tvd_2.setText(index);
                    tvd_2.setBackgroundResource(R.drawable.white_bg_box_black_border);
                    tvd_2.setPadding(10, 10, 10, 10);
                    //tvd_2.setText(String.valueOf(index+1));
                    tvd_2.setText(walletDetails[i].getSno());

                    tbrow1.addView(tvd_2);
                }catch (Exception e){
                    e.printStackTrace();
                }




            /*Set Date*/
                TextView tvd_3 = new TextView(context);

                tvd_3.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_3.setText(walletDetails[i].getDate());

                tvd_3.setTextColor(Color.BLACK);

                tvd_3.setBackgroundResource(R.drawable.white_bg_box_black_border);

                tvd_3.setGravity(Gravity.LEFT);

                tvd_3.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_3);

             /*Remark*/

                TextView tvd_4 = new TextView(context);

                tvd_4.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                //tv13.setText(compList[i].getStatus());

                tvd_4.setTextColor(Color.BLACK);

                tvd_4.setBackgroundResource(R.drawable.white_bg_box_black_border);

                tvd_4.setGravity(Gravity.CENTER | Gravity.LEFT);

                tvd_4.setPadding(10, 10, 10, 10);


                //SpannableString spanString = new SpannableString(compList[i].getStatus());

                //spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);

                //spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);

                tvd_4.setText(walletDetails[i].getRemarks());

                tbrow1.addView(tvd_4);


            /*Deposit*/

                TextView tvd_5= new TextView(context);

                tvd_5.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_5.setText(walletDetails[i].getDeposit());

                tvd_5.setTextColor(Color.BLACK);

                tvd_5.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_5.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_5.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_5);

             /*Used Balance*/

                TextView tvd_6= new TextView(context);

                tvd_6.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_6.setText(walletDetails[i].getUsed());

                tvd_6.setTextColor(Color.BLACK);

                tvd_6.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_6.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_6.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_6);

             /* Remaning Balance*/

                TextView tvd_7= new TextView(context);

                tvd_7.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_7.setText(walletDetails[i].getBalance());

                tvd_7.setTextColor(Color.BLACK);

                tvd_7.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_7.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_7.setPadding(10, 10, 10, 10);

                //tbrow1.addView(tvd_7);

                tableLayoutWalletRecord.addView(tbrow1);


            }
        }

        else {

                tableLayoutWalletRecord.setVisibility(View.VISIBLE);
             /*Add Data*/
            for (int i = 0; i < walletDetails.length; i++) {

                TableRow tbrow1 = new TableRow(context);

                final int from = i;

                TextView tvd_1 = new TextView(context);
                tvd_1.setText(" ");
                tvd_1.setGravity(Gravity.CENTER);
                tvd_1.setPadding(10, 10, 10, 10);
                tbrow1.addView(tvd_1);

            /*Serial Numaber*/
                try{
                    TextView tvd_2 = new TextView(context);
                    tvd_2.setEllipsize(TextUtils.TruncateAt.END);
                    tvd_2.setGravity(Gravity.CENTER);
                    tvd_2.setBackgroundResource(R.drawable.white_bg_box_black_border);
                    tvd_2.setPadding(10, 10, 10, 10);
                    //tvd_2.setText(index);

                    //tvd_2.setText(String.valueOf(index+1));
                    tvd_2.setText(walletDetails[i].getSno());
                    tbrow1.addView(tvd_2);
                }catch (Exception e){
                    e.printStackTrace();
                }




            /*Set Date*/
                TextView tvd_3 = new TextView(context);

                tvd_3.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_3.setText(walletDetails[i].getDate());

                tvd_3.setTextColor(Color.BLACK);

                tvd_3.setBackgroundResource(R.drawable.white_bg_box_black_border);

                tvd_3.setGravity(Gravity.LEFT);

                tvd_3.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_3);

             /*Remark*/

                TextView tvd_4 = new TextView(context);

                tvd_4.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                //tv13.setText(compList[i].getStatus());

                tvd_4.setTextColor(Color.BLACK);

                tvd_4.setBackgroundResource(R.drawable.white_bg_box_black_border);

                tvd_4.setGravity(Gravity.CENTER | Gravity.LEFT);

                tvd_4.setPadding(10, 10, 10, 10);


                //SpannableString spanString = new SpannableString(compList[i].getStatus());

                //spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);

                //spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);

                tvd_4.setText(walletDetails[i].getRemarks());

                tbrow1.addView(tvd_4);


            /*Deposit*/

                TextView tvd_5= new TextView(context);

                tvd_5.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_5.setText(walletDetails[i].getDeposit());

                tvd_5.setTextColor(Color.BLACK);

                tvd_5.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_5.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_5.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_5);

             /*Used Balance*/

                TextView tvd_6= new TextView(context);

                tvd_6.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_6.setText(walletDetails[i].getUsed());

                tvd_6.setTextColor(Color.BLACK);

                tvd_6.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_6.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_6.setPadding(10, 10, 10, 10);

                tbrow1.addView(tvd_6);

             /* Remaning Balance*/

                TextView tvd_7= new TextView(context);

                tvd_7.setEllipsize(TextUtils.TruncateAt.END);

                // tv12.setMaxLines(1);

                tvd_7.setText(walletDetails[i].getBalance());

                tvd_7.setTextColor(Color.BLACK);

                tvd_7.setBackgroundResource(R.drawable.white_bg_box_black_border);
                //tv14.setBackgroundColor(getResources().getColor(R.color.gray));

                tvd_7.setGravity(Gravity.CENTER | Gravity.RIGHT);

                tvd_7.setPadding(10, 10, 10, 10);

                //tbrow1.addView(tvd_7);

                tableLayoutWalletRecord.addView(tbrow1);


            }
        }



    }

    //Back Press Arrow o ToolBar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
        return true;
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            onBackPressed();
            *//*if(progressDialog.isShowing())
                progressDialog.dismiss();
            fetchFlightSearch.cancel();*//*
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
       /* if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }*/
        super.onBackPressed();

        //overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        //Intent intent=new Intent(this,BusPassengerInfoActivity.class);
        //startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
        finish();
    }
}
