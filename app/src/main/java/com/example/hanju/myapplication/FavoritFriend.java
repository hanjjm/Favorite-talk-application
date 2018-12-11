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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.hanju.myapplication.MainActivity.mDbOpenHelper;
import static java.lang.String.valueOf;

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
                ClickAdapter.ViewHolder holder = (ClickAdapter.ViewHolder) view.getTag();
                if(holder.clickedOddTimes){

                    holder.star.setImageResource(R.drawable.star2);
                    holder.clickedOddTimes = false;
                }
                else{

                    holder.star.setImageResource(R.drawable.star);
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
                    int FAVOR = cursor.getInt(3);
                    if(Objects.equals(PHONE, phoneNum)){
                        mDbOpenHelper.updateColumn(ID,NAME,PHONE,1-FAVOR);
                    }
                }

            }
        });


        Button btn = (Button) findViewById(R.id.favoriteaddbtn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
            //    Toast.makeText(getApplicationContext(), "왜 안뜰까요~~~", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        Button exitbtn = (Button) findViewById(R.id.exitbtn);
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ListUpdate(listview);
    }

    public void ListUpdate(ListView l1){

        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> phoneNumList = new ArrayList<>();

        //if ( mDbOpenHelper.mDB == null){
       //
        //     Toast.makeText(this, "왜 1123122~13243~~", Toast.LENGTH_SHORT);
        //}
int aa = 0;
       // if ( mDbOpenHelper.mDB != null) {
            Cursor cursor = mDbOpenHelper.mDB.rawQuery("SELECT * FROM people", null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phoneNum = cursor.getString(2);
            //    Toast.makeText(this, valueOf(aa), Toast.LENGTH_SHORT).show();
                int favor = cursor.getInt(3);
                if(favor == 0){
                    nameList.add(name);
                    phoneNumList.add(phoneNum);
                }
         //   }
        }
        //Toast.makeText(this, nameList.get(0), Toast.LENGTH_SHORT);
        ClickAdapter adapter = new ClickAdapter(this,R.layout.item_list2, nameList, phoneNumList);
        l1.setAdapter(adapter);
        //Toast.makeText(getApplicationContext(), nameList.get(0) , Toast.LENGTH_LONG).show();
    }
}