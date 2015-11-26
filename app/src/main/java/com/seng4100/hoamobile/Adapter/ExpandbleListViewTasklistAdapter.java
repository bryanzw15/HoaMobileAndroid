package com.seng4100.hoamobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.seng4100.hoamobile.Model.Task;
import com.seng4100.hoamobile.Model.Tasklist;
import com.seng4100.hoamobile.R;

import java.util.List;

public class ExpandbleListViewTasklistAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Tasklist> tasklist;
    private LayoutInflater mInflater;

    public ExpandbleListViewTasklistAdapter(Context context, List<Tasklist> tasklist) {
        mContext = context;
        this.tasklist = tasklist;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return tasklist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return tasklist.get(groupPosition).getTasks().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return tasklist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return tasklist.get(groupPosition).getTasks().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.expandable_list_view_tasklist_adapter, null);
        }

        // Get the group item
        Tasklist tasklist = (Tasklist) getGroup(groupPosition);

        // Set group name
        TextView textView = (TextView) convertView.findViewById(R.id.groupName);
        textView.setText(tasklist.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.exapnadble_list_view_tasklist_item, null);
        }

        // Get child name
        Task task = (Task) getChild(groupPosition, childPosition);

        // Set child name
        TextView text = (TextView) convertView.findViewById(R.id.itemName);
        text.setText(task.getDescription());

        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, children, Toast.LENGTH_SHORT).show();
            }
        });*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
