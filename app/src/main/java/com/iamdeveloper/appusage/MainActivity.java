package com.iamdeveloper.appusage;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Adapter.PagerAdapter;


public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new Adapter.PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }
}
