package com.example.uvce;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
    public  class BottomNavigator {

        public void editNavigation(BottomNavigationViewEx bottomNavigationViewEx)
        {
            bottomNavigationViewEx.enableAnimation(true);
            bottomNavigationViewEx.setTextVisibility(false);
            bottomNavigationViewEx.enableShiftingMode(false);

        }
        public void shiftingActivity(final Context context,BottomNavigationViewEx bottomNavigationViewEx)
        {
            bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id=menuItem.getItemId();
                    int activity_number;

                    switch(id) {
                        case R.id.home://0
                            Intent intent = new Intent(context, MainActivity.class);
                            activity_number=0;
                            intent.putExtra("activity_number",activity_number);
                            context.startActivity(intent);

                            break;
                        case R.id.home1://1
                            Intent intent1=new Intent(context, Main2Activity.class);
                            activity_number=1;
                            intent1.putExtra("activity_number",activity_number);
                            context.startActivity(intent1);
                            break;
                        case R.id.orders://2
                            Intent intent2=new Intent(context, Orders.class);
                            activity_number=2;
                            intent2.putExtra("activity_number",activity_number);
                            context.startActivity(intent2);
                            break;
                        case R.id.profile://3
                            activity_number=3;
                            Intent intent3=new Intent(context, ProfileActivity.class);
                            intent3.putExtra("activity_number",activity_number);

                            context.startActivity(intent3);
                            break;
                        case R.id.categories:
                            Intent intent4=new Intent(context,Categories.class);
                            context.startActivity(intent4);
                    }



                    return false;
                }
            });
        }
    }


