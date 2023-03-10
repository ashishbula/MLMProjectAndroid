package com.vadicindia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.base.network.NetworkClient;

import com.vadicindia.business.activity.BusinessDashboardActivity;
import com.vadicindia.business.activity.ForgotpassActivity;
import com.vadicindia.business.activity.RegistrationActivity;
import com.vadicindia.business.business_constants.ApiConstants;
import com.vadicindia.business.call_api.ProfileServices;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.responsemodel.LoginResponseEntity;
import com.vadicindia.business.shared_pref.SharedPrefrence_Business;
import com.vadicindia.business.utility.ConnectivityUtils;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtxtUserId;
    EditText edtxtUserPass;
    MaterialButton btnSecure_Login;
    boolean flagValidation;
    String stringUserid = "";
    String stringPassword = "";
    String strErrorMessage = "";
    String stringReqType;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    TextView txtforgetpass;
    TextView txtNewReg;
    ImageView imgPassVisible;

    private MaterialCheckBox rememberCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private Boolean IsLoginFirstTime;
    Context context;
    ProgressDialog pDialog;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login_activity);

        view=findViewById(android.R.id.content);

        try{

            LoginActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            edtxtUserId = (EditText) findViewById(R.id.login_edtxt_userid);
            edtxtUserPass = (EditText) findViewById(R.id.login_edtxt_pass);
            txtforgetpass = (TextView) findViewById(R.id.login_txt_forgot_pass);
            txtNewReg = (TextView) findViewById(R.id.login_txt_registration);
            rememberCheckBox=(MaterialCheckBox) findViewById(R.id.login_chkbox_remember);
            btnSecure_Login = (MaterialButton) findViewById(R.id.login_btn_login);
            imgPassVisible = (ImageView) findViewById(R.id.login_img_pass);

            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();

            saveLogin = loginPreferences.getBoolean("saveLogin",false);

            if (saveLogin == true) {
                edtxtUserId.setText(loginPreferences.getString("userid", ""));
                edtxtUserPass.setText(loginPreferences.getString("password", ""));
                rememberCheckBox.setChecked(true);
            }

            /* image in edit text in right side
               eys img on click password visible or not*/
            imgPassVisible.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowHidePass(v);
                    edtxtUserPass.setSelection(edtxtUserPass.length());
                }
            });

            txtforgetpass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ForgotpassActivity.class);
                    startActivity(i);
                   // Toast.makeText(LoginActivity.this,"Coming soon..",Toast.LENGTH_SHORT).show();
                }
            });

            txtNewReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                    i.putExtra("From","Login");
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);
                    //Toast.makeText(LoginActivity.this,"Coming soon..",Toast.LENGTH_SHORT).show();
                }
            });

            if(rememberCheckBox.isChecked()){
                stringUserid = edtxtUserId.getText().toString().trim();
                stringPassword = edtxtUserPass.getText().toString().trim();

                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("userid", stringUserid);
                loginPrefsEditor.putString("password", stringPassword);
                loginPrefsEditor.commit();

            }
            else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            rememberCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (rememberCheckBox.isChecked()) {

                        // null value validation
                        if (edtxtUserId.getText().toString().trim().equals("")) {
                            Toast.makeText(LoginActivity.this,
                                    "Please Enter Userid.", Toast.LENGTH_LONG)
                                    .show();
                            edtxtUserId.requestFocus();
                            return;
                        } else if (edtxtUserPass.getText().toString().trim().equals("")) {
                            Toast.makeText(getApplicationContext(),
                                    "Please Enter Password.", Toast.LENGTH_LONG)
                                    .show();
                            edtxtUserPass.requestFocus();
                            return;

                        } else {
                            stringUserid = edtxtUserId.getText().toString().trim();
                            stringPassword = edtxtUserPass.getText().toString().trim();

                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("userid", stringUserid);
                            loginPrefsEditor.putString("password", stringPassword);
                            loginPrefsEditor.commit();

                        }

                    }
                    else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                }
            });

            btnSecure_Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // null value validation
                    if (edtxtUserId.getText().toString().trim().equals("")) {
                        edtxtUserId.setError("Please enter user id");
                        edtxtUserId.requestFocus();
                        return;
                    } else if (edtxtUserPass.getText().toString().trim().equals("")) {
                        edtxtUserPass.setError("Please enter user password");
                        edtxtUserPass.requestFocus();
                        return;

                    } else {
                        View view1 = LoginActivity.this.getCurrentFocus();
                        if (view1 != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(),
                                    0);
                        }
                        stringUserid = edtxtUserId.getText().toString().trim();
                        stringPassword = edtxtUserPass.getText().toString().trim();

                        if (rememberCheckBox.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("userid", stringUserid);
                            loginPrefsEditor.putString("password", stringPassword);
                            loginPrefsEditor.commit();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }
                        //Call Login Api
                        if(!ConnectivityUtils.isNetworkEnabled(LoginActivity.this)){
                            Toast.makeText(LoginActivity.this,getString(R.string.network_error),Toast.LENGTH_SHORT).show();
                        }
                        else {
                           getBusinessLoginDetail();

                        }

                    }


                }

            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ShowHidePass(View view){

        if(view.getId()==R.id.login_img_pass){

            if(edtxtUserPass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_visible_off);

                //Show Password
                edtxtUserPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_visible);

                //Hide Password
                edtxtUserPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }

    public void getBusinessLoginDetail(){
        pDialog=new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Please wait");
        pDialog.setCancelable(false);
        pDialog.show();

        BaseRequest Request = new BaseRequest();
        /*Set value in Entity class*/
        Request.setReqtype(ApiConstants.REQUEST_LOGIN);
        Request.setPasswd(stringPassword);
        Request.setUserid(stringUserid);
        Request.setIslogin("N");

        Call<LoginResponseEntity> loginResponseEntityCall=
                NetworkClient.getInstance(LoginActivity.this).create(ProfileServices.class).fetchLogin(Request);


        loginResponseEntityCall.enqueue(new Callback<LoginResponseEntity>() {
            @Override
            public void onResponse(Call<LoginResponseEntity> call, Response<LoginResponseEntity> response) {

                if(pDialog.isShowing())
                    pDialog.dismiss();
                try {

                    LoginResponseEntity Response =new LoginResponseEntity();
                    Response=response.body();
                    if(Response != null){
                        if (Response.getResponse().equals("OK")) {
                            SharedPrefrence_Business.setUserID(LoginActivity.this,edtxtUserId.getText().toString());
                            SharedPrefrence_Business.setPassword(LoginActivity.this,edtxtUserPass.getText().toString());
                            SharedPrefrence_Business.setUsername(LoginActivity.this,Response.getMname());
                            SharedPrefrence_Business.setUserMobileNumber(LoginActivity.this,Response.getMobileno());
                            SharedPrefrence_Business.setProfileIamge(LoginActivity.this,Response.getProfilepic());
                            SharedPrefrence_Business.setEmailId(LoginActivity.this,Response.getEmailid());
                            SharedPrefrence_Business.setIsActive(LoginActivity.this,Response.getIsactive());
                            SharedPrefrence_Business.setAddress(LoginActivity.this,Response.getAddress());
                            SharedPrefrence_Business.setIsLogin(LoginActivity.this,"Y");
                            SharedPrefrence_Business.setApiKey(LoginActivity.this,Response.getApikey());
                            SharedPrefrence_Business.setPackage(LoginActivity.this,Response.getPackagename());

                            //loginAPICall_shop();

                            Intent i=new Intent(LoginActivity.this, BusinessDashboardActivity.class);
                            startActivity(i);
                            finish();
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);
                        }
                        else {

                            String toast= Response.getResponse()+ ": "+Response.getMsg();
                            Snackbar.make(view, toast, Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                    .show();

                        }
                    }
                    else {

                        String toast= Response.getResponse()+ ": "+Response.getMsg();
                        Snackbar.make(view, toast, Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                .show();
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseEntity> call, Throwable t) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                Snackbar.make(view, t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
