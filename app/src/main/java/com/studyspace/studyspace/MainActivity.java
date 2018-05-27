package com.studyspace.studyspace;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.studyspace.rest.client.GetData_Distance;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        db = new DB(this);
        DataReader reader = new DataReader(this);
        MyLocListener list = new MyLocListener(this, reader);

        if(db.isEmpty()) {
            Log.d("Empty DB: ", "Search");
            list.getData(); //API Call(s)
        }else{
            ArrayList<FrontendData> data = db.get_data_list();
            for(FrontendData i : data){
                Log.d("Properties: ", i.getBuild() + " " + i.getRoom() + i.getDist());
            }
            Log.d("DB Full", "No Search");
        }
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }
    public void Activity_Select_page_home(View view){
        android.content.Intent Activity_Select_page_home = new android.content.Intent(this,select_page_home.class);
        startActivity(Activity_Select_page_home);

    }
}
