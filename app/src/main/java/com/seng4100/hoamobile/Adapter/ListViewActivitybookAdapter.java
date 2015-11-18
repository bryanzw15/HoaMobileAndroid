package com.seng4100.hoamobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.seng4100.hoamobile.Model.Activitybook;
import com.seng4100.hoamobile.R;

import java.util.List;

public class ListViewActivitybookAdapter extends ArrayAdapter<Activitybook> {

    static class ViewHolder {
        TextView title;
        TextView date;
        TextView active;
        int position;
    }

    public ListViewActivitybookAdapter(Context context, List<Activitybook> items) {
        super(context, R.layout.list_view_activitybook_adapter, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_view_activitybook_adapter, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.date = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.active = (TextView) convertView.findViewById(R.id.tvActive);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Activitybook item = getItem(position);
        viewHolder.title.setText(item.getName());
        viewHolder.date.setText("Created On: " + item.getCreatedAt());
        if(item.getActive() != null && item.getActive().equals("1"))
            viewHolder.active.setText("Active");
        else
            viewHolder.active.setText("Inactive");

        return convertView;
    }
}
