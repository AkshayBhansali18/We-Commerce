package com.example.uvce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> images;
    ArrayList<String> texts;

    public CategoryAdapter(Context context, ArrayList<Integer> images, ArrayList<String> texts) {
        this.context = context;
        this.images = images;
        this.texts = texts;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView2);
        TextView categoryname = (TextView)convertView.findViewById(R.id.categoryTV);
        categoryname.setText(texts.get(position));
        imageView.setImageResource(images.get(position));

        return convertView;
    }
}
