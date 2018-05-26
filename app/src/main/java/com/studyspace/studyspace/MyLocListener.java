package com.studyspace.studyspace;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.studyspace.rest.client.GetData_UW;

public class MyLocListener implements LocationListener {

    private Context ctx;

    public MyLocListener(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null)
        {
            Log.e("Latitude :", "" + location.getLatitude());
            Log.e("Latitude :", "" + location.getLongitude());
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

}

