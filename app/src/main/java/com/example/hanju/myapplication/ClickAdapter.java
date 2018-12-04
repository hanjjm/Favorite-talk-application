package com.example.hanju.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ClickAdapter extends BaseAdapter {
    int groupid;
    ArrayList<String> nameList;
    ArrayList<String> phoneNumList;
    Context context;

    public ClickAdapter(Context context, int vg, ArrayList<String> nameList, ArrayList<String> phoneNumList){
        this.context=context;
        groupid=vg;
        this.nameList = nameList;
        this.phoneNumList= phoneNumList;
    }
    static class ViewHolder {
        public TextView name;
        public TextView phoneNum;
        public Button button;
        public ImageView star;
        public boolean clickedOddTimes;
    }
    public Object getItem(int position) {
        return nameList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public  int getCount() {
        return nameList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name2);
            viewHolder.phoneNum = (TextView) rowView.findViewById(R.id.phoneNum2);
            viewHolder.star = (ImageView) rowView.findViewById(R.id.star);
            viewHolder.star.setImageResource(R.drawable.star2);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.name.setText(nameList.get(position));
        holder.phoneNum.setText(phoneNumList.get(position));
        holder.clickedOddTimes = false;
        return rowView;
    }
}