package com.example.fitme.fitme.dbget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fitme.fitme.Choose_Type;
import com.example.fitme.fitme.Product;
import com.example.fitme.fitme.R;

import java.util.List;

/**
 * Created by Jonathan on 2/19/2017.
 */

public class ListTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<Exercise_Type> myTypeList;

    public ListTypeAdapter(Context mContext, List<Exercise_Type> myTypeList) {
        this.mContext = mContext;
        this.myTypeList = myTypeList;
    }

    @Override
    public int getCount() {
        return myTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return myTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myTypeList.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item,null);
        TextView tType = (TextView)v.findViewById(R.id.tType);
        tType.setText(myTypeList.get(position).getExc_type());
        return v;
    }


}
