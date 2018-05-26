package com.studyspace.studyspace;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.studyspace.rest.client.GetData_UW;


public class MyLocListener {

    private String build_code, room_num;
    private Context ctx;
    private FusedLocationProviderClient mFusedLocationClient;


    public MyLocListener(Context ctx, String build_code, String room_num){
        this.ctx = ctx;
        this.build_code = build_code;
        this.room_num = room_num;
    }

    public void getData(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(ctx);
        try{
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            //Log.d("Here: ", location.toString());
                            double [] origin = {43.4643,-80.5204};
                            if (location != null) {
                                origin[0] = location.getLatitude();
                                origin[1] = location.getLongitude();
                            }
                            GetData_UW data = new GetData_UW(ctx,build_code, room_num, origin);
                            Log.d("Origin : ", origin[0] + " " + origin[1]);
                        }
                    });
        }catch(SecurityException e){
            Log.d("Using default location", "Waterloo");
            double [] origin = {43.4643,-80.5204};
            GetData_UW data = new GetData_UW(ctx,build_code, room_num, origin);
            Log.d("Origin : ", origin[0] + " " + origin[1]);
        }
    }
}

