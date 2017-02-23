package com.example.fitme.fitme.dbget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fitme.fitme.R;

import java.util.List;

/**
 * Created by Jonathan on 2/19/2017.
 */

public class ListExerciseAdapter extends BaseAdapter {

    private Context mContext;
    private List<Exercise_Name> myExerciseList;

    public ListExerciseAdapter(Context mContext, List<Exercise_Name> myExerciseList) {
        this.mContext = mContext;
        this.myExerciseList = myExerciseList;
    }

    @Override
    public int getCount() {
        return  myExerciseList.size();
    }

    @Override
    public Object getItem(int position) {
        return  myExerciseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  myExerciseList.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item,null);
        TextView tType = (TextView)v.findViewById(R.id.tType);
        tType.setText(myExerciseList.get(position).getExc_name());
        return v;
    }
}
