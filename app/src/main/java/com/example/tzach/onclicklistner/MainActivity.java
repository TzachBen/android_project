package com.example.tzach.onclicklistner;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

//import com.example.tzach.onclicklistner.core.MyInfoManager;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentManager fm= getFragmentManager();
                    FragmentTransaction ft =fm.beginTransaction();
                    ImageFragment imf = new ImageFragment();
                    ft.replace(R.id.content,imf);
                    ft.commit();
                    return true;
                case R.id.navigation_dashboard:
                    FragmentManager fm2= getFragmentManager();
                    FragmentTransaction ft2 =fm2.beginTransaction();
                    album_fragment af = new album_fragment();
                    ft2.replace(R.id.content,af);
                    ft2.commit();
                    return true;
                case R.id.navigation_notifications:
                    FragmentManager fm3= getFragmentManager();
                    FragmentTransaction ft3 =fm3.beginTransaction();
                    ShareFragment sf = new ShareFragment();
                    ft3.replace(R.id.content,sf);
                    ft3.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MyInfoManager.getInstance().openDatabase(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
//    protected void onResume() {
//        super.onResume();
//        MyInfoManager.getInstance().openDatabase(this);
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        MyInfoManager.getInstance().closeDatabase();
//    }

}
