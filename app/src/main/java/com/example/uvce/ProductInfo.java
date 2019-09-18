package com.example.uvce;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Exception;


public class ProductInfo extends AppCompatActivity {

    private Button intentWhatsapp;
    private String name;
    private String people;
    private String seller;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        ImageView product=(ImageView)findViewById(R.id.productImage);
        TextView pname=(TextView)findViewById(R.id.productName);
        TextView numpeople=findViewById(R.id.people);
        TextView maker=findViewById(R.id.productMaker);

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        int image=intent.getIntExtra("image",0);
        seller=intent.getStringExtra("seller");
        people=intent.getIntExtra("people",0) + " people have joined this group.";
        product.setImageResource(image);
        pname.setText(name);
        numpeople.setText(people);
        maker.setText(seller);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        intentWhatsapp = (Button)findViewById(R.id.invite);

        intentWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "Buy " + "*" + name + "*" +  " sold by " + seller + " with me on We-Commerce!\n\n " + people;

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Whatsapp not installed",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public void makePhoneCall()
    {
        String number = "9964526549";


        if (ContextCompat.checkSelfPermission(ProductInfo.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ProductInfo.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
