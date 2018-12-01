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
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView name;
        public TextView phoneNum;
        public Button button;
     //  public ImageView heart;
        public boolean clickedOddTimes;
    }
    public Object getItem(int position) {
        return  nameList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public  int getCount() {
        return nameList.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name);
            viewHolder.phoneNum = (TextView) rowView.findViewById(R.id.phoneNum);
            rowView.setTag(viewHolder);
        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.name.setText(nameList.get(position));
        holder.phoneNum.setText(phoneNumList.get(position));
        holder.clickedOddTimes = false;
        return rowView;
    }
}