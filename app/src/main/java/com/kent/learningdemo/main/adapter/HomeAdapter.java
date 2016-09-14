package com.kent.learningdemo.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kent.learningdemo.R;
import com.kent.learningdemo.model.HomeItemBean;

import java.util.ArrayList;

/**
 * Created by kent on 16/6/3.
 */
public class HomeAdapter extends BaseAdapter {

    private ArrayList<HomeItemBean> mData = null;
    private Context mContext = null;
    private LayoutInflater mInflater = null;

    public HomeAdapter(Context context, ArrayList<HomeItemBean> demoList){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mData = demoList;
    }

    @Override
    public int getCount() {
        return mData == null? 0 : mData.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.home_item, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView date = (TextView) convertView.findViewById(R.id.date);
            viewHolder = new ViewHolder();
            viewHolder.title = title;
            viewHolder.date = date;
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.title.setText(mData.get(position).getTitle());
        viewHolder.date.setText(mData.get(position).getDate());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, mData.get(position).getTargetActivity());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    final class ViewHolder{
        public TextView title;
        public TextView date;
    }
}
