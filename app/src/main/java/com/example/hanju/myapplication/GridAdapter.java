package com.example.hanju.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<String> thumbsList;
    LayoutInflater inf;

    public GridAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        thumbsList = data;
    }

    @Override
    public int getCount(){
        return thumbsList.size();
    }

    @Override
    public Object getItem(int position) {
        return  thumbsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view;
        if (convertView == null){
            view = new ImageView(context);
        }else{
            view = (ImageView) convertView;
        }
        if (position < thumbsList.size()) {
            Bitmap bmp = BitmapFactory.decodeFile(thumbsList.get(position));
            Bitmap resized = Bitmap.createScaledBitmap(bmp, 360, 360, true);
            view.setPadding(2, 2, 2, 2);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageBitmap(resized);
        }
        return view;
    }
}