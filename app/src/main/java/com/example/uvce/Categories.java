package com.example.uvce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class Categories extends AppCompatActivity {
    GridView gridView;
    ArrayList<String> tv3=new ArrayList<>();
    ArrayList<Integer> images=new ArrayList<>();
    ListView listView;
    BottomNavigationViewEx bottomNavigationViewEx;
    BottomNavigator bottomNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        gridView=findViewById(R.id.gridView);
        bottomNavigationViewEx=findViewById(R.id.bottom_nav);
        bottomNavigator=new BottomNavigator();
        tv3.add("Electronics");
        tv3.add("Clothing");
        tv3.add("Handicrafts");
        tv3.add("Chemicals");
        tv3.add("Medicines");
        tv3.add("Furniture");
        images.add(R.drawable.electronics);
        images.add(R.drawable.online);
        images.add(R.drawable.handicraft);
        images.add(R.drawable.chemistry5);
        images.add(R.drawable.medicines);
        images.add(R.drawable.furn);

        bottomNavigator.editNavigation(bottomNavigationViewEx);
        bottomNavigator.shiftingActivity(Categories.this,bottomNavigationViewEx);

        CategoryAdapter adapter=new CategoryAdapter(Categories.this,images,tv3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(Categories.this,Main2Activity.class);
                    startActivity(intent);
                }
            }
        });


    }
}
