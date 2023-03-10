package com.vadicindia.business.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.base.network.NetworkClient;
import com.base.ui.BaseActivity;
import com.vadicindia.R;
import com.vadicindia.business.adapter.AccountTypeAdapter;
import com.vadicindia.business.adapter.BankListAdapter;
import com.vadicindia.business.adapter.MemberRelationAdapter;
import com.vadicindia.business.adapter.PincodeAreaListAdapter;
import com.vadicindia.business.adapter.StateListAdapter;
import com.vadicindia.business.call_api.ProfileServices;
import com.vadicindia.business.call_api.UploadKYCServices;
import com.vadicindia.business.business_constants.ApiConstants;
import com.vadicindia.business.model_business.requestmodel.BaseRequest;
import com.vadicindia.business.model_business.requestmodel.CheckUserNameRequest;
import com.vadicindia.business.model_business.requestmodel.NewJoinOtpRequest;
import com.vadicindia.business.model_business.requestmodel.NewRegistrationRequest;
import com.vadicindia.business.model_business.requestmodel.PincodeDetailRequest;
import com.vadicindia.business.model_business.responsemodel.AccountTypeListResponse;
import com.vadicindia.business.model_business.responsemodel.BankListResponse;
import com.vadicindia.business.model_business.responsemodel.BaseResponse;
import com.vadicindia.business.model_business.responsemodel.GetMemberNameResponse;
import com.vadicindia.business.model_business.responsemodel.MemberRelationListResponse;
import com.vadicindia.business.model_business.responsemodel.NewRegisterResponse;
import com.vadicindia.business.model_business.responsemodel.PincodeDetailRespose;
import com.vadicindia.business.model_business.responsemodel.StateListResponse;
import com.vadicindia.business.shared_pref.SharedPrefrence_Business;
import com.vadicindia.business.utility.ConnectivityUtils;
import com.vadicindia.business.utility.Utilities;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.fragment.app.DialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends BaseActivity {
    View view;
    RadioGroup rdGroupSide;
    RadioGroup rdGPan;
    RadioButton rdbYes;
    RadioButton rdbNo;
    RadioButton rdBtnLeft;
    RadioButton rdBtnRight;
    EditText edTextSponsorIdNo;
    EditText edTextPanNo;
    EditText edTxtFatherName;
    EditText edTxtName;
    EditText edTxtBankName;
    EditText edTxtUserName;
    EditText edTxtBranch;
    EditText edTxtAcNo;
    EditText edTxtAadharNo;
    EditText edTxtPanNo;
    EditText edTxtIfsc;
    EditText edTxtAddress;
    EditText edTxtPostalAddress;
    EditText edTxtDistric;
    EditText edTxtPostalDistric;
    EditText edTxtCity;
    EditText edTxtPostalCity;
    EditText edTxtState;
    EditText edTxtPincode;
    EditText edTxtPostalPincode;
    EditText edTxtMobile;
    EditText edTxtPhone;
    EditText edTxtEmail;
    EditText edTxtNomineeName;
    EditText edTxtNomineeRelation;
    EditText edTxtNomineeMobile;
    CheckBox checkBoxTerm;
    CheckBox checkBoxSameAs;

    EditText edTxtPassword;
    EditText edTxtCnfmPassword;
    TextView txtSponserName;
    TextView txtUser;
    TextView txtViewTerm;
    TextView txtUplinerName;
    TextView txtSideMsg;
    LinearLayout layoutPanNo;
    LinearLayout layoutSubmit;
    LinearLayout layoutSide;
    LinearLayout layoutDetail;
    LinearLayout layoutCheckUserNAme;
    LinearLayout layoutSponserNme;
    Button btnSubmit;
    TextView btnCheckSponser;
    Button btnCheckUser;
    Spinner spinnerPreFix;
    Spinner spinnerBank;
    Spinner spinnerState;
    Spinner spinnerArea;
    Spinner spinnerPostalArea;
    Spinner spinnerPostalState;
    Spinner spinnerAcType;
    public static EditText edTextDOB;
    public static EditText edTxtNomineeDOB;
    ProgressDialog pDialog;
    ImageView imgCap;
    ImageView imgCapRefresh;
    EditText edTxtCaptcha;
    TextView txtans;

    String strSide="";
    String strSponserID="";
    String strUplinerID="";
    String strSponserNme="";
    String strUplinerNme="";
    String strName="";
    String strUserName="";
    String strF_H_Name="";
    String strDOB="";
    String strMarriedDate="";
    String strMarital="";
    String strMemRelation="";
    String strAddress="";
    String strPostalAddress="";
    String strDistrict="";
    String strPostalDistrict="";
    String strDistrictId="";
    String strPostalDistrictId="";
    String strStateName="";
    String strPostalStateName="";
    String strStateId="";
    String strPostalStateId="";
    String strAeraName="";
    String strPostalAeraName="";
    String strAeraId="0";
    String strPostalAeraId="0";
    String strCity="";
    String strPostalCity="";
    String strCityID="";
    String strPostalCityID="";
    String strPincode="";
    String strPostalPincode="";
    String strMobile="";
    String strPhone="";
    String strEmail="";
    String strCaptcha="";
    public static String stringDOB="";
    public static String strNomineeDOB="";
    public static String strMarrigeDate="";;
    String strpass="";
    String strNomineName="";
    String strNomineMobile="";
    String strNomineRelation="";
    Button btn1;


    String strFrom="";
    String strAcNo="";
    String strBranch="";
    String strIfsc="";
    String strPanNo="";
    String strAadhar="";
    String strAcType="";
    String strAcTypeID="";
    String strBank="";
    String strBankId="0";
    String strApiKey="0";
    String strPanAvailable="";
    boolean user=false;
    boolean sponsorId=false;
    boolean isPan=false;
    boolean isCaptcha=false;

    public static int yyFromDate ;
    public static int mmFromDate ;
    public static int ddFromDate ;

    private Timer timer = new Timer();
    private final long DELAY = 600; // in ms

    ArrayList<StateListResponse.StateList> stateListArrayList;
    StateListResponse.StateList[] stateList;
    StateListAdapter stateListAdapter;

    ArrayList<PincodeDetailRespose.PincodeDetail> pincodeAreaArrayList;
    PincodeDetailRespose.PincodeDetail[] pincodeDetails;
    PincodeAreaListAdapter adapter;

    ArrayList<BankListResponse.BankList> bankListArrayList;
    BankListResponse.BankList[] bankLists;
    BankListAdapter bankAccountAdapter;
    AccountTypeListResponse.AccountList[] accountList;
    ArrayList<AccountTypeListResponse.AccountList> accountListArrayList;
    AccountTypeAdapter accountTypeAdapter;


    Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_activity_registratioin);

        view=findViewById(android.R.id.content);
        try {

            /*Toolbar back arrow and title enable */
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("New Registration");

            /* Get api key value from Shared Preference */
            strApiKey=SharedPrefrence_Business.getApiKey(this);

        /*Call method view reference*/
            contentView(view);

            /*Text sponsor name on click */
            btnCheckSponser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edTextSponsorIdNo.getText().toString().equals("")){
                        Toast.makeText(RegistrationActivity.this,"Plz First Enter Sponsor Id No.", Toast.LENGTH_SHORT).show();
                        edTextSponsorIdNo.requestFocus();
                    }

                    else {

                        //Call MemberName Api
                        if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                            Toast.makeText(RegistrationActivity.this,getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                        else {

                            getMemberName();
                        }
                    }


                }
            });


            /*Text User name on click */
          /*  btnCheckUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edTxtUserName.getText().toString().equals("")){
                        //Toast.makeText(RegistrationActivity.this,"Plz First Enter Sponser Id No.", Toast.LENGTH_SHORT).show();
                        edTxtUserName.setError("Enter user name");
                        edTxtUserName.requestFocus();
                    }

                    else {

                        //Call MemberName Api
                        if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                            Toast.makeText(RegistrationActivity.this,getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                        else {

                            getCheckUserName();
                        }
                    }
                }
            });*/
        //Check Radio button is check on uncheck
            if(rdBtnLeft.isChecked()){
                strSide="1";
            }
            else if(rdBtnRight.isChecked()){
                strSide="2";
            }

        /*Radio Group Side on checked change listener*/
           rdGroupSide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(RadioGroup group, int checkedId) {
                   int selectId=rdGroupSide.getCheckedRadioButtonId();
                   RadioButton rb=(RadioButton)group.findViewById(checkedId);
                   String strRbtnVlaue=rb.getText().toString();
                   if(strRbtnVlaue.equals("Left")){
                       strSide="1";
                   }
                   else if(strRbtnVlaue.equals("Right")){
                       strSide="2";
                   }
               }
           });

        //EditText Dob Click listner
            /*edTextDOB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = new SelectDOBDateFragment();
                    newFragment.show(getSupportFragmentManager(), "DatePicker");
                }
            });*/

        /*EditText Nominee Dob Click listner*/
           /* edTxtNomineeDOB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = new SelectNomineeDOBDateFragment();
                    newFragment.show(getSupportFragmentManager(), "DatePicker");
                }
            });*/

            /*Call state api method for STate Spinner*/
           getStateList();
            /*Spinner Name Prefix listner*/
            spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    StateListResponse.StateList stateList=
                            (StateListResponse.StateList)parent.getItemAtPosition(position);
                    strStateId=stateList.getStatecode();
                    strStateName=stateList.getStatename();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinnerPostalState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    StateListResponse.StateList stateList=
                            (StateListResponse.StateList)parent.getItemAtPosition(position);
                    strPostalStateId=stateList.getStatecode();
                    strPostalStateName=stateList.getStatename();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



            //spinner Area selection
            spinnerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View arg1, int position, long arg3) {
                    //String item = parent.getItemAtPosition(position).toString();

                    PincodeDetailRespose.PincodeDetail stateList1=(PincodeDetailRespose.PincodeDetail)parent.getItemAtPosition(position);
                    strAeraId = stateList1.getAreacode();
                    strAeraName=stateList1.getAreaname();

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });



            //spinner Area selection
            spinnerPostalArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View arg1, int position, long arg3) {
                    //String item = parent.getItemAtPosition(position).toString();

                    PincodeDetailRespose.PincodeDetail stateList1=(PincodeDetailRespose.PincodeDetail)parent.getItemAtPosition(position);
                    strPostalAeraId = stateList1.getAreacode();
                    strPostalAeraName=stateList1.getAreaname();

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });

            /*Call method for Member relation Spinner*/
            initMembRelation();
        /*Spinner Name Prefix listner*/
            spinnerPreFix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MemberRelationListResponse.MemberRelation memberRelation1=
                            (MemberRelationListResponse.MemberRelation)parent.getItemAtPosition(position);
                    strMemRelation=memberRelation1.getMemrel();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /*Edit Text Pincode On Text Change Listner*/
            edTxtPincode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    strPincode=edTxtPincode.getText().toString();

               /* if(strPincode.length() != 6){
                   Toast.makeText(context,"Pincode not complete",Toast.LENGTH_SHORT).show();
                }*/
                    if(strPincode.length() ==6){
                        //Call Pincode Aera List Api
                        if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                            Toast.makeText(RegistrationActivity.this,getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                        else {

                            getPincodeAreaList("state");
                        }
                    }

                }
            });

            edTxtPostalPincode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    strPostalPincode=edTxtPostalPincode.getText().toString();

               /* if(strPincode.length() != 6){
                   Toast.makeText(context,"Pincode not complete",Toast.LENGTH_SHORT).show();
                }*/
                    if(strPostalPincode.length() ==6){
                        //Call Pincode Aera List Api
                        if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                            Toast.makeText(RegistrationActivity.this,getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }
                        else {

                            getPincodeAreaList("postal");
                        }
                    }

                }
            });

        /*Edit text pincode on text change linstner*/
          /*  edTxtPincode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(timer != null)
                        timer.cancel();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    strPincode=edTxtPincode.getText().toString();
                    if (strPincode.length() == 6) {
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // TODO: do what you need here (refresh list)
                                // you will probably need to use
                                // runOnUiThread(Runnable action) for some specific
                                // actions
                                //Call Pincode Aera List Api
                                if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                                    Toast.makeText(RegistrationActivity.this,getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    getPincodeAreaList();
                                }
                            }

                        }, DELAY);
                    }
                }
            });*/

            /*Call Account type list api*/
            /*if(!ConnectivityUtils.isNetworkEnabled(this)){
                Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.network_error),
                        Toast.LENGTH_SHORT).show();
            }
            else {
               // getAccountTypeList();
            }*/


        /*Spinner Account type on item select listener*/
           /* spinnerAcType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    AccountTypeListResponse.AccountList acType=(AccountTypeListResponse.AccountList)adapterView.getItemAtPosition(i);
                    //AcTypeModel acType=(AcTypeModel)parent.getItemAtPosition(position);
                    strAcType=acType.getAccountype();
                    strAcTypeID=acType.getAccid();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });*/

           /* //spinner state selection
            spinnerBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent,
                                           View arg1, int position, long arg3) {

                    BankListResponse.BankList bankList=( BankListResponse.BankList)parent.getItemAtPosition(position);
                    strBankId = bankList.getBankcode();
                    strBank=bankList.getBankname();

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });*/

           //Spinner Bank on click get bank list
           /* edTxtBankName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(bankListArrayList != null && bankListArrayList.size() > 0){
                        Intent intent=new Intent(RegistrationActivity.this, BankListActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("BankList",bankListArrayList);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                    }
                    else {
                        Toast.makeText(RegistrationActivity.this,"something went wrong.. try again.",Toast.LENGTH_SHORT).show();
                    }

                }
            });*/

            /*Radio Group Pan Availbale*/
            /*if (rdbYes.isChecked()){
                strPanAvailable="Yes";
                //edTxtPanNo.setEnabled(true);
                layoutPanNo.setVisibility(View.VISIBLE);

            }
            else if(rdbNo.isChecked()){
                strPanAvailable="No";
                //edTxtPanNo.setEnabled(false);
                layoutPanNo.setVisibility(View.GONE);
            }*/

            /*Radio Group Register As*/
          /*  rdGPan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int selectId=rdGPan.getCheckedRadioButtonId();
                    RadioButton rb=(RadioButton)group.findViewById(checkedId);
                    String strRbtnVlaue=rb.getText().toString();
                    if(strRbtnVlaue.equals("Yes")){
                        strPanAvailable="Yes";
                        // edTxtPanNo.setEnabled(true);
                        layoutPanNo.setVisibility(View.VISIBLE);
                    }
                    else if(strRbtnVlaue.equals("No")){
                        strPanAvailable="No";
                        //edTxtPanNo.setEnabled(false);
                        layoutPanNo.setVisibility(View.GONE);
                    }
                }
            });*/


            checkBoxSameAs.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (checkBoxSameAs.isChecked()) {
                        if(edTxtAddress.getText().toString().equals("")||edTxtPincode.getText().toString().equals("") ||
                         strStateId.equals("")|| edTxtDistric.getText().toString().equals("")||
                            edTxtCity.getText().toString().equals("")){
                            Toast.makeText(RegistrationActivity.this,"Something missing field",Toast.LENGTH_SHORT).show();
                            checkBoxSameAs.setChecked(false);

                        }else {
                            checkBoxSameAs.setChecked(true);
                            edTxtPostalAddress.setText(edTxtAddress.getText().toString());
                            edTxtPostalAddress.setEnabled(false);
                            edTxtPostalPincode.setText(edTxtPincode.getText().toString());
                            edTxtPostalPincode.setEnabled(false);
                            spinnerPostalState.setSelection(getIndexSpinPostalState(spinnerPostalState, strStateName));
                            spinnerPostalState.setEnabled(false);
                            edTxtPostalDistric.setText(edTxtDistric.getText().toString());
                            edTxtPostalDistric.setEnabled(false);
                            edTxtPostalCity.setText(edTxtCity.getText().toString());
                            edTxtPostalCity.setEnabled(false);
                            spinnerPostalArea.setSelection(getIndexSpinPostalArea(spinnerPostalArea, strAeraName));
                            spinnerPostalArea.setEnabled(false);
                        }

                    } else {

                        checkBoxSameAs.setChecked(false);
                        edTxtPostalAddress.setText("");
                        edTxtPostalAddress.setEnabled(true);
                        edTxtPostalPincode.setText("");
                        edTxtPostalPincode.setEnabled(true);
                        spinnerPostalState.setSelection(getIndexSpinPostalState(spinnerPostalState, "0"));
                        spinnerPostalState.setEnabled(true);
                        edTxtPostalDistric.setText("");
                        edTxtPostalDistric.setEnabled(true);
                        edTxtPostalCity.setText("");
                        edTxtPostalCity.setEnabled(true);
                        spinnerPostalArea.setSelection(getIndexSpinPostalArea(spinnerPostalArea, "0"));
                        spinnerPostalArea.setEnabled(true);
                    }
                }
            });

            if(checkBoxTerm.isChecked()){
                layoutSubmit.setVisibility(View.VISIBLE);

            }
            else{
                layoutSubmit.setVisibility(View.GONE);

            }
            checkBoxTerm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (checkBoxTerm.isChecked()) {
                        layoutSubmit.setVisibility(View.VISIBLE);
                        //String str=getToken(6);
                        //editTextpassword.setText(str);
                        //editTextConfirmpassword.setText(str);
                    } else {
                        layoutSubmit.setVisibility(View.GONE);
                    }
                }
            });

            /*Text Term and Condition Click Listner*/
            txtViewTerm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   /* Intent intentTerm=new Intent(RegistrationActivity.this, WebViewActivity_gift.class);
                    //intentTerm.putExtra("From","DashHome");
                    intentTerm.putExtra("URL", ApiConstants.Term_condition_Url);
                    intentTerm.putExtra("Type", getResources().getString(R.string.str_term_cond));
                    startActivity(intentTerm);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);*/

                    /*Text Term and Condition Click Listner*/
                    txtViewTerm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Dialog dialog = new Dialog(RegistrationActivity.this);
                            // Include business_dialog.xmlialog.xml file
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.business_dialog);
                            // Set business_dialog title


                            // set values for custom business_dialog components - text, image and
                            // button

                            ImageView image = (ImageView) dialog
                                    .findViewById(R.id.cross);

                            // if decline button is clicked, close the custom business_dialog
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                            image.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // Close business_dialog

                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }


                    });
                }


            });

            /*Button submit on click*/
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkValidation();
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void contentView(View view){
        rdGroupSide=(RadioGroup)findViewById(R.id.signup_act_radioGroup_leg);
        rdBtnLeft=(RadioButton)findViewById(R.id.signup_act_radiobtn_left);
        rdBtnRight=(RadioButton)findViewById(R.id.signup_act_radiobtn_right);
        edTextSponsorIdNo=(EditText) findViewById(R.id.signup_act_edTxt_sponserId);
        edTextPanNo=(EditText) findViewById(R.id.signup_act_edTxt_pan_no);
        txtSponserName=(TextView)findViewById(R.id.signup_act_TxtView_sponsername);
        //txtUser=(TextView)findViewById(R.id.signup_act_txt_user);

        /*Personal detail*/
        edTxtName=(EditText)findViewById(R.id.signup_act_edTxt_name);
        //edTxtUserName=(EditText)findViewById(R.id.signup_act_edTxt_username);
        edTextDOB=(EditText)findViewById(R.id.signup_act_edTxt_dob);
        edTxtFatherName=(EditText)findViewById(R.id.signup_act_edTxt_fath_husband_name);
        spinnerPreFix=(Spinner)findViewById(R.id.signup_act_spiner_prefix);
        spinnerArea=(Spinner)findViewById(R.id.signup_act_spiner_area);
        spinnerPostalArea=(Spinner)findViewById(R.id.signup_act_spiner_postal_area);


        /*contact detail*/
        edTxtAddress=(EditText)findViewById(R.id.signup_act_edTxt_address);
        edTxtPostalAddress=(EditText)findViewById(R.id.signup_act_edTxt_postal_address);
        edTxtDistric=(EditText)findViewById(R.id.signup_act_edTxt_distric);
        edTxtPostalDistric=(EditText)findViewById(R.id.signup_act_edTxt_postal_distric);
        //edTxtState=(EditText)findViewById(R.id.signup_act_edTxt_state);
        edTxtCity=(EditText)findViewById(R.id.signup_act_edTxt_city);
        edTxtPostalCity=(EditText)findViewById(R.id.signup_act_edTxt_postal_city);
        edTxtPincode=(EditText)findViewById(R.id.signup_act_edTxt_pincode);
        edTxtPostalPincode=(EditText)findViewById(R.id.signup_act_edTxt_postal_pincode);
        //edTxtPhone=(EditText)findViewById(R.id.signup_act_edTxt_phone);
        edTxtMobile=(EditText)findViewById(R.id.signup_act_edTxt_mobile);
        edTxtEmail=(EditText)findViewById(R.id.signup_act_edTxt_email);
        spinnerState=(Spinner)findViewById(R.id.signup_act_spiner_state);
        spinnerPostalState=(Spinner)findViewById(R.id.signup_act_spiner_postal_state);

        /*Nominee Detail*/
        edTxtNomineeName=(EditText)findViewById(R.id.signup_act_edTxt_nomineeName);
        edTxtNomineeRelation=(EditText)findViewById(R.id.signup_act_edTxt_nomineerelation);
        edTxtNomineeMobile=(EditText)findViewById(R.id.signup_act_edTxt_nominee_mobile);
        edTxtNomineeDOB=(EditText)findViewById(R.id.signup_act_edTxt_nomineedob);

        /*Bank Detail*/
        edTxtBankName=(EditText)findViewById(R.id.signup_act_edTxt_bank);
        edTxtBranch=(EditText)findViewById(R.id.signup_act_edTxt_branch);
        edTxtAcNo=(EditText)findViewById(R.id.signup_act_edTxt_acctNo);
        edTxtIfsc=(EditText)findViewById(R.id.signup_act_edTxt_ifsc);
        //edTxtPanNo=(EditText)findViewById(R.id.signup_act_edTxt_panno);
        edTxtAadharNo=(EditText)findViewById(R.id.signup_act_edTxt_aadhar);
        //edTxtCnfmPassword=(EditText)findViewById(R.id.signup_act_edTxt_confirm_pass);
        edTxtPassword=(EditText)findViewById(R.id.signup_act_edTxt_password);
        spinnerAcType=(Spinner)findViewById(R.id.signup_act_spiner_actype);
        //spinnerBank=(Spinner)findViewById(R.id.signup_act_spiner_bank);
        rdGPan=(RadioGroup)findViewById(R.id.signup_act_radioGroup_panCard);
        rdbYes=(RadioButton)findViewById(R.id.signup_act_radiobtn_panYes);
        rdbNo=(RadioButton)findViewById(R.id.signup_act_radiobtn_panNo);

        /*Login Detail*/
        checkBoxTerm=(CheckBox)findViewById(R.id.signup_act_checkBox_term);
        checkBoxSameAs=(CheckBox)findViewById(R.id.signup_act_checkBox_same);
        layoutPanNo=(LinearLayout)findViewById(R.id.signup_act_layout_pan);
        layoutSubmit=(LinearLayout)findViewById(R.id.signup_act_layout_button);
        layoutSide=(LinearLayout)findViewById(R.id.signup_act_layout_side);
        //layoutCheckUserNAme=(LinearLayout)findViewById(R.id.signup_act_layout_check_username);
        layoutDetail=(LinearLayout)findViewById(R.id.signup_act_layout_content);
        txtViewTerm=(TextView)findViewById(R.id.signup_act_txtView_term);
        edTxtPassword=(EditText)findViewById(R.id.signup_act_edTxt_password);
        btnSubmit=(Button)findViewById(R.id.signup_act_button_submit);
        btnCheckSponser=(TextView) findViewById(R.id.signup_act_btn_check_sponserId);
        //btnCheckUser=(Button)findViewById(R.id.signup_act_btn_check_username);

        edTextSponsorIdNo.addTextChangedListener(new MyTextWatcher(edTextSponsorIdNo));
        edTxtName.addTextChangedListener(new MyTextWatcher(edTxtName));
        edTxtNomineeName.addTextChangedListener(new MyTextWatcher(edTxtNomineeName));
        edTxtNomineeRelation.addTextChangedListener(new MyTextWatcher(edTxtNomineeName));
        edTxtEmail.addTextChangedListener(new MyTextWatcher(edTxtEmail));
        //edTxtPassword.addTextChangedListener(new MyTextWatcher(edTxtPassword));
        imgCap=(ImageView)findViewById(R.id.signup_act_img_captcha);
        imgCapRefresh=(ImageView)findViewById(R.id.signup_act_img_refresh);
        edTxtCaptcha=(EditText)findViewById(R.id.signup_act_edTxt_captca);
        //txtans=(TextView)findViewById(R.id.textView1);

        /* Get Intent Value */
        Intent intent=getIntent();
        if(intent != null){
            strFrom=intent.getStringExtra("From");
        }


        /* This code for get Captcha code on create activity */
//        Captcha c = new TextCaptcha(150,50,4, TextCaptcha.TextOptions.LETTERS_ONLY);
//        //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
//        imgCap.setImageBitmap(c.getImage());
//        imgCap.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
//        strCaptcha=c.answer;
        //txtans.setText(c.answer);

        /* on Refresh img click
        regenerate Captcha code */
        /*imgCapRefresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Captcha c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
                Captcha c = new TextCaptcha(150,50,4, TextCaptcha.TextOptions.LETTERS_ONLY);
                //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
                imgCap.setImageBitmap(c.getImage());
                imgCap.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
                strCaptcha=c.answer;
                //txtans.setText(c.answer);
            }
        });*/

        edTxtAddress.addTextChangedListener(new MyTextWatcher(edTxtAddress));

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };

        InputFilter filter1 = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        InputFilter letterFilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String filtered = "";
                for (int i = start; i < end; i++) {
                    char character = source.charAt(i);
                    int index=i+1;
                    //if(index)
                       /* if (Character.isWhitespace(character)) {
                            return "";
                        }*/
                    if (Character.isWhitespace(source.charAt(i))) {
                        if (dstart == 0)
                            return "";
                    }
                    else if (!Character.isLetter(source.charAt(i))) {
                        return "";
                    }

                }

                return null;
            }

        };

        //edTxtName.setFilters(new InputFilter[]{letterFilter});
        //edTxtUserName.setFilters(new InputFilter[]{filter});
        edTxtEmail.setFilters(new InputFilter[]{filter1});
        //edTxtPassword.setFilters(new InputFilter[]{filter1});

    }

    /*Method of Spinner Set Index*/
    private int getIndexSpinPostalState(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {

            StateListResponse.StateList model = (StateListResponse.StateList) spinner.getItemAtPosition(i);

            if (model.getStatecode().equalsIgnoreCase(myString)) {
                index = i;
            }
        }
        return index;
    }
    private int getIndexSpinState(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {

            MemberRelationListResponse.MemberRelation model = (MemberRelationListResponse.MemberRelation) spinner.getItemAtPosition(i);

            if (model.getMemrel().equalsIgnoreCase(myString)) {
                index = i;
            }
        }
        return index;
    }

    /*Method of Spinner Set Index*/
    private int getIndexSpinArea(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {

            PincodeDetailRespose.PincodeDetail model = (PincodeDetailRespose.PincodeDetail) spinner.getItemAtPosition(i);

            if (model.getAreaname().equalsIgnoreCase(myString)) {
                index = i;
            }
        }
        return index;
    }
    private int getIndexSpinPostalArea(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {

            PincodeDetailRespose.PincodeDetail model = (PincodeDetailRespose.PincodeDetail) spinner.getItemAtPosition(i);

            if (model.getAreacode().equalsIgnoreCase(myString)) {
                index = i;
            }
        }
        return index;
    }

    public class MyTextWatcher implements TextWatcher {
        private EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String text = editText.getText().toString();
            if (text.startsWith(" ")) {
                editText.setText(text.trim());

            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    // date picker open for CHECKIN-Date and selected date set to textview
    public static class SelectDOBDateFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar1 = Calendar.getInstance();
            int yy = calendar1.get(Calendar.YEAR);
            int mm = calendar1.get(Calendar.MONTH);
            int dd = calendar1.get(Calendar.DAY_OF_MONTH);

            //Disable date before today date
            DatePickerDialog dpd = new DatePickerDialog(getContext(), this, yy, mm, dd);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                int thisMonth = mm + 1;
                String stringTodayDate = dd + "/" + thisMonth + "/" + yy;
                Date todayDate = sdf.parse(stringTodayDate);
                dpd.getDatePicker().setMaxDate(todayDate.getTime());

            } catch (ParseException e) {
                //handle exception
            }
            return dpd;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {


            Calendar userAge = new GregorianCalendar(yy,mm,dd);
            Calendar minAdultAge = new GregorianCalendar();
            minAdultAge.add(Calendar.YEAR, -18);
            if (minAdultAge.before(userAge)) {
                Toast.makeText(getActivity(),"Please select 18 year above date",Toast.LENGTH_SHORT).show();
            }
            else {
                SetDOBDate(yy, mm +1, dd);
            }
        }
    }

    public static void SetDOBDate(int year, int month, int day) {
        String Month = Utilities.convertMonth(month);
        edTextDOB.setText(day + "-" + Month + "-" + year);
        stringDOB = (Utilities.convertdayNumber(day) + "-" + Utilities.convertMonthNumber(month) + "-" + year ).toString();

        yyFromDate = year;
        mmFromDate = month;
        ddFromDate = day;

        Calendar mCalendarTo = Calendar.getInstance();
        mCalendarTo.set(yyFromDate, mmFromDate, ddFromDate);
        mCalendarTo.add(Calendar.DATE, 1);
        int yy = mCalendarTo.get(Calendar.YEAR);
        int mm = mCalendarTo.get(Calendar.MONTH);
        int dd = mCalendarTo.get(Calendar.DAY_OF_MONTH);
        String mon = Utilities.convertMonthtoText(mm);

        //set Next Date as Check out date
        //textViewToDate.setText(dd  + "-" + mon + "-" + yy);
        //stringToDate = (yy + "-" + Utilities.convertMonthNumber(mm) + "-" + Utilities.convertdayNumber(dd)).toString();
    }

    // date picker open for CHECKIN-Date and selected date set to textview
    public static class SelectNomineeDOBDateFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar1 = Calendar.getInstance();
            int yy = calendar1.get(Calendar.YEAR);
            int mm = calendar1.get(Calendar.MONTH);
            int dd = calendar1.get(Calendar.DAY_OF_MONTH);

            //Disable date before today date
            DatePickerDialog dpd = new DatePickerDialog(getContext(), this, yy, mm, dd);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                int thisMonth = mm + 1;
                String stringTodayDate = dd + "/" + thisMonth + "/" + yy;
                Date todayDate = sdf.parse(stringTodayDate);
                dpd.getDatePicker().setMaxDate(todayDate.getTime());

            } catch (ParseException e) {
                //handle exception
            }
            return dpd;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {


            Calendar userAge = new GregorianCalendar(yy,mm,dd);
            Calendar minAdultAge = new GregorianCalendar();
            minAdultAge.add(Calendar.YEAR, -18);
            if (minAdultAge.before(userAge)) {
                Toast.makeText(getActivity(),"Please select 18 year above date",Toast.LENGTH_SHORT).show();
            }
            else {
                SetNomineeDOBDate(yy, mm +1, dd);
            }
        }
    }

    public static void SetNomineeDOBDate(int year, int month, int day) {
        String Month = Utilities.convertMonth(month);
        edTxtNomineeDOB.setText(day + "-" + Month + "-" + year);
        strNomineeDOB = (Utilities.convertdayNumber(day) + "-" + Utilities.convertMonthNumber(month) + "-" + year ).toString();

        yyFromDate = year;
        mmFromDate = month;
        ddFromDate = day;

        Calendar mCalendarTo = Calendar.getInstance();
        mCalendarTo.set(yyFromDate, mmFromDate, ddFromDate);
        mCalendarTo.add(Calendar.DATE, 1);
        int yy = mCalendarTo.get(Calendar.YEAR);
        int mm = mCalendarTo.get(Calendar.MONTH);
        int dd = mCalendarTo.get(Calendar.DAY_OF_MONTH);
        String mon = Utilities.convertMonthtoText(mm);

        //set Next Date as Check out date
        //textViewToDate.setText(dd  + "-" + mon + "-" + yy);
        //stringToDate = (yy + "-" + Utilities.convertMonthNumber(mm) + "-" + Utilities.convertdayNumber(dd)).toString();
    }

    /*Initialize Spinner Account Type*/
    public void initMembRelation(){

        ArrayList<MemberRelationListResponse.MemberRelation> acTypeArryayList=new ArrayList<MemberRelationListResponse.MemberRelation>();
        MemberRelationListResponse.MemberRelation acTypeModel[]= new MemberRelationListResponse.MemberRelation[4];
        acTypeModel[0]=new MemberRelationListResponse.MemberRelation();
        acTypeModel[0].setMemrel("S/O");
        //acTypeModel[0].setAccountype("CHOOSE ACCOUNT TYPE");

        acTypeModel[1]=new MemberRelationListResponse.MemberRelation();
        acTypeModel[1].setMemrel("W/O");
        //acTypeModel[1].setAccountype("SAVING ACCOUNT");

        acTypeModel[2]=new MemberRelationListResponse.MemberRelation();
        acTypeModel[2].setMemrel("D/O");

        acTypeModel[3]=new MemberRelationListResponse.MemberRelation();
        acTypeModel[3].setMemrel("C/O");
        // acTypeModel[2].setAccountype("CURRENT ACCOUNT");
        acTypeArryayList=new ArrayList<MemberRelationListResponse.MemberRelation>(Arrays.asList(acTypeModel));

        MemberRelationAdapter typeAdapter;
        typeAdapter=new MemberRelationAdapter(this,acTypeArryayList);
        spinnerPreFix.setAdapter(typeAdapter);

    }

    /*Get Member Name Api request ane Response*/
    private void getMemberName(){
        showProgressDialog();
        BaseRequest baseRequest=new BaseRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_GET_MEMBER_NAME);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        baseRequest.setMemberid(edTextSponsorIdNo.getText().toString());

        Call<GetMemberNameResponse> memberNameResponseCall=
                NetworkClient.getInstance(this).create(ProfileServices.class).fetchMemberName(baseRequest,strApiKey);

        memberNameResponseCall.enqueue(new Callback<GetMemberNameResponse>() {
            @Override
            public void onResponse(Call<GetMemberNameResponse> call, Response<GetMemberNameResponse> response) {
                hideProgressDialog();
                try {

                    GetMemberNameResponse Response =new GetMemberNameResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {
                        //layoutCheckUserNAme.setVisibility(View.GONE);
                        txtSponserName.setText(Response.getMemname());
                        layoutDetail.setVisibility(View.VISIBLE);
                        layoutSide.setVisibility(View.VISIBLE);

                        sponsorId=true;

                    }
                    else if(Response.getResponse().equals("FAILED") && Response.getMsg().contains("Invalid Login Details")){
                        //blankValueFromSharePreference(CommonReportActivity.this,Response.getMsg());
                        new BusinessDashboardActivity().blankValueFromSharedPreference(RegistrationActivity.this);
                    }
                    else if(Response.getResponse().equals("FAILED")) {

                        // layoutCheckUserNAme.setVisibility(View.GONE);
                        layoutDetail.setVisibility(View.GONE);
                        layoutSide.setVisibility(View.GONE);

                        /*if(layoutDetail.getVisibility()== View.VISIBLE)
                            layoutDetail.setVisibility(View.GONE);
                        else
                            layoutDetail.setVisibility(View.GONE);*/
                        //if(layoutSide.getVisibility()==View.VISIBLE)
                        // layoutSide.setVisibility(View.GONE);
                        txtSponserName.setText(Response.getMsg());
                        sponsorId=false;
                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                        String msg=Response.getResponse()+ " "+"Something went wrong..";
                        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                .show();

                    }
                    else {
                       // layoutCheckUserNAme.setVisibility(View.GONE);
                        layoutDetail.setVisibility(View.GONE);
                        layoutSide.setVisibility(View.GONE);

                        /*if(layoutDetail.getVisibility()== View.VISIBLE)
                            layoutDetail.setVisibility(View.GONE);
                        else
                            layoutDetail.setVisibility(View.GONE);*/
                        //if(layoutSide.getVisibility()==View.VISIBLE)
                           // layoutSide.setVisibility(View.GONE);
                        txtSponserName.setText(Response.getMsg());
                        sponsorId=false;
                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GetMemberNameResponse> call, Throwable t) {
                hideProgressDialog();
                showToast(t.getMessage());
            }
        });
    }

    /*Get User Name is already available or not Api request ane Response*/
    private void getCheckUserName(){
        showProgressDialog();
        CheckUserNameRequest baseRequest=new CheckUserNameRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_CHECK_USER_NAME);
        //baseRequest.setPasswd(SharedPrefrence.getPassword(this));
        //baseRequest.setUserid(SharedPrefrence.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        baseRequest.setUsername(edTxtUserName.getText().toString());

        Call<BaseResponse> memberNameResponseCall=
                NetworkClient.getInstance(this).create(ProfileServices.class).fetchChecjUserName(baseRequest,strApiKey);

        memberNameResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                hideProgressDialog();
                try {

                    BaseResponse Response =new BaseResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("Ok")) {

                        txtUser.setVisibility(View.VISIBLE);
                        txtUser.setText("User name available");
                        layoutSide.setVisibility(View.VISIBLE);
                        layoutDetail.setVisibility(View.VISIBLE);
                        user=true;
                    }
                    else {
                        layoutSide.setVisibility(View.GONE);
                        layoutDetail.setVisibility(View.GONE);
                        user=false;
                        txtUser.setVisibility(View.VISIBLE);
                        txtUser.setText("User name already exist, enter another");

                        //String toast= Response.getResponse()+ ":" + Response.getMsg();
                       // Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                hideProgressDialog();
                showToast(t.getMessage());
            }
        });
    }
    /*Pincode detail REquest and Response*/
    private void getPincodeAreaList(String type){
        showProgressDialog();
        PincodeDetailRequest baseRequest=new PincodeDetailRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_PINCODE_DETAIL);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        baseRequest.setPincode(strPincode);
        //baseRequest.setCountrycode("1");

        Call<PincodeDetailRespose> pincodeDetailResposeCall=
                NetworkClient.getInstance(this).create(UploadKYCServices.class).fetchPincodeAreaDetail(baseRequest,strApiKey);

        pincodeDetailResposeCall.enqueue(new Callback<PincodeDetailRespose>() {
            @Override
            public void onResponse(Call<PincodeDetailRespose> call, Response<PincodeDetailRespose> response) {
                hideProgressDialog();
                try {

                    PincodeDetailRespose Response =new PincodeDetailRespose();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {

                        pincodeDetails=Response.getPincodedetail();
                        if(pincodeDetails.length > 0){
                            pincodeAreaArrayList=new ArrayList<PincodeDetailRespose.PincodeDetail>(Arrays.asList(pincodeDetails));
                            adapter=new PincodeAreaListAdapter(RegistrationActivity.this,pincodeAreaArrayList);
                            if(type.equals("state")){
                                spinnerArea.setAdapter(adapter);

                                edTxtDistric.setText(Response.getDistrictname());
                                edTxtCity.setText(Response.getCityname());

                                strCityID=Response.getCitycode();
                                strCity=Response.getCityname();
                                strDistrictId=Response.getDistrictcode();
                                strDistrict=Response.getDistrictname();
                                strStateId=Response.getStatecode();
                                strStateName=Response.getStatename();
                                spinnerState.setSelection(getIndexSpinPostalState(spinnerState,Response.getStatecode()));
                                spinnerArea.setSelection(getIndexSpinPostalArea(spinnerArea,strAeraId));
                            }
                            else {
                                spinnerPostalArea.setAdapter(adapter);

                                edTxtPostalDistric.setText(Response.getDistrictname());
                                edTxtPostalCity.setText(Response.getCityname());
                                //edTxtPostalDistric.setTextColor(getResources().getColor(R.color.black));

                                strPostalCityID=Response.getCitycode();
                                strPostalCity=Response.getCityname();
                                strPostalDistrictId=Response.getDistrictcode();
                                strPostalDistrict=Response.getDistrictname();
                                strPostalStateId=Response.getStatecode();
                                strPostalStateName=Response.getStatename();
                                spinnerPostalState.setSelection(getIndexSpinPostalState(spinnerPostalState,Response.getStatecode()));
                                spinnerPostalArea.setSelection(getIndexSpinPostalArea(spinnerPostalArea,strPostalAeraId));
                            }



                        }
                        else {
                            String toast= Response.getResponse()+ "Area List is Null";
                            Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();
                            edTxtState.setText("");
                           // editTextCity.setText("");
                            edTxtDistric.setText("");
                            strCityID="";
                            strDistrictId="";
                            strStateId="";
                        }
                    }
                    else {
                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PincodeDetailRespose> call, Throwable t) {
                hideProgressDialog();
                showToast(t.getMessage());
            }
        });

    }

    /*Bank list Api Request and Response*/
    private void getBankList(){

        showProgressDialog();
        BaseRequest baseRequest= new BaseRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_BANKLIST);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));

        Call<BankListResponse> bankListCall=
                NetworkClient.getInstance(this).create(UploadKYCServices.class).fetchBanklist(baseRequest,strApiKey);

        bankListCall.enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                hideProgressDialog();
                try {

                    BankListResponse Response = new BankListResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {

                        bankLists=Response.getBankers();
                        if(bankLists.length > 0){
                            bankListArrayList=new ArrayList<BankListResponse.BankList>(Arrays.asList(bankLists));
                            bankAccountAdapter=new BankListAdapter(RegistrationActivity.this,bankListArrayList);
                           // spinnerBank.setAdapter(bankAccountAdapter);

                            /*//call Api
                            if(!ConnectivityUtils.isNetworkEnabled(context))
                                Toast.makeText(context,getString(R.string.network_error),Toast.LENGTH_SHORT).show();
                            else
                                getBankProofDetail();*/

                            //ad.setData(context,mProductList,CreateInvoiceDetailFragment.this);



                        }
                        else {
                            String toast= Response.getResponse()+ "Record is Null";
                            Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();
                        }


                    }
                    else if(Response.getResponse().equals("FAILED") && Response.getMsg().contains("Invalid Login Details")){
                        //blankValueFromSharePreference(CommonReportActivity.this,Response.getMsg());
                        new BusinessDashboardActivity().blankValueFromSharedPreference(RegistrationActivity.this);
                    }
                    else if(Response.getResponse().equals("FAILED")) {


                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                        String msg=Response.getResponse()+ " "+"Something went wrong..";
                        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                .show();

                    }
                    else {
                        String toast= Response.getResponse()+ ": Somthing Wrong" ;
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {

                hideProgressDialog();
                showToast(t.getMessage());
            }
        });

    }

    /*StateList Api REquest and Response*/
    private void getStateList(){
        pDialog=new ProgressDialog(this);
        pDialog.setMessage("please wait..");
        pDialog.setCancelable(false);
        pDialog.show();

        BaseRequest baseRequest=new BaseRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_STATELIST);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        baseRequest.setCountrycode("1");

        Call<StateListResponse> stateListResponseCall=
                NetworkClient.getInstance(this).create(ProfileServices.class).fetchStateList(baseRequest,strApiKey);

        stateListResponseCall.enqueue(new Callback<StateListResponse>() {
            @Override
            public void onResponse(Call<StateListResponse> call, Response<StateListResponse> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                try {

                    StateListResponse Response =new StateListResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {

                        stateList=Response.getStates();
                        if(stateList.length > 0){
                            stateListArrayList=new ArrayList<StateListResponse.StateList>(Arrays.asList(stateList));
                            stateListAdapter=new StateListAdapter(RegistrationActivity.this,stateListArrayList);
                            spinnerState.setAdapter(stateListAdapter);
                            spinnerPostalState.setAdapter(stateListAdapter);
                            //getIdProoftList();
                        }
                        else {

                            String toast= Response.getResponse()+ ":" + Response.getMsg();
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
                     else if(Response.getResponse().equals("FAILED") && Response.getMsg().contains("Invalid Login Details")){
                        //blankValueFromSharePreference(CommonReportActivity.this,Response.getMsg());
                        new BusinessDashboardActivity().blankValueFromSharedPreference(RegistrationActivity.this);
                    }
                    else if(Response.getResponse().equals("FAILED")) {


                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                        String msg=Response.getResponse()+ " "+"Something went wrong..";
                        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                .show();

                    }
                    else {
                        String toast= Response.getResponse()+ ":" + Response.getMsg();
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
            public void onFailure(Call<StateListResponse> call, Throwable t) {
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

    /*AccountType List Api Request and Response*/
    private void getAccountTypeList(){

        showProgressDialog();

        BaseRequest baseRequest= new BaseRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_ACCOUNT_TYPE);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));

        Call<AccountTypeListResponse> accountTypeListCall=
                NetworkClient.getInstance(this).create(UploadKYCServices.class).fetchAccountTypeList(baseRequest,strApiKey);

        accountTypeListCall.enqueue(new Callback<AccountTypeListResponse>() {
            @Override
            public void onResponse(Call<AccountTypeListResponse> call, Response<AccountTypeListResponse> response) {

                hideProgressDialog();
                try {

                    AccountTypeListResponse Response = new AccountTypeListResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {

                        accountList=Response.getAccounttype();
                        if(accountList.length > 0){
                            accountListArrayList=new ArrayList<AccountTypeListResponse.AccountList>(Arrays.asList(accountList));
                            accountTypeAdapter=new AccountTypeAdapter(RegistrationActivity.this,accountListArrayList);
                            spinnerAcType.setAdapter(accountTypeAdapter);

                            //call Api
                            if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this))
                                Toast.makeText(RegistrationActivity.this,getString(R.string.network_error),Toast.LENGTH_SHORT).show();
                            else
                                getBankList();


                        }
                        else {
                            String toast= Response.getResponse()+ "Record is Null";
                            showSnackbar(toast);
                            /*Snackbar.make(view1, toast, Snackbar.LENGTH_LONG)
                                    .setAction("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                        }
                                    })
                                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                    .show();*/
                        }


                    }
                    else {
                        String toast= Response.getResponse()+ ": Somthing Wrong" ;
                        showSnackbar(toast);
                        /*Snackbar.make(view1, toast, Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                                .show();*/

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AccountTypeListResponse> call, Throwable t) {

                /*if(pDialog.isShowing())
                    pDialog.dismiss();*/
                hideProgressDialog();
                showSnackbar(t.getMessage());
                /*Snackbar.make(view1, t.getMessage(), Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                        .show();*/
            }
        });

    }

    private void checkValidation(){
        try {

            if(edTextSponsorIdNo.getText().toString().equals("")){
                    edTextSponsorIdNo.setError("Enter Sponsor Id.No.");
                    edTextSponsorIdNo.requestFocus();
            }
            else if(!sponsorId){
                    Toast.makeText(RegistrationActivity.this,"entered sponser id not valid"
                            ,Toast.LENGTH_SHORT).show();
            }
            else if(strSide.equals("")){
                //edTextSponsorIdNo.setError("Enter Sponsor Id.No.");
                //edTextSponsorIdNo.requestFocus();
                Toast.makeText(RegistrationActivity.this,"Select side",Toast.LENGTH_SHORT).show();
            }
            else if(edTxtName.getText().toString().equals("")){
                    edTxtName.setError("Enter Member Name");
                    edTxtName.requestFocus();
            }
            else if(edTxtFatherName.getText().toString().equals("")){
                    edTxtFatherName.setError("Enter Guardian Name");
                    edTxtFatherName.requestFocus();
            }
            else if(edTextDOB.getText().toString().equals("")){
                edTextDOB.setError("Enter Date of Birth");
                edTextDOB.requestFocus();
            }
            else if(edTxtMobile.getText().toString().equals("")){
                    edTxtMobile.setError("Enter Mobile No.");
                    edTxtMobile.requestFocus();
            }
            else if(edTxtMobile.getText().toString().length() > 10 || edTxtMobile.getText().toString().length() < 10){
                edTxtMobile.setError("Enter valid Mobile No.");
                edTxtMobile.requestFocus();
            }
            else if(edTxtEmail.getText().toString().equals("")){
                edTxtEmail.setError("Enter Email Id.");
                edTxtEmail.requestFocus();
            }
            else if(edTxtPanNo.getText().toString().equals("")){
                edTxtPanNo.setError("Enter Pan number");
                edTxtPanNo.requestFocus();
            }
            else if(edTxtAddress.getText().toString().equals("")){
                edTxtAddress.setError("Enter Address");
                edTxtAddress.requestFocus();
            }
            else if(edTxtPincode.getText().toString().equals("")){
                    edTxtPincode.setError("Enter Pin code no.");
                    edTxtPincode.requestFocus();
            }
            else if(strStateId.toString().equals("0")){
                    Toast.makeText(RegistrationActivity.this,"please select state"
                            ,Toast.LENGTH_SHORT).show();
            }
            else if(edTxtDistric.getText().toString().equals("")){
                edTxtDistric.setError("Enter District Name.");
                edTxtDistric.requestFocus();
            }
            else if(edTxtCity.getText().toString().equals("")){
                edTxtCity.setError("Enter City Name.");
                edTxtCity.requestFocus();
            }

            else if(edTxtPostalAddress.getText().toString().equals("")){
                edTxtPostalAddress.setError("Enter Address");
                edTxtPostalAddress.requestFocus();
            }
            else if(edTxtPostalPincode.getText().toString().equals("")){
                edTxtPostalPincode.setError("Enter Pin code no.");
                edTxtPostalPincode.requestFocus();
            }
            else if(strStateId.toString().equals("0")){
                Toast.makeText(RegistrationActivity.this,"please select state"
                        ,Toast.LENGTH_SHORT).show();
            }
            else if(edTxtDistric.getText().toString().equals("")){
                edTxtDistric.setError("Enter District Name.");
                edTxtDistric.requestFocus();
            }
            else if(edTxtCity.getText().toString().equals("")){
                edTxtCity.setError("Enter City Name.");
                edTxtCity.requestFocus();
            }

            else {

                    View view = RegistrationActivity.this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(),
                                0);
                    }

                    strSponserID=edTextSponsorIdNo.getText().toString();
                    strName=edTxtName.getText().toString();
                    strPanNo=edTextPanNo.getText().toString();
                    //strUserName=edTxtUserName.getText().toString();
                    strF_H_Name=edTxtFatherName.getText().toString();
                    //strAddress=edTxtAddress.getText().toString();
                    strPincode=edTxtPincode.getText().toString();
                    //strAcNo=edTxtAcNo.getText().toString();
                    // strStateName=edTxtState.getText().toString();
                     strDistrict=edTxtDistric.getText().toString();
                     strCity=edTxtCity.getText().toString();
                     strNomineName=edTxtNomineeName.getText().toString();
                     strNomineRelation=edTxtNomineeRelation.getText().toString();
                    //strpass=edTxtPassword.getText().toString();
                    strMobile=edTxtMobile.getText().toString();
                    //strPhone=edTxtPhone.getText().toString();
                    strEmail=edTxtEmail.getText().toString();

                    Matcher matcher = pattern.matcher(strPanNo);
                    if(!edTextPanNo.getText().toString().equals("")){
                        if(matcher.matches() ){
                            isPan=true;
                        }
                        else
                            isPan=false;

                        if(isPan ){
                            if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                                Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.network_error)
                                        ,Toast.LENGTH_SHORT).show();
                            }
                            else {
                                getNewRegistrationDetail();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this,"Pan no. not valid please check"
                                    ,Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                            Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.network_error)
                                    ,Toast.LENGTH_SHORT).show();
                        }
                        else {
                            getNewRegistrationDetail();
                        }
                    }



            }




        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void checkValidation1(){
        try {
            if(edTextSponsorIdNo.getText().toString().equals("")){
                edTextSponsorIdNo.setError("Enter Sponsor Id.No.");
                edTextSponsorIdNo.requestFocus();
            }
            else if(txtSponserName.getText().toString().equals("Click here to get name")){
                Toast.makeText(RegistrationActivity.this,"Click to click here text"
                        ,Toast.LENGTH_SHORT).show();
            }
            else if(!sponsorId){
                Toast.makeText(RegistrationActivity.this,"entered sponser id not valid"
                        ,Toast.LENGTH_SHORT).show();
            }
            else if(strSide.equals("")){
                //edTextSponsorIdNo.setError("Enter Sponsor Id.No.");
                //edTextSponsorIdNo.requestFocus();
                Toast.makeText(RegistrationActivity.this,"Select side",Toast.LENGTH_SHORT).show();
            }
            else if(edTxtUserName.getText().toString().equals("")){
                edTxtUserName.setError("Enter User Name");
                edTxtUserName.requestFocus();
            }//User name already exit
            else if(txtUser.getText().toString().contains("user name is")){
                Toast.makeText(RegistrationActivity.this,"Click to Check User Name"
                        ,Toast.LENGTH_SHORT).show();
            }
            else if(!user){
                Toast.makeText(RegistrationActivity.this,"user name already exist, please enter another name "
                        ,Toast.LENGTH_SHORT).show();
            }

            else if(edTxtName.getText().toString().equals("")){
                edTxtName.setError("Enter Member Name");
                edTxtName.requestFocus();
            }
            else if(edTextDOB.getText().toString().equals("")){
                edTextDOB.setError("Enter DOB");
                edTextDOB.requestFocus();
            }
            else if(edTxtAddress.getText().toString().equals("")){
                edTxtAddress.setError("Enter Address");
                edTxtAddress.requestFocus();
            }
            else if(edTxtPincode.getText().toString().equals("")){
                edTxtPincode.setError("Enter Pincode No.");
                edTxtPincode.requestFocus();
            }
            else if(edTxtMobile.getText().toString().equals("")){
                edTxtMobile.setError("Enter Mobile No.");
                edTxtMobile.requestFocus();
            }
           /* else if(edTxtNomineeName.getText().toString().equals("")){
                edTxtNomineeName.setError("Enter Nominee Name");
                edTxtNomineeName.requestFocus();
            }*/
           /* else if(edTxtNomineeDOB.getText().toString().equals("")){
                edTxtNomineeDOB.setError("Enter Sponsor Id.No.");
                edTxtNomineeDOB.requestFocus();
            }*/

           /* else if(edTxtPassword.getText().toString().equals("")){
                edTxtPassword.setError("Enter Password");
                edTxtPassword.requestFocus();
            }*/
            else {

                View view = RegistrationActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(),
                            0);
                }
               /* if(!sponsorId){
                    Toast.makeText(RegistrationActivity.this,"entered sponser id not valid"
                            ,Toast.LENGTH_SHORT).show();
                }
                else if(!user){
                    Toast.makeText(RegistrationActivity.this,"entered user name already exist"
                            ,Toast.LENGTH_SHORT).show();
                }*/
                // else {
                strSponserID=edTextSponsorIdNo.getText().toString();
                strName=edTxtName.getText().toString();
                strUserName=edTxtUserName.getText().toString();
                strF_H_Name=edTxtFatherName.getText().toString();
                strAddress=edTxtAddress.getText().toString();
                strPincode=edTxtPincode.getText().toString();
                strAcNo=edTxtAcNo.getText().toString();
                strStateName=edTxtState.getText().toString();
                strDistrict=edTxtDistric.getText().toString();
                strNomineName=edTxtNomineeName.getText().toString();
                strpass=edTxtPassword.getText().toString();
                strMobile=edTxtMobile.getText().toString();
                strPhone=edTxtPhone.getText().toString();
                strEmail=edTxtEmail.getText().toString();
                strNomineRelation=edTxtNomineeRelation.getText().toString();
                //strNomineMobile=edTxtNomineeMobile.getText().toString();
                strBranch=edTxtBranch.getText().toString();
                strIfsc=edTxtIfsc.getText().toString();
                //strPanNo=edTxtPanNo.getText().toString();
                strAadhar=edTxtAadharNo.getText().toString();

                if(rdbYes.isChecked()) {

                    if(edTxtPanNo.getText().toString().equals("")){
                        edTxtPanNo.setError("Enter Pan no.");
                        edTxtPanNo.requestFocus();
                    }
                    else {
                        strPanNo=edTxtPanNo.getText().toString();
                        Matcher matcher = pattern.matcher(strPanNo);
                        if(matcher.matches()){
                            if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                                Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.network_error)
                                        ,Toast.LENGTH_SHORT).show();
                            }
                            else {
                                getNewRegistrationDetail();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this,"Enter proper pan no."
                                    ,Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else {
                    strPanNo="";
                    if(!ConnectivityUtils.isNetworkEnabled(RegistrationActivity.this)){
                        Toast.makeText(RegistrationActivity.this,getResources().getString(R.string.network_error)
                                ,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        getNewRegistrationDetail();
                    }
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*Get otp Api request ane Response*/
    private void getNewJoinOtp(){
        showProgressDialog();
        NewJoinOtpRequest baseRequest=new NewJoinOtpRequest();
        /*Set value in Entity class*/
        baseRequest.setReqtype(ApiConstants.REQUEST_NEW_JOIN_OTP);
        baseRequest.setPasswd(SharedPrefrence_Business.getPassword(this));
        baseRequest.setUserid(SharedPrefrence_Business.getUserID(this));
        baseRequest.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        baseRequest.setMobileno(strMobile);
        baseRequest.setMembername(strName);

        Call<BaseResponse> memberNameResponseCall=
                NetworkClient.getInstance(this).create(ProfileServices.class).fetchOtp(baseRequest,strApiKey);

        memberNameResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                hideProgressDialog();
                try {

                    BaseResponse Response =new BaseResponse();
                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {

                        getNewRegistrationDetail();
                    }
                    else {

                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                hideProgressDialog();
                showToast(t.getMessage());
            }
        });
    }

    /*New Registration Api Request and Response*/
    private void getNewRegistrationDetail(){

      pDialog=new ProgressDialog(this);
      pDialog.setMessage("please wait...");
      pDialog.setCancelable(false);
      pDialog.show();
        NewRegistrationRequest Request = new NewRegistrationRequest();

        /*Set value in Entity class*/
        Request.setReqtype(ApiConstants.REQUEST_NEW_REGISTER);
        Request.setUsername(strUserName);
        Request.setIslogin(SharedPrefrence_Business.getIsLogin(this));
        Request.setMobile("0");
       // Request.setOtpcode("");
        Request.setReferralid(strSponserID);
        Request.setUplinerid(strUplinerID);
        Request.setSide(strSide);
        Request.setName(strName);
        Request.setFrelation(strMemRelation);//strMemRelation
        Request.setFname(strF_H_Name);//strF_H_Name
        Request.setDob("");//stringDOB
        Request.setIsmarried("");//strMarital
        Request.setMarriagedate("");//strMarriedDate
        Request.setAddress("");//strAddress
        Request.setStatecode("0");//strStateId
        Request.setDistrict(strDistrict);//strDistrict
        Request.setDistrictcode("0");//strDistrictId
        Request.setCity(strCity);//strCity
        Request.setCitycode("0");//strCityID
        Request.setPincode(strPincode);//strPincode
        Request.setMobl(strMobile);
        Request.setPhoneno("0");//strPhone
        Request.setEmail(strEmail);
        Request.setNominee(strNomineName);//strNomineName
        Request.setRelation(strNomineRelation);//strNomineRelation
        Request.setAreacode("0");//strAeraId
        Request.setAccountno("0");//strAcNo
        Request.setActype("CHOOSE ACCOUNT");//strAcType
        Request.setBankcode("0");//strBankId
        Request.setBranch("");//strBranch
        //Request.setCompanyname(strCompanyName);
        Request.setIfsc("");//strIfsc
        Request.setPanno(strPanNo);//strPanNo
        Request.setAadharno("");//strAadhar
        ///Request.setRegistrationno(strCompRegiserNo);
        //Request.setRegistrationtype(strRegisterType);
        //Request.setTranspasswd(strTranspass);
        Request.setMpasswd("");
        //Request.setPassword(strpass);
        String str=new Gson().toJson(Request);
        Log.e("newRegisterReq=",str);

        Call<NewRegisterResponse> newRegisterResponseCall=
                NetworkClient.getInstance(this).create(ProfileServices.class).fetchNewRegistration(Request,strApiKey);

        newRegisterResponseCall.enqueue(new Callback<NewRegisterResponse>() {
            @Override
            public void onResponse(Call<NewRegisterResponse> call, Response<NewRegisterResponse> response) {
                if(pDialog.isShowing())
                    pDialog.dismiss();
                try {

                    NewRegisterResponse Response = new NewRegisterResponse();

                    Response=response.body();

                    if (Response.getResponse().equals("OK")) {
                        String toast= Response.getResponse()+ ":" + Response.getMsg();
                        Toast.makeText(RegistrationActivity.this, toast, Toast.LENGTH_SHORT).show();
                        ApiConstants.welcomeurl=Response.getUrl();

                        Intent intentTerm=new Intent(RegistrationActivity.this, WebViewActivity.class);
                        //intentTerm.putExtra("From","DashHome");
                        intentTerm.putExtra("URL", ApiConstants.welcomeurl);
                        intentTerm.putExtra("Type","Welcome Letter");
                        intentTerm.putExtra("From",strFrom);
                        startActivity(intentTerm);
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NewRegisterResponse> call, Throwable t) {
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
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if(reqCode==1){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle=data.getExtras();

                if(bundle != null){
                    // productDetail=(ProductDetail)bundle.getSerializable()
                    strBankId=bundle.getString("BankId");
                    strBank=bundle.getString("BankName");
                   edTxtBankName.setText(strBank);


                }

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

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
       /* if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }*/
        super.onBackPressed();
        finish();
        //overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);

    }
}
