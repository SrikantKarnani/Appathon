package com.example.srikant.appathon;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

import layout.AdvAct;
import layout.basicAct;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TabLayout tl;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tl = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tl.setupWithViewPager(viewPager);
        boolean firstboot = getSharedPreferences("BOOT_PREF", MODE_PRIVATE).getBoolean("firstboot", true);
        if (firstboot){
            // 1) Launch the activity with a toast msg.
            Toast.makeText(this,"Basic is for Random data\nAdvanced if for specific data",Toast.LENGTH_LONG).show();
            // 2) Then save the state
            getSharedPreferences("BOOT_PREF", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstboot", false)
                    .apply();
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Add the two frgments of our basic and advanced tab.
        adapter.addFragment(new basicAct(),"Basic");
        adapter.addFragment(new AdvAct(),"Advanced");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private ArrayList<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
}

        @Override
    public CharSequence getPageTitle(int position) {
return mFragmentTitleList.get(position);
}
    }
}
