package com.vadicindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.base.network.NetworkClient1;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.vadicindia.business.activity.BusinessDashboardActivity;
import com.vadicindia.business.business_constants.ApiConstants;
import com.vadicindia.business.call_api.ProfileServices;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.responsemodel.WelcomeBannerResponse;
import com.vadicindia.business.shared_pref.SharedPrefrence_Business;
import com.vadicindia.business.utility.ConnectivityUtils;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    View view;
    private SliderLayout mDemoSlider;
    private TextView textviewSkip;
    ArrayList<Integer> images = new ArrayList<>();
    ProgressDialog progressDialog;
    ArrayList<WelcomeBannerResponse.WelcomeBanner> bannerList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        try {
            view=findViewById(android.R.id.content);
            mDemoSlider = (SliderLayout) findViewById(R.id.slider_intro);

            textviewSkip = (TextView) findViewById(R.id.textview_skip);


            //call api
            if(!ConnectivityUtils.isNetworkEnabled(WelcomeActivity.this)){
                Toast.makeText(WelcomeActivity.this,getResources().getString(R.string.network_error),Toast.LENGTH_SHORT).show();
            }
            else {
                getWelcomeBanner();
            }

            textviewSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

              /*  if(SharedPrefrence_Business.getPassword(WelcomeActivity.this).toString().equals("")
                        && SharedPrefrence_Business.getUserID(SplashActivity.this).toString().equals("")
                        || new LoginPreferences_Utility(SplashActivity.this).getLoggedinUser().getUserName().equals("")){

                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);

                    startActivity(i);
                    finish();overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);

                }
                else {
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);

                    startActivity(i);
                    //finish();
                    finish();overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);
                }*/

                    Intent i = new Intent(WelcomeActivity.this, BusinessDashboardActivity.class);
                    startActivity(i);
                    finish();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void getWelcomeBanner(){
        try {
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();

            String strApiRequest="";

            /*Main Request Model*/
            BaseRequest baseRequest =new BaseRequest();

            try {

                /*Base Request Model*/

                baseRequest.setReqtype(ApiConstants.REQUEST_GET_POPUP);
                baseRequest.setUserid( SharedPrefrence_Business.getUserID(this));
                baseRequest.setPasswd( SharedPrefrence_Business.getPassword(this));

                strApiRequest=new Gson().toJson(baseRequest);

                Log.e("Value",strApiRequest);
            }catch (Exception e){
                e.printStackTrace();
            }


            Call<WelcomeBannerResponse> fetchHomeDash=
                    NetworkClient1.getInstance(this).create(ProfileServices.class).fetchWelcomeBanner(baseRequest);

            fetchHomeDash.enqueue(new Callback<WelcomeBannerResponse>() {
                @Override
                public void onResponse(Call<WelcomeBannerResponse> call, Response<WelcomeBannerResponse> response) {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    try {

                        WelcomeBannerResponse Response =new WelcomeBannerResponse();
                        Response=response.body();

                        if(Response != null){
                            if (Response.getResponse().equals("OK")) {

                              if(Response.getPopupdetail() != null && Response.getPopupdetail().size() > 0){
                                  bannerList=new ArrayList<WelcomeBannerResponse.WelcomeBanner>();
                                  bannerList=Response.getPopupdetail();
                                  /* SEt banner in slider*/
                                  if(bannerList != null && bannerList.size() > 0){

                                      intiatSlider(bannerList);
                                  }
                              }
                              else {
                                  images.add(R.mipmap.business_slider_1);
                                  images.add(R.mipmap.business_slider_1);
                                  //images.add(R.mipmap.app_banner_1);
                                  setBanners(images);
                              }
                            }
                            else if(Response.getResponse().equals("FAILED") && Response.getMsg().contains("Invalid Login")){
                                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                                //finish();
                            }

                            else {

                                String toast= Response.getResponse()+ ":" +Response.getMsg();
                                Toast.makeText(WelcomeActivity.this, toast, Toast.LENGTH_SHORT).show();
                                // showSnackbar(toast);

                            }
                        }
                        else {
                            //Toast.makeText(context,"flight not available / please try later or other",Toast.LENGTH_SHORT).show();
                            String toast= "Something wrong..";
                            Snackbar.make(view, toast, Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    })
                                    .setActionTextColor(WelcomeActivity.this.getResources().getColor(android.R.color.holo_red_light ))
                                    .show();
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<WelcomeBannerResponse> call, Throwable t) {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(WelcomeActivity.this.getResources().getColor(android.R.color.holo_red_light ))
                            .show();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBanners(ArrayList<Integer> Image) {
        mDemoSlider.removeAllSliders();
        ArrayList<HashMap<String, Integer>> url_maps_array = new ArrayList<HashMap<String, Integer>>();

        for (int i = 0; i < Image.size(); i++) {
            HashMap<String, Integer> url_maps = new HashMap<String, Integer>();
            url_maps.put("", Image.get(i));
            url_maps_array.add(url_maps);
        }
        for (int i = 0; i < url_maps_array.size(); i++) {
            for (String name : url_maps_array.get(i).keySet()) {
                DefaultSliderView textSliderView = new DefaultSliderView(this);
//TextSliderView.. USE THIS IF U WANT DESCRIPTION ON SLIDER

// initialize a SliderLayout
                textSliderView
//.description(name)//FOR DESC
                        .image(url_maps_array.get(i).get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
// Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                    }
                });

//add your extra informatio
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", name);

                mDemoSlider.addSlider(textSliderView);
            }
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

    }

    /*Slider 1*/
    public void intiatSlider(ArrayList<WelcomeBannerResponse.WelcomeBanner> Image) {

        mDemoSlider.removeAllSliders();

        ArrayList<HashMap<String, String>> url_maps_array = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < Image.size(); i++) {
            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put(Image.get(i).toString(), Image.get(i).getPopupurl());
            url_maps_array.add(url_maps);
        }
        for (int i = 0; i < url_maps_array.size(); i++) {
            for (String name : url_maps_array.get(i).keySet()) {
                String id= String.valueOf(url_maps_array.get(i).keySet());
                String imgName= String.valueOf(url_maps_array.get(i).values());
                DefaultSliderView textSliderView = new DefaultSliderView(getApplicationContext());

                //TextSliderView.. USE THIS IF U WANT DESCRIPTION ON SLIDER

                // initialize a SliderLayout
                textSliderView
                        .description(name)//FOR DESC
                        .image(String.valueOf(url_maps_array.get(i).get(name)))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        String imgName = slider.getBundle().get("Name").toString();
                        String imgID = slider.getBundle().get("Id").toString();
                       /* if(j==0)
                            Utilities.goToPage(HomeActivity.this, HotelSearchActivity.class, null);*/
                    }
                });

                //add your extra informatio
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("Name", imgName);
                textSliderView.getBundle().putString("Id", id);

                mDemoSlider.addSlider(textSliderView);
            }
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.SimpleOnPageChangeListener());
    }
}