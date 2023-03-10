package com.vadicindia.business.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vadicindia.LoginActivity;
import com.vadicindia.R;

import com.vadicindia.business.shared_pref.SharedPrefrence_Business;



public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    Context context;
    ProgressDialog progressDialog;
    String webUrl = "";
    String type = "";
    String from = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_activity_web_view);

        try {

            /*Toolbar back arrow and title enable */
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            Bundle b = getIntent().getExtras();

            if (b != null) {
                  from=b.getString("From");
                webUrl = b.getString("URL");
                type = b.getString("Type");

                getSupportActionBar().setTitle(type);
               /* if(from.contains("DashHome")){
                    webUrl=b.getString("URL");
                }
                else {
                    webUrl=b.getString(Constants_gift.WEB_URL);
                }*/


                webView = (WebView) findViewById(R.id.webview_act_view);
                startWebView(webUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        context = this;


    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void startWebView(String url) {

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        //Inject WebAppInterface methods into Web page by having Interface name 'Android'
        // webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new HelloWebViewClient());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl(url);
    }

    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

    }

    //Back Press Arrow o ToolBar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
       /* if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }*/
        super.onBackPressed();
        if (SharedPrefrence_Business.getPassword(WebViewActivity.this).toString().equals("")
                || SharedPrefrence_Business.getUserID(WebViewActivity.this).toString().equals("")) {

            Intent i = new Intent(WebViewActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
        }
        else {

            if(from.equals("Login")){
                Intent i = new Intent(WebViewActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
            }
            else  if(from.equals("Utility")){
                Intent i = new Intent(WebViewActivity.this, BusinessDashboardActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
            }
            else  if(from.equals("Brand")){
                Intent i = new Intent(WebViewActivity.this, BusinessDashboardActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
            }
            else if (type.equals("Business") || type.equals("Generation View") || type.equals("Geneology")
                    || type.equals("Website") || type.equals("Welcome Letter")|| type.equals("Statement")
                    ||type.equals("Global Pool")) {
                Intent i = new Intent(WebViewActivity.this, BusinessDashboardActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
            }
            else {
                finish();
            }

        }


    }
}
