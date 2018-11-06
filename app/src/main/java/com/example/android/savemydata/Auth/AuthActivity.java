package com.example.android.savemydata.Auth;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.android.savemydata.Dashboard.DashboardActivity;
import com.example.android.savemydata.Models.User;
import com.example.android.savemydata.R;
import com.example.android.savemydata.Utility.SessionManager;

public class AuthActivity extends AppCompatActivity implements SetPasswordFragment.SetPasswordClick,LoginFragment.SetLoginClick {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
        if(SessionManager.isUserAccountSet(AuthActivity.this)){
            //todo if user is logged in or not
            if(SessionManager.isUserLoggedIn(AuthActivity.this) ){
                startDashboardActivity();
            }else{
                ft.add(R.id.view_pager, new LoginFragment(), "login_fragment");
            }
            ft.commit();
        }else{
            ft.add(R.id.view_pager, new SetPasswordFragment(), "password_fragment");
            ft.commit();
        }
    }

    @Override
    public void onRegistrationAction(User user) {

        Log.d("Registered User", "onRegistrationAction: "+user.getName());
        SessionManager.setUserLoggedIn(AuthActivity.this,true);
        SessionManager.saveUsernameAndPassword(AuthActivity.this,user.getName(),user.getPassword(),user.getHint());
        startDashboardActivity();
//        FragmentTransaction ft = getSupportFragmentManager().begi123456nTransaction();
//        ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right,R.anim.slide_in_right, R.anim.slide_out_left);
//        ft.replace(R.id.view_pager, new LoginFragment());
//        ft.addToBackStack(null);
//        ft.commit();
    }

    @Override
    public void onLoginAction(User user) {
        Log.d("Login User", "onLoginAction: "+user.getName());
        SessionManager.setUserLoggedIn(AuthActivity.this,true);
        SessionManager.saveUsernameAndPassword(AuthActivity.this,user.getName(),user.getPassword(),user.getHint());
        startDashboardActivity();
    }
    public void startDashboardActivity(){
        Intent dashboard = new Intent(AuthActivity.this, DashboardActivity.class);
        finish();
        startActivity(dashboard);
    }
}
