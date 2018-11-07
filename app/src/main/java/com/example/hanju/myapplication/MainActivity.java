package com.example.hanju.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener, Tab3.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("TAB 1"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 2"));
        tabLayout.addTab(tabLayout.newTab().setText("TAB 3"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ChangingTab adapter = new ChangingTab(getSupportFragmentManager(), tabLayout.getTabCount());;
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
    }
    public void makeTabs() {
        //TabLayout tabLayout =  findViewById(R.id.tablayout);
        //ChangingTab adapter = new ChangingTab(getSupportFragmentManager(), tabLayout.getTabCount());
    }

}
