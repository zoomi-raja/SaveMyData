package com.example.android.savemydata.Auth;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.android.savemydata.R;

public class AuthActivity extends AppCompatActivity {

    private  ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewPager = findViewById(R.id.view_pager);
    }
}
