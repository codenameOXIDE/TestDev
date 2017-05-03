package com.example.aarondavid.tutorialapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Aaron David on 4/28/2017.
 */

public class CustomList extends ArrayAdapter<String>
{
    private Activity context;
    private final String[] label;
    private final String[] subLabel;

    public CustomList(Activity context, String[] label, String[] subLabel)
    {
        super(context, R.layout.activity_listview, label);
        this.context = context;
        this.label = label;
        this.subLabel = subLabel;
    }

    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_listview, null, true);

        TextView textLabel = (TextView) rowView.findViewById(R.id.label);
        textLabel.setText(label[position]);

        TextView textSubLabel = (TextView) rowView.findViewById(R.id.subLabel);
        textSubLabel.setText(subLabel[position]);
        return rowView;
    }
}
