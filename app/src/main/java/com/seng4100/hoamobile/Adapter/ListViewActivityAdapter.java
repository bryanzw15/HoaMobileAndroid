package com.seng4100.hoamobile.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.seng4100.hoamobile.Model.Activity;
import com.seng4100.hoamobile.R;

import java.util.List;

public class ListViewActivityAdapter extends ArrayAdapter<Activity> {

    List<Activity> activities;

    static class ViewHolder {
        TextView title;
        TextView activitybookId;
        TextView date;
        int position;
    }

    public ListViewActivityAdapter(Context context, List<Activity> items) {
        super(context, R.layout.list_view_activity_adapter, items);

        activities = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_activitybook_adapter, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.activitybookId = (TextView) convertView.findViewById(R.id.tvActivitybook);
            viewHolder.date = (TextView) convertView.findViewById(R.id.tvDate);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Activity item = getItem(position);
        viewHolder.title.setText(item.getName());
        if(item.getCreatedAt() != null)
            viewHolder.date.setText("Created At: " + item.getCreatedAt());
        else
            viewHolder.date.setText("Created At: Unknown");

        if(item.getActivitybookId() != null) {
            Log.d("BryanID", item.getActivitybookId());
            //viewHolder.activitybookId.setText("Activitybook ID: " + item.getActivitybookId());
            // ^This gives a null pointer exception and I can't figure out why
        } else
            viewHolder.activitybookId.setText("Activitybook ID: Unknown");
        return convertView;
    }

    @Override
    public int getCount(){
        return activities.size();
    }
}
