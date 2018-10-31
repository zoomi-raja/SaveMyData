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
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(0).setChecked(true).setIcon(R.drawable.ic_email_yellow_24dp);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("WebSites");
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
                    item.setIcon(R.drawable.ic_email_yellow_24dp);
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    toolbar.setTitle(R.string.title_medium);
                    return true;
                case R.id.navigation_bank:
                    item.setIcon(R.drawable.ic_account_balance_wallet_yellow_24dp);
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    toolbar.setTitle(R.string.title_banks);
                    return true;
                case R.id.navigation_other:
                    item.setIcon(R.drawable.ic_other_yellow_24dp);
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    toolbar.setTitle(R.string.title_other);
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
