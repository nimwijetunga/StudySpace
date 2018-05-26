package com.studyspace.studyspace;

import android.content.Context;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.studyspace.rest.client.GetData_UW;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private Context ctx;
    private String build_code, room_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ctx = this;
        this.build_code = "MC";
        this.room_num = "1085";
        getData();
    }

    public void getData(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        try{
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                double [] origin = {location.getLatitude(), location.getLongitude()};
                                GetData_UW data = new GetData_UW(ctx,build_code, room_num, origin);
                                Log.d("Origin : ", origin[0] + " " + origin[1]);
                            }
                        }
                    });
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }






}
