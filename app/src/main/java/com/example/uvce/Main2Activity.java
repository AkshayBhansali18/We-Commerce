package com.example.uvce;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<String> tv1=new ArrayList<>();
    ArrayList<String> tv2=new ArrayList<>();
    ArrayList<Integer> tv3=new ArrayList<>();
    ArrayList<Integer> images=new ArrayList<>();
    ListView listView;
    BottomNavigationViewEx bottomNavigationViewEx;
    BottomNavigator bottomNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView=findViewById(R.id.listView);
        bottomNavigationViewEx=findViewById(R.id.bottom_nav);
        bottomNavigator=new BottomNavigator();
        tv1.add("LG W10 (LMX130IM) - Smartphone With Dual AI Camera");
        tv2.add("LG India");
        tv3.add(237);
        tv1.add("ThinkPad X1 Extreme (1st gen");
        tv2.add("Gaurav Kashyap");
        tv3.add(54);
        tv1.add("Apple EarPods with Lightning Connector");
        tv2.add("Andrew Abishek");
        tv3.add(345);
        tv1.add("LG W10 (LMX130IM) - Smartphone With Dual AI Camera");
        tv2.add("LG India");
        tv3.add(237);

        images.add(R.drawable.lgtv);
        images.add(R.drawable.lenovo);
        images.add(R.drawable.download);
        images.add(R.drawable.lgtv);



        bottomNavigator.editNavigation(bottomNavigationViewEx);
        bottomNavigator.shiftingActivity(Main2Activity.this,bottomNavigationViewEx);

        ListActivity adapter=new ListActivity(this,images,tv1,tv2,tv3);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent=new Intent(Main2Activity.this,ProductInfo.class);
                    intent.putExtra("name",tv1.get(position));
                    intent.putExtra("image",images.get(position));
                    intent.putExtra("seller",tv2.get(position));
                    intent.putExtra("people",tv3.get(position));
                    startActivity(intent);
            }
        });
    }

}
