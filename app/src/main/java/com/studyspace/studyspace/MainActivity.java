package com.studyspace.studyspace;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.studyspace.rest.client.GetData_UW;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String room_num = "1085", build_code = "MC";
        GetData_UW data = new GetData_UW(this,build_code, room_num);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager myManager;
                    MyLocListener loc;
                    loc = new MyLocListener(this);
                    myManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                    try {
                        myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, loc );
                    } catch (SecurityException e) {
                        e.printStackTrace(); // lets the user know there is a problem with the gps
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
