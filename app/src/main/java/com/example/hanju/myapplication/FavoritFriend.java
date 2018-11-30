package com.example.hanju.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.hanju.myapplication.MainActivity.mDbOpenHelper;

public class FavoritFriend extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit_friend);
        context = this;
        ListView listview = findViewById(R.id.dblist);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "An item of the ListView is clicked.", Toast.LENGTH_LONG).show();
                ClickAdapter.ViewHolder holder = (ClickAdapter.ViewHolder) view.getTag();
                if(holder.clickedOddTimes){
                    holder.clickedOddTimes = false;
                }
                else{
                    holder.clickedOddTimes = true;
                    String name = (String) holder.name.getText();
                    String phoneNum = (String) holder.phoneNum.getText();
                }

                String phoneNum = (String) holder.phoneNum.getText();
                Cursor cursor = mDbOpenHelper.mDB.rawQuery("SELECT * FROM people", null);
                while (cursor.moveToNext()) {
                    int ID = cursor.getInt(0);
                    String NAME = cursor.getString(1);
                    String PHONE =  cursor.getString(2);
                    Log.d("database phoneNum",  PHONE);
                    Log.d("ViewHolder phoneNum",  phoneNum);
                    int FAVOR = cursor.getInt(3);
                    if(Objects.equals(PHONE, phoneNum)){
                        Log.d( "", "DETECTED!!!");
                        mDbOpenHelper.updateColumn(ID,NAME,PHONE,1-FAVOR);
                    }
                }

            }
        });


        ListUpdate(listview);
    }

    public void ListUpdate(ListView l1){

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> phoneNumList = new ArrayList<>();

        if ( mDbOpenHelper.mDB != null) {
            Cursor cursor = mDbOpenHelper.mDB.rawQuery("SELECT * FROM people", null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phoneNum = cursor.getString(2);
                int favor = cursor.getInt(3);
                if(favor == 0){
                    nameList.add(name);
                    phoneNumList.add(phoneNum);
                }
            }
        }
        ClickAdapter adapter = new ClickAdapter(this,R.layout.activity_favorit_friend, nameList, phoneNumList);
        l1.setAdapter(adapter);
    }
}