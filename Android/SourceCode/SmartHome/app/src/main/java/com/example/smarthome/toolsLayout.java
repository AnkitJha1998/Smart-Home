package com.example.smarthome;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class toolsLayout extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> category;
    private final ArrayList<String> desc;

    public toolsLayout(Activity context,ArrayList<String> category,ArrayList<String> desc)
    {
        super(context,R.layout.list_view_two_elements_layout,category);
        this.context=context;
        this.category=category;
        this.desc=desc;
    }

    public View getView(int position, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_two_elements_layout,null,true);
        TextView category1=rowView.findViewById(R.id.category);
        TextView desc1=rowView.findViewById(R.id.description);
        category1.setText(category.get(position));
        desc1.setText(desc.get(position));
        return rowView;

    }

}
