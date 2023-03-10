package com.vadicindia.business.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vadicindia.R;
import com.vadicindia.business.model_business.responsemodel.LevelListResponse;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

/**
 * Created by Dell on 10-01-2018.
 */

public class LevelListAdapter extends BaseAdapter {
    Context mContext;
    Activity act;
    // ArrayList<WeeklyIncentive> contactList;
    ArrayList<LevelListResponse.LevelList> levelListArrayList;

    public LevelListAdapter(Context context, ArrayList<LevelListResponse.LevelList> list) {

        this.mContext = context;
        this.levelListArrayList = list;
        this.act=act;

    }

    @Override
    public int getCount() {
        return levelListArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return levelListArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item_layout, parent, false);
        LevelListResponse.LevelList levelList= levelListArrayList.get(position);

        if(position % 2==0){
            itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white));
        }
        else {
            itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.LightGray));
        }

        TextView textViewNameList = (TextView) itemView.findViewById(R.id.list_namelist);
        textViewNameList.setText(levelListArrayList.get(position).getLevelname());



        return itemView;
    }
}
