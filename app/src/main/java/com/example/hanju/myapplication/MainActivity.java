package com.example.hanju.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.design.widget.TabLayout;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener, Tab3.OnFragmentInteractionListener{
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;
    private static Context mContext;
    public static DBOpenHelper mDbOpenHelper;
public static int a = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, Loading.class);
        startActivity(intent);


        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.phonebook));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.gallery));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.star));
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

        mContext = this;

        if( (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
        {
            Permissions();
        }
        //Toast.makeText(getApplicationContext(), "TEST2", Toast.LENGTH_LONG).show();
        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
       // Toast.makeText(getApplicationContext(), valueOf(a).toString(), Toast.LENGTH_LONG).show();
    }

    private void Permissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {
          //
            //  Toast.makeText(this, "Need to access contacts", Toast.LENGTH_SHORT).show();
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)){
        //    Toast.makeText(this, "Need to access External Storage", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
     //   Toast.makeText(getApplicationContext(), "TEST1", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
