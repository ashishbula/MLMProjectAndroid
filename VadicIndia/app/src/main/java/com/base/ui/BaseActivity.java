package com.base.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.base.util.PermissionUtil;
import com.vadicindia.LoginActivity;
import com.vadicindia.R;


import com.google.android.material.snackbar.Snackbar;
import com.vadicindia.business.business_constants.AppConstants;
import com.vadicindia.business.fragment.BusinessHomeFragment;
import com.vadicindia.business.shared_pref.SharedPrefrence_Business;


public class BaseActivity extends AppCompatActivity {

    final int locationBuffer = 500;
    public final String TAG = getClass().getSimpleName();
    private BaseProgressDialog baseProgressDialog;
    private boolean isProgressAdded;
    public static int count;
    private String mLastAssetsKey;

    private String mFilePath;
    private String mContentId;

    private long delayTimeForExitToast = 2000;
    private long backPressedTime;

    //To store longitude and latitude
    public static double longitude = 0;
    public static double latitude = 0;

    private LocationProvider mLocationProvider;
    Fragment fragment = null;
    Activity mActivity = null;

    String balance = "";

    public static String imageFilePath;


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.business_content_frame);
    }

    /*public void replaceFragment(Fragment newFragment, Bundle bundle, boolean isAddToBackStack) {
        try {
            String tag = newFragment.getClass().getSimpleName();
            if (bundle != null) {
                newFragment.setArguments(bundle);
            }

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (getCurrentFragment() != null) {
                ft.hide(getCurrentFragment());
            }
            ft.add(R.id.frame, newFragment, tag);
            newFragment.setRetainInstance(true);
            if (isAddToBackStack) {
                ft.addToBackStack(tag);
            }
            try {
                ft.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                ft.commitAllowingStateLoss();

            }
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
    }*/


    public void clearFragmentBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount() - 1; i++) {
            fm.popBackStack();
        }
    }
    public void loadHomeFragment() {
        // setting home default_img checked
        //navigationView.getMenu().getItem(0).setChecked(true);
        //multiLevelListView.
        clearBackStack();
        /*if (SharedValues.getInstance().flagFromSearch) {
            SharedValues.getInstance().flagFromSearch = false;
            EShopDetailFragment searchFragment = new EShopDetailFragment();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, searchFragment, "StoreDetailFragment");
            ft.commit();
        } else {*/
        BusinessHomeFragment homeFragment = new BusinessHomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.business_content_frame, homeFragment, AppConstants.TAG_HOME);
        ft.commit();
        //}
    }

    public void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
    public void replaceFragment(Fragment fragment, String tag, Bundle bundle) {

        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.business_content_frame, fragment, tag);
        fragment.setRetainInstance(true);
        /*to add fragment to stack*/
        ft.addToBackStack(tag);
        try {
            ft.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            ft.commitAllowingStateLoss();
        }
    }


    public  void blankValueFromSharePreference(Context context,String msg) {
        SharedPrefrence_Business.setPassword(context, "");
        SharedPrefrence_Business.setUserID(context, "");
        SharedPrefrence_Business.setUsername(context, "");
        SharedPrefrence_Business.setProfileIamge(context,"");
        SharedPrefrence_Business.setIsActive(context,"");
        SharedPrefrence_Business.setUserMobileNumber(context,"");

        String toast= msg;
        Toast.makeText(context,toast,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        finish();
    }

    public void hideSoftKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showSoftKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public synchronized void showProgressDialog() {
        if (baseProgressDialog == null) {
            baseProgressDialog = BaseProgressDialog.getInstance();
        }
        if (isProgressAdded) {
            return;
        }
        if (!isFinishing() && !baseProgressDialog.isAdded())
            baseProgressDialog.show(getSupportFragmentManager(), BaseProgressDialog.TAG);

        isProgressAdded = true;
    }

    public void hideProgressDialog() {
        if (baseProgressDialog == null) {
            return;
        }
        if (!isFinishing() && baseProgressDialog.isAdded())
            baseProgressDialog.dismiss();

        isProgressAdded = false;
    }

    public void showSnackbar(String string) {
        showSnackbar(findViewById(android.R.id.content), string);
    }

    public void showSnackbar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT).show();
    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboardForced(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboardForced(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

   /* @Override
    public void onClick(View view) {

    }*/


    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtil.with(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /*@Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            return;
        } else {
            ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            assert cn != null;
            if (cn.getClassName().equals("com.goldwings.shopping_gift.activity_gift.MainActivity_gift")) {
                super.onBackPressed();
            } else if (cn.getClassName().equals("com.goldwings.shopping.activity_shop.MainActivity_shopping")) {
                super.onBackPressed();
            } else if (cn.getClassName().equals("com.goldwings.utility_services.activity.BusinessDashboardActivity")) {
                super.onBackPressed();
            } else {
                finish();
            }
        }
    }*/

    /*@Override
    protected void onStart() {
        super.onStart();
        count = count + 1;
        Log.d("BaseActivity", "onStart" + count);
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "1");

    }*/

    /*protected void onStop() {
        super.onStop();
        count = count - 1;
    }*/

    /*@Override
    public void onPause() {
        super.onPause();
    }*/

    public void exitApplication() {
        if (backPressedTime + delayTimeForExitToast > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.press_back_msg), Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    public boolean validateUserLocation(Location fromLocation, int locationBuffer) {
        Location mCurrentLocation = new Location("");
        mCurrentLocation.setLatitude(latitude);
        mCurrentLocation.setLongitude(longitude);

        float dist = fromLocation.distanceTo(mCurrentLocation);

        return dist < locationBuffer;

    }



   /* public void showWebpage(Uri uri) {
        try {
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
            CustomTabsIntent customTabsIntent = intentBuilder.build();

            customTabsIntent.launchUrl(this, uri);
        } catch (Exception ex) {
            ex.printStackTrace();
            showToast("Please install Google Chrome Browser to use this feature");
        }
    }*/




    ///////////////////////////  UTILITY  /////////////////////////////////

   /* @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
        super.onSaveInstanceState(outState);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }*/

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
   /* protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_right);
    }*/

    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
   /* protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_left);
    }*/

    /*public void openActivityfromBottom() {
        overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    }*/

   /* public void openActivityFromTop() {
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
    }*/



    public void clearStack()
    {
        while(getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            getSupportFragmentManager().popBackStack();
        }
    }



}