package com.example.android.savemydata.Dashboard;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.android.savemydata.Dashboard.dummy.DummyContent;
import com.example.android.savemydata.R;

public class DashboardActivity extends AppCompatActivity implements BankFragment.OnListFragmentInteractionListener, OtherFragment.OnListFragmentInteractionListener,WebsitesFragment.OnWebsiteFragmentListener {
    private ActionBar toolbar;
    Fragment fragment1 = WebsitesFragment.newInstance();
    Fragment fragment2 = BankFragment.newInstance();
    Fragment fragment3 = OtherFragment.newInstance();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = getSupportActionBar();
        BottomNavigationView navigation =  findViewById(R.id.navigation);
//        navigation.getMenu().findItem(R.id.navigation_website).setIcon(R.drawable.ic_email_yellow_24dp);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle(R.string.title_medium);
        fm.beginTransaction().add(R.id.frame_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.frame_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame_container,fragment1, "1").commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_website:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    toolbar.setTitle(item.getTitle());
                    return true;
                case R.id.navigation_bank:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    toolbar.setTitle(item.getTitle());
                    return true;
                case R.id.navigation_other:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    toolbar.setTitle(item.getTitle());
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void bankFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
