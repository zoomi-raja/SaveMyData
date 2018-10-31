package com.example.android.savemydata.Auth;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.android.savemydata.R;
import com.example.android.savemydata.Utility.SessionManager;

public class AuthActivity extends AppCompatActivity implements SetPasswordFragment.SetPasswordClick {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FragmentTransaction ft  = getSupportFragmentManager().beginTransaction();
        if(SessionManager.isUserLoggedIn(AuthActivity.this)){
            ft.add(R.id.view_pager, new LoginFragment(),"login_fragment");
            ft.commit();
        }else{
            ft.add(R.id.view_pager, new SetPasswordFragment(), "password_fragment");
            ft.commit();
        }
    }

    @Override
    public void switchFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right,R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.view_pager, new LoginFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
