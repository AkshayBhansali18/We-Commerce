package com.example.uvce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListActivity extends BaseAdapter {
    Context context;
    ArrayList<Integer> images;
    ArrayList<String> tv1;
    ArrayList<String> tv2;
    ArrayList<Integer> tv3;
    public ListActivity(Context context, ArrayList<Integer> images,ArrayList<String> tv1,ArrayList<String> tv2,ArrayList<Integer> tv3)
    {
        this.context=context;
        this.images=images;
        this.tv1=tv1;
        this.tv2=tv2;
        this.tv3=tv3;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView);
        TextView textView_1=(TextView)convertView.findViewById(R.id.tv1);
        TextView textView_2=(TextView)convertView.findViewById(R.id.tv2);
        TextView textView_3=(TextView)convertView.findViewById(R.id.tv3);
        imageView.setImageResource(images.get(position));
        textView_1.setText(tv1.get(position));
        textView_2.setText(tv2.get(position));
        textView_3.setText(tv3.get(position).toString() + " people have joined this group");

        return convertView;

    }
}
